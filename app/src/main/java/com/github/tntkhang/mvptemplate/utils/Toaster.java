package com.github.tntkhang.mvptemplate.utils;

import android.content.Context;

import es.dmoral.toasty.Toasty;

public class Toaster {

    public static void success(Context context, String message) {
        Toasty.success(context, message).show();
    }

    public static void error(Context context, String message) {
        Toasty.error(context, message).show();
    }

    public static void warning(Context context, String message) {
        Toasty.warning(context, message).show();
    }
}
