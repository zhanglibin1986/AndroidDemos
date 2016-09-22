package com.zlb.demos.androiddemos.gank.data;

import android.provider.BaseColumns;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/9/19 上午10:12
 */
public final class GankPersistenceContract {
    private GankPersistenceContract() {}

    public static abstract class GankEntry implements BaseColumns {
        public static final String TABLE_NAME = "gank";

        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_LOCAL_URL = "localurl";
        public static final String COLUMN_NAME_IS_STAR = "star";
    }
}
