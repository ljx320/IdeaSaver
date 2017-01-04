package top.a5focus.www.ideasaver.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 69133 on 2017/1/4.
 */

public class User  extends DataSupport{

    private int ID;
    private  String Tel;
    private String Birthday;
    private  String Sex;
    private  String UserName;
    private  String Password;
    private  String CreatTime;
    private String HeadPortrait;
    private int UpdateCloud;
    private  int Available;

    public void setID(int id){
        this.ID=id;

    }
    public int getID(){
        return  ID;

    }


    public  void setBirthday(String birthday){
        this.Birthday=birthday;

    }

    public  String getBirthday(){

        return  Birthday;
    }

    public  void setTel(String tel){
         this.Tel=tel;

    }
    public  String getTel(){
        return  Tel;

    }

    public void setSex(String sex){

        this.Sex=sex;
    }
    public String getSex(){
        return  Sex;

    }

    public void setUserName(String userName){

        this.UserName=userName;
    }
    public String getUserName(){
        return  UserName;

    }

    public  void setPassword(String password){
        this.Password=password;

    }
    public  String getPassword(){

        return  Password;
    }

    public  void setCreatTime(String creatTime){

        this.CreatTime=creatTime;
    }
    public String getCreatTime(){

        return  CreatTime;
    }

    public String getHeadPortrait(){
        return  HeadPortrait;
    }

    public  void  setHeadPortrait(String headPortrait){

        this.HeadPortrait=headPortrait;
    }


    public void setUpdateCloud(int updateCloud){

        this.UpdateCloud=updateCloud;
    }
    public  int getUpdateCloud(){

        return  UpdateCloud;
    }

    public  void setAvailable(int available){

        this.Available=available;
    }
    public  int getAvailable(){

        return  Available;
    }

}
