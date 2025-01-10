package org.example;

import java.time.LocalDate;

public class Patient extends Person {
    private static int numberOfPatients = 0;
    private int patientId;
    private LocalDate joinedToClinic;
    private Doctor supervisor;

    public Patient(String firstName, String lastName, LocalDate birthDate, String address) {
        super(firstName, lastName, birthDate, address);
        this.patientId = numberOfPatients++;
        this.joinedToClinic = LocalDate.now();
        this.supervisor = null;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setJoinedToClinic(LocalDate joinedToClinic) {
        if(joinedToClinic.isBefore(this.getBirthDate())) {
            this.joinedToClinic = joinedToClinic;
        } else {
            System.out.println("Patient couldn't join the clinic before date of birth!");
        }
    }

    public Doctor getSupervisor() {
        return this.supervisor;
    }

    public void setSupervisor(Doctor doctor) {
        this.supervisor = doctor;
    }

    @Override
    public String toString() {
        return "Pacjent: \n" +
               "ID: " + getPatientId() + "\n" +
               "Imie: " + getFirstName() + "\n" +
               "Nazwisko: " + getLastName() + "\n" +
               "Data urodzenia: " + getBirthDate().toString() + "\n" +
               "Miejsce zamieszkania: " + getAddress() + "\n";
    }
}
