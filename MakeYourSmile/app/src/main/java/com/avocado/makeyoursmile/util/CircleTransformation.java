package com.avocado.makeyoursmile.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class CircleTransformation extends BitmapTransformation {

    public static final int PAINT_FLAGS = Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG;

    public CircleTransformation(Context context) {
        super(context);
    }

    private static Bitmap.Config getSafeConfig(Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap toReuse = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        Bitmap transformed = circleCrop(toReuse, toTransform, outWidth, outHeight);
        if (toReuse != null && toReuse != transformed && !pool.put(toReuse)) {
            toReuse.recycle();
        }
        return transformed;
    }

    private Bitmap circleCrop(Bitmap recycled, Bitmap toCrop, int width, int height) {
        if (toCrop == null) {
            return null;
        }

        final float scale;
        float dx = 0, dy = 0;
        final float r;
        Matrix m = new Matrix();
        if (toCrop.getWidth() * height > width * toCrop.getHeight()) {
            scale = (float) height / (float) toCrop.getHeight();
            dx = (width - toCrop.getWidth() * scale) * 0.5f;
            r = toCrop.getHeight() / 2;
        } else {
            scale = (float) width / (float) toCrop.getWidth();
            dy = (height - toCrop.getHeight() * scale) * 0.5f;
            r = toCrop.getWidth() / 2;
        }

        m.setScale(scale, scale);
        m.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
        final Bitmap result;
        if (recycled != null) {
            result = recycled;
        } else {
            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        canvas.setMatrix(m);
        Paint paint = new Paint(PAINT_FLAGS);
        paint.setShader(new BitmapShader(toCrop, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawCircle(toCrop.getWidth() / 2, toCrop.getHeight() / 2, r, paint);

        return result;
    }

    @Override
    public String getId() {
        return "CircleTransformation()";
    }

}
