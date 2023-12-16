package fr.souha.pro.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "cats")
public class Cat extends Animal{
    private String chipId;

    public Cat() {
        super();
    }

    public Cat(Date birth, String color, String chipId) {
        super(birth, color);
        this.chipId = chipId;
    }

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "chipId='" + chipId + '\'' +
                '}';
    }
}
