package com.georgeren.drawer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.georgeren.drawer.base.BaseActivity;
import com.georgeren.drawer.util.SettingUtil;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * https://www.diycode.cc/topics/269
 *
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{
    private NavigationView navigationView;
    private SwitchCompat switchInput;
    private DrawerLayout drawerLayout;
    private Observable<Boolean> observable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // rxBus注册，订阅事件
        observable = RxBus.getInstance().register(Boolean.class);
        observable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isNightMode) throws Exception {
                showAnimation();
                refreshUI();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar, false, null);

        // 初始化NavigationView，设置item选择监听
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // 初始化开关：白天\夜晚
        initSwitchInput();
    }

    /**
     * 初始化 白天\夜晚 主题开关
     */
    private void initSwitchInput(){
        switchInput = navigationView.getMenu().findItem(R.id.app_bar_switch).getActionView().findViewById(R.id.switch_input);
        switchInput.setChecked(SettingUtil.getInstance().getIsNightMode());// 初始化当前 开\关 模式
        setUpSwitch();// 初始化开关颜色（主题色）
        switchInput.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {// 开关操作监听
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isNightMode) {
                SettingUtil.getInstance().setIsNightMode(isNightMode);// 保存改变后的状态
                setUpSwitch();// 跟新开关颜色（主题色）
                if (isNightMode) {// 更新主题
                    setTheme(R.style.DarkTheme);
                } else {
                    setTheme(R.style.LightTheme);
                }
                RxBus.getInstance().post(isNightMode);// 发送事件：白天\夜晚 模式改变
            }
        });

    }


    /**
     * 获取view转bitmap
     * @param view
     * @return
     */
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnable = true;
        view.setDrawingCacheEnabled(drawingCacheEnable);// 提高绘图速度
        view.buildDrawingCache(drawingCacheEnable);
        final Bitmap drawingCache = view.getDrawingCache();// 获取缓存bitmap
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);// 从原位图src复制出一个新的位图，和原始位图相同
            view.setDrawingCacheEnabled(false);// 关闭缓存
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    /**
     * 顶级视图过度动画：旧顶级视图生成bitmap，添加view（以bitmap作为背景）view覆盖到顶级视图上，
     * view位于最上层顶级视图位于最下层，中间层是内容部分，对view执行渐变动画（alpha：1--》0），动画结束后移除view
     */
    private void showAnimation() {
        final View decorview = getWindow().getDecorView();// 顶级视图
        Bitmap cacheBitmap = getCacheBitmapFromView(decorview);// 顶级视图生成bitmap
        if (decorview instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackground(new BitmapDrawable((getResources()), cacheBitmap));
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorview).addView(view, layoutParams);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorview).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }

    /**
     * 刷新 navigationView 背景色 item背景色 text字体色 icon着色
     */
    protected void refreshUI() {
        Resources.Theme theme = getTheme();
        TypedValue rootViewBackground = new TypedValue();// 背景色
        TypedValue textColorPrimary = new TypedValue();// 字体色
        theme.resolveAttribute(R.attr.rootViewBackground, rootViewBackground, true);
        theme.resolveAttribute(R.attr.textColorPrimary, textColorPrimary, true);
        Resources resources = getResources();
        navigationView.setBackgroundResource(rootViewBackground.resourceId);// navigationView 背景色
        navigationView.setItemBackgroundResource(rootViewBackground.resourceId);// item 背景色
        navigationView.setItemTextColor(resources.getColorStateList(textColorPrimary.resourceId));// text字体色
        navigationView.setItemIconTintList(resources.getColorStateList(textColorPrimary.resourceId));// icon着色
    }

    /**
     * 对开关着色（主题色） 白天\夜晚
     */
    private void setUpSwitch() {
        boolean isNightMode = SettingUtil.getInstance().getIsNightMode();
        if (isNightMode) {
            switchInput.setThumbTintList(ColorStateList.valueOf(SettingUtil.getInstance().getColor()));
        } else {
            Resources.Theme theme = getTheme();
            Resources resources = getResources();
            TypedValue textColorPrimary = new TypedValue();
            theme.resolveAttribute(R.attr.textColorPrimary, textColorPrimary, true);
            switchInput.setThumbTintList(resources.getColorStateList(textColorPrimary.resourceId));
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_product) {
//            replaceFragment(ZhuanlanPresenter.TYPE_PRODUCT);

        } else if (id == R.id.nav_life) {
//            replaceFragment(ZhuanlanPresenter.TYPE_LIFE);

        } else if (id == R.id.nav_music) {
//            replaceFragment(ZhuanlanPresenter.TYPE_MUSIC);

        } else if (id == R.id.nav_emotion) {
//            replaceFragment(ZhuanlanPresenter.TYPE_EMOTION);

        } else if (id == R.id.nav_profession) {
//            replaceFragment(ZhuanlanPresenter.TYPE_FINANCE);

        } else if (id == R.id.nav_zhihu) {
//            replaceFragment(ZhuanlanPresenter.TYPE_ZHIHU);

        } else if (id == R.id.nav_user_add) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new UserAddView()).commit();

        } else if (id == R.id.nav_color_chooser) {
//            createColorChooserDialog();

        } else if (id == R.id.nav_about) {
//            startActivity(new Intent(this, AboutActivity.class));

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        RxBus.getInstance().unregister(Boolean.class, observable);
        super.onDestroy();
    }

}
