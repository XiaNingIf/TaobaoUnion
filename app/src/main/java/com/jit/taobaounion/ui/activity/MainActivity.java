package com.jit.taobaounion.ui.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jit.taobaounion.R;
import com.jit.taobaounion.base.BaseActivity;
import com.jit.taobaounion.base.BaseFragment;
import com.jit.taobaounion.ui.fragment.HomeFragment;
import com.jit.taobaounion.ui.fragment.OnSellFragment;
import com.jit.taobaounion.ui.fragment.SearchFragment;
import com.jit.taobaounion.ui.fragment.SelectedFragment;
import com.jit.taobaounion.utils.LogUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IMainActivity{

    @BindView(R.id.main_navigation_bar)
    BottomNavigationView mNavigationView;
    private HomeFragment mHomeFragment;
    private OnSellFragment mRedPacketFragment;
    private SelectedFragment mSelectedFragment;
    private SearchFragment mSearchFragment;
    private FragmentManager mFm;

    @Override
    protected void initPresenter() {

    }

    public void switch2Search(){
        //switchFragment(mSearchFragment);
        //切换导航栏中的选项
        mNavigationView.setSelectedItemId(R.id.search);
    }

    @Override
    protected void initEvent() {
        initListener();
    }

    @Override
    protected void initView() {
        initFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mRedPacketFragment = new OnSellFragment();
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

    private BaseFragment lastOneFragment = null;

    private void switchFragment(BaseFragment targetFragment) {
        //如果上一个fragment跟当前要切换的fragment是同一个，那么不需要切换
        if(lastOneFragment == targetFragment) {
            return;
        }
        //修改成add和hide的方式来控制fragment的切换
        FragmentTransaction fragmentTransaction = mFm.beginTransaction();
        if (!targetFragment.isAdded()) {
            fragmentTransaction.add(R.id.main_page_container,targetFragment);
        }else{
            fragmentTransaction.show(targetFragment);
        }
        if (lastOneFragment != null) {
            fragmentTransaction.hide(lastOneFragment);
        }
        lastOneFragment = targetFragment;
        //fragmentTransaction.replace(R.id.main_page_container,targetFragment);
        fragmentTransaction.commit();
    }
}