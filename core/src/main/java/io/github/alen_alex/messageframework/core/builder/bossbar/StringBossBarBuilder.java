package io.github.alen_alex.messageframework.core.builder.bossbar;

import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import net.kyori.adventure.bossbar.BossBar;
import org.jetbrains.annotations.NotNull;

public final class StringBossBarBuilder extends ComponentBossBarBuilder {

    private final TranslatorEngine engine;

    private StringBossBarBuilder(String text, @NotNull TranslatorEngine engine) {
        super(engine.parse(text));
        this.engine = engine;
    }

    private StringBossBarBuilder(@NotNull String text, @NotNull InternalPlaceholders placeholders, @NotNull TranslatorEngine engine){
        super(engine.parse(text,placeholders));
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

    public static StringBossBarBuilder builder(@NotNull String text, @NotNull TranslatorEngine engine){
        return new StringBossBarBuilder(text,engine);
    }

    public static StringBossBarBuilder builder(@NotNull String text, @NotNull InternalPlaceholders placeholders, @NotNull TranslatorEngine engine){
        return new StringBossBarBuilder(text,placeholders,engine);
    }
}
