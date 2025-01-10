package org.example;

import java.time.LocalDate;

public class Doctor extends Person {
    private static int numberOfDoctors = 0;
    private int doctorId;
    private final String specialization;
    private LocalDate dateOfGraduation;

    public Doctor(String firstName, String lastName, LocalDate birthDate, String address, String specialization) {
        super(firstName, lastName, birthDate, address);
        this.doctorId = numberOfDoctors++;
        this.specialization = specialization;
        this.dateOfGraduation = LocalDate.now();
    }

    public int getDoctorId() {
        return doctorId;
    }

    public LocalDate getDateOfGraduation() {
        return this.dateOfGraduation;
    }

    public void setDateOfGraduation(LocalDate dateOfGraduation) {
        if (dateOfGraduation.isAfter(this.getBirthDate())) {
            this.dateOfGraduation = dateOfGraduation;
        } else {
            System.out.println("Date of graduation can't be after date of birth!");
        }
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
