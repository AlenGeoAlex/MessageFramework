package io.github.alen_alex.messageframework.model;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ActionMessage {

    private final MessageFramework framework;
    private final List<IActions> actionsList;

    public ActionMessage(MessageFramework framework, List<IActions> actionsList) {
        this.framework = framework;
        this.actionsList = actionsList;
    }

    public void processList(@NotNull UUID playerUID){
        final List<IActions> toProcess = actionsList;
        final JavaPlugin plugin = (JavaPlugin) framework.getJavaPlugin();
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(actionsList.isEmpty()){
                    this.cancel();
                    return;
                }

                final IActions msg = toProcess.get(0);
                msg.executeAction(playerUID,framework).thenAccept(comp -> {
                    if(comp) {
                        toProcess.remove(msg);
                    }
                });
            }
        };
        runnable.runTaskTimer(plugin, 0,20);
    }
}
