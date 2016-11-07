package com.zlb.demos.androiddemos.html.mmjpg.database;

import android.provider.BaseColumns;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/10/25 下午5:24
 */

public final class MmPersistenceContract {
    class MmListEntry implements BaseColumns {
        public static final String TABLE_NAME = "mmjpg";

        public static final String TITLE = "title";
        public static final String PIC_URL = "picUrl";
        public static final String IS_WRITE_TO_FILE = "isWriteToFile";
        public static final String IS_STAR = "isStar";
    }

    /**
     * private String detailUrl;
     private String title;
     private String picUrl;
     private boolean isStar;
     *
     *
     */
}
