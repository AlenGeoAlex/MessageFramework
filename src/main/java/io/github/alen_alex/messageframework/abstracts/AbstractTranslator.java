package io.github.alen_alex.messageframework.abstracts;

import io.github.alen_alex.messageframework.translator.TranslatorEngine;

public abstract class AbstractTranslator {

    protected TranslatorEngine engine;

    public AbstractTranslator(TranslatorEngine engine) {
        this.engine = engine;
    }

}
