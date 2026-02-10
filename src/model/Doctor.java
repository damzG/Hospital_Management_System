package model;

public class Doctor {
    private  int doctorId;
    private String name;
    private String specialization;

    public Doctor(String name, String specialization){
        this.name = name;
        this.specialization = specialization;
    }

    public Doctor(int doctorId, String name, String spec){
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = spec;
    }


    public String getName() {
        return name;
    }

    public int getId(){
        return doctorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public String toString(){
        return name + " (" + doctorId + ")";
    }
}
