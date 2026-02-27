package com.ace.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class SelfComponent<T> {

    @Autowired
    ApplicationContext context;

    public T self() {
        return context.getBean(getSelfClass());
    }

    public abstract Class<T> getSelfClass();
}
