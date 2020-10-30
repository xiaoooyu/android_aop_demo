package com.xiaoooyu.demo.trace;

import android.util.Log;

/**
 * Copyright (c) 2020 Teambition All Rights Reserved.
 */
public class DebugLog {
    private DebugLog(){}

    public static void log(String tag, String message) {
        Log.d(tag, message);
    }
}
