package top.a5focus.www.ideasaver.util;

import android.util.Log;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


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

    private static   final  String MYHOSTADDRESS="http://www.5focus.top/api/";

    public static  void sendOkHttpRequest(String address, Callback callback){
        String addressResult=MYHOSTADDRESS+address;


        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(addressResult).build();


        client.newCall(request).enqueue(callback);

    }

    public static  void sendPostUseOkhttpRequest(String address,String userJson,Callback callback){

        MediaType MEDIA_TYPE_MARJDOWN=MediaType.parse("application/json;charset=utf-8");
        String addressResult=MYHOSTADDRESS+address;

        OkHttpClient client=new OkHttpClient();

        RequestBody body=RequestBody.create(MEDIA_TYPE_MARJDOWN,userJson);
        Request request=new Request.Builder().url(addressResult).post(body).build();
        client.newCall(request).enqueue(callback);


    }

}
