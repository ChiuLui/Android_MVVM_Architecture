package com.chiului.android_mvvm_architecture.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chiului.android_mvvm_architecture.lifecycler.BaseLifecycle;

/**
 * 基础Activity$
 *
 * @author 神经大条蕾弟
 * @date 2020/07/20 22:29
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new BaseLifecycle());
        initBaseWidget();
        initViewModel();
        onCreating(savedInstanceState);
    }

    /**
     * 获取 DataBinding
     * @param modelClass
     * @param <T>
     * @return
     */
    public <T extends ViewDataBinding> T getDataBinding(@NonNull Class<T> modelClass){
        return DataBindingUtil.setContentView(this, setContentViewID());
    }

    /**
     * 初始化基类小工具
     * 在onCreating()之前
     */
    private void initBaseWidget(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            initBundle(bundle);
        }
    }

    /**
     * 跳转页面
     *
     * @param context 来源
     * @param cls 去向
     */
    public static void startActivity(Context context, Class cls) {
        startActivity(context, cls, null);
    }

    /**
     * 跳转页面
     *
     * @param context 来源
     * @param cls 去向
     * @param bundle 携带参数
     */
    public static void startActivity(Context context, Class cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转页面带回调
     * @param fromActivity 来源
     * @param toCls 去向
     * @param activityResultLauncher 注册的 registerForActivityResult 对象：示例👇
     * private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
     *                               new ActivityResultContracts.StartActivityForResult(), result -> {});
     */
    public static void startActivity(Activity fromActivity, Class toCls, ActivityResultLauncher<Intent> activityResultLauncher) {
        startActivity(fromActivity, toCls, null, activityResultLauncher);
    }

    /**
     * 跳转页面带回调
     * @param fromActivity 来源
     * @param toCls 去向
     * @param bundle 携带参数
     * @param activityResultLauncher 注册的 registerForActivityResult 对象
     */
    public static void startActivity(Activity fromActivity, Class toCls, Bundle bundle , ActivityResultLauncher<Intent> activityResultLauncher) {
        Intent intent = new Intent(fromActivity, toCls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activityResultLauncher.launch(intent);
    }

    /**
     * 初始化数据
     */
    public void initBundle(Bundle bundle) {}

    /**
     * @return 布局文件ID
     */
    public abstract @LayoutRes int setContentViewID();

    /**
     * 初始化 ViewModel
     */
    public abstract void initViewModel();

    /**
     * 初始化操作
     * @param savedInstanceState
     */
    public abstract void onCreating(@Nullable Bundle savedInstanceState);

}
