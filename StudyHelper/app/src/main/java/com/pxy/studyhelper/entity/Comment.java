package com.pxy.studyhelper.entity;

import java.util.Date;

import cn.bmob.v3.BmobObject;

/**
 * User: Pxy(15602269883@163.com)
 * Date: 2016-02-15
 * Time: 23:11
 * 用户评论表
 * FIXME
 */
public class Comment  extends BmobObject {
    private String  topicId;
    private String  userId;
    private String  content;
    private Date  createAt;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "topicId='" + topicId + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
