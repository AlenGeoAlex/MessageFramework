package io.github.alen_alex.messageframework.builder;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.builder.title.StringTitleBuilder;
import io.github.alen_alex.messageframework.enums.ActionType;
import io.github.alen_alex.messageframework.exception.IllegalActionDefinition;
import io.github.alen_alex.messageframework.exception.MissingActionDefinition;
import io.github.alen_alex.messageframework.model.ActionMessage;
import io.github.alen_alex.messageframework.model.action.actions.*;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionMessageBuilder {

    private List<IActions> iActions;
    private final MessageFramework framework;

    private static final String DELIMITER = "::";

    private ActionMessageBuilder(MessageFramework framework) {
        this.framework = framework;
        this.iActions = new ArrayList<>();
    }

    public ActionMessageBuilder register(@NotNull String actionMessage){
        Validate.notNull(actionMessage);
        final String[] splitMsg = actionMessage.split(DELIMITER);
        if(splitMsg.length == 0)
            throw new MissingActionDefinition("The action definition is not specified for "+actionMessage+"!, Seems like the message is not formatted correctly");

        ActionType type = Arrays.stream(ActionType.values()).filter(actionType -> splitMsg[0].equals(actionType.getActionTag())).findFirst().orElse(null);
        if(type == null)
            throw new IllegalActionDefinition("Unable to determine a valid action for "+actionMessage+". The current parsed ("+splitMsg[0]+") seems to be not in one of the defined one!");

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

    private void buildMessage(@NotNull String[] actionMessage){
        if(actionMessage.length == 1){
            ((JavaPlugin) framework.getJavaPlugin()).getLogger().warning("The message params is empty, Skipping!");
            return;
        }
        final List<Component> toRegister = new ArrayList<>();
        for (int i = 1; i < actionMessage.length; i++){
            toRegister.add(framework.engine().parse(actionMessage[i]));
        }
        this.iActions.add(new MessageImpl(toRegister));
    }
}
