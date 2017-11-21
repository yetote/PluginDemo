package com.example.admin.plugindemo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.admin.pluginstander.PluginInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * com.example.admin.plugindemo
 *
 * @author Swg
 * @date 2017/11/20 10:43
 */
public class ProxyActivity extends Activity {

    //要跳转的插件的类名
    private String className;
    private PluginInterface pluginInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra("className");

        try {
            Class activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Class[]{});

            pluginInterface = (PluginInterface) instance;

            pluginInterface.attach(this);
            Bundle bundle=new Bundle();
            pluginInterface.onCreate(bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    protected void onStart() {
        super.onStart();
        pluginInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pluginInterface.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pluginInterface.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pluginInterface.onDestroy();
    }

    @Override
    public void startActivity(Intent intent) {
        String className=intent.getStringExtra("className");
        Intent i=new Intent();
        i.setClass(this,ProxyActivity.class);
        i.putExtra("className",className);
        super.startActivity(i);
    }
}
