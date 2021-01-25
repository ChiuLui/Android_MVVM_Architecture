package com.chiului.android_mvvm_architecture.retrofit.converter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * $结果处理
 *
 * @author 神经大条蕾弟
 * @date 2021/01/19 10:38
 */
final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        // 请求成功对返回的数据进行预处理，根据具体情况而定。
        String jsonString = value.string();
        try {
            JsonElement jsonElement = JsonParser.parseString(jsonString);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonElement codeElement = jsonObject.get("code");
            int code = codeElement.getAsInt();
            if (code != NetErrorException.SUCCEED_CODE) {
                // 后台返回错误
                // 获取错误描述
                JsonElement msgElement = jsonObject.get("msg");
                String message = msgElement.getAsString();
                // 返回自定义错误
                throw new NetErrorException(message, code);
            }
            return adapter.fromJson(jsonObject.get("data").getAsString());

        } finally {
            value.close();
        }
    }
}
