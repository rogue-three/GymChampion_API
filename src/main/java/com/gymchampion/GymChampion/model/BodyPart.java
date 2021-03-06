package com.gymchampion.GymChampion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "body_part")
public class BodyPart {

    @Id
    @Column(name = "body_part_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bodyPartId;

    @Column(name = "body_part_name", nullable = false, length = 20)
    private String bodyPartName;

    @OneToMany(mappedBy = "bodyPart", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Exercise> exercises;

    public BodyPart() {}

    public BodyPart(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }

    public int getBodyPartId() {
        return this.bodyPartId;
    }

    public void setBodyPartId(int bodyPartId) {
        this.bodyPartId = bodyPartId;
    }

    public String getBodyPartName() {
        return this.bodyPartName;
    }

    public void setBodyPartName(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }

    public List<Exercise> getExercises() { return this.exercises; }

    public void setExercises(List<Exercise> exercises) { this.exercises = exercises; }
}
