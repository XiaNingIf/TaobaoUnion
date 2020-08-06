package com.jit.taobaounion.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.jit.taobaounion.R;
import com.jit.taobaounion.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.test_navigation_bar)
    RadioGroup navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        navigationBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                LogUtils.e(TestActivity.this,"checkedId---->"+checkedId);
                switch (checkedId){
                    case R.id.test_home:
                        LogUtils.e(TestActivity.this,"切换到首页");
                        break;
                    case R.id.test_selected:
                        LogUtils.e(TestActivity.this,"切换到精选");
                        break;
                    case R.id.test_red_packet:
                        LogUtils.e(TestActivity.this,"切换到特惠");
                        break;
                    case R.id.test_search:
                        LogUtils.e(TestActivity.this,"切换到搜索");
                        break;
                }
            }
        });
    }
}