package com.airtribe.learntrack.entity;

public class Student extends Person {
    public Student(int id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

    public Student(int id, String firstName, String lastName) {
        super(id, firstName, lastName, "");
    }

    private String batch;
    private boolean isActive;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String getDisplayName() {
        return getFirstName() + getLastName() + "<" + getEmail() + ">";
    }
}
