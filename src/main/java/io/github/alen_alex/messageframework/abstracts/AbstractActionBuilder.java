package io.github.alen_alex.messageframework.abstracts;

import io.github.alen_alex.messageframework.translator.TranslatorEngine;

public abstract class AbstractActionBuilder {

    protected final String[] args;
    protected TranslatorEngine engine;

    public AbstractActionBuilder(String[] args, TranslatorEngine engine) {
        this.args = args;
        this.engine = engine;
    }

    public AbstractActionBuilder(String[] args) {
        this.args = args;
    }
}
