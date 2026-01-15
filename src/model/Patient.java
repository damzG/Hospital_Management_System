package model;

import java.time.LocalDate;

public class Patient {
    private int patientId;
    private String name;
    private LocalDate dob;
    private String phone;
    private String address;
    private String gender;

    public Patient(int id, String name, LocalDate dob, String phone, String address, String gender){
        this.patientId = id;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }

    public Patient(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPatientId(int patientId) {
        patientId++;
    }

    public int getPatientId() {
        return patientId;
    }
}
