package com.zlb.demos.androiddemos.utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author zhanglibin
 * @Project: AndroidDemos
 * @Package com.zlb.demos.androiddemos.utils
 * @date 16/8/2上午11:36
 * @Description
 */
public class Logger {
    private static LogLevel logLevel = LogLevel.DEBUG;

    private static final String TAG = "BreadTrip";
    private static final boolean SPECIAL_LOGGER = false;
    private static final int METHOD_COUNT = 2;
    private static final boolean SHOW_THREAD_INFO = true;

    /**
     * Android's max limit for a log entry is ~4076 bytes, so 4000 bytes is used as chunk size since default charset is
     * UTF-8
     */
    private static final int CHUNK_SIZE = 4000;
    private static final int JSON_INDENT = 4;
    private static final Settings settings = new Settings();

    //no instance
    private Logger() {
    }

    /**
     * init
     *
     * @param printLog
     * @return
     */
    public static Settings init(boolean printLog) {
        if (printLog) {
            Logger.logLevel = LogLevel.DEBUG;
        } else {
            Logger.logLevel = LogLevel.RELEASE;
        }
        return settings;
    }

    public static boolean isDebugable(){
        return logLevel == LogLevel.DEBUG;
    }

    public static void d(String message) {
        d(TAG, message);
    }

    public static void d(String tag, String message) {
        d(tag, message, settings.methodCount);
    }

    private static void d(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        log(tag, Log.DEBUG, message, methodCount);
    }

    public static void e(String message) {
        e(TAG, message);
    }

    public static void e(String tag, String message) {
        e(tag, message, null, settings.methodCount);
    }

    public static void e(Exception e) {
        e(TAG, e);
    }

    public static void e(String tag, Exception e) {
        e(tag, null, e, settings.methodCount);
    }

    public static void e(String tag, String message, Exception e) {
        e(tag, message, e, settings.methodCount);
    }

    private static void e(String tag, String message, Exception e, int methodCount) {
        validateMethodCount(methodCount);
        if (e != null && message != null) {
            message += " : " + e.toString();
        }
        if (e != null && message == null) {
            message = e.toString();
        }
        if (message == null) {
            message = "No message/exception is set";
        }
        log(tag, Log.ERROR, message, methodCount);
    }

    public static void w(String message) {
        w(TAG, message);
    }

    public static void w(String tag, String message) {
        w(tag, message, settings.methodCount);
    }

    private static void w(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        log(tag, Log.WARN, message, methodCount);
    }

    public static void i(String message) {
        i(TAG, message);
    }

    public static void i(String tag, String message) {
        i(tag, message, settings.methodCount);
    }

    private static void i(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        log(tag, Log.INFO, message, methodCount);
    }

    public static void v(String message) {
        v(TAG, message);
    }

    public static void v(String tag, String message) {
        v(tag, message, settings.methodCount);
    }

    private static void v(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        log(tag, Log.VERBOSE, message, methodCount);
    }

    public static void wtf(String message) {
        wtf(TAG, message);
    }

    public static void wtf(String tag, String message) {
        wtf(tag, message, settings.methodCount);
    }

    private static void wtf(String tag, String message, int methodCount) {
        validateMethodCount(methodCount);
        log(tag, Log.ASSERT, message, methodCount);
    }

    /**
     * Formats the json content and print it
     *
     * @param json
     *            the json content
     */
    public static void json(String json) {
        json(TAG, json);
    }

