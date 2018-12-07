package com.epam.javaee.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewsBuilder {

    private News news;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public NewsBuilder(long id, String title, Date date, String brief, String content) {
        news = new News();
        news.setId(id);
        news.setTitle(title);
        news.setDate(date);
        news.setBrief(brief);
        news.setContent(content);
    }

    public NewsBuilder(long id, String title, String date, String brief, String content) throws ParseException {
        this(id, title, dateFormat.parse(date), brief, content);
    }

    public News getNews() {
        return news;
    }
}
