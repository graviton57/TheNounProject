package com.havrylyuk.thenounproject.utils;

import android.net.Uri;

import com.facebook.common.util.UriUtil;

/**
 * Created by Igor Havrylyuk on 01.06.2017.
 */

public final class ImageUtils {

   public static Uri getFrescoUri(int resId){
       return new Uri.Builder()
               .scheme(UriUtil.LOCAL_RESOURCE_SCHEME) // "res"
               .path(String.valueOf(resId))
               .build();
   }


}
