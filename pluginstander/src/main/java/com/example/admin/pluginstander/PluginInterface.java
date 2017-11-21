package com.example.admin.pluginstander;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * com.example.admin.pluginstander
 *
 * @author Swg
 * @date 2017/11/20 10:17
 */
public interface PluginInterface {
    public void onCreate(Bundle saveInstanceState);

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void onSaveInstance(Bundle onState);

    public boolean onTouchEvent(MotionEvent event);

    public void onBackPressed();

    public void attach(Activity activity);
}
