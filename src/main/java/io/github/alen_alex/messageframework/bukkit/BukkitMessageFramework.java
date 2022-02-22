package io.github.alen_alex.messageframework.bukkit;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.abstracts.AbstractTranslator;

import io.github.alen_alex.messageframework.builder.action.ActionMessageBuilder;
import io.github.alen_alex.messageframework.builder.book.BookBuilder;
import io.github.alen_alex.messageframework.builder.bossbar.BossBarBuilder;
import io.github.alen_alex.messageframework.builder.bossbar.ComponentBossBarBuilder;
import io.github.alen_alex.messageframework.builder.title.ComponentTitleBuilder;
import io.github.alen_alex.messageframework.builder.title.StringTitleBuilder;
import io.github.alen_alex.messageframework.builder.title.TitleBuilder;
import io.github.alen_alex.messageframework.model.ActionMessage;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    public void sendComponent(@NotNull Audience audience, @NotNull Component message) {
        audience.sendMessage(message);
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
    public void sendComponent(@NotNull Audience audience, @NotNull List<Component> message) {
        message.iterator().forEachRemaining(m -> this.sendComponent(audience,m));
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
    public void sendMessage(@NotNull Audience audience, @NotNull String message) {
        audience.sendMessage(this.engine.parse(message));
    }

    @Override
    public void sendMessage(@NotNull Audience audience, @NotNull String message, @NotNull InternalPlaceholders placeholders) {
        audience.sendMessage(this.engine.parse(message,placeholders));
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
    public void sendMessage(@NotNull Audience audience, @NotNull List<String> message) {
        message.iterator().forEachRemaining(m->this.sendMessage(audience,m));
    }

    @Override
    public void sendMessage(@NotNull Audience audience, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders) {
        message.iterator().forEachRemaining(m->this.sendMessage(audience,m,placeholders));

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
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;

        final BukkitRunnable runnable = new BukkitRunnable() {
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
        };

        runnable.runTaskTimerAsynchronously(plugin,interval,interval);
    }

    @Override
    public void sendMessageWithIntervalOf(@NotNull UUID playerUID, @NotNull List<String> message, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;

        final BukkitRunnable runnable = new BukkitRunnable() {
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
        };

        runnable.runTaskTimerAsynchronously(plugin,interval,interval);
    }

    @Override
    public void sendMessageWithIntervalOf(@NotNull Player player, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;


        final BukkitRunnable runnable = new BukkitRunnable() {
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
        };

        runnable.runTaskTimerAsynchronously(plugin,interval,interval);
    }

    @Override
    public void sendMessageWithIntervalOf(@NotNull UUID playerUID, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;

        final BukkitRunnable runnable = new BukkitRunnable() {
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
        };

        runnable.runTaskTimerAsynchronously(plugin, interval,interval);
    }

    @Override
    public void sendMessageWithIntervalOf(@NotNull Audience audience, @NotNull List<String> message, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;

        final BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final String toSend = message.get(0);
                audience.sendMessage(engine.parse(toSend));
                message.remove(toSend);
            }
        };

        runnable.runTaskTimerAsynchronously(plugin, interval,interval);
    }

    @Override
    public void sendMessageWithIntervalOf(@NotNull Audience audience, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;

        final BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final String toSend = message.get(0);
                audience.sendMessage(engine.parse(toSend,placeholders));
                message.remove(toSend);
            }
        };

        runnable.runTaskTimerAsynchronously(plugin, interval,interval);
    }

    @Override
    public void sendComponentWithIntervalOf(@NotNull Player player, @NotNull List<Component> message, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;

        final BukkitRunnable runnable = new BukkitRunnable() {
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
        };

        runnable.runTaskTimerAsynchronously(plugin, interval, interval);
    }

    @Override
    public void sendComponentWithIntervalOf(@NotNull UUID playerUID, @NotNull List<Component> message, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;

        final BukkitRunnable runnable = new BukkitRunnable() {
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
        };

        runnable.runTaskTimerAsynchronously(plugin, interval, interval);
    }

    @Override
    public void sendComponentWithIntervalOf(@NotNull Audience audience, @NotNull List<Component> message, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;

        final BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(message.size() <= 0){
                    this.cancel();
                    return;
                }

                final Component toSend = message.get(0);
                audience.sendMessage(toSend);
                message.remove(toSend);
            }
        };

        runnable.runTaskTimerAsynchronously(plugin, interval, interval);
    }

    @Override
    public void broadcastWithIntervalOf(@NotNull List<String> message, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;

        final BukkitRunnable runnable = new BukkitRunnable() {
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
        };

        runnable.runTaskTimerAsynchronously(plugin, interval, interval);
    }

    @Override
    public void broadcastWithIntervalOf(@NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;


        final BukkitRunnable runnable = new BukkitRunnable() {
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
        };

        runnable.runTaskTimerAsynchronously(plugin, interval, interval);
    }

    @Override
    public void broadcastComponentWithIntervalOf(@NotNull List<Component> message, int interval) {
        if(interval <= 0)
            throw new IllegalArgumentException("Interval should be greater than 0 ticks!");

        if(message.isEmpty())
            return;


        final BukkitRunnable runnable = new BukkitRunnable() {
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
        };

        runnable.runTaskTimerAsynchronously(plugin, interval, interval);
    }

    @Override
    public void sendMessageLater(@NotNull Player player, @NotNull String message, int delay) {
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

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
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

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
    public void sendMessageLater(@NotNull Audience audience, @NotNull String message, int delay) {
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

        if(StringUtils.isBlank(message))
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                audience.sendMessage(engine.parse(message));
            }
        },delay);
    }

    @Override
    public void sendMessageLater(@NotNull Audience audience, @NotNull String message, @NotNull InternalPlaceholders placeholders, int delay) {
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

        if(StringUtils.isBlank(message))
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                audience.sendMessage(engine.parse(message,placeholders));
            }
        },delay);
    }

    @Override
    public void sendMessageLater(@NotNull Player player, @NotNull List<String> message, int delay) {
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

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
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

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
    public void sendMessageLater(@NotNull Audience audience, @NotNull List<String> message, int delay) {
        message.iterator().forEachRemaining(m -> sendMessageLater(audience,m,delay));
    }

    @Override
    public void sendMessageLater(@NotNull Audience audience, @NotNull List<String> message, @NotNull InternalPlaceholders placeholders, int delay) {
        message.iterator().forEachRemaining(m -> sendMessageLater(audience,m,placeholders,delay));
    }

    @Override
    public void sendMessageLater(@NotNull UUID playerUID, @NotNull String message, int delay) {
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

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
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

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
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

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
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

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
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                audiences.player(player).sendMessage(message);
            }
        },delay);
    }

    @Override
    public void sendComponentLater(@NotNull Player player, @NotNull List<Component> message, int delay) {
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

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
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                audiences.player(playerUID).sendMessage(message);
            }
        },delay);
    }

    @Override
    public void sendComponentLater(@NotNull UUID playerUID, @NotNull List<Component> message, int delay) {
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

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
    public void sendComponentLater(@NotNull Audience audience, @NotNull Component message, int delay) {
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                audience.sendMessage(message);
            }
        },delay);
    }

    @Override
    public void sendComponentLater(@NotNull Audience audience, @NotNull List<Component> message, int delay) {
        if(delay <= 0)
            throw new IllegalArgumentException("Delay should be greater than 0 ticks!");

        if(message.isEmpty())
            return;

        this.plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                message.iterator().forEachRemaining(audience::sendMessage);
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
    public void broadcastComponent(@NotNull Component component) {
        audiences.all().sendMessage(component);
    }

    @Override
    public void broadcastComponent(@NotNull List<Component> components) {
        components.iterator().forEachRemaining(this::broadcastComponent);
    }

    @Override
    public void sendActionBar(@NotNull Player player, @NotNull String message) {
        this.audiences.player(player).sendActionBar(this.engine.parse(message));
    }

    @Override
    public void sendActionBar(@NotNull Player player, @NotNull String message, @NotNull InternalPlaceholders placeholders) {
        this.audiences.player(player).sendActionBar(this.engine.parse(message,placeholders));
    }

    @Override
    public void sendActionBar(@NotNull UUID playerUID, @NotNull String message) {
        this.audiences.player(playerUID).sendActionBar(this.engine.parse(message));
    }

    @Override
    public void sendActionBar(@NotNull UUID playerUID, @NotNull String message, @NotNull InternalPlaceholders placeholders) {
        this.audiences.player(playerUID).sendActionBar(this.engine.parse(message,placeholders));
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
    public void sendActionBar(@NotNull Audience audience, @NotNull String message) {
        audience.sendActionBar(this.engine.parse(message));
    }

    @Override
    public void sendActionBar(@NotNull Audience audience, @NotNull String message, @NotNull InternalPlaceholders placeholders) {
        audience.sendActionBar(this.engine.parse(message,placeholders));

    }

    @Override
    public void sendActionBarComponent(@NotNull Player player, @NotNull Component message) {
        this.audiences.player(player).sendActionBar(message);
    }

    @Override
    public void sendActionBarComponent(@NotNull UUID playerUID, @NotNull Component message) {
        this.audiences.player(playerUID).sendActionBar(message);
    }

    @Override
    public void sendActionBarComponent(@NotNull List<UUID> playerUIDs, @NotNull Component message) {
        playerUIDs.iterator().forEachRemaining(uid -> this.sendActionBarComponent(playerUIDs,message));
    }

    @Override
    public void sendActionBarComponent(@NotNull Audience audience, @NotNull Component component) {
        audience.sendActionBar(component);
    }

    @Override
    public void sendTitle(@NotNull Player player, @NotNull Title title) {
        audiences.player(player).showTitle(title);
    }

    @Override
    public void sendTitle(@NotNull UUID playerUID, @NotNull Title title) {
        audiences.player(playerUID).showTitle(title);

    }

    @Override
    public void sendTitle(@NotNull List<UUID> playerUID, @NotNull Title title) {
        playerUID.iterator().forEachRemaining(uid -> this.sendTitle(uid,title));
    }

    @Override
    public void sendTitle(@NotNull Audience audience, @NotNull Title title) {
        audience.showTitle(title);
    }

    @Override
    public void sendTitle(@NotNull Player player, @NotNull TitleBuilder builder) {
        this.sendTitle(player,builder.build());
    }

    @Override
    public void sendTitle(@NotNull UUID playerUID, @NotNull TitleBuilder builder) {
        this.sendTitle(playerUID,builder.build());

    }

    @Override
    public void sendTitle(@NotNull List<UUID> playerUID, @NotNull TitleBuilder builder) {
        this.sendTitle(playerUID,builder.build());
    }

    @Override
    public void sendTitle(@NotNull Audience audience, @NotNull TitleBuilder builder) {
        this.sendTitle(audience,builder.build());
    }

    @Override
    public BossBar showBossBar(@NotNull Player player, @NotNull BossBar bossBar) {
        this.audiences.player(player).showBossBar(bossBar);
        return bossBar;
    }

    @Override
    public BossBar hideBossBar(@NotNull Player player, @NotNull BossBar bossBar) {
        this.audiences.player(player).hideBossBar(bossBar);
        return bossBar;
    }

    @Override
    public BossBar showBossBar(@NotNull UUID playerUID, @NotNull BossBar bossBar) {
        this.audiences.player(playerUID).showBossBar(bossBar);
        return bossBar;
    }

    @Override
    public BossBar hideBossBar(@NotNull UUID playerUID, @NotNull BossBar bossBar) {
        this.audiences.player(playerUID).hideBossBar(bossBar);
        return bossBar;
    }

    @Override
    public BossBar showBossBar(@NotNull Audience audience, @NotNull BossBar bossBar) {
        audience.showBossBar(bossBar);
        return bossBar;
    }

    @Override
    public BossBar hideBossBar(@NotNull Audience audience, @NotNull BossBar bossBar) {
        audience.hideBossBar(bossBar);
        return bossBar;
    }

    @Override
    public BossBar showCommonBossBar(@NotNull List<UUID> playerUIDs, @NotNull BossBar bossBar) {
        playerUIDs.iterator().forEachRemaining(uids-> audiences.player(uids).showBossBar(bossBar));
        return bossBar;
    }

    @Override
    public BossBar showBossBar(@NotNull Player player, @NotNull BossBarBuilder bossBar) {
        return this.showBossBar(player,bossBar.build());
    }

    @Override
    public BossBar showBossBar(@NotNull UUID playerUID, @NotNull BossBarBuilder bossBar) {
        return this.showBossBar(playerUID,bossBar.build());
    }


    @Override
    public BossBar showCommonBossBar(@NotNull List<UUID> playerUIDs, @NotNull BossBarBuilder bossBar) {
        return this.showCommonBossBar(playerUIDs,bossBar.build());
    }

    @Override
    public void sendActionMessages(@NotNull Player player,@NotNull ActionMessage actionMessage) {
        actionMessage.processList(player.getUniqueId());
    }

    @Override
    public void sendActionMessages(@NotNull ActionMessage actionMessage) {
        actionMessage.processList();
    }

    @Override
    public void openBook(@NotNull Player player, @NotNull Book book) {
        this.audiences.player(player).openBook(book);
    }

    @Override
    public void openBook(@NotNull UUID playerUID, @NotNull Book book) {
        this.audiences.player(playerUID).openBook(book);
    }

    @Override
    public void openBook(@NotNull Audience audience, @NotNull Book book) {
        audience.openBook(book);
    }

    @Override
    public void openBook(@NotNull List<UUID> playerUID, @NotNull Book book) {
        playerUID.iterator().forEachRemaining(uid -> this.openBook(uid,book));
    }

    @Override
    public void openBook(@NotNull Player player, @NotNull BookBuilder book) {
        this.openBook(player,book.build());
    }

    @Override
    public void openBook(@NotNull UUID playerUID, @NotNull BookBuilder book) {
        this.openBook(playerUID,book.build());
    }

    @Override
    public void openBook(@NotNull Audience audience, @NotNull BookBuilder book) {
        this.openBook(audience,book.build());
    }

    @Override
    public void openBook(@NotNull List<UUID> playerUID, @NotNull BookBuilder book) {
        this.openBook(playerUID,book.build());
    }

    @Override
    public Audience getPlayerAudience(@NotNull Player player) {
        return audiences.player(player);
    }

    @Override
    public Audience getPlayerAudience(@NotNull UUID playerUID) {
        return audiences.player(playerUID);
    }

    @Override
    public Audience getConsole() {
        return audiences.console();
    }

    @Override
    public Audience getAll() {
        return audiences.all();
    }

    @Override
    public Audience ofCondition(@NotNull Predicate<CommandSender> playerPredicate) {
        return audiences.filter(playerPredicate);
    }

    @Override
    public Audience ofPermission(@NotNull String permission) {
        return audiences.permission(permission);
    }

    @Override
    public Audience ofWorld(@NotNull World world) {
        return audiences.world(Key.key(world.getName()));
    }

    @Override
    public Audience getPlayersOfName(@NotNull List<String> players) {
        return audiences.filter(cs -> players.contains(cs.getName()));
    }

    @Override
    public Audience getPlayersOf(@NotNull List<Player> players) {
        return getPlayersOfName(players.stream().map(Player::getName).collect(Collectors.toList()));
    }

    @Override
    public Optional<Audience> ofWorld(@NotNull String worldName) {
        final World world = Bukkit.getWorld(worldName);
        return world == null ? Optional.empty() : Optional.of(ofWorld(world));
    }

    @Override
    public TranslatorEngine engine() {
        if(engine == null)
            throw new NullPointerException("Failed to instantiate the Translator Engine!");

        return this.engine;
    }

    @Override
    public <T> T getJavaPlugin() {
        return (T) plugin;
    }

}
