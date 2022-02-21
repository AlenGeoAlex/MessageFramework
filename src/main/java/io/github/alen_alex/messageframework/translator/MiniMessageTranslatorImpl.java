package io.github.alen_alex.messageframework.translator;

import io.github.alen_alex.messageframework.bukkit.enums.EngineType;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class MiniMessageTranslatorImpl implements TranslatorEngine{

    private final MiniMessage messageFormatter;

    public MiniMessageTranslatorImpl(){
        messageFormatter = MiniMessage.builder()
                .tags(TagResolver.builder()
                        .resolver(StandardTags.color())
                        .resolver(StandardTags.decoration())
                        .resolver(StandardTags.hoverEvent())
                        .resolver(StandardTags.clickEvent())
                        .resolver(StandardTags.keybind())
                        .resolver(StandardTags.translatable())
                        .resolver(StandardTags.translatable())
                        .resolver(StandardTags.insertion())
                        .resolver(StandardTags.font())
                        .resolver(StandardTags.gradient())
                        .resolver(StandardTags.rainbow())
                        .build())
                .strict(false)
                .build();
    }

    public MiniMessageTranslatorImpl(boolean strict){
        messageFormatter = MiniMessage.builder()
                .tags(TagResolver.builder()
                        .resolver(StandardTags.color())
                        .resolver(StandardTags.decoration())
                        .resolver(StandardTags.hoverEvent())
                        .resolver(StandardTags.clickEvent())
                        .resolver(StandardTags.keybind())
                        .resolver(StandardTags.translatable())
                        .resolver(StandardTags.translatable())
                        .resolver(StandardTags.insertion())
                        .resolver(StandardTags.font())
                        .resolver(StandardTags.gradient())
                        .resolver(StandardTags.rainbow())
                        .build())
                .strict(strict)
                .build();
    }

    @Override
    public Component parse(@NotNull String message) {
        if(StringUtils.isBlank(message))
            return Component.empty();

        return messageFormatter.deserialize(message);
    }

    @Override
    public List<Component> parse(@NotNull List<String> message) {
        return message.stream().map(this::parse).collect(Collectors.toList());
    }

    @Override
    public Component parse(@NotNull String message, @NotNull InternalPlaceholders placeholders) {
        return this.parse(placeholders.parse(message));
    }

    @Override
    public List<Component> parse(@NotNull List<String> message, @NotNull InternalPlaceholders placeholders) {
        return this.parse(placeholders.parse(message));
    }

    @Override
    public String parseToString(@NotNull Component component) {
        return messageFormatter.serialize(component);
    }

    @Override
    public String stripColors(@NotNull Component component) {
        return GsonComponentSerializer.gson().serialize(component);
    }

    @Override
    public EngineType engine() {
        return EngineType.MINI_MESSAGE;
    }
}
