package io.github.alen_alex.messageframework.builder;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.builder.title.StringTitleBuilder;
import io.github.alen_alex.messageframework.enums.ActionType;
import io.github.alen_alex.messageframework.model.ActionMessage;
import io.github.alen_alex.messageframework.model.action.actions.*;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionMessageBuilder {

    private List<IActions> iActions;
    private final MessageFramework framework;

    private ActionMessageBuilder(MessageFramework framework) {
        this.framework = framework;
        this.iActions = new ArrayList<>();
    }

    public ActionMessageBuilder register(@NotNull String actionMessage){
        final String[] splitMsg = actionMessage.split("::");
        if(splitMsg.length == 0)
            throw new IllegalArgumentException("You should provide a valid Action Message Format");

        System.out.println(actionMessage);
        System.out.println(splitMsg[0]);

        ActionType type = Arrays.stream(ActionType.values()).filter(actionType -> splitMsg[0].equals(actionType.getActionTag())).findFirst().orElse(null);
        if(type == null)
            throw new IllegalArgumentException("You should provide a valid Action Message Format");
        System.out.println(type.name());

        try {
            System.out.println("Register "+type.name());
            if(type == ActionType.MESSAGE) {
                iActions.add(new MessageImpl(framework.engine().parse(splitMsg[1])));
            }else if(type == ActionType.ACTION_BAR){
                iActions.add(new ActionBarImpl(this.framework.engine().parse(splitMsg[1])));
            }else if(type == ActionType.BROADCAST){
                iActions.add(new BroadcastImpl(this.framework.engine().parse(splitMsg[1])));
            }else if(type == ActionType.TITLE){
                iActions.add(new TitleImpl(StringTitleBuilder.builder(splitMsg[1],framework.engine()).build()));
            }else if(type == ActionType.DELAY){
                iActions.add(new DelayImpl(Long.parseLong(splitMsg[1])));
            }
        } catch (IllegalAccessException e) {
                e.printStackTrace();
        }
        return this;
    }

    public ActionMessageBuilder register(@NotNull List<String> message){
        message.iterator().forEachRemaining(this::register);
        return this;
    }

    public ActionMessage build(){
        return new ActionMessage(framework,iActions);
    }

    public static ActionMessageBuilder builder(@NotNull MessageFramework framework){
        return new ActionMessageBuilder(framework);
    }
}
