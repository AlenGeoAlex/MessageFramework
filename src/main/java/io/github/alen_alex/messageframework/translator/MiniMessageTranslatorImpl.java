package io.github.alen_alex.messageframework.translator;

import io.github.alen_alex.messageframework.bukkit.enums.EngineType;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.transformation.TransformationRegistry;
import net.kyori.adventure.text.minimessage.transformation.TransformationType;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class MiniMessageTranslatorImpl implements TranslatorEngine{

    private final static MiniMessage messageFormatter;

    static {
        messageFormatter = MiniMessage.builder()
                .transformations(TransformationRegistry.builder()
                        .add(TransformationType.COLOR)
                        .add(TransformationType.DECORATION)
                        .add(TransformationType.HOVER_EVENT)
                        .add(TransformationType.CLICK_EVENT)
                        .add(TransformationType.KEYBIND)
                        .add(TransformationType.TRANSLATABLE)
                        .add(TransformationType.INSERTION)
                        .add(TransformationType.FONT)
                        .add(TransformationType.GRADIENT)
                        .add(TransformationType.RAINBOW)
                        .build())
                .strict(false)
                .build();
    }

    public MiniMessageTranslatorImpl(){

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
