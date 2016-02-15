package com.pxy.studyhelper.entity;

import com.bmob.BmobProFile;

import java.util.Date;

import cn.bmob.v3.BmobObject;

/**
 * User: Pxy(15602269883@163.com)
 * Date: 2016-02-15
 * Time: 23:07
 * FIXME
 */
public class Topic  extends BmobObject {
    private String  userId;
    private String  content;
    private BmobProFile  image;
    private int love;
    private Date  createAt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BmobProFile getImage() {
        return image;
    }

    public void setImage(BmobProFile image) {
        this.image = image;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", image=" + image +
                ", love=" + love +
                ", createAt=" + createAt +
                '}';
    }
}
