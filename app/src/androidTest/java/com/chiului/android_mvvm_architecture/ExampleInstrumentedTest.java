package com.chiului.android_mvvm_architecture;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.gson.JsonObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        assertEquals(getJsonString(), "fafd");
    }

    private String getJsonString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("userName", "account");
        jsonObject.addProperty("password", "psw");
        String json = jsonObject.toString();
        return json;
    }

}