package com.edvantis.workopolis.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Vacancy {

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("imgUrl")
    private String imgUrl;

    @SerializedName("address")
    private String address;

    @SerializedName("salary")
    private int salary;

    @SerializedName("direction")
    private Direction direction;

    @SerializedName("responsibilities")
    private String responsibilities;

    @SerializedName("technicalSkills")
    private List<TechnicalSkill> technicalSkills;

    @SerializedName("communicationSkills")
    private List<CommunicationSkill> communicationSkills;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return address;
    }

    public void setLocation(String location) {
        this.address = location;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public List<TechnicalSkill> getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(List<TechnicalSkill> technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    public List<CommunicationSkill> getCommunicationSkills() {
        return communicationSkills;
    }

    public void setCommunicationSkills(List<CommunicationSkill> communicationSkills) {
        this.communicationSkills = communicationSkills;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
