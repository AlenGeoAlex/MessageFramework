package io.github.alen_alex.messageframework.model.action.actions.builders;

import io.github.alen_alex.messageframework.abstracts.AbstractActionBuilder;
import io.github.alen_alex.messageframework.exception.IllegalMessageStatement;
import io.github.alen_alex.messageframework.model.action.actions.DelayImpl;
import io.github.alen_alex.messageframework.model.action.interfaces.IActions;
import io.github.alen_alex.messageframework.model.action.interfaces.IBuilder;
import io.github.alen_alex.messageframework.translator.TranslatorEngine;

public class DelayActionBuilder extends AbstractActionBuilder implements IBuilder {

    public DelayActionBuilder(String[] args) {
        super(args);
    }

    @Override
    public IActions build() {
        if(args.length == 1){
            throw new IllegalMessageStatement("You should provide a valid statement for Delay!");
        }

        final int secs = Integer.parseInt(args[1]);

        if(secs <= 0){
            throw new IllegalMessageStatement("You should provide a valid delay value higher than 0!");
        }

        return new DelayImpl(secs);
    }

    public static DelayActionBuilder builder(String[] args){
        return new DelayActionBuilder(args);
    }
}
