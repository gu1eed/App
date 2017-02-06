package com.example.vaibhav.upworkfirstproject.Related_to_card_data;

/**
 * Created by VAIBHAV on 30-Jan-17.
 */

public class ProfessionEntry {
    String name,gender;
    String profession,studentid;

    public ProfessionEntry(){}

    public ProfessionEntry(String ename,String ggender,String studentid,String pro){
        this.name=ename;
        this.gender=ggender;
        this.profession=pro;
        this.studentid=studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
}
