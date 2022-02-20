package io.github.alen_alex.messageframework.model.action.interfaces;

import io.github.alen_alex.messageframework.MessageFramework;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface ICondition {

    default boolean shouldContinue(){
        return true;
    }

    void cancel(@NotNull UUID playerUID, @NotNull MessageFramework framework);

}
