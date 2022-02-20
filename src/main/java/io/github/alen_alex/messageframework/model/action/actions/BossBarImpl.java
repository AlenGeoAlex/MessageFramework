package io.github.alen_alex.messageframework.model.action.actions;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.enums.ActionType;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import io.github.alen_alex.messageframework.model.action.interfaces.ICondition;
import net.kyori.adventure.bossbar.BossBar;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class BossBarImpl implements IActions, ICondition {

    private final BossBar bossBar;

    public BossBarImpl(BossBar bossBar) {
        this.bossBar = bossBar;
    }

    @Override
    public CompletableFuture<Boolean> executeAction(@NotNull UUID playerUID, @NotNull MessageFramework framework) {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        framework.showBossBar(playerUID,bossBar);
        future.complete(true);
        return future;
    }

    @Override
    public ActionType action() {
        return ActionType.BOSS_BAR;
    }

    @Override
    public void cancel(@NotNull UUID playerUID, @NotNull MessageFramework framework) {
        framework.hideBossBar(playerUID,bossBar);
    }
}
