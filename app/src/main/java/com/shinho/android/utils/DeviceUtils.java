package com.shinho.android.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 设备工具类
 */
public class DeviceUtils {

    public static class ScreenState {
        public interface IOnScreenOff {
            void onScreenOff();
        }

        public ScreenState(final Context context, final IOnScreenOff onScreenOffInterface) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);

            context.registerReceiver(new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent in) {
                    String action = in == null ? "" : in.getAction();
                    Log.i("DDD", "ScreenReceiver: action = " + action);
                    if (Intent.ACTION_SCREEN_OFF.equals(action)) {
                        if (onScreenOffInterface != null) {
                            onScreenOffInterface.onScreenOff();
                        }
                    }
                    context.unregisterReceiver(this);
                }
            }, filter);
        }
    }

    /**
     * 保存Bitmap到指定绝对路径和图库
     * @param context
     * @param absolutePath
     * @param bmp
     * @return
     */
    public static boolean saveImage(Context context, String absolutePath, Bitmap bmp) {
        // 首先保存图片
        File file = new File(absolutePath);
        File appDir = file.getParentFile();
        if (!appDir.exists()) {
            boolean mkdir = appDir.mkdir();
            if(!mkdir) return false;
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            // 其次把文件插入到系统图库
            String result = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    absolutePath, file.getName(), null);
            if(result == null) return false;
        } catch (IOException e) {
            return false;
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + absolutePath)));
        return true;
    }

    /**
     * 获取简单包名
     */
    public static String getSimplePackageName(Context context) {
        String packageName = context.getPackageName();
        return packageName.substring(packageName.lastIndexOf("."));
    }

    /**
     * 调用第三方浏览器打开
     *
     */
    public static void openBrowser(Context context, String url, String chooseTip, String errorTip) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(intent, chooseTip));
        } else {
//            ToastUtils.showToast(context, errorTip);
        }
    }

    /**
     * 获取版本名称
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 获取版本号 100
     */
    public static int getAppVersionCode(Context context) {
        int versioncode = -1;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode;
    }


    /**
     *  获取IMEI号
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 显示软键盘
     */
    public static void showSoftInput(EditText et) {
        InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm == null ) return;
        imm.showSoftInput(et, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInput(EditText et) {
        InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm == null ) return;
        imm.hideSoftInputFromWindow(et.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    /**
     * 隐藏软键盘
     *
     * @param activity 对应的activity
     */
    public static void hideSoftInput(Activity activity) {
        if(activity == null) return;
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity
                    .INPUT_METHOD_SERVICE);
            if(imm == null ) return;
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     */
    public static void showSoftInput(@NonNull Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity
                    .INPUT_METHOD_SERVICE);
            if(imm == null ) return;
            imm.showSoftInput(activity.getCurrentFocus(), 0);
        }
    }



    /**
     * 安装文件
     */
    public static void promptInstall(Context context, Uri data) {
        Intent promptInstall = new Intent(Intent.ACTION_VIEW)
                .setDataAndType(data, "application/vnd.android.package-archive");
        // FLAG_ACTIVITY_NEW_TASK 可以保证安装成功时可以正常打开 app
        promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(promptInstall);
    }


}
