package com.example.ekta.myexampleprojects.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.ekta.myexampleprojects.ApplicationScope;
import com.example.ekta.myexampleprojects.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ekta on 21/12/16.
 */

@ApplicationScope
public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();
    private static FileUtils sInstance;
    private Context mContext;

    public FileUtils(Context context) {
        mContext = context;
    }

    /**
     * This is used to store the images in device internal directory path.
     *
     * @param bitmap      The bitmap to be saved
     * @param filename    Name of the file
     * @param storageType StorageType value in terms of internal cache directory, internal private
     *                    directory, external private directory or external public directory.
     * @param imageType   ImageType enum value.
     * @return boolean value indicating whether image saved or not.
     */
    public boolean saveImage(Bitmap bitmap,
                             String filename, StorageType storageType, ImageType imageType) {
        if (!isExternalStorageWritable()) {

            return false;
        }
        try {

            filename = filename + ".jpg";
            File imageFile = new File(getStorageDir(storageType), filename);
            FileOutputStream stream = new FileOutputStream(imageFile);
            if (null != bitmap) {
                bitmap.compress(getImageCompressFormat(imageType), 100, stream);
            }
            stream.flush();
            stream.close();


            return true;
        } catch (FileNotFoundException ex) {
            Log.e(TAG, "saveImage: filenotfound");
            ex.printStackTrace();

        } catch (IOException ex) {
            Log.e(TAG, "saveImage: ioexception");
            ex.printStackTrace();
        }
        return false;

    }

    public Bitmap loadImageFromStorage(String path, String imageName) {

        Bitmap bitmap = null;

        File file = new File(path, imageName);
        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath().trim());

        return bitmap;

    }

    /**
     * Checks if external storage is available for read and write
     */
    public boolean isExternalStorageWritable() {
        if (Environment.MEDIA_MOUNTED.equals(
                Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    public void deleteFile(File uri) {
        File fdelete = new File(uri.getPath());
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                Log.e(TAG, "file Deleted :" + uri.getPath());
            } else {
                Log.e(TAG, "file not Deleted :" + uri.getPath());
            }
        }
    }

    /**
     * Used to save images in either internal or external storage directory specified by StorageType
     *
     * @param storageType Enum value specifying directory to save the image.
     * @return - the directory that holds the image to be saved.
     */
    public File getStorageDir(StorageType storageType) {
        File file = null;
        if (mContext == null) {
            return file;
        }
        switch (storageType) {
            case INTERNAL_CACHE:
                // Files will be saved in path /data/data/<package>/cache/<app name>/
                file = new File(mContext.getCacheDir(), mContext.getString(R.string.app_name));
                break;
            case INTERNAL_PRIVATE:
                // Files will be saved in path /data/data/<package>/files/<app name>/
                file = new File(mContext.getFilesDir(), mContext.getString(R.string.app_name));
                break;
            case EXTERNAL_PRIVATE:
                // Files will be saved in path
                // /sdcard/Android/data/<package>/files/pictures/<app name>/
                file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                        mContext.getString(R.string.app_name));
                break;
            case EXTERNAL_PRIVATE_TEMP:
                // Files will be saved in path
                // /sdcard/Android/data/<package>/files/pictures/<app name>/temp/
                file = new File(mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                        mContext.getString(R.string.app_name) + "/temp");
                break;
            case EXTERNAL_PUBLIC:
                // Files will be saved in path /sdcard/
                file = new File(Environment.getExternalStorageDirectory(),
                        mContext.getString(R.string.app_name));
                break;
            default:
                new File(Environment.getExternalStorageDirectory(),
                        mContext.getString(R.string.app_name));
        }
        if (file != null && !file.exists() && !file.mkdirs()) {
            Log.d(TAG, "Directory not created");
        }
        return file;
    }

    public Bitmap.CompressFormat getImageCompressFormat(ImageType imageType) {
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
        switch (imageType) {
            case TYPE_JPEG:
                compressFormat = Bitmap.CompressFormat.JPEG;
                break;
            case TYPE_PNG:
                compressFormat = Bitmap.CompressFormat.PNG;

        }
        return compressFormat;
    }

    private void saveToInternalStorage(Bitmap bitmapImage) {
        ContextWrapper cw = new ContextWrapper(mContext);
// path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
// Create imageDir
        File mypath = new File(directory, "profile.jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
// Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public enum StorageType {
        INTERNAL_CACHE,
        INTERNAL_PRIVATE,
        EXTERNAL_PRIVATE,
        EXTERNAL_PRIVATE_TEMP,
        EXTERNAL_PUBLIC
    }


    public enum ImageType {
        TYPE_JPEG,
        TYPE_PNG,
        TYPE_GIF
    }

}
