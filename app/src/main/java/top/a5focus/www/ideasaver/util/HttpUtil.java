package top.a5focus.www.ideasaver.util;

import android.util.Log;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by 69133 on 2017/1/4.
 */

public class HttpUtil {



//    public static  void sendOkhttpRequest(String address, Callback callback){
//
//
//        OkHttpClient client=new OkHttpClient();
//        Request request=new Request.Builder().url(addressResult).build();
//        client.newCall(request).enqueue(callback);
//
//    }

    public static  void sendOkHttpRequest(String address, Callback callback){
        String addressResult="http://www.5focus.top/api/"+address;


        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(addressResult).build();


        client.newCall(request).enqueue(callback);

    }
}
