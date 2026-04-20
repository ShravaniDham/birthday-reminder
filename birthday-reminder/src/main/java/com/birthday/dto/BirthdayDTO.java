package com.birthday.dto;

import java.time.LocalDate;

public class BirthdayDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Integer birthYear;
    private String relationship;

    public BirthdayDTO() {}

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public Integer getBirthYear() { return birthYear; }
    public String getRelationship() { return relationship; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }
    public void setRelationship(String relationship) { this.relationship = relationship; }
}