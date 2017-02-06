package com.example.vaibhav.upworkfirstproject.Related_to_card_data;

import java.util.List;

/**
 * Created by VAIBHAV on 02-Feb-17.
 */

public class TutorDummy {
    public String coursename,introtutor,subject;

    public TutorDummy(){

    }

    public TutorDummy(String name, String into, String sub){
        this.coursename=name;
        this.introtutor=into;
        this.subject=sub;
    }

    public String getIntrotutor() {
        return introtutor;
    }

    public void setIntrotutor(String introtutor) {
        this.introtutor = introtutor;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
