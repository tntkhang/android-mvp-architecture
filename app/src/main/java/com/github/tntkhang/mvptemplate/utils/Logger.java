package com.github.tntkhang.mvptemplate.utils;

import android.util.Log;

import com.github.tntkhang.mvptemplate.BaseApplication;
import com.github.tntkhang.mvptemplate.BuildConfig;
import com.github.tntkhang.mvptemplate.R;

public class Logger {

    private static final int STACK_TRACE_LEVELS_UP = 5;

    private static final String GENERAL_TAG = BaseApplication.getApplication().getString(R.string.app_name);

    public static void v(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, getClassNameMethodNameAndLineNumber() + message);
        }
    }
    public static void d(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, getClassNameMethodNameAndLineNumber() + message);
        }
    }
    public static void e(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, getClassNameMethodNameAndLineNumber() + message);
        }
    }
    public static void i(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, getClassNameMethodNameAndLineNumber() + message);
        }
    }
    public static void w(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, getClassNameMethodNameAndLineNumber() + message);
        }
    }

    public static void v(String message) {
        if (BuildConfig.DEBUG) {
            Log.v(GENERAL_TAG, getClassNameMethodNameAndLineNumber() + message);
        }
    }
    public static void d(String message) {
        if (BuildConfig.DEBUG) {
            Log.d(GENERAL_TAG, getClassNameMethodNameAndLineNumber() + message);
        }
    }
    public static void e(String message) {
        if (BuildConfig.DEBUG) {
            Log.e(GENERAL_TAG, getClassNameMethodNameAndLineNumber() + message);
        }
    }
    public static void i(String message) {
        if (BuildConfig.DEBUG) {
            Log.i(GENERAL_TAG, getClassNameMethodNameAndLineNumber() + message);
        }
    }
    public static void w(String message) {
        if (BuildConfig.DEBUG) {
            Log.w(GENERAL_TAG, getClassNameMethodNameAndLineNumber() + message);
        }
    }

    /**
     * Get the current line number. Note, this will only work as called from
     * this class as it has to go a predetermined number of steps up the stack
     * trace. In this case 5.
     *
     * @author kvarela
     * @return int - Current line number.
     */
    private static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getLineNumber();
    }

    /**
     * Get the current class name. Note, this will only work as called from this
     * class as it has to go a predetermined number of steps up the stack trace.
     * In this case 5.
     *
     * @author kvarela
     * @return String - Current line number.
     */
    private static String getClassName() {
        String fileName = Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName();

        // kvarela: Removing ".java" and returning class name
        return fileName.substring(0, fileName.length() - 5);
    }

    /**
     * Get the current method name. Note, this will only work as called from
     * this class as it has to go a predetermined number of steps up the stack
     * trace. In this case 5.
     *
     * @author kvarela
     * @return String - Current line number.
     */
    private static String getMethodName() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName();
    }

    /**
     * Returns the class name, method name, and line number from the currently
     * executing log call in the form .()-
     *
     * @author kvarela
     * @return String - String representing class name, method name, and line
     *         number.
     */
    private static String getClassNameMethodNameAndLineNumber() {
        return "[" + getClassName() + "." + getMethodName() + "():" + getLineNumber() + "]: ";
    }
}