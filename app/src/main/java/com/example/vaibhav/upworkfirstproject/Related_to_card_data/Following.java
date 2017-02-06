package com.example.vaibhav.upworkfirstproject.Related_to_card_data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VAIBHAV on 02-Feb-17.
 */

public class Following  {
    public List<String> coursename;
    public List<String> introtutor;
    public List<String> subject;

    public Following(){}

    public Following(List<String> name,List<String> into,List<String> sub){
        this.coursename=name;
        this.introtutor=into;
        this.subject=sub;
    }

    public List<String> getCoursename() {
        return coursename;
    }

    public void setCoursename(List<String> coursename) {
        this.coursename = coursename;
    }

    public List<String> getIntrotutor() {
        return introtutor;
    }

    public void setIntrotutor(List<String> introtutor) {
        this.introtutor = introtutor;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }
}
