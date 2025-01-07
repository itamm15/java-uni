package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Clinic clinic = new Clinic();

        // pierwsze wylistowanie;
        System.out.println("================================================================");
        System.out.println("Pierwsze listowanie");
        System.out.println("================================================================");
        clinic.getDoctors();
        clinic.getPatients();
        clinic.getAppointments();

        // Doctors
        Doctor Lukas = new Doctor("Lukasz", "Kosmienski", LocalDate.of(200, 1, 12), "Zakopane, ul. Kalatowki 2", "Onkolog");
        Doctor Thomas = new Doctor("Thomas", "Samoski", LocalDate.of(1998, 2, 3), "Zakopane, ul. Kalatowki 23", "Chirurg");
        Doctor Ursula = new Doctor("Ursula", "Samosieniak", LocalDate.of(1964, 5, 10), "Krakow, ul. Kalatowki 23", "Pediatra");

        clinic.addDoctor(Lukas);
        clinic.addDoctor(Thomas);
        clinic.addDoctor(Ursula);

        // Patients
        Patient Grzegorz = new Patient("Grzegorz", "Pszczyna", LocalDate.of(1999, 7, 28), "Lubon, ul. Testowa 3");
        Patient Grazyna = new Patient("Grazyna", "Pszczyna", LocalDate.of(1999, 8, 29), "Lubon, ul. Testowa 3");
        Patient Maria = new Patient("Maria", "Holewinska", LocalDate.of(1947, 10, 3), "Poznan, ul. Rydla 234/a");

        clinic.addPatient(Grzegorz);
        clinic.addPatient(Grazyna);
        clinic.addPatient(Maria);

        // Appointments
        Appointment First = new Appointment(Lukas, Grzegorz, LocalDate.of(2024, 8, 10), "Zapalenie krtani");
        Appointment Second = new Appointment(Lukas, Grazyna, LocalDate.of(2024, 8, 10), "Skurcze lewego uda");
        Appointment Third = new Appointment(Thomas, Maria, LocalDate.of(2024, 12, 26), "Krztusiec");
        Appointment Fourth = new Appointment(Thomas, Grzegorz, LocalDate.of(2025, 1, 4), "Lekkie zapalenie krtani");

        clinic.addAppointment(First);
        clinic.addAppointment(Second);
        clinic.addAppointment(Third);
        clinic.addAppointment(Fourth);

        // drugie wylistowanie;
        System.out.println("================================================================");
        System.out.println("Drugie listowanie");
        System.out.println("================================================================");
        clinic.getDoctors();
        clinic.getPatients();
        clinic.getAppointments();

        // usuwanie
        clinic.removeDoctor(Lukas.getDoctorId());
        clinic.removeDoctor(Thomas.getDoctorId());

        clinic.removePatient(Grzegorz.getPatientId());
        clinic.removePatient(Grazyna.getPatientId());

        // trzecie wylistowanie;
        System.out.println("================================================================");
        System.out.println("Trzecie listowanie");
        System.out.println("================================================================");
        clinic.getDoctors();
        clinic.getPatients();
    }
}