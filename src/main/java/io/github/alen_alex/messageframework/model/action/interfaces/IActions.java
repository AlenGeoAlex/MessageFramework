package io.github.alen_alex.messageframework.model.action.interfaces;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.enums.ActionType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface IActions {

    CompletableFuture<Boolean> executeAction(@NotNull UUID playerUID, @NotNull MessageFramework framework);

    default CompletableFuture<Boolean> executeAction(@NotNull MessageFramework framework){
        return CompletableFuture.completedFuture(true);
    }

    ActionType action();
}
