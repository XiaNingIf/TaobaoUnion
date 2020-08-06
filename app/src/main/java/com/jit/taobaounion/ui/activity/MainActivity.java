package com.jit.taobaounion.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jit.taobaounion.R;
import com.jit.taobaounion.base.BaseFragment;
import com.jit.taobaounion.ui.fragment.HomeFragment;
import com.jit.taobaounion.ui.fragment.RedPacketFragment;
import com.jit.taobaounion.ui.fragment.SearchFragment;
import com.jit.taobaounion.ui.fragment.SelectedFragment;
import com.jit.taobaounion.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_navigation_bar)
    BottomNavigationView mNavigationView;
    private String TAG = "MainActivity";
    private HomeFragment mHomeFragment;
    private RedPacketFragment mRedPacketFragment;
    private SelectedFragment mSelectedFragment;
    private SearchFragment mSearchFragment;
    private FragmentManager mFm;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        initFragment();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mRedPacketFragment = new RedPacketFragment();
        mSelectedFragment = new SelectedFragment();
        mSearchFragment = new SearchFragment();
        mFm = getSupportFragmentManager();
        switchFragment(mHomeFragment);
    }

    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        LogUtils.e(MainActivity.this,"切换到首页");
                        switchFragment(mHomeFragment);
                        break;
                    case R.id.selected:
                        LogUtils.e(MainActivity.this,"切换到精选");
                        switchFragment(mSelectedFragment);
                        break;
                    case R.id.red_packet:
                        LogUtils.e(MainActivity.this,"切换到特惠");
                        switchFragment(mRedPacketFragment);
                        break;
                    case R.id.search:
                        LogUtils.e(MainActivity.this,"切换到搜索");
                        switchFragment(mSearchFragment);
                        break;
                }
                return true;
            }
        });
    }

    private void switchFragment(BaseFragment targetFragment) {
        FragmentTransaction fragmentTransaction = mFm.beginTransaction();
        fragmentTransaction.replace(R.id.main_page_container,targetFragment);
        fragmentTransaction.commit();
    }

}