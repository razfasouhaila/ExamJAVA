package fr.souha.pro.entities;

import fr.souha.pro.enums.ProdType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_products")
    @SequenceGenerator(name = "seq_products", sequenceName = "seq_products", allocationSize = 1)
    private Long id;
    private String code;
    private String label;
    @Enumerated(EnumType.STRING)
    private ProdType type;
    private Double price;
    @ManyToMany(mappedBy = "products")
    private List<PetStore> petStores = new ArrayList<>();


    public Product() {
    }

    public Product(String code, String label, ProdType type, Double price) {
        this.code = code;
        this.label = label;
        this.type = type;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ProdType getType() {
        return type;
    }

    public void setType(ProdType type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<PetStore> getPetStores() {
        return petStores;
    }

    public void setPetStores(List<PetStore> petStores) {
        this.petStores = petStores;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", type=" + type +
                ", price=" + price +
                '}';
    }

    public void addPetStore(PetStore petStore) {
        petStores.add(petStore);
        petStore.getProducts().add(this);
    }
}
