package com.driver;

import org.apache.commons.lang3.tuple.Triple;

import java.util.*;

public class Gmail extends Email {

    List<Triple<Date, String, String>> mailList;
    List<Triple<Date, String, String>> trashList;

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {

        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.mailList = new ArrayList<>();
        this.trashList = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if (inboxCapacity == mailList.size()) {
            // move the last mail to trash
            Triple<Date, String, String> oldestMail = mailList.get(0);

            trashList.add(oldestMail);

            mailList.remove(0);
        }

        Triple<Date, String, String> mail = Triple.of(date, sender, message);
        mailList.add(mail);
    }


    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        if (mailList.isEmpty()) {
            return;
        }

        int index = -1;

        for (int i = 0; i < mailList.size(); i++) {

            Triple<Date, String, String> mail = mailList.get(i);

            if (mail.getRight().equals(message)) {
                // moveMailToTrash
                trashList.add(mail);
                index = i;
            }
        }

        if (index != -1) {
            // delete mail form inbox
            mailList.remove(mailList.get(index));
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

        if (mailList.isEmpty()) {
            return null;
        }

        return mailList.get(mailList.size() - 1).getRight();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

        if (mailList.size() == 0) {
            return null;
        }

        return mailList.get(0).getRight();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        int numberOfMailsBetweenDates = 0;

        for (Triple<Date, String, String> mail : mailList) {

            Date date = mail.getLeft();

            if (date.equals(start) || date.equals(end) || date.after(start) || date.before(end)) {
                numberOfMailsBetweenDates = numberOfMailsBetweenDates + 1;
            }
        }
        return numberOfMailsBetweenDates;
    }

    public int getInboxSize(){
        // Return number of mails in inbox

        return mailList.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash

        return trashList.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash

        trashList.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox

        return this.inboxCapacity;
    }
}
