package org.example;

import java.util.ArrayList;

public class Clinic {
    private final String name = "Klinika w Zakopanem";
    private final String address = "Zakopane, ul. Krzeptówki 43";
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<Appointment> appointments;

    public Clinic() {
        this.doctors = new ArrayList<Doctor>();
        this.patients = new ArrayList<Patient>();
        this.appointments = new ArrayList<Appointment>();
    }

    public void getDoctors() {
        System.out.println("Lista doktorow:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void getPatients() {
        System.out.println("Lista pacjentow:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void getAppointments() {
        System.out.println("Lista wizyt:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void removeDoctor(int doctorId) {
        Doctor doctorToRemove = null;
        for(Doctor doctor : doctors) {
            if(doctor.getDoctorId() == doctorId) {
                doctorToRemove = doctor;
                break;
            }
        }

        if (doctorToRemove != null) {
            doctors.remove(doctorToRemove);
        } else {
            System.out.println("Nie udalo sie znalezc lekarza!");
        }
    }

    public void removePatient(int patientId) {
        Patient patientToRemove = null;
        for(Patient patient : patients) {
            if(patient.getPatientId() == patientId) {
                patientToRemove = patient;
                break;
            }
        }

        if (patientToRemove != null) {
            patients.remove(patientToRemove);
        } else {
            System.out.println("Nie udalo sie znalezc pacjenta!");
        }
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
}
