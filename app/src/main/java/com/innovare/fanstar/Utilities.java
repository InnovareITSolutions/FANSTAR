package com.innovare.fanstar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class Utilities {
    public static void mediaScan(Context ctx,File imgFile) {
        try
        {

            MediaScannerConnection.scanFile(ctx, new String[] { imgFile.getPath() }, new String[] { "image/jpeg" }, null);
            Log.d("Scan completed", "Scan Completed");
        }
        catch(Exception e)
        {
            Log.d("Exception", "Exception:"+e.getMessage());
        }
    }
    public static byte[]  getThumbNailImage(Context ctx,String fileName)
    {
        byte[] imageData = null;
        try
        {

            int THUMBNAIL_SIZE = 64;
            float deviceDensity=ctx.getResources().getDisplayMetrics().density;
            if(deviceDensity==0.75)
                THUMBNAIL_SIZE=24;
            else if(deviceDensity==1)
                THUMBNAIL_SIZE=32;
            else if(deviceDensity==1.5)
                THUMBNAIL_SIZE=48;
            else if(deviceDensity==2)
                THUMBNAIL_SIZE=64;
            else if(deviceDensity==3)
                THUMBNAIL_SIZE=96;
            FileInputStream fis = new FileInputStream(fileName);
            Bitmap imageBitmap = BitmapFactory.decodeStream(fis);

            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            imageData = baos.toByteArray();

        }
        catch(Exception ex) {

        }
        return imageData;
    }
    public static int dpToPx(Context ctx,int dp) {
        DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
    public static float pixelsToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px/scaledDensity;
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public static Bitmap decodeSampledBitmapFromPath(String path,
                                                     int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }
}
