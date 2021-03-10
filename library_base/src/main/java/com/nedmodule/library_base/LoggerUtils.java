package com.nedmodule.library_base;

import android.util.Log;
import android.util.Printer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;


public class LoggerUtils implements Printer{
    private static final String LOG_PREFIX = "u_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    public static boolean LOGGING_ENABLED = true;//!BuildConfig.BUILD_TYPE.equalsIgnoreCase("release");

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }


    @NonNull
    private static String makeLogTag(@Nullable StackTraceElement caller) {
        if (caller == null) {
            return makeLogTag("service");
        }
        String className = caller.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1);
        return String.format(Locale.CHINA, "%s.%s(L:%d)",
                className,
                caller.getMethodName(),
                caller.getLineNumber());
    }


    /**
     * Don't use this when obfuscating class names!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void LOGD(final String tag, String message) {
        if (LOGGING_ENABLED) {
            if (Log.isLoggable(tag, Log.DEBUG)) {
                Log.d(tag, message);
            }
        }
    }

    public static void LOGD(final String tag, String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            if (Log.isLoggable(tag, Log.DEBUG)) {
                Log.d(tag, message, cause);
            }
        }
    }

    public static void LOGV(final String tag, String message) {
        if (LOGGING_ENABLED) {
            if (Log.isLoggable(tag, Log.VERBOSE)) {
                Log.v(tag, message);
            }
        }
    }

    public static void LOGV(final String tag, String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            if (Log.isLoggable(tag, Log.VERBOSE)) {
                Log.v(tag, message, cause);
            }
        }
    }

    public static void LOGI(final String tag, String message) {
        if (LOGGING_ENABLED) {
            Log.i(tag, message);
        }
    }

    public static void LOGI(final String tag, String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            Log.i(tag, message, cause);
        }
    }

    public static void LOGW(final String tag, String message) {
        Log.w(tag, message);
    }

    public static void LOGW(final String tag, String message, Throwable cause) {
        Log.w(tag, message, cause);
    }

    public static void LOGE(final String tag, String message) {
        Log.e(tag, message);
    }

    public static void LOGE(String message, Throwable cause) {
        String tag = makeLogTag(getCallerStackTraceElement());
        Log.e(tag, message, cause);
    }

    public static void LOGE(Throwable cause) {
        String tag = makeLogTag(getCallerStackTraceElement());
        Log.e(tag, "", cause);
    }

    public static void LOGD(String message) {
        if (LOGGING_ENABLED) {
            String tag = makeLogTag(getCallerStackTraceElement());
            Log.d(tag, message);
        }
    }

    public static void LOGV(String message) {
        if (LOGGING_ENABLED) {
            String tag = makeLogTag(getCallerStackTraceElement());
            Log.v(tag, message);
        }
    }

    public static void LOGI(String message) {
        if (LOGGING_ENABLED) {
            String tag = makeLogTag(getCallerStackTraceElement());
            Log.i(tag, message);
        }
    }

    public static void LOGE(String message) {
        String tag = makeLogTag(getCallerStackTraceElement());
        Log.e(tag, message);
    }

    public static void LOGW(String message) {
        String tag = makeLogTag(getCallerStackTraceElement());
        Log.w(tag, message);
    }


    public LoggerUtils() {
    }

    @Override
    public void println(String x) {
        LOGD(x);
    }
}
