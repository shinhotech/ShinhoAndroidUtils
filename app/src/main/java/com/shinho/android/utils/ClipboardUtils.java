package com.shinho.android.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class ClipboardUtils {

    /**
     * 复制text到剪切板
     */
    public static void copy2clipboard(Context context, String text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("clip", text);
        cm.setPrimaryClip(clip);
    }

    /**
     * 获取系统剪贴板
     */
    public static CharSequence getFromClipboard(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 获取剪贴板的剪贴数据集
        ClipData clipData = clipboard.getPrimaryClip();
        if (clipData != null && clipData.getItemCount() > 0) {
            // 从数据集中获取（粘贴）第一条文本数据
            CharSequence text = clipData.getItemAt(0).getText();
            return text;
        } else {
            return "";
        }
    }

    /**
     * 获取系统剪切板管理对象
     */
    public static ClipboardManager getClipboardManager(Context context) {
        return (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }
}


