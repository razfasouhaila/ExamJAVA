package fr.souha.pro.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pet_stores")
public class PetStore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_pet_stores", sequenceName = "seq_pet_stores", allocationSize = 1)
    private Long id;
    private String name;
    @Column(name = "manager_name")
    private String managerName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @ManyToMany
    @JoinTable(
            name = "pet_stores_products",
            joinColumns = @JoinColumn(name = "pet_store_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();
    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL)
    private List<Animal> animals = new ArrayList<>();

    public PetStore() {
    }

    public PetStore(String name, String managerName, Address address) {
        this.name = name;
        this.managerName = managerName;
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return this.managerName;
    }


    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "PetStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managerName='" + managerName + '\'' +
                ", address=" + address +
                ", products=" + products +
                ", animals=" + animals +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetStore)) return false;

        PetStore petStore = (PetStore) o;

        if (!getId().equals(petStore.getId())) return false;
        if (!getName().equals(petStore.getName())) return false;
        if (!getManagerName().equals(petStore.getManagerName())) return false;
        if (!getAddress().equals(petStore.getAddress())) return false;
        if (!getProducts().equals(petStore.getProducts())) return false;
        if (!getAnimals().equals(petStore.getAnimals())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getManagerName().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getProducts().hashCode();
        result = 31 * result + getAnimals().hashCode();
        return result;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.getPetStores().add(this);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setPetStore(this);
    }

}
