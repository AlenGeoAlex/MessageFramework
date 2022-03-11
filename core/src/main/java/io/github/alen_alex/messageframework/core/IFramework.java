package io.github.alen_alex.messageframework.core;

import io.github.alen_alex.messageframework.core.enums.EngineType;
import io.github.alen_alex.messageframework.core.placeholders.InternalPlaceholders;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface IFramework<T> {

    /**
     * Send a component message to any of the provided audience or players.
     * Two of the methods allow you to send a solo message and the 2 of the methods
     * allow you to send a list of messages.
     *
     * The {@link IFramework#sendMessage(UUID, List)} & {@link IFramework#sendMessage(Audience, List)} will send those
     * message in the main thread.
     *
     * {@link Component}
     **/
    void sendMessage(@NotNull UUID playerUID, @NotNull Component message);

    void sendMessage(@NotNull Audience audience, @NotNull Component message);

    void sendMessage(@NotNull List<UUID> playerUIDS, @NotNull Component message);

    void sendMessage(@NotNull UUID playerUID, @NotNull List<Component> message);

    void sendMessage(@NotNull Audience audience, @NotNull List<Component> message);

    void sendMessage(@NotNull List<UUID> playerUIDS, @NotNull List<Component> message);

    /**
     * Sends a {@link String} message to the provided audience or players.
     *
     * If a {@link io.github.alen_alex.messageframework.core.translator.TranslatorEngine} isn't specified, it will
     * default to {@link io.github.alen_alex.messageframework.core.translator.MiniMessageTranslatorImpl} or MiniMessage
     * formatting. If you would to specify the translator engine use {@link IFramework#sendMessage(UUID, String, EngineType)}
     * or {@link IFramework#sendMessage(Audience, String, EngineType)}
     *
     * <b>NOTE: </b><i>This methods are designed to fail silently, The String fields are nullable. That means if you
     * pass a string which is empty, it will fail silently rather than throw errors!</i>
     */
    void sendMessage(@NotNull UUID playerUID, @Nullable String message);
    
    void sendMessage(@NotNull Audience audience, @Nullable String message);

    void sendMessage(@NotNull List<UUID> playerUIDs, @NotNull String message);
    
    void sendMessage(@NotNull UUID playerUID, @Nullable String message, @NotNull EngineType translatorEngine);

    void sendMessage(@NotNull Audience audience, @Nullable String message, @NotNull EngineType translatorEngine);

    void sendMessage(@NotNull List<UUID> playerUIDs, @NotNull String message, @NotNull EngineType translatorEngine);

    void sendMessage(@NotNull UUID playerUID, @Nullable String message, @NotNull InternalPlaceholders placeholders);

    void sendMessage(@NotNull Audience audience, @Nullable String message, @NotNull InternalPlaceholders placeholders);

    void sendMessage(@NotNull List<UUID> playerUIDs, @Nullable String message, @NotNull InternalPlaceholders placeholders);

    void sendMessage(@NotNull UUID playerUID, @Nullable String message, @NotNull InternalPlaceholders placeholders, @NotNull EngineType translatorEngine);

    void sendMessage(@NotNull Audience audience, @Nullable String message, @NotNull InternalPlaceholders placeholders, @NotNull EngineType translatorEngine);

    void sendMessage(@NotNull List<UUID> playerUIDs, @Nullable String message, @NotNull InternalPlaceholders placeholders, @NotNull EngineType translatorEngine);


    T getClazz();


}
