package io.github.alen_alex.messageframework.core.builder.book;

import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentBookBuilder implements BookBuilder {

    protected Component title = Component.empty();
    protected Component author = Component.empty();
    protected Collection<Component> pages = new ArrayList<>();

    protected ComponentBookBuilder(@NotNull Component title,@NotNull Component author,@NotNull Collection<Component> pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    protected ComponentBookBuilder(@NotNull Component title, @NotNull Component author, @NotNull Component... page){
        this.title = title;
        this.author = author;
        pages.addAll(Arrays.asList(page));
    }

    protected ComponentBookBuilder(@NotNull Component title, @NotNull Component author){
        this.title = title;
        this.author = author;
    }

    public Component getTitle() {
        return title;
    }

    public Component getAuthor() {
        return author;
    }

    public Collection<Component> getPages() {
        return pages;
    }

    public ComponentBookBuilder title(@NotNull Component title){
        this.title = title;
        return this;
    }

    public ComponentBookBuilder author(@NotNull Component author){
        this.author = author;
        return this;
    }

    public ComponentBookBuilder addPage(@NotNull Component page){
        this.pages.add(page);
        return this;
    }

    public ComponentBookBuilder addComponentPages(@NotNull List<Component> pageList){
        this.pages.addAll(pageList);
        return this;
    }

    public ComponentBookBuilder addComponentPages(@NotNull Component... pageList){
        this.pages.addAll(Arrays.stream(pageList).collect(Collectors.toList()));
        return this;
    }

    public ComponentBookBuilder removeComponentPage(@NotNull Component component){
        this.pages.remove(component);
        return this;
    }

    public ComponentBookBuilder removeComponentPage(@NotNull List<Component> components){
        components.iterator().forEachRemaining(this::removeComponentPage);
        return this;
    }

    public ComponentBookBuilder removeComponentPage(@NotNull Component... pagesList){
        Arrays.stream(pagesList).iterator().forEachRemaining(this::removeComponentPage);
        return this;
    }

    @Override
    public Book build(){
        return Book.book(title,author,pages);
    }

    public static ComponentBookBuilder builder(@NotNull Component title, @NotNull Component author, @NotNull Collection<Component> components){
        return new ComponentBookBuilder(title,author,components);
    }

    public static ComponentBookBuilder builder(@NotNull Component title, @NotNull Component author){
        return new ComponentBookBuilder(title, author);
    }

    public static ComponentBookBuilder builder(@NotNull Component title, @NotNull Component author, @NotNull Component... page){
        return new ComponentBookBuilder(title,author,page);
    }
}
