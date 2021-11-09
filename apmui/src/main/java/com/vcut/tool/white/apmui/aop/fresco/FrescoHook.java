package com.vcut.tool.white.apmui.aop.fresco;

import android.net.Uri;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.Postprocessor;

/**
 * ================================================
 * 作    者：ZhouZhengyi
 * 创建日期：2020/4/2 22:08
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class FrescoHook {

    public static Postprocessor process(Uri uri, Postprocessor postprocessor,ResizeOptions resizeOptions){
       return new FrescoLargeImageProcessor(postprocessor,uri,resizeOptions);
    }
}
