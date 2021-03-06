package com.zhanghao.gankio.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zhanghao.gankio.entity.GankContent;
import com.zhanghao.gankio.entity.GankSection;
import com.zhanghao.gankio.ui.activity.DetailActivity;
import com.zhanghao.gankio.ui.activity.FavActivity;
import com.zhanghao.gankio.ui.activity.GankTypeListActivity;
import com.zhanghao.gankio.ui.activity.MainActivity;
import com.zhanghao.gankio.ui.activity.PhotoActivity;
import com.zhanghao.gankio.ui.activity.SearchActivity;
import com.zhanghao.gankio.ui.activity.SettingActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhanghao on 2017/3/17.
 */

public class ActivityUtil {
    private static final String TAG = "ActivityUtil";


    public static void gotoMainActivity(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public static void gotoSettingActivity(Context context){
        Intent intent=new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
    public static void gotoDetailActivity(Context context,String url,String title){
        Intent intent=new Intent(context, DetailActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    public static void gotoTypeListActivity(Context context,String type){
        Intent intent=new Intent(context, GankTypeListActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    public static void gotoPhotoActivity(Context context,List<MultiItemEntity> list,int pos){
        ArrayList<String> urls=new ArrayList<>();
        list.stream().filter(itemEntity -> itemEntity instanceof GankContent).forEach(itemEntity -> {
            GankContent content = (GankContent) itemEntity;
            urls.add(content.getUrl());
        });
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putStringArrayListExtra("urls",urls);
        intent.putExtra("position",pos);
        context.startActivity(intent);
    }




    public static void gotoPhotoActivityH(Context context,List<GankSection> list,
                                          List<MultiItemEntity> itemEntities,int pos){
        ArrayList<String> urls=new ArrayList<>();
        GankSection section= (GankSection) itemEntities.get(pos);
        int realPos=list.indexOf(section);
        LogUtil.d(TAG,realPos);
        urls.addAll(list.stream().map(gankSection -> gankSection.getContent().getUrl()).collect(Collectors.toList()));
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putStringArrayListExtra("urls",urls);
        intent.putExtra("position",realPos);
        context.startActivity(intent);
    }

    public static void gotoFavActivity(Context context) {
        context.startActivity(new Intent(context,FavActivity.class));
    }

    public static void gotoSearchActivity(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    public static void gotoSetting(Context context) {
        Uri uri=Uri.parse("package:" + context.getPackageName());
        context.startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,uri));
    }
}
