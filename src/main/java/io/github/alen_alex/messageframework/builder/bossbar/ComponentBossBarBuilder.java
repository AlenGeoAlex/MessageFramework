package io.github.alen_alex.messageframework.builder.bossbar;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class ComponentBossBarBuilder {

    private Component text;
    private float progress = 0.0f;
    private BossBar.Color color = BossBar.Color.WHITE;
    private BossBar.Overlay overlay = BossBar.Overlay.PROGRESS;;

    protected ComponentBossBarBuilder(Component text) {
        this.text = text;
    }

    public ComponentBossBarBuilder text(@NotNull Component text){
        this.text = text;
        return this;
    }

    public ComponentBossBarBuilder progress(float progress){
        this.progress = progress;
        return this;
    }

    public ComponentBossBarBuilder color(BossBar.Color color){
        this.color = color;
        return this;
    }

    public ComponentBossBarBuilder overlay(BossBar.Overlay overlay){
        this.overlay = overlay;
        return this;
    }

    public Component getText() {
        return text;
    }

    public float getProgress() {
        return progress;
    }

    public BossBar.Color getColor() {
        return color;
    }

    public BossBar.Overlay getOverlay() {
        return overlay;
    }

    public ComponentBossBarBuilder reset(){
        this.overlay = BossBar.Overlay.PROGRESS;
        this.progress = 0.0f;
        this.color = BossBar.Color.WHITE;
        return this;
    }

    public BossBar build(){
        return BossBar.bossBar(text,progress,color,overlay);
    }

    public static ComponentBossBarBuilder builder(@NotNull Component text){
        return new ComponentBossBarBuilder(text);
    }
}
