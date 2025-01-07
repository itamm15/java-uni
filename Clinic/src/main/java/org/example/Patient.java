package org.example;

import java.time.LocalDate;

public class Patient extends Person {
    private static int numberOfPatients = 0;
    private int patientId;

    public Patient(String firstName, String lastName, LocalDate birthDate, String address) {
        super(firstName, lastName, birthDate, address);
        this.patientId = numberOfPatients++;
    }

    public int getPatientId() {
        return patientId;
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
