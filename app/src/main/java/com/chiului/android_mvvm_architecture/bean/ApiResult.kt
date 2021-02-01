package com.chiului.android_mvvm_architecture.bean

/**
 * 基础返回对象数据类$
 * @author    神经大条蕾弟
 * @date      2021/02/01 16:35
 */
data class ApiResult<T>(

        /**
         * success : true
         * msg : ok
         * code : 200
         * timestamp : 1610271439614
         * data : {}
         */
        val success: Boolean,
        val msg: String,
        val code: Int,
        val timestamp: Long,
        val data: T

)
