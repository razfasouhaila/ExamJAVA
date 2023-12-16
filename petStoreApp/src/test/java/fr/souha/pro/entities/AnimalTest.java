package fr.souha.pro.entities;

import fr.souha.pro.enums.FishLivEnv;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AnimalTest {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private Animal animal1;
    private Animal animal2;
    private Cat cat1;
    private Cat cat2;
    private Cat cat3;
    private Cat cat4;
    private Fish fish1;
    private Fish fish2;
    private Fish fish3;
    private Fish fish4;
    private PetStore petStore1;
    private PetStore petStore2;
    private static EntityManager em;
    private static EntityManagerFactory emf;

    @BeforeEach
    public void init() throws ParseException {
        emf = Persistence.createEntityManagerFactory("pet-store-app-souhaila");
        em = emf.createEntityManager();
        animal1 = new Animal(dateFormat.parse("18-10-2010"), "white");
        animal2 = new Animal(dateFormat.parse("13-11-2015"), "black");
        fish1 = new Fish(dateFormat.parse("15-12-2022"), "blue", FishLivEnv.FRESH_WATER);
        fish2 = new Fish(dateFormat.parse("5-03-2023"), "yellow", FishLivEnv.SEA_WATER);
        fish3 = new Fish(dateFormat.parse("18-11-2021"), "green", FishLivEnv.SEA_WATER);
        fish4 = new Fish(dateFormat.parse("18-11-2020"), "purple", FishLivEnv.FRESH_WATER);
        cat1 = new Cat(dateFormat.parse("15-10-2012"), "orange", "1236546");
        cat2 = new Cat(dateFormat.parse("18-7-2010"), "white", "111521");
        cat3 = new Cat(dateFormat.parse("18-11-2015"), "gray", "789654");
        cat4 = new Cat(dateFormat.parse("10-11-2020"), "black", "888545");

        //create pet stores
        petStore1 = new PetStore("petStore 1", "manager pet strore 1", new Address("123a", "street1", "1522a", "city 1"));
        petStore2 = new PetStore("petStore 2", "manager pet strore 2", new Address("123b", "street2", "1522b", "city 2"));


    }

    @AfterAll
    static void tearDown() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    private void persistAnimals(){
        em.getTransaction().begin();
        em.persist(animal1);
        em.persist(animal2);
        em.persist(cat1);
        em.persist(cat2);
        em.persist(cat3);
        em.persist(cat4);
        em.persist(fish1);
        em.persist(fish2);
        em.persist(fish3);
        em.persist(fish4);
        em.getTransaction().commit();
    }

    private void persistAnimalsAndStoresAndAddAnimalsToPetStores(){
        persistAnimals();
        persisteStores();
        petStore1.addAnimal(animal1);
        petStore1.addAnimal(cat1);
        petStore1.addAnimal(cat2);
        petStore1.addAnimal(fish1);
        petStore1.addAnimal(fish2);

        petStore2.addAnimal(animal2);
        petStore2.addAnimal(cat3);
        petStore2.addAnimal(cat4);
        petStore2.addAnimal(fish3);
        petStore2.addAnimal(fish4);
    }

    private void persisteStores(){
        em.getTransaction().begin();
        em.persist(petStore1);
        em.persist(petStore1);
        em.getTransaction().commit();
    }

    @Test
    public void testCreateAnimal(){
        persistAnimals();

        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a ORDER BY a.id ASC", Animal.class);
        List<Animal> animals = query.getResultList();

        assertNotNull(animals);
        assertEquals(10, animals.size());
        assertEquals(animal1, animals.get(0));
        assertEquals(animal2, animals.get(1));
        assertEquals(cat1, (Cat)animals.get(2));
        assertEquals(cat2, (Cat)animals.get(3));
        assertEquals(cat3, (Cat)animals.get(4));
        assertEquals(cat4, (Cat)animals.get(5));
        assertEquals(fish1, (Fish)animals.get(6));
        assertEquals(fish2, (Fish)animals.get(7));
        assertEquals(fish3, (Fish)animals.get(8));
        assertEquals(fish4, (Fish)animals.get(9));
    }

    @Test
    public void testGetAllAnimals() throws ParseException {
        persistAnimals();

        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a ORDER BY a.id ASC", Animal.class);
        List<Animal> animals = query.getResultList();

        assertNotNull(animals);
        assertEquals(10, animals.size());

        assertEquals(dateFormat.parse("18-10-2010"), animals.get(0).getBirth());
        assertEquals("white", animals.get(0).getColor());

        assertEquals(dateFormat.parse("13-11-2015"), animals.get(1).getBirth());
        assertEquals("black", animals.get(1).getColor());

        assertEquals(dateFormat.parse("15-10-2012"), animals.get(2).getBirth());
        assertEquals("orange", animals.get(2).getColor());
        assertEquals("1236546", ((Cat)animals.get(2)).getChipId());

        assertEquals(dateFormat.parse("18-7-2010"), animals.get(3).getBirth());
        assertEquals("white", animals.get(3).getColor());
        assertEquals("111521", ((Cat)animals.get(3)).getChipId());

        assertEquals(dateFormat.parse("18-11-2015"), animals.get(4).getBirth());
        assertEquals("gray", animals.get(4).getColor());
        assertEquals("789654", ((Cat)animals.get(4)).getChipId());

        assertEquals(dateFormat.parse("10-11-2020"), animals.get(5).getBirth());
        assertEquals("black", animals.get(5).getColor());
        assertEquals("888545", ((Cat)animals.get(5)).getChipId());


        assertEquals(dateFormat.parse("15-12-2022"), animals.get(6).getBirth());
        assertEquals("blue", animals.get(6).getColor());
        assertEquals("Fresh Water", ((Fish)animals.get(6)).getLivingEnv().getFishLivValue());

        assertEquals(dateFormat.parse("5-03-2023"), animals.get(7).getBirth());
        assertEquals("yellow", animals.get(7).getColor());
        assertEquals("Sea Water", ((Fish)animals.get(7)).getLivingEnv().getFishLivValue());

        assertEquals(dateFormat.parse("18-11-2021"), animals.get(8).getBirth());
        assertEquals("green", animals.get(8).getColor());
        assertEquals("Sea Water", ((Fish)animals.get(8)).getLivingEnv().getFishLivValue());

        assertEquals(dateFormat.parse("18-11-2020"), animals.get(9).getBirth());
        assertEquals("purple", animals.get(9).getColor());
        assertEquals("Fresh Water", ((Fish)animals.get(9)).getLivingEnv().getFishLivValue());
    }

    @Test
    public void testGetAnimalById() throws ParseException {
        persistAnimals();

        Animal foundedAnimal1 = em.find(Animal.class, animal1.getId());
        Animal foundedAnimal2 = em.find(Animal.class, animal2.getId());
        Animal foundedAnimal3 = em.find(Animal.class, cat1.getId());
        Animal foundedAnimal4 = em.find(Animal.class, cat2.getId());
        Animal foundedAnimal5 = em.find(Animal.class, cat3.getId());
        Animal foundedAnimal6 = em.find(Animal.class, cat4.getId());
        Animal foundedAnimal7 = em.find(Animal.class, fish1.getId());
        Animal foundedAnimal8 = em.find(Animal.class, fish2.getId());
        Animal foundedAnimal9 = em.find(Animal.class, fish3.getId());
        Animal foundedAnimal10 = em.find(Animal.class, fish4.getId());

        assertEquals(dateFormat.parse("18-10-2010"), foundedAnimal1.getBirth());
        assertEquals("white", foundedAnimal1.getColor());

        assertEquals(dateFormat.parse("13-11-2015"), foundedAnimal2.getBirth());
        assertEquals("black", foundedAnimal2.getColor());

        assertEquals(dateFormat.parse("15-10-2012"), foundedAnimal3.getBirth());
        assertEquals("orange", foundedAnimal3.getColor());
        assertEquals("1236546", ((Cat)foundedAnimal3).getChipId());

        assertEquals(dateFormat.parse("18-7-2010"), foundedAnimal4.getBirth());
        assertEquals("white", foundedAnimal4.getColor());
        assertEquals("111521", ((Cat)foundedAnimal4).getChipId());

        assertEquals(dateFormat.parse("18-11-2015"), foundedAnimal5.getBirth());
        assertEquals("gray", foundedAnimal5.getColor());
        assertEquals("789654", ((Cat)foundedAnimal5).getChipId());

        assertEquals(dateFormat.parse("10-11-2020"), foundedAnimal6.getBirth());
        assertEquals("black", foundedAnimal6.getColor());
        assertEquals("888545", ((Cat)foundedAnimal6).getChipId());


        assertEquals(dateFormat.parse("15-12-2022"), foundedAnimal7.getBirth());
        assertEquals("blue", foundedAnimal7.getColor());
        assertEquals("Fresh Water", ((Fish)foundedAnimal7).getLivingEnv().getFishLivValue());

        assertEquals(dateFormat.parse("5-03-2023"), foundedAnimal8.getBirth());
        assertEquals("yellow", foundedAnimal8.getColor());
        assertEquals("Sea Water", ((Fish)foundedAnimal8).getLivingEnv().getFishLivValue());

        assertEquals(dateFormat.parse("18-11-2021"), foundedAnimal9.getBirth());
        assertEquals("green", foundedAnimal9.getColor());
        assertEquals("Sea Water", ((Fish)foundedAnimal9).getLivingEnv().getFishLivValue());

        assertEquals(dateFormat.parse("18-11-2020"), foundedAnimal10.getBirth());
        assertEquals("purple", foundedAnimal10.getColor());
        assertEquals("Fresh Water", ((Fish)foundedAnimal10).getLivingEnv().getFishLivValue());
    }

    @Test
    public void testUpdateAnimal() throws ParseException {
        persistAnimals();
        animal1.setBirth(dateFormat.parse("1-1-2021"));
        animal1.setColor("brown");

        fish2.setBirth(dateFormat.parse("2-2-2022"));
        fish2.setColor("maginta");
        fish2.setLivingEnv(FishLivEnv.FRESH_WATER);

        em.getTransaction().begin();
        em.merge(animal1);
        em.merge(fish2);
        em.getTransaction().commit();

        Animal foundedAnimal1 = em.find(Animal.class, animal1.getId());
        Animal foundedAnimal2 = em.find(Animal.class, fish2.getId());

        assertEquals(dateFormat.parse("1-1-2021"), foundedAnimal1.getBirth());
        assertEquals("brown", foundedAnimal1.getColor());

        assertEquals(dateFormat.parse("2-2-2022"), foundedAnimal2.getBirth());
        assertEquals("maginta", foundedAnimal2.getColor());
        assertEquals("Fresh Water", ((Fish)foundedAnimal2).getLivingEnv().getFishLivValue());
    }
    
    @Test
    public void testDeleteAnimal(){
        persistAnimals();

        em.getTransaction().begin();
        em.remove(animal1);
        em.remove(cat4);
        em.getTransaction().commit();

        Animal foundedAnimal1 = em.find(Animal.class, animal1.getId());
        Animal foundedAnimal2 = em.find(Animal.class, cat4.getId());

        assertNull(foundedAnimal1);
        assertNull(foundedAnimal2);

        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a ORDER BY a.id ASC", Animal.class);
        List<Animal> animals = query.getResultList();

        assertNotNull(animals);
        assertEquals(8, animals.size());
    }

    @Test
    public void testAnimalsStatistics(){

        persistAnimals();

        TypedQuery<Animal> query1 = em.createQuery("SELECT a FROM Animal a ORDER BY a.id ASC", Animal.class);
        List<Animal> animals = query1.getResultList();
        int numberOfAnimals = animals.size();

        TypedQuery<Cat> query2 = em.createQuery("SELECT c FROM Cat c ORDER BY c.id ASC", Cat.class);
        List<Cat> cats = query2.getResultList();
        int numberOfCats = cats.size();

        TypedQuery<Fish> query = em.createQuery("SELECT f FROM Fish f ORDER BY f.id ASC", Fish.class);
        List<Fish> fishs = query.getResultList();
        int numberOfFishs = fishs.size();

        assertEquals(10, numberOfAnimals);
        assertEquals(4, numberOfCats);
        assertEquals(4, numberOfFishs);
    }

    @Test
    public void testRelationOneToManyBetweenAnimalAndPetStore(){
        persistAnimalsAndStoresAndAddAnimalsToPetStores();

        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a ORDER BY a.id ASC", Animal.class);
        List<Animal> animals = query.getResultList();

        assertNotNull(animals);
        assertEquals(10, animals.size());

        assertEquals("petStore 1", animals.get(0).getPetStore().getName());
        assertEquals("manager pet strore 1", animals.get(0).getPetStore().getManagerName());

        assertEquals("petStore 2", animals.get(1).getPetStore().getName());
        assertEquals("manager pet strore 2", animals.get(1).getPetStore().getManagerName());

        assertEquals("petStore 1", animals.get(2).getPetStore().getName());
        assertEquals("manager pet strore 1", animals.get(2).getPetStore().getManagerName());

        assertEquals("petStore 1", animals.get(3).getPetStore().getName());
        assertEquals("manager pet strore 1", animals.get(3).getPetStore().getManagerName());

        assertEquals("petStore 2", animals.get(4).getPetStore().getName());
        assertEquals("manager pet strore 2", animals.get(4).getPetStore().getManagerName());

        assertEquals("petStore 2", animals.get(5).getPetStore().getName());
        assertEquals("manager pet strore 2", animals.get(5).getPetStore().getManagerName());


        assertEquals("petStore 1", animals.get(6).getPetStore().getName());
        assertEquals("manager pet strore 1", animals.get(6).getPetStore().getManagerName());

        assertEquals("petStore 1", animals.get(7).getPetStore().getName());
        assertEquals("manager pet strore 1", animals.get(7).getPetStore().getManagerName());

        assertEquals("petStore 2", animals.get(8).getPetStore().getName());
        assertEquals("manager pet strore 2", animals.get(8).getPetStore().getManagerName());

        assertEquals("petStore 2", animals.get(9).getPetStore().getName());
        assertEquals("manager pet strore 2", animals.get(9).getPetStore().getManagerName());
    }
}
