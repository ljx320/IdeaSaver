package top.a5focus.www.ideasaver.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 69133 on 2017/1/4.
 */

public class Idea  extends DataSupport{

    private  int ID;
    private  String IdeaTitle;
    private String Suggestor;
    private int ProjectID;
    private int UserID;
    private String IdeaContent;
    private int UpdateCloud;
    private String CreateTime;
    private String CreateLocation;

    public  void setID(int id){
        this.ID=id;

    }
    public  int getID(){
        return  ID;

    }

    public  void setIdeaTitle(String ideaTitle){

        this.IdeaTitle=ideaTitle;

    }
    public  String getIdeaTitle(){
        return  IdeaTitle;

    }

    public String getSuggestor() {
        return Suggestor;
    }

    public void setSuggestor(String suggestor) {
        Suggestor = suggestor;
    }

    public void setProjectID(int projectID){

        this.ProjectID=projectID;
    }
    public int getProjectID(){

        return  ProjectID;
    }

    public  void setUserID(int userID){
        this.UserID=userID;


    }

    public  int getUserID(){

        return  UserID;
    }

    public  void setIdeaContent(String ideaContent){

        this.IdeaContent=ideaContent;
    }

    public String getIdeaContent(){

        return  IdeaContent;
    }

    public void setUpdateCloud(int updateCloud){
        this.UpdateCloud=updateCloud;

    }

    public  int getUpdateCloud(){

        return  UpdateCloud;
    }

    public void setCreateTime(String createTime){
        this.CreateTime=createTime;

    }

    public  String getCreateTime(){

        return  CreateTime;
    }

    public void  setCreateLocation(String createLocation){

        this.CreateLocation=createLocation;
    }
    public  String  getCreateLocation(){
        return  CreateLocation;

    }
}
