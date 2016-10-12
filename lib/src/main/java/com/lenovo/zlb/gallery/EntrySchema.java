package com.lenovo.zlb.gallery;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;


public class EntrySchema {
	private String parseTableName(Class<? extends Entry> clazz) {
		Field[] fields = clazz.getFields();
        // Check for a table annotation.
		Entry.Table table = clazz.getAnnotation(Entry.Table.class);
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<fields.length;i++) {
			Entry.Column info = ((AnnotatedElement)fields[i]).getAnnotation(Entry.Column.class);
			list.add(info.value());
		}
		System.out.println(list);
        if (table == null) {
            return null;
        }
        // Return the table name.
        return table.value();
    }
	private Annotation[] parseProp(Class<? extends Entry> clazz) {
		Annotation[] s = clazz.getAnnotations();
		return s;
	}
	public static void main(String[] args) {
		System.out.println((new EntrySchema()).parseTableName(A.class));
		System.out.println((new EntrySchema()).parseTableName(B.class));
	}
}
