package com.example.project.entity;

public class NewMsg {
    private int cnewlpost=0;//count new likedpost
    private int cnewlcomment=0;//count new likedcomment
    private int cnewpostcomment=0;//count new postccomment
    private int cnewcommentreply=0;//count new commentreply

    public void setcnewlpost(int count){this.cnewlpost=count;}
    public int getCnewlpost(){return cnewlpost;};

    public void setcnewlcomment(int count){this.cnewlcomment=count;}
    public int getCnewlcomment(){return cnewlcomment;}

    public void setcnewpostcomment(int count){this.cnewpostcomment=count;}
    public int getCnewpostcomment(){return cnewpostcomment;}

    public void setcnewcommentreply(int count){this.cnewcommentreply=count;}
    public int getCnewcommentreply(){return cnewcommentreply;}
}
