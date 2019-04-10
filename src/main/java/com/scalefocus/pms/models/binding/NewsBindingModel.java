// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.models.binding;

import com.scalefocus.pms.constants.ValidationConstants;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.Date;

public class NewsBindingModel {
    private Long id;
    private String title;
    private UserBindingModel author;
    @DateTimeFormat(pattern = ValidationConstants.DATE_PATTERN)
    private Date publishingDate;
    private String content;

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

    public UserBindingModel getAuthor() {
        return author;
    }

    public void setAuthor(UserBindingModel author) {
        this.author = author;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    /**
     * @param publishingDate - The publishing date of the News.
     */
    public void setPublishingDate(Date publishingDate) {
        if (publishingDate == null) {
            this.publishingDate = Date.from(Instant.now());
        } else {
            this.publishingDate = publishingDate;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
