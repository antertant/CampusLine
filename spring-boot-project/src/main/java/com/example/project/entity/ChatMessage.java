package com.example.project.entity;

import java.util.Date;

public class ChatMessage {

    //文本
    public static final int MESSAGE_TYPE_TEXT = 0;

    //图片
    public static final int MESSAGE_TYPE_IMAGE = 1;


    //信息id（自增）
    private int message_id;

    //发送者
    private String from_user;

    //接收者
    private String to_user;

    //内容
    private String content;

    //发送时间
    private Date send_time;


    //是否为最后一条
    private int unread;

    public String getTo_user() {
        return to_user;
    }

    public int getMessage_id() {
        return message_id;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public int getUnread() {
        return unread;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}

