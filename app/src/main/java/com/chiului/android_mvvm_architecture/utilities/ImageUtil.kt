package com.chiului.android_mvvm_architecture.utilities

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestOptions
import java.security.MessageDigest

/**
 * 图片加载工具类$
 * @author    神经大条蕾弟
 * @date      2021/02/18 16:49
 */
object ImageUtil {

    private val options: RequestOptions = RequestOptions()
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)

    fun load(context: Context, any: Any?, imageView: ImageView){
        Glide.with(context).load(any).apply(options).into(imageView)
    }

    /**
     * 将imageView里显示的图片旋转指定的角度
     * @param imageView 显示图片的imageView控件
     * @param rotateRotationAngle 旋转角度
     */
    fun rotate(context: Context, imageView: ImageView, rotateRotationAngle: Float) {
        Glide.with(context).load(imageView.drawable)
                .apply(RequestOptions.bitmapTransform(RotateTransformation(rotateRotationAngle)))
                .into(imageView)
    }

    /**
     * 图片旋转处理类
     * @param rotateRotationAngle 旋转角度
     */
    internal class RotateTransformation(var rotateRotationAngle: Float) : BitmapTransformation() {

        override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
            val matrix = Matrix()
            matrix.postRotate(rotateRotationAngle)
            return Bitmap.createBitmap(toTransform, 0, 0, toTransform.width, toTransform.height, matrix, true)
        }

        override fun updateDiskCacheKey(messageDigest: MessageDigest) {}
    }

}

fun ImageView.load(any: Any?) = ImageUtil.load(this.context, any, this)




