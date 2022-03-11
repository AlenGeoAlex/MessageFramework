package io.github.alen_alex.messageframework.core.translator;

import io.github.alen_alex.messageframework.core.enums.EngineType;
import io.github.alen_alex.messageframework.core.placeholders.InternalPlaceholders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class LegacyTranslatorImpl implements TranslatorEngine {

    private final static LegacyComponentSerializer serializer;

    static {
        serializer = LegacyComponentSerializer.legacyAmpersand();
    }

    private static LegacyTranslatorImpl translatorImpl = null;

    private LegacyTranslatorImpl(){

    }

    @Override
    public Component parse(@NotNull String message) {
        if(StringUtils.isBlank(message))
            return Component.empty();

        return serializer.deserialize(message);
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
        return serializer.serialize(component);
    }

    @Override
    public String stripColors(@NotNull Component component) {
        return ChatColor.stripColor(parseToString(component));
    }

    @Override
    public EngineType engine() {
        return EngineType.LEGACY;
    }

    public static LegacyTranslatorImpl get(){
        if(translatorImpl == null)
            translatorImpl = new LegacyTranslatorImpl();

        return translatorImpl;
    }
}
