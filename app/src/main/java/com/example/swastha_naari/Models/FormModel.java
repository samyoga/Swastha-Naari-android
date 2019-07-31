package com.example.swastha_naari.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class FormModel extends RealmObject {

    @Required
    @PrimaryKey
    private String id;

    String Name;
    String Age;
    String GuardianName;
    String BloodPressure;
    String Weight;
    String FirstPregnancyAge;
    String ChildNumber;
    String MenopauseAge;
    String BirthSpacing;
    String MenstrualInterval;
    String History;
    String MedicalReport;

    public String getFirstPregnancyAge() {
        return FirstPregnancyAge;
    }

    public void setFirstPregnancyAge(String firstPregnancyAge) {
        FirstPregnancyAge = firstPregnancyAge;
    }

    public String getChildNumber() {
        return ChildNumber;
    }

    public void setChildNumber(String childNumber) {
        ChildNumber = childNumber;
    }

    public String getMenopauseAge() {
        return MenopauseAge;
    }

    public void setMenopauseAge(String menopauseAge) {
        MenopauseAge = menopauseAge;
    }

    public String getBirthSpacing() {
        return BirthSpacing;
    }

    public void setBirthSpacing(String birthSpacing) {
        BirthSpacing = birthSpacing;
    }

    public String getMenstrualInterval() {
        return MenstrualInterval;
    }

    public void setMenstrualInterval(String menstrualInterval) {
        MenstrualInterval = menstrualInterval;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
