package org.geekbang.bean.scope;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 级别 Scope
 * Created by eru on 2020/7/25.
 */
public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread-local";

    public final NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal("thread-local-scope"){
        @Override
        protected Object initialValue() {
            return new HashMap();
        }
    };

    @NonNull
    public Map<String, Object> getContext(){
        return threadLocal.get();
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = getContext();

        Object object = context.get(name);
        if (object == null){
            object = objectFactory.getObject();
            context.put(name, object);
        }

        return object;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> context = getContext();

        return context.remove(name);
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {
        // TODO
    }

    @Override
    public Object resolveContextualObject(String s) {
        Map<String, Object> context = getContext();
        return context.get(s);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }



}
