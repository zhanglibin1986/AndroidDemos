package com.zlb.demos.androiddemos.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.Log;
import com.zlb.demos.androiddemos.AppApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    private static final String TAG = "FileUtil";

    /**
     * 将一个序列化对象写入文件
     */
    public static void writeSerObjectToFile(Object object, String fileName) {
        ObjectOutputStream out = null;
        try {
            makeDIRAndCreateFile(fileName);
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(object);
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.toString());
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                }
            }
        }
    }

    /**
     * 从文件中直接读一个对象
     */
    public static Object readSerObjectFromFile(String fileName) {
        Object b = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            b = in.readObject();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                }
            }
        }
        return b;
    }

    /**
     * 从assets文件中读取对象
     */
    public static Object readSerObjectFromAssets(Context context, String fileName) {
        Object b = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(context.getResources().getAssets().open(fileName));
            b = in.readObject();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e(TAG, e.toString());
                }
            }
        }
        return b;
    }

    /**
     * 创建目录和文件， 如果目录或文件不存在，则创建出来
     *
     * @throws IOException
     */
    public static File makeDIRAndCreateFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.isDirectory()) {
            file.mkdirs();
            return file;
        }

        String parent = file.getParent();
        File parentFile = new File(parent);
        if (!parentFile.exists()) {
            if (parentFile.mkdirs()) {
                file.createNewFile();
            } else {
                throw new IOException("创建目录失败！");
            }
        } else {
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        return file;
    }

    public static StringBuilder readFileFromAssets(Context context, String fileName) {

        StringBuilder fileContent = new StringBuilder("");
        BufferedReader reader = null;
        try {
            InputStreamReader is =
                    new InputStreamReader(context.getResources().getAssets().open(fileName));
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    public static StringBuilder readFile(String filePath, String charsetName) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (file == null || !file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file
     *
     * @param append is append, if true, write to the end of file, else clear content of file and
     * write into it
     * @return return true
     * @throws IOException if an error occurs while operator FileWriter
     */
    public static boolean writeFile(String filePath, String content, boolean append) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            Log.e("crash", "IOException 1 ");
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    Log.e("crash", "IOException 2 ");
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * write file
     *
     * @return return true
     * @throws IOException if an error occurs while operator FileWriter
     */
    public static boolean writeFile(String filePath, InputStream stream) {
        OutputStream o = null;
        try {
            o = new FileOutputStream(filePath);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (o != null) {
                try {
                    o.close();
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * 根据URI获取path
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver()
                    .query(uri, new String[] { ImageColumns.DATA }, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getModifiedTime(String filePath) {
        File f = new File(filePath);
        if (f.exists()) {
            long time = f.lastModified();
            Date date = new Date(time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            return sdf.format(date);
        }
        return null;
    }

    /**
     * 递归删除文件和文件夹
     *
     * @param file 要删除的根目录
     */
    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();
                    Intent media = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(file);
                    media.setData(contentUri);
                    AppApplication.getApplication().sendBroadcast(media);
                    return;
                }
                for (File f : childFile) {
                    deleteFile(f);
                }
                file.delete();
            }
        }
    }

    /**
     * 获取文件名
     */
    public static String getNameViaPath(String path) {
        int separatorIndex = path.lastIndexOf(File.separator);
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }

    public static boolean renameFile(String filePath) {
        if(filePath == null || filePath.trim().equals("")) {
            return false;
        }
        File file = new File(filePath);
        if(!file.exists()) {
            return false;
        }


    }

}
