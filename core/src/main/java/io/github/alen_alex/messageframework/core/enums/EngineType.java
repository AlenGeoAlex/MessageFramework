package io.github.alen_alex.messageframework.core.enums;

import io.github.alen_alex.messageframework.core.translator.LegacyTranslatorImpl;
import io.github.alen_alex.messageframework.core.translator.MiniMessageTranslatorImpl;
import io.github.alen_alex.messageframework.core.translator.TranslatorEngine;

public enum EngineType {

    LEGACY (LegacyTranslatorImpl.get()),
    MINI_MESSAGE (MiniMessageTranslatorImpl.get());

    private final TranslatorEngine engine;

    EngineType(TranslatorEngine engine) {
        this.engine = engine;
    }

    public TranslatorEngine getEngine() {
        return engine;
    }
}
