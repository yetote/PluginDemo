package com.example.admin.plugindemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button load, click;
    public static final int PERMISSION_READ_SDCARD = 1;
    private RecyclerView rv;
    private ArrayList<String> list;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager.getInstance().setContext(this);

        initViews();

        load.setOnClickListener(this);
        click.setOnClickListener(this);

        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");

        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(this, list);
        rv.setAdapter(adapter);
    }

    private void initViews() {
        load = findViewById(R.id.load);
        click = findViewById(R.id.click);
        rv=findViewById(R.id.rv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.load:
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ_SDCARD);
                } else {
                    loadAPK();
                }
                break;
            case R.id.click:
                Intent i = new Intent();
                i.setClass(this, ProxyActivity.class);
                i.putExtra("className", PluginManager.getInstance().getEntryActivityName());
                startActivity(i);
                break;
            default:
                break;
        }
    }

    /**
     * 加载sd卡中的插件apk
     */
    private void loadAPK() {
        File file = new File("sdcard/Download","plugin.apk");
        PluginManager.getInstance().loadPath(file.getAbsolutePath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_READ_SDCARD:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadAPK();
                } else {
                    Toast.makeText(this, "该功能需要读取sd权限,请同意,否则将无法使用该功能", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(this, "未识别的权限请求码", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
