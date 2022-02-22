package io.github.alen_alex.messageframework.builder.book;

import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class StringBookBuilder extends ComponentBookBuilder{

    private final TranslatorEngine engine;

    protected StringBookBuilder(@NotNull String title, @NotNull String author, @NotNull Collection<Component> pages, @NotNull TranslatorEngine engine) {
        super(engine.parse(title), engine.parse(author), pages);
        this.engine = engine;
    }

    public StringBookBuilder(@NotNull Component title, @NotNull Component author, @NotNull TranslatorEngine engine ,@NotNull Component... page) {
        super(title, author, page);
        this.engine = engine;

    }

    public StringBookBuilder(@NotNull Component title, @NotNull Component author, @NotNull TranslatorEngine engine) {
        super(title, author);
        this.engine = engine;
    }

    public StringBookBuilder title(@NotNull String title){
        if(StringUtils.isBlank(title))
            super.title(Component.empty());

        super.title(this.engine.parse(title));
        return this;
    }

    public StringBookBuilder title(@NotNull String title, @NotNull InternalPlaceholders placeholders){
        if(StringUtils.isBlank(title))
            super.title(Component.empty());

        super.title(this.engine.parse(title,placeholders));
        return this;
    }

    public StringBookBuilder author(@NotNull String author){
        if(StringUtils.isBlank(author))
            super.author(Component.empty());

        super.author(this.engine.parse(author));
        return this;
    }

    public StringBookBuilder author(@NotNull String author, @NotNull InternalPlaceholders placeholders){
        if(StringUtils.isBlank(author))
            super.author(Component.empty());

        super.author(this.engine.parse(author, placeholders));
        return this;
    }

    public StringBookBuilder addPage(@NotNull String text){
        if(StringUtils.isBlank(text))
            super.addPage(Component.empty());

        super.addPage(this.engine.parse(text));
        return this;
    }

    public StringBookBuilder addPage(@NotNull String text, @NotNull InternalPlaceholders placeholders){
        if(StringUtils.isBlank(text))
            super.addPage(Component.empty());

        super.addPage(this.engine.parse(text,placeholders));
        return this;
    }

    public StringBookBuilder addStringPages(@NotNull List<String> pages){
        pages.iterator().forEachRemaining(this::addPage);
        return this;
    }

    public StringBookBuilder addStringPages(@NotNull List<String> pages, @NotNull InternalPlaceholders placeholders){
        pages.iterator().forEachRemaining(p -> this.addPage(p,placeholders));
        return this;
    }
}
