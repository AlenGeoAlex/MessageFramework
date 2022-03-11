package io.github.alen_alex.messageframework.bukkit;

import io.github.alen_alex.messageframework.core.IFramework;
import io.github.alen_alex.messageframework.core.enums.EngineType;
import io.github.alen_alex.messageframework.core.placeholders.InternalPlaceholders;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class BukkitFramework implements IFramework<BukkitFramework> {

    private final BukkitAudiences audiences;
    private final JavaPlugin plugin;

    private BukkitFramework(@NotNull final JavaPlugin plugin){
        this.plugin = plugin;
        this.audiences = BukkitAudiences.create(plugin);
    }

    @Override
    public void sendMessage(@NotNull UUID playerUID, @NotNull Component message) {
        audiences.player(playerUID).sendMessage(message);
    }

    @Override
    public void sendMessage(@NotNull Audience audience, @NotNull Component message) {
        audience.sendMessage(message);
    }

    @Override
    public void sendMessage(@NotNull List<UUID> playerUIDS, @NotNull Component message) {
        for(UUID uid : playerUIDS){
            this.sendMessage(uid, message);
        }
    }

    @Override
    public void sendMessage(@NotNull UUID playerUID, @NotNull List<Component> message) {
        for(Component component : message){
            this.sendMessage(playerUID, component);
        }
    }

    @Override
    public void sendMessage(@NotNull Audience audience, @NotNull List<Component> message) {
        for(Component component : message){
            this.sendMessage(audience, component);
        }
    }

    @Override
    public void sendMessage(@NotNull List<UUID> playerUIDS, @NotNull List<Component> message) {
        for(Component component : message){
            this.sendMessage(playerUIDS, component);
        }
    }

    @Override
    public void sendMessage(@NotNull UUID playerUID, @Nullable String message) {
        this.sendMessage(playerUID, message, EngineType.MINI_MESSAGE);
    }

    @Override
    public void sendMessage(@NotNull Audience audience, @Nullable String message) {
        this.sendMessage(audience, message, EngineType.MINI_MESSAGE);
    }

    @Override
    public void sendMessage(@NotNull List<UUID> playerUIDs, @NotNull String message) {
        for(UUID uid : playerUIDs){
            this.sendMessage(uid, message);
        }
    }

    @Override
    public void sendMessage(@NotNull UUID playerUID, @Nullable String message, @NotNull EngineType translatorEngine) {
        if(StringUtils.isBlank(message))
            return;

        this.sendMessage(playerUID, translatorEngine.getEngine().parse(message));
    }

    @Override
    public void sendMessage(@NotNull Audience audience, @Nullable String message, @NotNull EngineType translatorEngine) {
        if(StringUtils.isBlank(message))
            return;

        this.sendMessage(audience, translatorEngine.getEngine().parse(message));
    }

    @Override
    public void sendMessage(@NotNull List<UUID> playerUIDs, @NotNull String message, @NotNull EngineType translatorEngine) {
        for(UUID uid : playerUIDs){
            this.sendMessage(uid, message, translatorEngine);
        }
    }

    @Override
    public void sendMessage(@NotNull UUID playerUID, @Nullable String message, @NotNull InternalPlaceholders placeholders) {
        if(StringUtils.isBlank(message))
            return;

        this.sendMessage(playerUID, message, placeholders, EngineType.MINI_MESSAGE);
    }

    @Override
    public void sendMessage(@NotNull Audience audience, @Nullable String message, @NotNull InternalPlaceholders placeholders) {
        if(StringUtils.isBlank(message))
            return;

        this.sendMessage(audience, message, placeholders, EngineType.MINI_MESSAGE);
    }

    @Override
    public void sendMessage(@NotNull List<UUID> playerUIDs, @Nullable String message, @NotNull InternalPlaceholders placeholders) {

    }

    @Override
    public void sendMessage(@NotNull UUID playerUID, @Nullable String message, @NotNull InternalPlaceholders placeholders, @NotNull EngineType translatorEngine) {
        if(StringUtils.isBlank(message))
            return;

        this.sendMessage(playerUID, translatorEngine.getEngine().parse(placeholders.parse(message)));
    }

    @Override
    public void sendMessage(@NotNull Audience audience, @Nullable String message, @NotNull InternalPlaceholders placeholders, @NotNull EngineType translatorEngine) {
        if(StringUtils.isBlank(message))
            return;

        this.sendMessage(audience, translatorEngine.getEngine().parse(placeholders.parse(message)));
    }

    @Override
    public void sendMessage(@NotNull List<UUID> playerUIDs, @Nullable String message, @NotNull InternalPlaceholders placeholders, @NotNull EngineType translatorEngine) {
        for(UUID uid : playerUIDs){
            this.sendMessage(uid, message, placeholders, translatorEngine);
        }
    }

    @Override
    public BukkitFramework getClazz() {
        return this;
    }
}
