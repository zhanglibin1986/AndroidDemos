package com.lenovo.zlb.gallery;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public abstract class Entry {
    // The primary key of the entry.
    @Column("_id")
    public long id = 0;

    @Retention(RetentionPolicy.RUNTIME)//加载在 VM 中,在运行时进行映射
    @Target(ElementType.TYPE)//限定此 annotation只能标示type
    public @interface Table {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)//加载在 VM 中,在运行时进行映射
    @Target(ElementType.FIELD)//限定此 annotation只能标示FIELD
    public @interface Column {
        String value();

        boolean indexed() default false;

        boolean fullText() default false;
    }
}
