package com.georgeren.savepic2local.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.georgeren.savepic2local.InitApp;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by georgeRen on 2017/9/4.
 */

public class ImageUtils {
    private static final String TAG = "ImageUtils";

    private ImageUtils() {
    }

    static {
        initImageLoader();
    }

    public ImageLoader mImageLoader = ImageLoader.getInstance();
    public static DisplayImageOptions.Builder mDisplayImageOptions = new DisplayImageOptions
            .Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(false)
            .imageScaleType(ImageScaleType.EXACTLY)
            .bitmapConfig(Bitmap.Config.RGB_565);

    public void displayImage(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url) || imageView == null) {
            return;
        }
        mImageLoader.displayImage(url, imageView, mDisplayImageOptions.build());
    }

    public static void initImageLoader() {
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration
                .Builder(InitApp.AppContext)
                .threadPoolSize(3)//1-5
                .threadPriority(Thread.NORM_PRIORITY)
                .memoryCacheExtraOptions(480, 800)
                .diskCacheExtraOptions(480, 800, null)
                .memoryCache(new WeakMemoryCache())
                .writeDebugLogs()// Remove for releaseapp
                .imageDownloader(new BaseImageDownloader(InitApp.AppContext))
                .diskCache(new UnlimitedDiskCache(StorageUtils.getOwnCacheDirectory(InitApp.AppContext, new StringBuffer()
                        .append(InitApp.AppContext.getPackageName())
                        .append(File.separator)
                        .append("/cacheImg")
                        .toString()
                )))
                .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }

    public Bitmap getBitmapFromMemoryCache(String url) {
        String pathSmall = null;
        if (mImageLoader != null) {
            List<String> memCacheKeyNameList = MemoryCacheUtils.findCacheKeysForImageUri(url, mImageLoader.getMemoryCache());
            if (memCacheKeyNameList == null) {
            } else {
                if (memCacheKeyNameList.size() > 0) {
                    for (String each : memCacheKeyNameList
                            ) {
                        if (each.startsWith(url)) {
                            if (each.startsWith(url + "_")) {
                                Bitmap bitmap = mImageLoader.getMemoryCache().get(each);
                                return bitmap;
                            } else {
                                pathSmall = each;
                            }
                        }
                    }
                    if (pathSmall != null) {
                        Bitmap bitmap = mImageLoader.getMemoryCache().get(pathSmall);
                        return bitmap;
                    }
                }
            }
            return null;
        }
        return null;
    }

    /**
     * 保存bitmap到本地
     *
     * @param bitmap
     * @param file
     * @param needRecycle
     */
    public void saveBitmap(Bitmap bitmap, File file, boolean needRecycle) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (needRecycle && bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }
        }
    }

    /**
     * 外部存储路径：com.georgeren.savepic2local
     *
     * @param cache
     * @return
     */
    public String getCachePathPub(Context context, String cache) {
        String headerDirPub;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {// 外部存储，
            headerDirPub = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.georgeren.savepic2local";
        } else {// 内部存储
            headerDirPub = context.getFilesDir().getAbsolutePath();
        }
        File filePub = new File(headerDirPub + cache);
        if (!filePub.exists()) {
            filePub.mkdirs();
        }
        return filePub.getAbsolutePath();
    }


    /**
     * 网络图片保存到相册：通知方式实现
     * 保存到：com.georgeren.savepic2local/myPic 文件夹下
     */
    public void saveImage2(Context context, String picUrl, String saveName) {
        String systemPhotoPath = ImageUtils.getInstance().getCachePathPub(context, "/myPic");
        Bitmap memoryCacheBitmap = ImageUtils.getInstance().getBitmapFromMemoryCache(picUrl);
        if (memoryCacheBitmap == null || memoryCacheBitmap.isRecycled()) {
            return;
        }
        File file = new File(systemPhotoPath, saveName);
        ImageUtils.getInstance().saveBitmap(memoryCacheBitmap, file, false);
        Uri uri = Uri.fromFile(file);
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        Log.d(TAG, "保存成功");

    }

    public static ImageUtils getInstance() {
        return SettingsUtilInstance.instance;
    }

    private static final class SettingsUtilInstance {
        private static final ImageUtils instance = new ImageUtils();
    }

    /**
     * 网络图片保存到相册：相册数据库插入
     * @param imageUrl
     * @param saveName
     */
    public void saveImage(String imageUrl, String saveName) {
        String systemPhotoPath = getSystemPhotoPath();
        Bitmap memoryCacheBitmap = ImageUtils.getInstance().getBitmapFromMemoryCache(imageUrl);
        if (memoryCacheBitmap == null || memoryCacheBitmap.isRecycled()) {
            return;
        }
        File file = new File(systemPhotoPath, saveName);
        ImageUtils.getInstance().saveBitmap(memoryCacheBitmap, file, false);
        if (file.exists()) {
            insertSystemPhotos(file);
        }
    }

    /**
     * 插入系统图库图片：必须保证文件存在，不然浪费
     *
     * @param newImgFile
     */
    public static void insertSystemPhotos(File newImgFile) {
        ContentResolver contentResolver = InitApp.AppContext.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.ImageColumns.DATA, newImgFile.getAbsolutePath());
        values.put(MediaStore.Images.ImageColumns.DATE_ADDED, Long.toString(System.currentTimeMillis()));
        values.put(MediaStore.Images.ImageColumns.DATE_MODIFIED, Long.toString(System.currentTimeMillis()));
        values.put(MediaStore.Images.ImageColumns.DATA, newImgFile.getAbsolutePath());
        values.put(MediaStore.Images.ImageColumns.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.ImageColumns.SIZE, newImgFile.length());
        int updateResult = contentResolver.update(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values, MediaStore.Images.ImageColumns.DATA + "=?",
                new String[]{newImgFile.getAbsolutePath()});
        if (updateResult > 0) {
            Log.d(TAG, "更新系统图库图片updateResult=====>" + updateResult);
        } else {
            Uri insert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Log.d(TAG, "插入系统图库图片insert=====>" + insert);
        }
    }

    /**
     * 系统相册目录
     *
     * @return
     */
    public static String getSystemPhotoPath() {
        String systemPhotoPath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            systemPhotoPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM" + File.separator + "Camera";
        }
        return systemPhotoPath;
    }
}
