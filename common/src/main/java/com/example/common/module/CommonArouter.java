package com.example.common.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class CommonArouter {

    private static CommonArouter frameArouter;

    private CommonArouter() {

    }

    public synchronized static CommonArouter getInstance(){
        if (frameArouter == null) {
            frameArouter = new CommonArouter();
        }
        return frameArouter;
    }


    //arouter
    private Map<String,Class<?>> map = new HashMap<>();
    private Class<?> displayActivity;
    private Context context;
    private Bundle bundle = null;
    private Intent intent = null;
    public void init(Context context){
        this.context = context;
    }

    public void registerPath(String path,Class<?> clazz){
        if (!map.containsKey(path)) {
            map.put(path,clazz);
        }
    }


    public CommonArouter build(String path){
        displayActivity = map.get(path);
        return this;
    }
    public CommonArouter with(Bundle bundle){
        this.bundle = bundle;
        return this;
    }
    public void navigation(){
        intent = new Intent(context, displayActivity);
        if(context instanceof Activity){
            intent.putExtra("param",bundle);
            context.startActivity(intent);
        } else{
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public void startActivityForResult(){
        intent = new Intent(context, displayActivity);
        if(context instanceof Activity){
            intent.putExtra("param",bundle);
            ((Activity) context).startActivityForResult(intent,101);
        } else{
            intent.putExtra("param",bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ((Activity) context).startActivityForResult(intent,101);
        }
    }

    public Intent getIntent() {
        return intent;
    }

    public Bundle getBundle() {
        return bundle;
    }


}
