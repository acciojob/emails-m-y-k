package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.

        super(emailId, Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar

        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        // make arrayLists for start and end meeting times
        List<Pair<LocalTime, Integer>> endTimes = new ArrayList<>();

        for (int i = 0; i < this.calendar.size(); i++) {

            LocalTime end_time = calendar.get(i).getEndTime();

            endTimes.add(Pair.of(end_time, i));
        }

        // sort the timings list based on end time
        Collections.sort(endTimes);

        int count = 0;

        if (!endTimes.isEmpty()) {
            count += 1;
        }

        LocalTime endTime = endTimes.get(0).getLeft();

        for (int i = 1; i < endTimes.size(); i++) {

            if (calendar.get(endTimes.get(i).getRight()).getStartTime().compareTo(endTime) > 0) {
                count++;
                endTime = endTimes.get(i).getLeft();
            }
        }

        return count;
    }
}
