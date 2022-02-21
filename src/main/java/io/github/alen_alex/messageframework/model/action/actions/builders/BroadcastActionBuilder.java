package io.github.alen_alex.messageframework.model.action.actions.builders;

import io.github.alen_alex.messageframework.abstracts.AbstractActionBuilder;
import io.github.alen_alex.messageframework.exception.IllegalMessageStatement;
import io.github.alen_alex.messageframework.model.action.actions.MessageImpl;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import io.github.alen_alex.messageframework.model.action.interfaces.IBuilder;
import io.github.alen_alex.messageframework.model.action.interfaces.IPlaceholder;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BroadcastActionBuilder extends AbstractActionBuilder implements IBuilder, IPlaceholder {

    private InternalPlaceholders placeholders;

    public BroadcastActionBuilder(String[] args, TranslatorEngine engine) {
        super(args, engine);
    }


    public static BroadcastActionBuilder builder(@NotNull String[] args, @NotNull final TranslatorEngine engine){
        return  new BroadcastActionBuilder(args,engine);
    }

    @Override
    public IActions build(){
        if(args.length == 1){
            throw new IllegalMessageStatement("There should be at-least one message to build an action message!");
        }

        final List<Component> actionsList = new ArrayList<>();
        for (int i = 1; i < args.length; i++){
            if(placeholders == null)
                actionsList.add(this.engine.parse(args[i]));
            else actionsList.add(this.engine.parse(args[i],placeholders));
        }

        return new MessageImpl(actionsList);
    }

    @Override
    public BroadcastActionBuilder withPlaceholders(@NotNull InternalPlaceholders placeholders) {
        this.placeholders = placeholders;
        return this;
    }

}
