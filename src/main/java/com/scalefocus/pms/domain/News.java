// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This model class represents News table from the Database.
 */
@Entity
@Table(name = "NEWS")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_SEQ")
    @SequenceGenerator(sequenceName = "NEWS_SEQ", name = "NEWS_SEQ", catalog = "pms", allocationSize = 1)
    @Column(name = "NEWS_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User author;

    @Column(name = "PUBLISHING_DATE")
    private Timestamp publishingDate;

    @Column(name = "MY_CONTENT")
    private String content;

    /**
     * Constructor for News.
     *
     * @param content - Content of news.
     */
    public News(String content, User author) {
        this.author = author;
        this.content = content;
    }

    public News() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Timestamp getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Timestamp publishingDate) {
        this.publishingDate = publishingDate;
    }
}
