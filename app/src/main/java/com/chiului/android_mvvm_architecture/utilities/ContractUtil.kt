package com.chiului.android_mvvm_architecture.utilities

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LifecycleOwner

/**
 * ActivityResultContracts 的工具类封装$
 *
 * 对内存占用有比较高要求也可使用原生写法，因为原生写法定义出来的 ActivityResultLauncher 可以复用，但是要分两步。这个工具类封装过后会使用比较简单，只要及时 unregister 差别不大。
 * 原生写法：
 * // 在 Activity 或 Fragment 中
 * activityResultLauncher = registerForActivityResult(ActivityResultContract, ActivityResultCallback)
 * activityResultLauncher.launch();
 *
 * @author    神经大条蕾弟
 * @date      2020/11/20 11:38
 */
object ContractUtil {

    /**
     * 请求单个权限(需要手动 unregister)
     * @param registry ActivityResultRegistry 通过 Activity 或 Fragment getActivityResultRegistry() 方法获取
     * @param permission String 权限
     * @param callback ActivityResultCallback<Boolean> 结果回调
     * @return ActivityResultLauncher<String>   返回给页面及时调用 unregister() 在解除注册
     */
    @JvmStatic
    fun requestPermission(registry: ActivityResultRegistry, permission: String, callback: ActivityResultCallback<Boolean>): ActivityResultLauncher<String> {
        var launcher = registry.register(
                "requestPermission",
                ActivityResultContracts.RequestPermission(),
                callback
        )
        launcher.launch(permission)
        return launcher
    }

    /**
     * 请求单个权限(自动 unregister 通过 LifecycleOwner)
     * @param registry ActivityResultRegistry   通过 Activity 或 Fragment getActivityResultRegistry() 方法获取
     * @param lifecycleOwner LifecycleOwner must call register before they are STARTED.
     * @param permission String 权限
     * @param callback ActivityResultCallback<Boolean>  结果回调
     */
    @JvmStatic
    fun requestPermission(registry: ActivityResultRegistry, lifecycleOwner: LifecycleOwner, permission: String, callback: ActivityResultCallback<Boolean>) {
        var launcher = registry.register(
                "requestPermission",
                lifecycleOwner,
                ActivityResultContracts.RequestPermission(),
                callback
        )
        launcher.launch(permission)
    }

    /**
     * 请求多个权限(需要手动 ActivityResultLauncher.unregister())
     * @param registry ActivityResultRegistry   通过 Activity 或 Fragment getActivityResultRegistry() 方法获取
     * @param permissionArray Array<String>     权限数组
     * @param callback ActivityResultCallback<Map<String, Boolean>> 结果回调
     * @return ActivityResultLauncher<Array<String>>    返回给页面及时调用 unregister() 在解除注册
     */
    @JvmStatic
    fun requestMultiplePermission(registry: ActivityResultRegistry, permissionArray: Array<String>, callback: ActivityResultCallback<Map<String, Boolean>>): ActivityResultLauncher<Array<String>> {
        var launcher = registry.register(
                "requestMultiplePermission",
                ActivityResultContracts.RequestMultiplePermissions(),
                callback
        )
        launcher.launch(permissionArray)
        return launcher
    }

    /**
     * 请求多个权限(自动 unregister 通过 LifecycleOwner)
     * @param registry ActivityResultRegistry   通过 Activity 或 Fragment getActivityResultRegistry() 方法获取
     * @param lifecycleOwner LifecycleOwner must call register before they are STARTED.
     * @param permissionArray Array<String> 权限数组
     * @param callback ActivityResultCallback<Map<String, Boolean>> 结果回调
     */
    @JvmStatic
    fun requestMultiplePermission(registry: ActivityResultRegistry, lifecycleOwner: LifecycleOwner, permissionArray: Array<String>, callback: ActivityResultCallback<Map<String, Boolean>>){
        var launcher = registry.register(
                "requestMultiplePermission",
                lifecycleOwner,
                ActivityResultContracts.RequestMultiplePermissions(),
                callback
        )
        launcher.launch(permissionArray)
    }

}