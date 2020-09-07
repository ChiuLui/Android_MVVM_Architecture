package com.chiului.android_mvvm_architecture.api;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 用户模块网络请求$
 *
 * @author 神经大条蕾弟
 * @date 2020/08/31 16:46
 */
public interface UserService {

    static UserService create() {

        return BaseApiService.getRetrofit().create(UserService.class);

    }

    @GET("search/photos")
    Single<String> searchPhotos(@Query("query") String query,
                                @Query("page") int page,
                                @Query("per_page") int perPage,
                                @Query("client_id")String clientId);

    @GET("api/account/users/")
    Single<String> getUsers();

}
