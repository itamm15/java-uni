package org.example;

import java.time.LocalDate;

public class Appointment {
    private static int appointmentId = 0;
    private Doctor doctor;
    private Patient patient;
    private LocalDate date;
    private String description;

    public Appointment(Doctor doctor, Patient patient, LocalDate date, String description) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.description = description;
        appointmentId++;
    }

    public static int getAppointmentId() {
        return appointmentId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Wizyta lekarska: \n" +
            "ID: " + getAppointmentId() + "\n" +
            "Data wizyty: " + getDate().toString() + "\n" +
            "Opis: " + getDescription() + "\n" +
            getDoctor() + "\n" +
            getPatient() + "\n";
    }
}
