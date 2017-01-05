package top.a5focus.www.ideasaver.util;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import top.a5focus.www.ideasaver.db.User;

/**
 * Created by 69133 on 2017/1/5.
 */

public class GsonUtil {

    public  static User handUserResponse(String response){

        try{

           return new Gson().fromJson(response,User.class);
        }
        catch (Exception e){

            e.printStackTrace();
        }

        return  null;

    }

    public static String handUserToJson(User user){


        String result=new Gson().toJson(user);

        return  result;
    }

    public  static boolean handResponseBoolen(String boolenResponse){

        Boolean result=new Gson().fromJson(boolenResponse,Boolean.class);

        return result;
    }
}
