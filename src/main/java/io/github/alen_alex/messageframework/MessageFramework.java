package io.github.alen_alex.messageframework;

import io.github.alen_alex.messageframework.builder.bossbar.ComponentBossBarBuilder;
import io.github.alen_alex.messageframework.bukkit.framework.FrameworkBuilder;
import io.github.alen_alex.messageframework.builder.title.ComponentTitleBuilder;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public interface MessageFramework {

    void sendComponent(@NotNull final Player player, @NotNull Component message);

    void sendComponent(@NotNull final UUID playerUID, @NotNull Component message);

    void sendComponent(@NotNull final Player player, @NotNull List<Component> message);

    void sendComponent(@NotNull final UUID playerUID, @NotNull List<Component> message);

    void sendMessage(@NotNull final Player player, @NotNull String message);

    void sendMessage(@NotNull final Player player, @NotNull String message, @NotNull InternalPlaceholders placeholders);

    void sendMessage(@NotNull final Player player, @NotNull List<String> message);

    void sendMessage(@NotNull final Player player, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders);

    void sendMessage(@NotNull final UUID playerUID, @NotNull String message);

    void sendMessage(@NotNull final UUID playerUID, @NotNull String message, @NotNull InternalPlaceholders placeholders);

    void sendMessage(@NotNull final UUID playerUID, @NotNull List<String> message);

    void sendMessage(@NotNull final UUID playerUID, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders);

    void sendMessageWithIntervalOf(@NotNull final Player player, @NotNull List<String> message, int interval);

    void sendMessageWithIntervalOf(@NotNull final UUID playerUID, @NotNull List<String> message, int interval);

    void sendMessageWithIntervalOf(@NotNull final Player player, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int interval);

    void sendMessageWithIntervalOf(@NotNull final UUID playerUID, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int interval);

    void sendComponentWithIntervalOf(@NotNull final Player player, @NotNull List<Component> message, int interval);

    void sendComponentWithIntervalOf(@NotNull final UUID playerUID, @NotNull List<Component> message, int interval);

    void broadcastWithIntervalOf(@NotNull List<String> message, int interval);

    void broadcastWithIntervalOf(@NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int interval);

    void broadcastComponentWithIntervalOf(@NotNull List<Component> message, int interval);

    void sendMessageLater(@NotNull Player player, @NotNull String message, int delay);

    void sendMessageLater(@NotNull Player player, @NotNull String message, @NotNull InternalPlaceholders placeholders, int delay);

    void sendMessageLater(@NotNull Player player, @NotNull List<String> message, int delay);

    void sendMessageLater(@NotNull Player player, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int delay);

    void sendMessageLater(@NotNull UUID playerUID, @NotNull String message, int delay);

    void sendMessageLater(@NotNull UUID playerUID, @NotNull String message, @NotNull InternalPlaceholders placeholders, int delay);

    void sendMessageLater(@NotNull UUID playerUID, @NotNull List<String> message, int delay);

    void sendMessageLater(@NotNull UUID playerUID, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int delay);

    void sendComponentLater(@NotNull Player player, @NotNull Component message, int delay);

    void sendComponentLater(@NotNull Player player, @NotNull List<Component> message, int delay);

    void sendComponentLater(@NotNull UUID playerUID, @NotNull Component message, int delay);

    void sendComponentLater(@NotNull UUID playerUID, @NotNull List<Component> message, int delay);

    void sendMessageOfPerm(@NotNull String perm, @NotNull String message);

    void sendMessageOfPerm(@NotNull String perm, @NotNull String message, @NotNull InternalPlaceholders placeholders);

    void sendMessageOfPerm(@NotNull String perm, @NotNull List<String> message);

    void sendMessageOfPerm(@NotNull String perm, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders);

    void broadcast(@NotNull String message);

    void broadcast(@NotNull String message, @NotNull InternalPlaceholders placeholders);

    void broadcast(@NotNull List<String> message);

    void broadcast(@NotNull List<String> message, @NotNull InternalPlaceholders placeholders);

    void sendActionBar(@NotNull Player player, @NotNull String message);

    void sendActionBar(@NotNull Player player, @NotNull String message, @NotNull InternalPlaceholders placeholders);

    void sendActionBar(@NotNull UUID playerUID, @NotNull String message);

    void sendActionBar(@NotNull UUID playerUID, @NotNull String message, @NotNull InternalPlaceholders placeholders);

    void sendActionBar(@NotNull List<Player> players, @NotNull String message);

    void sendActionBar(@NotNull List<Player> players, @NotNull String message, @NotNull InternalPlaceholders placeholders);

    void sendActionBarComponent(@NotNull Player player, @NotNull Component message);

    void sendActionBarComponent(@NotNull UUID playerUID, @NotNull Component message);

    void sendActionBarComponent(@NotNull List<UUID> playerUIDs, @NotNull Component message);

    void sendTitle(@NotNull Player player, @NotNull Title title);

    void sendTitle(@NotNull UUID uuid, @NotNull Title title);

    void sendTitle(@NotNull List<UUID> playerUID, @NotNull Title title);

    void sendTitle(@NotNull Player player, @NotNull ComponentTitleBuilder builder);

    void sendTitle(@NotNull UUID uuid, @NotNull ComponentTitleBuilder builder);

    void sendTitle(@NotNull List<UUID> playerUID, @NotNull ComponentTitleBuilder builder);

    BossBar showBossBar(@NotNull final Player player, @NotNull BossBar bossBar);

    BossBar hideBossBar(@NotNull final Player player, @NotNull BossBar bossBar);

    BossBar showBossBar(@NotNull final UUID playerUID, @NotNull BossBar bossBar);

    BossBar hideBossBar(@NotNull final UUID playerUID, @NotNull BossBar bossBar);

    BossBar showCommonBossBar(@NotNull final List<UUID> playerUIDs, @NotNull BossBar bossBar);

    BossBar showBossBar(@NotNull final Player player, @NotNull ComponentBossBarBuilder bossBar);

    BossBar showBossBar(@NotNull final UUID playerUID, @NotNull ComponentBossBarBuilder bossBar);

    BossBar showCommonBossBar(@NotNull final List<UUID> playerUIDs, @NotNull ComponentBossBarBuilder bossBar);

    Audience getPlayerAudience(@NotNull final Player player);

    Audience getPlayerAudience(@NotNull final UUID playerUID);

    Audience getConsole();

    Audience getAll();

    Audience ofCondition(@NotNull final Predicate<CommandSender> playerPredicate);

    Audience ofPermission(@NotNull final String permission);

    Audience ofWorld(@NotNull final World world);

    Optional<Audience> ofWorld(@NotNull final String worldName);

    TranslatorEngine engine() throws IllegalAccessException;

    default FrameworkBuilder builder(){
        return new FrameworkBuilder();
    }
}
