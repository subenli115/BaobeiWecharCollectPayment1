//package com.ziran.meiliao.widget;
//
//import android.app.Activity;
//import android.graphics.Bitmap;
//import android.graphics.drawable.Drawable;
//import android.widget.ImageView;
//
//import androidx.annotation.DrawableRes;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.resource.drawable.GlideDrawable;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.animation.GlideAnimation;
//import com.bumptech.glide.request.target.SimpleTarget;
//import com.bumptech.glide.request.target.Target;
//
//import cn.bingoogolapple.baseadapter.BGABaseAdapterUtil;
//import cn.bingoogolapple.photopicker.imageloader.BGAImageLoader;
//
//public class BGAGlideImageLoader3 extends BGAImageLoader {
//
//    @Override
//    public void display(final ImageView imageView, String path, @DrawableRes int loadingResId, @DrawableRes int failResId, int width, int height, final DisplayDelegate delegate) {
//        final String finalPath = getPath(path);
//        Activity activity = getActivity(imageView);
//        Glide.with(activity).load(finalPath).placeholder(loadingResId).error(failResId).override(width, height).dontAnimate().listener(new RequestListener<String, GlideDrawable>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                if (delegate != null) {
//                    delegate.onSuccess(imageView, finalPath);
//                }
//                return false;
//            }
//        }).into(imageView);
//    }
//
//    @Override
//    public void download(String path, final DownloadDelegate delegate) {
//        final String finalPath = getPath(path);
//        Glide.with(BGABaseAdapterUtil.getApp()).load(finalPath).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                if (delegate != null) {
//                    delegate.onSuccess(finalPath, resource);
//                }
//            }
//
//            @Override
//            public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                if (delegate != null) {
//                    delegate.onFailed(finalPath);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void pause(Activity activity) {
//        Glide.with(activity).pauseRequests();
//    }
//
//    @Override
//    public void resume(Activity activity) {
//        Glide.with(activity).resumeRequestsRecursive();
//    }
//}