package io.github.alen_alex.messageframework.model.action.actions;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.enums.ActionType;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MessageImpl implements IActions {

    private final List<Component> text;

    public MessageImpl(List<Component> text) {
        this.text = text;
    }

    public MessageImpl(@NotNull Component text){
        this.text = new ArrayList<Component>(){{
            add(text);
        }};
    }

    public MessageImpl(Component... text){
        this.text = Arrays.stream(text).collect(Collectors.toList());
    }

    @Override
    public CompletableFuture<Boolean> executeAction(@NotNull UUID playerUID, @NotNull MessageFramework framework) {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        framework.sendComponent(playerUID,text);
        future.complete(true);
        return future;
    }

    @Override
    public ActionType action() {
        return ActionType.MESSAGE;
    }
}
