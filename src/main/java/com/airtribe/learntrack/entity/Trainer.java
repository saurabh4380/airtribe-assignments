package com.airtribe.learntrack.entity;

public class Trainer extends Person {

    public Trainer(int id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

    public Trainer() {
        super(0, null, null, null);
    }

    private final String TRAINER_SALUTATION = "Prof.";

    @Override
    public String getDisplayName() {
        return TRAINER_SALUTATION + " " + getFirstName() + " " + getLastName();
    }

}
