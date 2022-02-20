package io.github.alen_alex.messageframework.model.action.actions;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.enums.ActionType;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class TitleImpl implements IActions {

    private final Title title;

    public TitleImpl(Title title) {
        this.title = title;
    }

    @Override
    public CompletableFuture<Boolean> executeAction(@NotNull UUID playerUID, @NotNull MessageFramework framework) {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        framework.sendTitle(playerUID,title);
        future.complete(true);
        return future;
    }

    @Override
    public ActionType action() {
        return ActionType.TITLE;
    }

}
