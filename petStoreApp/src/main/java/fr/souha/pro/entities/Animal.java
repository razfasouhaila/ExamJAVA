package fr.souha.pro.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "animals")
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_animals", sequenceName = "seq_animals", allocationSize = 1)
    private Long id;
    private Date birth;
    private String color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_store_id")
    private PetStore petStore;

    public Animal() {
    }

    public Animal(Date birth, String color) {
        this.birth = birth;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public PetStore getPetStore() {
        return petStore;
    }

    public void setPetStore(PetStore petStore) {
        this.petStore = petStore;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", birth=" + birth +
                ", color='" + color + '\'' +
                ", petStore=" + petStore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;

        if (!getId().equals(animal.getId())) return false;
        if (!getBirth().equals(animal.getBirth())) return false;
        if (!getColor().equals(animal.getColor())) return false;
        return getPetStore().equals(animal.getPetStore());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getBirth().hashCode();
        result = 31 * result + getColor().hashCode();
        result = 31 * result + getPetStore().hashCode();
        return result;
    }
}
