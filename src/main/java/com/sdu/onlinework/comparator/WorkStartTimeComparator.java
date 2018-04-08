package com.sdu.onlinework.comparator;


import com.sdu.onlinework.pojo.Work;

import java.util.Comparator;

public class WorkStartTimeComparator implements Comparator<Work> {

    @Override
    public int compare(Work w1, Work w2) {
        return w1.getStartTime().compareTo(w2.getStartTime()) * -1;
    }
}
