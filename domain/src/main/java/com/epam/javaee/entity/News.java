package com.epam.javaee.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@Table(name = "NEWS")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
        generator = "NEWS_SEQ")
    @SequenceGenerator(name = "NEWS_SEQ",
        sequenceName = "NEWS_SEQ",
        allocationSize = 1)
    private long id;
    @Column(name = "TITLE")
    @Size(min = 2,
        max = 60,
        message = "validation.title.size")
    private String title;
    @Column(name = "NEWS_DATE")
    @Past(message = "validation.date.past")
    private Date date;
    @Column(name = "BRIEF")
    @Size(min = 2,
        max = 200,
        message = "validation.brief.size")
    private String brief;
    @Column(name = "CONTENT")
    @Size(min = 2, max = 2000, message = "validation.content.size")
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
