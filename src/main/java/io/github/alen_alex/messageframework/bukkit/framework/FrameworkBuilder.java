package io.github.alen_alex.messageframework.bukkit.framework;

import io.github.alen_alex.messageframework.MessageFramework;
import io.github.alen_alex.messageframework.bukkit.BukkitMessageFramework;
import io.github.alen_alex.messageframework.translator.LegacyTranslatorImpl;
import io.github.alen_alex.messageframework.translator.MiniMessageTranslatorImpl;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;
import org.apache.commons.lang3.Validate;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class FrameworkBuilder {

    private JavaPlugin javaPlugin;
    private TranslatorEngine engine;

    public MessageFramework buildDefault(@NotNull JavaPlugin plugin){
        this.javaPlugin = plugin;
        this.engine = new MiniMessageTranslatorImpl();
        return new BukkitMessageFramework(engine,plugin);
    }

    public FrameworkBuilder setPlugin(@NotNull JavaPlugin plugin){
        this.javaPlugin = plugin;
        return this;
    }

    public FrameworkBuilder withLegacyEngine(){
        this.engine = new LegacyTranslatorImpl();
        return this;
    }

    public FrameworkBuilder withMiniMessageEngine(){
        this.engine = new MiniMessageTranslatorImpl();

        MessageFramework messageFramework = new FrameworkBuilder()
                .buildDefault(javaPlugin);


        return this;
    }

    public MessageFramework build(){
        Validate.notNull(this.javaPlugin, "JavaPlugin shouldn't be null, Set one using FrameworkBuilder#setPlugin()");
        Validate.notNull(this.engine, "Translation Engine cannot be null, Set either of the one using FrameworkBuilder#withMiniMessageEngine or FrameworkBuilder#withLegacyEngine or use withLegacyEngine#buildDefault");
        return new BukkitMessageFramework(this.engine,this.javaPlugin);
    }
}
