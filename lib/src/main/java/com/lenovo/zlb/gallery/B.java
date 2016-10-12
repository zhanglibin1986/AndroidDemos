package com.lenovo.zlb.gallery;

import java.lang.annotation.Annotation;

@Entry.Table("photos")
public class B extends Entry{
	@Column("qiu")
    public String editUri;
    @Column("yue")
    public String title;

}
