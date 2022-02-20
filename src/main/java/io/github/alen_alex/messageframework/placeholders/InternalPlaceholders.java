package io.github.alen_alex.messageframework.placeholders;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InternalPlaceholders {

    private final HashMap<String, String> register;

    private InternalPlaceholders(){
        register = new HashMap<>();
    }

    public InternalPlaceholders registerPlaceholder(@NotNull String string, @Nullable String toReplace){
        this.register.put(string,toReplace);
        return this;
    }

    public InternalPlaceholders registerPlaceholder(@NotNull String string, @Nullable Integer toReplace){
        this.register.put(string,String.valueOf(toReplace));
        return this;
    }

    public InternalPlaceholders registerPlaceholder(@NotNull String string, @Nullable Double toReplace){
        this.register.put(string,String.valueOf(toReplace));
        return this;
    }

    public InternalPlaceholders registerPlaceholder(@NotNull String string, @Nullable Long toReplace){
        this.register.put(string,String.valueOf(toReplace));
        return this;
    }

    public InternalPlaceholders registerPlaceholder(@NotNull String string, @Nullable Boolean toReplace){
        this.register.put(string,String.valueOf(toReplace));
        return this;
    }

    public InternalPlaceholders registerPlaceholder(@NotNull String string, @Nullable Float toReplace){
        this.register.put(string,String.valueOf(toReplace));
        return this;
    }

    public InternalPlaceholders registerPlaceholder(@NotNull Map<String, String> placeholders){
        this.register.putAll(placeholders);
        return this;
    }

    public static InternalPlaceholders of(){
        return new InternalPlaceholders();
    }

    public InternalPlaceholders flush(){
        this.register.clear();
        return this;
    }

    public int size(){
        return this.register.size();
    }

    public boolean contains(@NotNull String placeholder){
        return this.register.containsKey(placeholder);
    }

    public String parse(@NotNull final String message){
        final String[] param = {message};
        this.register.forEach((placeholder, toReplace) -> {
            param[0] = StringUtils.replace(param[0],placeholder,toReplace);
        });
        return param[0];
    }

    public List<String> parse(@NotNull List<String> messages){
        return messages.stream().map(this::parse).collect(Collectors.toList());
    }


}