    /**
     * Formats the json content and print it
     *
     * @param json
     *            the json content
     */
    public static void json(String tag, String json) {
        int methodCount = settings.methodCount;
        validateMethodCount(methodCount);
        if (TextUtils.isEmpty(json)) {
            d(tag, "Empty/Null json content", methodCount);
            return;
        }
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(JSON_INDENT);
                d(tag, message, methodCount);
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(JSON_INDENT);
                d(tag, message, methodCount);
            }
        } catch (JSONException e) {
            d(tag, e.getCause().getMessage() + "\n" + json, methodCount);
        }
    }

    private static void log(String tag, int logType, String message, int methodCount) {
        if (logLevel == LogLevel.RELEASE) {
            return;
        }

        tag = getTag(tag);
        if(SPECIAL_LOGGER){
            logHeader(tag, logType);
            logHeaderContent(tag, logType, methodCount);

            //get bytes of message with system's default charset (which is UTF-8 for Android)
            byte[] bytes = message.getBytes();
            int length = bytes.length;
            if (length <= CHUNK_SIZE) {
                if (methodCount > 0) {
                    logDivider(tag, logType);
                }
                logContent(tag, logType, message);
                logFooter(tag, logType);
                return;
            }
            if (methodCount > 0) {
                logDivider(tag, logType);
            }
            for (int i = 0; i < length; i += CHUNK_SIZE) {
                int count = Math.min(length - i, CHUNK_SIZE);
                //create a new String with system's default charset (which is UTF-8 for Android)
                logContent(tag, logType, new String(bytes, i, count));
            }
            logFooter(tag, logType);
        } else {
            logChunk(tag, logType, message);
        }

    }

    private static void logHeader(String tag, int logType) {
        logChunk(tag, logType, "╔═════════════════════════════════════════════════════════════════════════════");
    }

    private static void logHeaderContent(String tag, int logType, int methodCount) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        if (settings.showThreadInfo) {
            logChunk(tag, logType, "║ Thread: " + Thread.currentThread().getName());
            logDivider(tag, logType);
        }
        String level = "";
        for (int i = methodCount; i > 0; i--) {
            int stackIndex = i + 5;
            StringBuilder builder = new StringBuilder();
            builder.append("║ ").append(level).append(getSimpleClassName(trace[stackIndex].getClassName())).append(".")
                    .append(trace[stackIndex].getMethodName()).append(" ").append(" (")
                    .append(trace[stackIndex].getFileName()).append(":").append(trace[stackIndex].getLineNumber())
                    .append(")");
            level += "   ";
            logChunk(tag, logType, builder.toString());
        }
    }

    private static void logFooter(String tag, int logType) {
        logChunk(tag, logType, "╚═════════════════════════════════════════════════════════════════════════════");
    }

    private static void logDivider(String tag, int logType) {
        logChunk(tag, logType, "╟─────────────────────────────────────────────────────────────────────────────");
    }

    private static void logContent(String tag, int logType, String chunk) {
        String[] lines = chunk.split(System.getProperty("line.separator"));
        for (String line : lines) {
            logChunk(tag, logType, "║ " + line);
        }
    }

    private static String getTag(String tag) {
        return TextUtils.isEmpty(tag) ? TAG : tag;
    }

    private static void logChunk(String tag, int logType, String chunk) {
        switch (logType) {
            case Log.ERROR:
                Log.e(tag, chunk);
                break;
            case Log.INFO:
                Log.i(tag, chunk);
                break;
            case Log.VERBOSE:
                Log.v(tag, chunk);
                break;
            case Log.WARN:
                Log.w(tag, chunk);
                break;
            case Log.ASSERT:
                Log.wtf(tag, chunk);
                break;
            case Log.DEBUG:
                // Fall through, log debug by default
            default:
                Log.d(tag, chunk);
                break;
        }
    }

    private static String getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }

    public static class Settings {
        int methodCount = METHOD_COUNT;
        boolean showThreadInfo = SHOW_THREAD_INFO;

        public Settings hideThreadInfo() {
            showThreadInfo = false;
            return this;
        }

        public Settings setMethodCount(int methodCount) {
            validateMethodCount(methodCount);
            this.methodCount = methodCount;
            return this;
        }
    }

    private static void validateMethodCount(int methodCount) {
        if (methodCount < 0 || methodCount > 5) {
            throw new IllegalStateException("methodCount must be bigger than 0 and less than 5");
        }
    }

    public enum LogLevel {
        /**
         * Prints all logs
         */
        DEBUG,

        /**
         * No log will be printed
         */
        RELEASE
    }
}
