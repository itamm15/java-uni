package org.example;

import java.time.LocalDate;

public class Doctor extends Person {
    private static int numberOfDoctors = 0;
    private int doctorId;
    private final String specialization;

    public Doctor(String firstName, String lastName, LocalDate birthDate, String address, String specialization) {
        super(firstName, lastName, birthDate, address);
        this.doctorId = numberOfDoctors++;
        this.specialization = specialization;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doktor: \n" +
            "ID: " + getDoctorId() + "\n" +
            "Specjalizacja: " + getSpecialization() + "\n" +
            "Imie: " + getFirstName() + "\n" +
            "Nazwisko: " + getLastName() + "\n" +
            "Data urodzenia: " + getBirthDate().toString() + "\n" +
            "Miejsce zamieszkania: " + getAddress() + "\n";
    }
}
