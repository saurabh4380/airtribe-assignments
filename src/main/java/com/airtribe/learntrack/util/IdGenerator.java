package com.airtribe.learntrack.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final AtomicInteger personCounter = new AtomicInteger(1);
    private static final AtomicInteger courseCounter = new AtomicInteger(1);
    private static final AtomicInteger enrollmentCounter = new AtomicInteger(1);

    public static int getNextPersonId() {
        return personCounter.getAndIncrement();
    }

    public int getNextCourseId() {
        return courseCounter.getAndIncrement();
    }

    public int getNextEnrollmentId() {
        return enrollmentCounter.getAndIncrement();
    }

}
