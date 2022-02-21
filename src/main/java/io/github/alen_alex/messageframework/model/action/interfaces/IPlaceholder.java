package io.github.alen_alex.messageframework.model.action.interfaces;

import io.github.alen_alex.messageframework.placeholders.InternalPlaceholders;
import org.jetbrains.annotations.NotNull;

public interface IPlaceholder {

    <T> T withPlaceholders(@NotNull InternalPlaceholders placeholders);

}
