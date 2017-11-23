package com.example.admin.plugone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;

import adapter.MyAdapter;

/**
 * @author Swg
 * @date 2017/11/20 15:01
 */
public class SecondActivity extends BaseActivity {
    private RecyclerView rv;
    private ArrayList<Uri> list;
    private MyAdapter adapter;
    public static final int PERMISSION_STORAGE = 1;
    public static final int MATISSE_CHOOSE_REQUEST = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    for (Uri uri : Matisse.obtainResult((Intent) msg.obj)) {
                        list.add(uri);
                    }
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    return;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();

        if (ActivityCompat.checkSelfPermission(that, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(that, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_STORAGE);
        } else {
            loadMatisse();
        }
        rv.setLayoutManager(new LinearLayoutManager(that));

        adapter = new MyAdapter(that, list);
        rv.setAdapter(adapter);

    }

    private void loadMatisse() {
        Matisse.from(that)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(9)
//                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.album_item_height))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(MATISSE_CHOOSE_REQUEST);
    }

    private void initViews() {
        rv = findViewById(R.id.rv);
        list = new ArrayList<>();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case MATISSE_CHOOSE_REQUEST:
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = data;
                    handler.sendMessage(msg);
                    break;
                default:
                    return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case PERMISSION_STORAGE:
                    loadMatisse();
                    break;
                default:
                    return;
            }
        } else {
            Toast.makeText(that, "请您同意该权限", Toast.LENGTH_SHORT).show();
        }
    }
}
