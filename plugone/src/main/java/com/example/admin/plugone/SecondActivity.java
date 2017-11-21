package com.example.admin.plugone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Swg
 * @date 2017/11/20 15:01
 */
public class SecondActivity extends BaseActivity {
    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();

        tv.setText("Second");
    }

    private void initViews() {
        tv = findViewById(R.id.tv);
    }
}
