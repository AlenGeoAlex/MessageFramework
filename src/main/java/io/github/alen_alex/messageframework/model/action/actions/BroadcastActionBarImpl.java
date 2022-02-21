package io.github.alen_alex.messageframework.model.action.actions;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.enums.ActionType;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BroadcastActionBarImpl extends ActionBarImpl{

    public BroadcastActionBarImpl(Component text) {
        super(text);
    }

    @Override
    public CompletableFuture<Boolean> executeAction(@NotNull UUID playerUID, @NotNull MessageFramework framework) {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        framework.getAll().sendActionBar(text);
        future.complete(true);
        return future;
    }

    @Override
    public ActionType action() {
        return ActionType.BROADCAST_ACTION_BAR;
    }

    @Override
    public CompletableFuture<Boolean> executeAction(@NotNull MessageFramework framework) {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        framework.getAll().sendActionBar(text);
        future.complete(true);
        return future;
    }
}
