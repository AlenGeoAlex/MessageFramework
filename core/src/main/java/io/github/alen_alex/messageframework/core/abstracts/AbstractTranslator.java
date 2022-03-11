package io.github.alen_alex.messageframework.core.abstracts;


import io.github.alen_alex.messageframework.core.translator.TranslatorEngine;

public abstract class AbstractTranslator {

    protected TranslatorEngine engine;

    public AbstractTranslator(TranslatorEngine engine) {
        this.engine = engine;
    }

}
