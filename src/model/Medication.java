package model;

public class Medication {
    private int medicationId;
    private Prescription prescription;
    private String drugName;
    private String dosage;
    private String instructions;

    public Medication(int id, Prescription prescription, String drugName, String dosage, String instructions){
        this.medicationId = id;
        this.prescription = prescription;
        this.drugName = drugName;
        this.dosage = dosage;
        this.instructions = instructions;
    }


    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
