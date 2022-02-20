package io.github.alen_alex.messageframework.model.action.actions;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.enums.ActionType;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class DelayImpl implements IActions {

    private long secs;

    public DelayImpl(long secs) {
        this.secs = secs;
    }

    @Override
    public CompletableFuture<Boolean> executeAction(@NotNull UUID playerUID, @NotNull MessageFramework framework) {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        final JavaPlugin plugin = (JavaPlugin) framework.getJavaPlugin();
        plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                future.complete(true);
            }
        },secs);
        return future;
    }

    @Override
    public CompletableFuture<Boolean> executeAction(@NotNull MessageFramework framework) {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        final JavaPlugin plugin = (JavaPlugin) framework.getJavaPlugin();
        plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                future.complete(true);
            }
        },secs);
        return future;
    }

    @Override
    public ActionType action() {
        return ActionType.DELAY;
    }
}
