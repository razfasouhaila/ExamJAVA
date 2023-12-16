package fr.souha.pro.entities;

import fr.souha.pro.enums.FishLivEnv;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "fishes")
public class Fish extends Animal{
    @Column(name = "living_env")
    private FishLivEnv livingEnv;

    public Fish() {
        super();
    }

    public Fish(Date birth, String color, FishLivEnv livingEnv) {
        super(birth, color);
        this.livingEnv = livingEnv;
    }

    public FishLivEnv getLivingEnv() {
        return livingEnv;
    }

    public void setLivingEnv(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "livingEnv=" + livingEnv +
                '}';
    }
}
