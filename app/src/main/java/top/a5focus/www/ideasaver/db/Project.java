package top.a5focus.www.ideasaver.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 69133 on 2017/1/4.
 */

public class Project  extends DataSupport{

    private  int ID;
    private String ProjectName;
    private String ProjectGoal;
    private String TechnicalStorage;
    private String ProjectDetails;
    private int UpdateCloud;
    private String CreateTime;
    private String CreateLocation;
    private  int UserID;

    public  void setID(int id){

        this.ID=id;
    }
    public  int getID(){

        return  ID;
    }

    public  void setProjectName(String projectName){

        this.ProjectName=projectName;
    }
    public  String getProjectName(){

        return  ProjectName;
    }
    public void setProjectGoal(String projectGoal){

        this.ProjectGoal=projectGoal;
    }
    public  String getProjectGoal(){
        return  ProjectGoal;
    }

    public int getUpdateCloud() {
        return UpdateCloud;
    }

    public String getCreateLocation() {
        return CreateLocation;
    }

    public int getUserID() {
        return UserID;
    }

    public void setCreateLocation(String createLocation) {
        CreateLocation = createLocation;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public String getProjectDetails() {
        return ProjectDetails;
    }

    public String getTechnicalStorage() {
        return TechnicalStorage;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public void setProjectDetails(String projectDetails) {
        ProjectDetails = projectDetails;
    }

    public void setTechnicalStorage(String technicalStorage) {
        TechnicalStorage = technicalStorage;
    }

    public void setUpdateCloud(int updateCloud) {
        UpdateCloud = updateCloud;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }


}
