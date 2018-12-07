package com.epam.javaee.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class News {
    @Id
    private long id;
    private String title;
    private Date date;
    private String brief;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", date=" + date +
               ", brief='" + brief + '\'' +
               ", content='" + content + '\'' +
               '}';
    }
}
