package com.lenovo.zlb.gallery;

public class A extends Entry{
	@Column("edit_uri")
    public String editUri;
    @Column("title")
    public String title;
    @Column("summary")
    public String summary;
    @Column("date_taken")
    public long dateTaken;
    @Column("latitude")
    public double latitude;
    @Column("longitude")
    public double longitude;
    @Column("thumbnail_url")
    public String thumbnailUrl;
    @Column("screennail_url")
    public String screennailUrl;
}
