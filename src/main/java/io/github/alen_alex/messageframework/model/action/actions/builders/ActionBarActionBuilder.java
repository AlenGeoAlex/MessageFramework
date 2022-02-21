package io.github.alen_alex.messageframework.model.action.actions.builders;

import io.github.alen_alex.messageframework.abstracts.AbstractActionBuilder;
import io.github.alen_alex.messageframework.exception.IllegalMessageStatement;
import io.github.alen_alex.messageframework.model.action.actions.ActionBarImpl;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import io.github.alen_alex.messageframework.model.action.interfaces.IBuilder;
import io.github.alen_alex.messageframework.model.action.interfaces.IPlaceholder;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import org.jetbrains.annotations.NotNull;

public class ActionBarActionBuilder extends AbstractActionBuilder implements IBuilder, IPlaceholder {

    private InternalPlaceholders placeholders;

    public ActionBarActionBuilder(String[] args, TranslatorEngine engine) {
        super(args, engine);
    }


    @Override
    public IActions build() {
        if(args.length <= 1){
            throw new IllegalMessageStatement("You should at-least provide the string for the actionbar action!");
        }else return new ActionBarImpl(this.engine.parse(args[1]));
    }

    @Override
    public ActionBarActionBuilder withPlaceholders(@NotNull InternalPlaceholders placeholders) {
        this.placeholders = placeholders;
        return this;
    }

    public static ActionBarActionBuilder builder(@NotNull String[] args, @NotNull TranslatorEngine engine){
        return new ActionBarActionBuilder(args,engine);
    }
}
