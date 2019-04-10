// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * This model class represents Messages table from the Database.
 */
@Entity
@Table(name = "MESSAGES")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGES_SEQ")
    @SequenceGenerator(sequenceName = "MESSAGES_SEQ", name = "MESSAGES_SEQ", catalog = "pms", allocationSize = 1)
    @Column(name = "MESSAGE_ID")
    private Long id;

    @Column(name = "RECEIVER")
    private String receiver;

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "MY_CONTENT")
    private String content;

    public Message() {
    }

    /**
     * Constructor for Message.
     *
     * @param receiver - receiver for email.
     * @param sender   - sender for email.
     * @param content  - content of email.
     */
    public Message(String receiver, String sender, String content) {
        this.receiver = receiver;
        this.sender = sender;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
