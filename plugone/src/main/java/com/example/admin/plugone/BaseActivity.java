package com.example.admin.plugone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.admin.pluginstander.PluginInterface;

/**
 * com.example.admin.plugone
 *
 * @author Swg
 * @date 2017/11/20 10:23
 */
public class BaseActivity extends Activity implements PluginInterface {
    protected Activity that;
    private static final String TAG = "BaseActivity";

    @Override
    public void setContentView(View view) {
        if (that == null) {
            super.setContentView(view);
        } else {
            that.setContentView(view);
        }

    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (that == null) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        if (that == null) {
            return super.getClassLoader();
        } else {
            return that.getClassLoader();
        }
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (that == null) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }
    }

    @Override
    public Window getWindow() {
        if (that == null) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (that == null) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();
        }
    }


    @Override
    public void onCreate(Bundle saveInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstance(Bundle onState) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void attach(Activity activity) {
        Log.e(TAG, "attach: " + activity);
        this.that = activity;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void startActivity(Intent intent) {
        Intent newIntent = new Intent();
        newIntent.putExtra("className", intent.getComponent().getClassName());
        that.startActivity(newIntent);
    }
}
