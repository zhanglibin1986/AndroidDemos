package com.zlb.demos.androiddemos.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;
import java.io.File;

/**
 * TODO
 *
 * @author zhanglibin
 * @since 16/10/25 上午11:14
 */

public class DownloadUtil {
    private final static String PATH_DEMO = "/demos/";

    public final static File getDownloadImagePath() {


        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), PATH_DEMO);
        if(!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static void updateMedia(Context context, String filepath ) {
        MediaScannerConnection.scanFile(context.getApplicationContext(), new String[] { filepath }, null, null );
    }

}
