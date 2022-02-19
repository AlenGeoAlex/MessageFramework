package io.github.alen_alex.messageframework.bukkit;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.abstracts.AbstractTranslator;
import io.github.alen_alex.messageframework.builder.title.StringTitleBuilder;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class BukkitMessageFramework extends AbstractTranslator implements MessageFramework {

    private final BukkitAudiences audiences;
    private final JavaPlugin plugin;

    public BukkitMessageFramework(TranslatorEngine engine, JavaPlugin plugin) {
        super(engine);
        this.audiences = BukkitAudiences.create(plugin);
        this.plugin = plugin;
    }

    @Override
    public void sendComponent(@NotNull Player player, @NotNull Component message) {
        this.audiences.player(player).sendMessage(message);
    }

    @Override
    public void sendComponent(@NotNull UUID playerUID, @NotNull Component message) {
        this.audiences.player(playerUID).sendMessage(message);
    }

    @Override
    public void sendComponent(@NotNull Player player, @NotNull List<Component> message) {
        message.iterator().forEachRemaining(comp -> sendComponent(player,comp));
    }

    @Override
    public void sendComponent(@NotNull UUID playerUID, @NotNull List<Component> message) {
        message.iterator().forEachRemaining(comp -> sendComponent(playerUID,comp));
    }

    @Override
    public void sendMessage(@NotNull Player player, @NotNull String message) {
        audiences.player(player).sendMessage(this.engine.parse(message));
    }

    @Override
    public void sendMessage(@NotNull Player player, @NotNull String message, @NotNull InternalPlaceholders placeholders) {
        audiences.player(player).sendMessage(this.engine.parse(message, placeholders));
    }

    @Override
    public void sendMessage(@NotNull Player player, @NotNull List<String> message) {
       message.iterator().forEachRemaining(m -> sendMessage(player,m));
    }

    @Override
    public void sendMessage(@NotNull Player player, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders) {
        message.iterator().forEachRemaining(m -> sendMessage(player,m,placeholders));
    }

    @Override
    public void sendMessage(@NotNull UUID playerUID, @NotNull String message) {
        audiences.player(playerUID).sendMessage(engine.parse(message));
    }

    @Override
    public void sendMessage(@NotNull UUID playerUID, @NotNull String message, @NotNull InternalPlaceholders placeholders) {
        audiences.player(playerUID).sendMessage(engine.parse(message,placeholders));
    }

    @Override
    public void sendMessage(@NotNull UUID playerUID, @NotNull List<String> message) {
        message.iterator().forEachRemaining(m -> this.sendMessage(playerUID,m));
    }

    @Override
    public void sendMessage(@NotNull UUID playerUID, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders) {
        message.iterator().forEachRemaining(m -> this.sendMessage(playerUID,m,placeholders));
    }

    @Override
    public void sendMessageWithIntervalOf(@NotNull Player player, @NotNull List<String> message, int interval) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final String toSend = message.get(0);
                audiences.player(player).sendMessage(engine.parse(toSend));
                message.remove(toSend);
            }
        }, interval, interval);
    }

    @Override
    public void sendMessageWithIntervalOf(@NotNull UUID playerUID, @NotNull List<String> message, int interval) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final String toSend = message.get(0);
                audiences.player(playerUID).sendMessage(engine.parse(toSend));
                message.remove(toSend);
            }
        },interval,interval);
    }

    @Override
    public void sendMessageWithIntervalOf(@NotNull Player player, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int interval) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final String toSend = message.get(0);
                audiences.player(player).sendMessage(engine.parse(toSend,placeholders));
                message.remove(toSend);
            }
        },interval,interval);
    }

    @Override
    public void sendMessageWithIntervalOf(@NotNull UUID playerUID, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int interval) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final String toSend = message.get(0);
                audiences.player(playerUID).sendMessage(engine.parse(toSend,placeholders));
                message.remove(toSend);
            }
        },interval,interval);
    }

    @Override
    public void sendComponentWithIntervalOf(@NotNull Player player, @NotNull List<Component> message, int interval) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final Component toSend = message.get(0);
                audiences.player(player).sendMessage(toSend);
                message.remove(toSend);
            }
        },interval,interval);
    }

    @Override
    public void sendComponentWithIntervalOf(@NotNull UUID playerUID, @NotNull List<Component> message, int interval) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final Component toSend = message.get(0);
                audiences.player(playerUID).sendMessage(toSend);
                message.remove(toSend);
            }
        },interval,interval);
    }

    @Override
    public void broadcastWithIntervalOf(@NotNull List<String> message, int interval) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final String toSend = message.get(0);
                audiences.all().sendMessage(engine.parse(toSend));
                message.remove(toSend);
            }
        },interval,interval);
    }

    @Override
    public void broadcastWithIntervalOf(@NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int interval) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final String toSend = message.get(0);
                audiences.all().sendMessage(engine.parse(toSend,placeholders));
                message.remove(toSend);
            }
        },interval,interval);
    }

    @Override
    public void broadcastComponentWithIntervalOf(@NotNull List<Component> message, int interval) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final Component toSend = message.get(0);
                audiences.all().sendMessage(toSend);
                message.remove(toSend);
            }
        },interval,interval);
    }

    @Override
    public void sendMessageLater(@NotNull Player player, @NotNull String message, int delay) {
        if(StringUtils.isBlank(message))
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
               audiences.player(player).sendMessage(engine.parse(message));
            }
        },delay);
    }

    @Override
    public void sendMessageLater(@NotNull Player player, @NotNull String message, @NotNull InternalPlaceholders placeholders, int delay) {
        if(StringUtils.isBlank(message))
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                audiences.player(player).sendMessage(engine.parse(message, placeholders));
            }
        },delay);
    }

    @Override
    public void sendMessageLater(@NotNull Player player, @NotNull List<String> message, int delay) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                message.iterator().forEachRemaining(m -> {
                    audiences.player(player).sendMessage(engine.parse(m));
                });
            }
        },delay);
    }

    @Override
    public void sendMessageLater(@NotNull Player player, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int delay) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                message.iterator().forEachRemaining(m -> {
                    audiences.player(player).sendMessage(engine.parse(m,placeholders));
                });
            }
        },delay);
    }

    @Override
    public void sendMessageLater(@NotNull UUID playerUID, @NotNull String message, int delay) {
        if(StringUtils.isBlank(message))
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                audiences.player(playerUID).sendMessage(engine.parse(message));
            }
        },delay);
    }

    @Override
    public void sendMessageLater(@NotNull UUID playerUID, @NotNull String message, @NotNull InternalPlaceholders placeholders, int delay) {
        if(StringUtils.isBlank(message))
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                audiences.player(playerUID).sendMessage(engine.parse(message, placeholders));
            }
        },delay);
    }

    @Override
    public void sendMessageLater(@NotNull UUID playerUID, @NotNull List<String> message, int delay) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                message.iterator().forEachRemaining(m -> {
                    audiences.player(playerUID).sendMessage(engine.parse(m));
                });
            }
        },delay);
    }

    @Override
    public void sendMessageLater(@NotNull UUID playerUID, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int delay) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                message.iterator().forEachRemaining(m -> {
                    audiences.player(playerUID).sendMessage(engine.parse(m,placeholders));
                });
            }
        },delay);
    }

    @Override
    public void sendComponentLater(@NotNull Player player, @NotNull Component message, int delay) {
        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                audiences.player(player).sendMessage(message);
            }
        },delay);
    }

    @Override
    public void sendComponentLater(@NotNull Player player, @NotNull List<Component> message, int delay) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                message.iterator().forEachRemaining(comp -> {
                    audiences.player(player).sendMessage(comp);
                });
            }
        },delay);
    }

    @Override
    public void sendComponentLater(@NotNull UUID playerUID, @NotNull Component message, int delay) {
        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                audiences.player(playerUID).sendMessage(message);
            }
        },delay);
    }

    @Override
    public void sendComponentLater(@NotNull UUID playerUID, @NotNull List<Component> message, int delay) {
        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                message.iterator().forEachRemaining(comp -> {
                    audiences.player(playerUID).sendMessage(comp);
                });
            }
        },delay);
    }


    @Override
    public void sendMessageOfPerm(@NotNull String perm, @NotNull String message) {
        audiences.permission(perm).sendMessage(engine.parse(message));
    }

    @Override
    public void sendMessageOfPerm(@NotNull String perm, @NotNull String message, @NotNull InternalPlaceholders placeholders) {
        audiences.permission(perm).sendMessage(engine.parse(placeholders.parse(message)));

    }

    @Override
    public void sendMessageOfPerm(@NotNull String perm, @NotNull List<String> message) {
        message.iterator().forEachRemaining((m) -> this.sendMessageOfPerm(perm,m));
    }

    @Override
    public void sendMessageOfPerm(@NotNull String perm, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders) {
        message.iterator().forEachRemaining((m) -> this.sendMessageOfPerm(perm,m,placeholders));
    }

    @Override
    public void broadcast(@NotNull String message) {
        audiences.all().sendMessage(this.engine.parse(message));
    }

    @Override
    public void broadcast(@NotNull String message, @NotNull InternalPlaceholders placeholders) {
        audiences.all().sendMessage(this.engine.parse(message,placeholders));
    }

    @Override
    public void broadcast(@NotNull List<String> message) {
        message.iterator().forEachRemaining(this::broadcast);
    }

    @Override
    public void broadcast(@NotNull List<String> message, @NotNull InternalPlaceholders placeholders) {
        message.iterator().forEachRemaining(m->this.broadcast(m,placeholders));
    }

    @Override
    public void sendActionBar(@NotNull Player player, @NotNull String message) {
        this.audiences.player(player).sendMessage(this.engine.parse(message));
    }

    @Override
    public void sendActionBar(@NotNull Player player, @NotNull String message, @NotNull InternalPlaceholders placeholders) {
        this.audiences.player(player).sendMessage(this.engine.parse(message,placeholders));
    }

    @Override
    public void sendActionBar(@NotNull UUID playerUID, @NotNull String message) {
        this.audiences.player(playerUID).sendMessage(this.engine.parse(message));
    }

    @Override
    public void sendActionBar(@NotNull UUID playerUID, @NotNull String message, @NotNull InternalPlaceholders placeholders) {
        this.audiences.player(playerUID).sendMessage(this.engine.parse(message,placeholders));
    }

    @Override
    public void sendActionBar(@NotNull List<Player> players, @NotNull String message) {
        players.iterator().forEachRemaining(pl -> this.sendActionBar(pl,message));
    }

    @Override
    public void sendActionBar(@NotNull List<Player> players, @NotNull String message, @NotNull InternalPlaceholders placeholders) {
        players.iterator().forEachRemaining(pl -> this.sendActionBar(pl,message,placeholders));
    }

    @Override
    public void sendActionBarComponent(@NotNull Player player, @NotNull Component message) {
        this.audiences.player(player).sendMessage(message);
    }

    @Override
    public void sendActionBarComponent(@NotNull UUID playerUID, @NotNull Component message) {
        this.audiences.player(playerUID).sendMessage(message);

    }

    @Override
    public void sendActionBarComponent(@NotNull List<UUID> playerUIDs, @NotNull Component message) {
        playerUIDs.iterator().forEachRemaining(uid -> this.sendActionBarComponent(playerUIDs,message));
    }

    @Override
    public TranslatorEngine engine() throws IllegalAccessException {
        if(engine == null)
            throw new IllegalAccessException("Failed to instantiate the Translator Engine!");
        return this.engine;
    }


}
