package io.github.alen_alex.messageframework.model;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
        final List<IActions> toProcess =  new ArrayList<>(actionsList);
        final JavaPlugin plugin = (JavaPlugin) framework.getJavaPlugin();
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(toProcess.isEmpty()){
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

    public void processList(){
        final List<IActions> toProcess = new ArrayList<>(actionsList);
        final JavaPlugin plugin = (JavaPlugin) framework.getJavaPlugin();
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (toProcess.isEmpty()) {
                    this.cancel();
                    return;
                }

                final IActions msg = toProcess.get(0);
                if (msg.action().isPlayerOptional()) {
                    msg.executeAction(framework).thenAccept(comp -> {
                        if (comp) {
                            toProcess.remove(msg);
                        }
                    });
                }else {
                    toProcess.remove(msg);
                    plugin.getLogger().info("Unable to parse "+msg.action().name()+" on a non-player call...Skipping!");
                }
            }
        };
        runnable.runTaskTimer(plugin, 0,20);
    }
}
