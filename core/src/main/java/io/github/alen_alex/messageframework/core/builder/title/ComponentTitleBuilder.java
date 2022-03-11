package io.github.alen_alex.messageframework.core.builder.title;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class ComponentTitleBuilder implements TitleBuilder {

    protected Component title = Component.empty();
    protected Component subtitle = Component.empty();

    protected long fadeIn = 300;
    protected long fadeOut = 500;
    protected long stay = 1000;

    protected ComponentTitleBuilder(Component title, Component subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    protected ComponentTitleBuilder(Component title) {
        this.title = title;
    }

    public ComponentTitleBuilder(Component title, Component subtitle, long fadeIn, long fadeOut, long stay) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.stay = stay;
    }

    public ComponentTitleBuilder title(@NotNull Component title){
        this.title = title;
        return this;
    }

    public ComponentTitleBuilder subtitle(@NotNull Component subtitle){
        this.subtitle = subtitle;
        return this;
    }

    public ComponentTitleBuilder fadeIn(long fadeIn){
        this.fadeIn = fadeIn;
        return this;
    }

    public ComponentTitleBuilder fadeOut(long fadeOut){
        this.fadeOut = fadeOut;
        return this;
    }

    public ComponentTitleBuilder stay(long stay){
        this.stay = stay;
        return this;
    }

    public ComponentTitleBuilder reset(){
        title = Component.empty();
        subtitle = Component.empty();
        this.fadeIn = 0;
        this.fadeOut = 0;
        this.stay = 0;
        return this;
    }

    public Component getTitle() {
        return title;
    }

    public Component getSubtitle() {
        return subtitle;
    }

    public long getFadeIn() {
        return fadeIn;
    }

    public long getFadeOut() {
        return fadeOut;
    }

    public long getStay() {
        return stay;
    }

    @Override
    public Title build(){
        return Title.title(title,subtitle,Title.Times.times(Duration.ofMillis(fadeIn),Duration.ofMillis(fadeIn),Duration.ofMillis(fadeIn)));
    }

    public static ComponentTitleBuilder builder(@NotNull Component title){
        return new ComponentTitleBuilder(title);
    }

    public static ComponentTitleBuilder builder(@NotNull Component title, @NotNull Component subtitle){
        return new ComponentTitleBuilder(title,subtitle);
    }
}
