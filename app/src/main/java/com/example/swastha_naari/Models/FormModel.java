package com.example.swastha_naari.Models;

import io.realm.RealmObject;

public class FormModel extends RealmObject {

//    Integer id;
    String Name;
    String Age;
    String GuardianName;
    String BloodPressure;
    String Weight;
    String History;
    String MedicalReport;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGuardianName() {
        return GuardianName;
    }

    public void setGuardianName(String guardianName) {
        GuardianName = guardianName;
    }

    public String getBloodPressure() {
        return BloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        BloodPressure = bloodPressure;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getHistory() {
        return History;
    }

    public void setHistory(String history) {
        History = history;
    }

    public String getMedicalReport() {
        return MedicalReport;
    }

    public void setMedicalReport(String medicalReport) {
        MedicalReport = medicalReport;
    }


}
