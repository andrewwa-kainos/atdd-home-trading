package com.kainos.andrewwa.atddhometrading.systemtest.commons.dsl;

import java.util.*;
import java.util.concurrent.atomic.*;

public class DslContext {
    private static final AtomicLong COUNTER = new AtomicLong();
    private final Map<String, String> aliases;

    public DslContext() {
        this.aliases = new HashMap<>();
    }

    public String alias(String key) {
        ensureAliasExists(key);
        return aliases.get(key);
    }

    private void ensureAliasExists(String key) {
        if (!aliases.containsKey(key)) {
            var suffix = COUNTER.incrementAndGet();
            var alias = key + "-" + suffix;
            aliases.put(key, alias);
        }
    }
}

