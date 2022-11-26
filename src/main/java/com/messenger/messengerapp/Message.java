package com.messenger.messengerapp;

import java.util.Date;

public class Message {
    public int messageId;
    public int fromUserId;
    public int toUserId;
    public String message;
    public long date;

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", dateHuman=" + new Date(date) +
                '}';
    }
}
