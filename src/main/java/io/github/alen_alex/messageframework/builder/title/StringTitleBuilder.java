package io.github.alen_alex.messageframework.builder.title;

import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import org.jetbrains.annotations.NotNull;

public final class StringTitleBuilder extends ComponentTitleBuilder{

    private final TranslatorEngine engine;

    private StringTitleBuilder(@NotNull String title, @NotNull TranslatorEngine engine) {
        super(engine.parse(title));
        this.engine = engine;
    }

    private StringTitleBuilder(@NotNull String title, @NotNull InternalPlaceholders placeholders, @NotNull TranslatorEngine engine){
        super(engine.parse(title,placeholders));
        this.engine = engine;
    }

    public StringTitleBuilder title(@NotNull String title){
        super.title(engine.parse(title));
        return this;
    }

    public StringTitleBuilder title(@NotNull String title, @NotNull InternalPlaceholders placeholders){
        super.title(engine.parse(title,placeholders));
        return this;
    }

    public StringTitleBuilder subtitle(@NotNull String subtitle){
        super.subtitle(engine.parse(subtitle));
        return this;
    }

    public StringTitleBuilder subtitle(@NotNull String subtitle, @NotNull InternalPlaceholders placeholders){
        super.subtitle(engine.parse(subtitle,placeholders));
        return this;
    }

    @Override
    public StringTitleBuilder fadeIn(long fadeIn){
        super.fadeIn(fadeIn);
        return this;
    }

    @Override
    public StringTitleBuilder fadeOut(long fadeOut){
        super.fadeOut(fadeIn);
        return this;
    }

    @Override
    public StringTitleBuilder stay(long stay){
        super.stay(stay);
        return this;
    }

    public static StringTitleBuilder builder(@NotNull String title, @NotNull TranslatorEngine engine){
        return new StringTitleBuilder(title,engine);
    }

    public static StringTitleBuilder builder(@NotNull String title, @NotNull InternalPlaceholders placeholders, @NotNull TranslatorEngine engine){
        return new StringTitleBuilder(title,placeholders,engine);
    }

}
