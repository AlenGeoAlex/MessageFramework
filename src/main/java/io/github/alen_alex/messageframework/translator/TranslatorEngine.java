package io.github.alen_alex.messageframework.translator;

import io.github.alen_alex.messageframework.bukkit.enums.EngineType;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TranslatorEngine {

    Component parse(@NotNull String message);

    List<Component> parse(@NotNull List<String> message);

    Component parse(@NotNull String message, @NotNull InternalPlaceholders placeholders);

    List<Component> parse(@NotNull List<String> message, @NotNull InternalPlaceholders placeholders);

    String parseToString(@NotNull Component component);

    String stripColors(@NotNull Component component);

    EngineType engine();
}
