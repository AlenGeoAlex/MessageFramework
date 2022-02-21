package io.github.alen_alex.messageframework.model.action.actions.builders;

import io.github.alen_alex.messageframework.builder.title.StringTitleBuilder;
import io.github.alen_alex.messageframework.exception.IllegalMessageStatement;
import io.github.alen_alex.messageframework.model.action.actions.TitleImpl;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import io.github.alen_alex.messageframework.model.action.interfaces.IBuilder;
import io.github.alen_alex.messageframework.model.action.interfaces.IPlaceholder;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import org.jetbrains.annotations.NotNull;

public class TitleActionBuilder implements IBuilder, IPlaceholder {

    private final String[] args;
    private final TranslatorEngine engine;

    private InternalPlaceholders placeholders;
    private TitleActionBuilder(String[] args, TranslatorEngine engine) {
        this.args = args;
        this.engine = engine;
    }

    @Override
    public IActions build() {
        if(args.length == 0){
            throw new IllegalMessageStatement("You should at-least provide the title string for the title action!");
        }

        String title = args[1];
        String subtitle = null;
        int fadeIn = 300;
        int stay = 1000;
        int fadeOut = 300;

        if(args.length >= 3){
            if(!args[2].equalsIgnoreCase("NULL"))
                subtitle = args[2];
        }

        if(args.length >= 4){
            if(!args[2].equalsIgnoreCase("NULL"))
                fadeIn = Integer.parseInt(args[3]);
        }

        if(args.length >= 5){
            if(!args[2].equalsIgnoreCase("NULL"))
                stay = Integer.parseInt(args[4]);
        }

        if(args.length >= 6){
            if(!args[2].equalsIgnoreCase("NULL"))
                fadeOut = Integer.parseInt(args[5]);
        }

        if(placeholders != null)
            return new TitleImpl(
                    StringTitleBuilder.builder(title,placeholders,engine)
                            .subtitle(subtitle)
                            .fadeIn(fadeIn)
                            .stay(stay)
                            .fadeOut(fadeOut)
                            .build()
            );
        else return new TitleImpl(
                StringTitleBuilder.builder(title,engine)
                        .subtitle(subtitle)
                        .fadeIn(fadeIn)
                        .stay(stay)
                        .fadeOut(fadeOut)
                        .build()
        );
    }

    @Override
    public TitleActionBuilder withPlaceholders(@NotNull InternalPlaceholders placeholders) {
        this.placeholders = placeholders;
        return this;
    }
}
