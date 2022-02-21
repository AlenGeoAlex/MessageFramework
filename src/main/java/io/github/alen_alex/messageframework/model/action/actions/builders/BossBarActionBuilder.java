package io.github.alen_alex.messageframework.model.action.actions.builders;

import io.github.alen_alex.messageframework.abstracts.AbstractActionBuilder;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import io.github.alen_alex.messageframework.model.action.interfaces.IBuilder;
import io.github.alen_alex.messageframework.model.action.interfaces.IPlaceholder;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import org.jetbrains.annotations.NotNull;

public class BossBarActionBuilder extends AbstractActionBuilder implements IBuilder, IPlaceholder {

    private InternalPlaceholders placeholders = null;

    public BossBarActionBuilder(String[] args, TranslatorEngine engine) {
        super(args, engine);
    }

    @Override
    public IActions build() {

        pr

        return null;
    }

    @Override
    public BossBarActionBuilder withPlaceholders(@NotNull InternalPlaceholders placeholders) {
        this.placeholders = placeholders;
        return this;
    }
}
