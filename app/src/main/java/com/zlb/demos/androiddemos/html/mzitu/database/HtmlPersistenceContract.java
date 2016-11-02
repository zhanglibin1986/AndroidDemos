package com.zlb.demos.androiddemos.html.mzitu.database;

import android.provider.BaseColumns;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/10/25 下午5:24
 */

public final class HtmlPersistenceContract {
    class HtmlListEntry implements BaseColumns {
        public static final String TABLE_NAME = "mztu";

        public static final String DETAIL_URL = "detailUrl";
        public static final String TITLE = "title";
        public static final String PIC_URL = "picUrl";
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
