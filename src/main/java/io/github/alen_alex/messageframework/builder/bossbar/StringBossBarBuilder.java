package io.github.alen_alex.messageframework.builder.bossbar;

import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class StringBossBarBuilder extends ComponentBossBarBuilder{

    private final TranslatorEngine engine;

    private StringBossBarBuilder(Component text, @NotNull TranslatorEngine engine) {
        super(text);
        this.engine = engine;
    }

    public StringBossBarBuilder text(@NotNull String text){
        super.text(engine.parse(text));
        return this;
    }

    public StringBossBarBuilder text(@NotNull String text, @NotNull InternalPlaceholders placeholders){
        super.text(engine.parse(text,placeholders));
        return this;
    }

    @Override
    public StringBossBarBuilder progress(float progress) {
        super.progress(progress);
        return this;
    }

    @Override
    public StringBossBarBuilder color(BossBar.Color color) {
        super.color(color);
        return this;
    }

    @Override
    public StringBossBarBuilder overlay(BossBar.Overlay overlay) {
        super.overlay(overlay);
        return this;
    }
}
