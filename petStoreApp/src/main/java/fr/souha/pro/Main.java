package fr.souha.pro;

import fr.souha.pro.entities.*;
import fr.souha.pro.enums.FishLivEnv;
import fr.souha.pro.enums.ProdType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pet-store-app-souhaila");
        EntityManager em = emf.createEntityManager();

        try {
            // Begin transaction
            em.getTransaction().begin();

            // Create ProdType
            ProdType accessory = ProdType.ACCESSORY;
            ProdType cleaning = ProdType.CLEANING;
            ProdType food = ProdType.FOOD;

            // Create Product for store 1
            Product product1s1 = new Product("code-101", "product 1", accessory, 1500.00);
            Product product2s1 = new Product("code-102", "product 2", food, 500.00);
            Product product3s1 = new Product("code-103", "product 3", cleaning, 180.00);

            // Create Product for store 2
            Product product1s2 = new Product("code-201", "product 1", accessory, 1500.00);
            Product product2s2 = new Product("code-202", "product 2", food, 500.00);
            Product product3s2 = new Product("code-203", "product 3", cleaning, 180.00);


            //create Addresses for PetStores
            Address address1 = new Address("123a", "street1", "1522a", "city 1");
            Address address2 = new Address("123a", "street2", "1522a", "city 1");
            //create pet stores
            PetStore perStore1 = new PetStore("petStore 1", "manager pet strore 1", address1);
            PetStore perStore2 = new PetStore("petStore 2", "manager pet strore 2", address2);

            // create animals
            Fish fish1s1 = new Fish(dateFormat.parse("15-12-2020"), "red", FishLivEnv.FRESH_WATER);
            Fish fish2s1 = new Fish(dateFormat.parse("18-11-2020"), "red", FishLivEnv.SEA_WATER);
            Cat cat1s1 = new Cat(dateFormat.parse("15-10-2012"), "orange", "1236546");
            Cat cat2s1 = new Cat(dateFormat.parse("18-10-2010"), "white", "888545");
            Animal animals1 = new Animal(dateFormat.parse("18-10-2010"), "white");

            // create animals
            Fish fish1s2 = new Fish(dateFormat.parse("15-12-2020"), "red", FishLivEnv.FRESH_WATER);
            Fish fish2s2 = new Fish(dateFormat.parse("18-11-2020"), "red", FishLivEnv.SEA_WATER);
            Cat cat1s2 = new Cat(dateFormat.parse("15-10-2012"), "orange", "1236546");
            Cat cat2s2 = new Cat(dateFormat.parse("18-10-2010"), "white", "888545");
            Animal animals2 = new Animal(dateFormat.parse("18-10-2010"), "white");

            //add Products to petStore1
            perStore1.addProduct(product1s1);
            perStore1.addProduct(product2s1);
            perStore1.addProduct(product3s1);

            //add animals to store 1
            perStore1.addAnimal(fish1s1);
            perStore1.addAnimal(fish2s1);
            perStore1.addAnimal(cat1s1);
            perStore1.addAnimal(cat2s1);
            perStore1.addAnimal(animals1);

            // add Products to petStore2
            perStore2.addProduct(product1s2);
            perStore2.addProduct(product2s2);
            perStore2.addProduct(product3s2);

            // add animals to store 2
            perStore2.addAnimal(fish1s2);
            perStore2.addAnimal(fish2s2);
            perStore2.addAnimal(cat1s2);
            perStore2.addAnimal(cat2s2);
            perStore2.addAnimal(animals2);

            // Persist PetStore (and cascade persist Product)
            em.persist(address1);
            em.persist(address2);
            em.persist(product1s1);
            em.persist(product2s1);
            em.persist(product3s1);
            em.persist(product1s2);
            em.persist(product2s2);
            em.persist(product3s2);
            em.persist(fish1s1);
            em.persist(fish2s1);
            em.persist(cat1s1);
            em.persist(cat2s1);
            em.persist(animals1);
            em.persist(fish1s2);
            em.persist(fish2s2);
            em.persist(cat1s2);
            em.persist(cat2s2);
            em.persist(animals2);
            em.persist(perStore1);
            em.persist(perStore2);

            // Commit transaction
            em.getTransaction().commit();

            // Retrieve ProdType and their associated Product



            // Retrieve PetStores and their associated Animals
            List<PetStore> petStores = em.createQuery("SELECT p FROM PetStore p", PetStore.class).getResultList();
            System.out.println(" -------- Retrieve PetStores and their associated Animals --------- ");
            for (PetStore petStore : petStores) {
                System.out.println("PetStore Name: " + petStore.getName());
                System.out.println("PetStore Manager: " + petStore.getManagerName());
                System.out.println("PetStore Address City: " + petStore.getAddress().getCity());
                System.out.println("PetStore Address Street: " + petStore.getAddress().getStreet());
                System.out.println("PetStore Address Zip code: " + petStore.getAddress().getZipCode());
                System.out.println("PetStore Address Number: " + petStore.getAddress().getNumber());
                List<Animal> animals = petStore.getAnimals();
                for (Animal anim : animals) {
                    System.out.println(" - Animal Id Id: " + anim.getId());
                    System.out.println(" - Animal Birth: " + anim.getBirth());
                    System.out.println(" - Animal Color: " + anim.getColor());
                    if(anim instanceof Cat){
                        Cat catInst = (Cat) anim;
                        System.out.println(" - Animal type is : Cat");
                        System.out.println(" - Cat chip ID: " + catInst.getChipId());
                    }else if(anim instanceof Fish){
                        Fish fishInst = (Fish) anim;
                        System.out.println(" - Animal type is : Fish");
                        System.out.println(" - Fish living Environment: " + fishInst.getLivingEnv().getFishLivValue());
                    }
                    System.out.println("------------------");
                    System.out.println();
                }
                System.out.println("********************************");
                System.out.println();
                System.out.println();
            }

            // Retrieve PetStores and their associated Product
            //List<PetStore> petStores = em.createQuery("SELECT p FROM PetStore p", PetStore.class).getResultList();
            System.out.println(" -------- Retrieve PetStores and their associated Product --------- ");
            for (PetStore petStore : petStores) {
                System.out.println("PetStore Name: " + petStore.getName());
                System.out.println("PetStore Manager: " + petStore.getManagerName());
                System.out.println("PetStore Address City: " + petStore.getAddress().getCity());
                System.out.println("PetStore Address Street: " + petStore.getAddress().getStreet());
                System.out.println("PetStore Address Zip code: " + petStore.getAddress().getZipCode());
                System.out.println("PetStore Address Number: " + petStore.getAddress().getNumber());
                List<Product> products = petStore.getProducts();
                for (Product product : products) {
                    System.out.println(" - Product Id: " + product.getId());
                    System.out.println(" - Product Code: " + product.getCode());
                    System.out.println(" - Product Label: " + product.getLabel());
                    System.out.println(" - Product Type: " + product.getType().getTypeValue());
                    System.out.println(" - Product Price: " + product.getPrice());
                    System.out.println("------------------");
                    System.out.println();
                }
                System.out.println("********************************");
                System.out.println();
                System.out.println();
            }
        } catch (Exception e) {
            // Rollback transaction if any exception occurs
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Close EntityManager and EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}