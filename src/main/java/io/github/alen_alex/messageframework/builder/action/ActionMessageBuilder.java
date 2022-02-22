package io.github.alen_alex.messageframework.builder.action;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.enums.ActionType;
import io.github.alen_alex.messageframework.exception.IllegalActionDefinition;
import io.github.alen_alex.messageframework.exception.MissingActionDefinition;
import io.github.alen_alex.messageframework.model.ActionMessage;
import io.github.alen_alex.messageframework.model.action.actions.builders.*;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
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
            iActions.add(
                    MessageActionBuilder
                            .builder(splitMsg,framework.engine())
                            .build()
            );
        }else if(type == ActionType.ACTION_BAR){
            iActions.add(
                    ActionBarActionBuilder
                            .builder(splitMsg,framework.engine())
                            .build()
            );
        }else if(type == ActionType.BROADCAST){
            iActions.add(
                    BroadcastActionBuilder
                            .builder(splitMsg,framework.engine())
                            .build()
            );
        }else if(type == ActionType.TITLE){
            iActions.add(
                    TitleActionBuilder
                            .builder(splitMsg, framework.engine())
                            .build()

            );
        }else if(type == ActionType.DELAY){
            iActions.add(
                    DelayActionBuilder
                            .builder(splitMsg)
                            .build()
            );
        }else if(type == ActionType.BROADCAST_TITLE){
            iActions.add(
                    TitleActionBuilder
                            .builder(splitMsg,framework.engine())
                            .build()
            );
        }
        return this;
    }

    public ActionMessageBuilder register(@NotNull String actionMessage, @NotNull InternalPlaceholders placeholders){
        Validate.notNull(actionMessage);
        final String[] splitMsg = actionMessage.split(DELIMITER);
        if(splitMsg.length == 0)
            throw new MissingActionDefinition("The action definition is not specified for "+actionMessage+"!, Seems like the message is not formatted correctly");

        ActionType type = Arrays.stream(ActionType.values()).filter(actionType -> splitMsg[0].equals(actionType.getActionTag())).findFirst().orElse(null);
        if(type == null)
            throw new IllegalActionDefinition("Unable to determine a valid action for "+actionMessage+". The current parsed ("+splitMsg[0]+") seems to be not in one of the defined one!");

        if(type == ActionType.MESSAGE) {
            iActions.add(
                    MessageActionBuilder
                            .builder(splitMsg,framework.engine())
                            .withPlaceholders(placeholders)
                            .build()
            );
        }else if(type == ActionType.ACTION_BAR){
            iActions.add(
                    ActionBarActionBuilder
                            .builder(splitMsg,framework.engine())
                            .withPlaceholders(placeholders)
                            .build()
            );
        }else if(type == ActionType.BROADCAST){
            iActions.add(
                    BroadcastActionBuilder
                            .builder(splitMsg,framework.engine())
                            .withPlaceholders(placeholders)
                            .build()
            );
        }else if(type == ActionType.TITLE){
            iActions.add(
                    TitleActionBuilder
                            .builder(splitMsg, framework.engine())
                            .withPlaceholders(placeholders)
                            .build()
            );
        }else if(type == ActionType.DELAY){
            iActions.add(
                    DelayActionBuilder
                            .builder(splitMsg)
                            .build()
            );
        }
        return this;
    }

    public ActionMessageBuilder register(@NotNull List<String> message){
        message.iterator().forEachRemaining(this::register);
        return this;
    }

    public ActionMessageBuilder register(@NotNull List<String> message, @NotNull InternalPlaceholders placeholders){
        message.iterator().forEachRemaining(string -> this.register(string,placeholders));
        return this;
    }

    public ActionMessage build(){
        return new ActionMessage(framework,iActions);
    }

    public static ActionMessageBuilder builder(@NotNull MessageFramework framework){
        return new ActionMessageBuilder(framework);
    }

    public static boolean isValidSyntax(@NotNull String actionMessage){
        if(StringUtils.isBlank(actionMessage))
            return false;

        final String[] splitMsg = actionMessage.split(DELIMITER);
        if(splitMsg.length == 0)
            return false;

        ActionType type = Arrays.stream(ActionType.values()).filter(actionType -> splitMsg[0].equals(actionType.getActionTag())).findFirst().orElse(null);
        if(type == null)
            return false;

        return true;
    }

    public static boolean isValidSyntax(@NotNull List<String> actionMessage){
        boolean valid = true;
        for(String message : actionMessage){
            if(!isValidSyntax(message)){
                valid = false;
                break;
            }
        }

        return valid;
    }
}
