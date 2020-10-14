package com.shinho.android.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import androidx.core.content.FileProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;


/**
 * 文件工具类
 */
public class FileUtils {
    private static final String MIME_TYPE_APK = "application/vnd.android.package-archive";
    private static final String FILE_MARK_NAME_MERGE = "merge";
    private static final String FILE_MARK_NAME = "markName=";

    /**
     * 读取asset文件内容
     */
    public static String getAssetsFileString(String fileName,Context context) throws IOException {
        AssetManager manager = context.getAssets();

        InputStream is = manager.open(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }

        String json = builder.toString();
        if (TextUtils.isEmpty(json)) {
            throw new IOException("no file");
        }

        return json;
    }

    /**
     * 获取App根目录
     */
    public static File getAppDir() {
        if (!hasSdcard()) return null;
        File file = new File(Environment.getExternalStorageDirectory(), "archex");
        if (!file.exists()) {
            boolean success = file.mkdirs();
            if (!success) return null;
        }
        return file;
    }

    /**
     * 获取App上传目录
     */
    public static File getUploadDir() {
        File dir = getAppDir();
        if (dir == null) return null;
        File file = new File(dir, "upload");
        if (!file.exists()) {
            boolean success = file.mkdirs();
            if (!success) return null;
        }
        return file;
    }

    /**
     * 产生一个唯一的上传图片名
     */
    public static String createUploadPhotoName() {
        return "upload_" + System.currentTimeMillis() + IdProducer.create() + ".jpg";
    }

    /**
     * 产生一个唯一的图片名
     */
    public static String createPhotoName() {
        return System.currentTimeMillis() + IdProducer.create() + ".jpg";
    }

    /**
     * 产生一个唯一的上传拼接的图片名
     */
    public static String createUploadMergePhotoName(String name) {
        return "upload_" + System.currentTimeMillis() + IdProducer.create() + FILE_MARK_NAME + FILE_MARK_NAME_MERGE + "_" + name + ".jpg";
    }

    /**
     * 获取App下载目录
     */
    public static File getDownloadDir() {
        File dir = getAppDir();
        if (dir == null) return null;
        File file = new File(dir, "download");
        if (!file.exists()) {
            boolean success = file.mkdirs();
            if (!success) return null;
        }
        return file;
    }


    /**
     * 是否有SD卡
     */
    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 删除某个文件夹下满足过滤条件的文件
     * @param dir
     * @param filenameFilter
     */
    public static void delete(File dir, FilenameFilter filenameFilter) {
        if (dir == null) return;
        File[] files = dir.listFiles(filenameFilter);
        if (files == null) return;
        for (File file : files) {
            file.delete();
        }
    }

    /**
     * 获取某个文件不含后缀的名字
     */
    public static String getFileNameWithoutExtension(String filename) {
        return filename.substring(0, filename.lastIndexOf("."));
    }


    /**
     * 将InputStream写入文件
     */
    public static File writeToDisk(InputStream inputStream, File file) throws IOException {
        OutputStream outputStream = null;
        try {
            byte[] fileReader = new byte[4096];
            long fileSizeDownloaded = 0;
            outputStream = new FileOutputStream(file);
            while (true) {
                int read = inputStream.read(fileReader);

                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
                fileSizeDownloaded += read;
            }
            outputStream.flush();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return file;
    }


    /**
     * 安装某个apk文件到手机
     */
    public static void installApk(File apkFile,Context context) {
        Intent installApkIntent = new Intent();
        installApkIntent.setAction(Intent.ACTION_VIEW);
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
        installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            Uri uri = FileProvider.getUriForFile(context, context.getPackageName() +
                    ".file.provider", apkFile);
            installApkIntent.setDataAndType(uri, MIME_TYPE_APK);
            installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            installApkIntent.setDataAndType(Uri.fromFile(apkFile), MIME_TYPE_APK);
        }

        if (context.getPackageManager().queryIntentActivities(installApkIntent, 0).size() > 0) {
            context.startActivity(installApkIntent);
        }
    }

    /**
     * 判断asset里是否有某个文件
     */
    public static boolean hasFileInAsset(Context context, String filePath) {
        AssetManager am = context.getAssets();
        String folder;
        String file;
        int divisionIndex = filePath.lastIndexOf("/");
        if (divisionIndex == -1 || divisionIndex == filePath.length() - 1) {
            folder = "";
            file = filePath;
        } else {
            folder = filePath.substring(0, divisionIndex);
            file = filePath.substring(divisionIndex + 1);
        }
        try {
            return Arrays.asList(am.list(folder)).contains(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将文件转化为Base64
     */
    public static String fileToBase64(String path) throws Exception {
        InputStream in = new FileInputStream(path);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        in.close();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * 将Base64转化为文件
     */
    public static void Base64ToFile(String base64, String path) throws Exception {
        byte[] bytes = Base64.decode(base64, Base64.DEFAULT);
        FileOutputStream out = new FileOutputStream(path);
        out.write(bytes);
        out.close();
    }

    /**
     * 按照指定名字将Bitmap保存为图片
     */
    public static File saveBitmapIntoJPG(Context context, Bitmap bitmap, String name) throws FileNotFoundException {
        File file = new File(getImageDir(context), name);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
        return file;
    }

    /**
     * 将多张图横向拼接为一张Bitmap
     */
    public static Bitmap stitchBitmap(List<String> pathList) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int width = 0;
        int height = 0;
        for (String path : pathList) {
            BitmapFactory.decodeFile(path, options);
            width += options.outWidth;
            height = Math.max(height, options.outHeight);
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        int drawnWidth = 0;
        for (String path : pathList) {
            Bitmap srcBitmap = BitmapFactory.decodeFile(path);
            canvas.drawBitmap(srcBitmap, drawnWidth, 0, null);
            drawnWidth += srcBitmap.getWidth();
        }
        return bitmap;
    }

    /**
     * 将多张图竖向拼接为一张Bitmap
     */
    public static Bitmap stitchBitmapVertical(List<String> pathList) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int width;
        int height;
        int maxWidth = 0;
        int maxHeight = 0;

        for (String path : pathList) {
            BitmapFactory.decodeFile(path, options);
            maxWidth = Math.max(maxWidth, options.outWidth);
            maxHeight = Math.max(maxHeight, options.outHeight);
        }

        height = maxHeight * 2;
        width = maxWidth * ((pathList.size() + 1) / 2);
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        for (int i = 0; i < pathList.size(); i++) {
            Bitmap srcBitmap = BitmapFactory.decodeFile(pathList.get(i));
            int n = i / 2;
            canvas.drawBitmap(srcBitmap, maxWidth * n, maxHeight * (i % 2), null);
        }
        return bitmap;
    }


    /**
     * 获取存放图片的文件夹
     */
    public static File getImageDir(Context context) {
        return context.getExternalFilesDir("images");
    }



}
