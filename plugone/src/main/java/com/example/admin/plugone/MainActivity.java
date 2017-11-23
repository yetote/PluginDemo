package com.example.admin.plugone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Swg
 *         2017/11/20 10:45
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button btn, matisseBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btn.setOnClickListener(this);
        matisseBtn.setOnClickListener(this);
    }

    private void initViews() {
        btn = findViewById(R.id.btn);
        matisseBtn = findViewById(R.id.matisse_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                Intent i = new Intent();
                i.setClass(that, SecondActivity.class);
                startActivity(i);
                Toast.makeText(that, "您点击击了按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.matisse_btn:
                
                break;
            default:
                return;
        }
    }
}
