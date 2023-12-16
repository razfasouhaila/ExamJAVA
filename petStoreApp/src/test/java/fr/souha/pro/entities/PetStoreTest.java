package fr.souha.pro.entities;

import fr.souha.pro.enums.FishLivEnv;
import fr.souha.pro.enums.ProdType;
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

public class PetStoreTest {
    private static EntityManager em;
    private static EntityManagerFactory emf;
    private Address address1;
    private Address address2;
    private PetStore petStore1;
    private PetStore petStore2;
    private Product product1s1;
    private Product product2s1;
    private Product product3s1;
    private Product product1s2;
    private Product product3s2;
    private Animal animals1;
    private Cat cat1s1;
    private Cat cat2s1;
    private Animal animals2;
    private Cat cat1s2;
    private Cat cat2s2;
    private Fish fish1s2;
    private Fish fish2s2;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @BeforeEach
    public void init() throws ParseException {
        emf = Persistence.createEntityManagerFactory("pet-store-app-souhaila");
        em = emf.createEntityManager();
        createAddresses();
        createPesStores();
        createAnimals();
        createProducts();
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

    private void createProducts(){
        // Create Product
        product1s1 = new Product("code-001", "product 1", ProdType.ACCESSORY, 1500.00);
        product2s1 = new Product("code-002", "product 2", ProdType.FOOD, 500.00);
        product3s1 = new Product("code-003", "product 3", ProdType.CLEANING, 180.00);

        product1s2 = new Product("code-001", "product 1", ProdType.ACCESSORY, 1500.00);
        product3s2 = new Product("code-003", "product 3", ProdType.CLEANING, 180.00);
    }

    private void createAnimals() throws ParseException {
        // create animals
        animals1 = new Animal(dateFormat.parse("18-10-2010"), "white");
        cat1s1 = new Cat(dateFormat.parse("15-10-2012"), "orange", "1236546");
        cat2s1 = new Cat(dateFormat.parse("18-10-2010"), "white", "888545");

        animals2 = new Animal(dateFormat.parse("18-10-2010"), "white");
        fish1s2 = new Fish(dateFormat.parse("15-12-2020"), "red", FishLivEnv.FRESH_WATER);
        fish2s2 = new Fish(dateFormat.parse("18-11-2020"), "red", FishLivEnv.SEA_WATER);
        cat1s2 = new Cat(dateFormat.parse("15-10-2012"), "orange", "1236546");
        cat2s2 = new Cat(dateFormat.parse("18-10-2010"), "white", "888545");
    }

    private void createAddresses(){
        address1 = new Address("123a", "street1", "1522a", "city 1");
        address2 = new Address("123b", "street2", "1522b", "city 2");
    }

    private void createPesStores(){
        //create pet stores
        petStore1 = new PetStore("petStore 1", "manager pet strore 1", address1);
        petStore2 = new PetStore("petStore 2", "manager pet strore 2", address2);
    }

    private void persistStoreWithAddressesWithAnimalsAndProducts(){
        em.getTransaction().begin();
        em.persist(address1);
        em.persist(address2);
        em.persist(product1s1);
        em.persist(product2s1);
        em.persist(product3s1);
        em.persist(product1s2);
        em.persist(product3s2);
        em.persist(animals1);
        em.persist(cat1s1);
        em.persist(cat2s1);
        em.persist(animals2);
        em.persist(animals2);
        em.persist(cat1s2);
        em.persist(cat1s2);
        em.persist(cat2s2);
        em.persist(cat2s2);
        em.persist(fish1s2);
        em.persist(fish1s2);
        em.persist(fish2s2);
        em.persist(fish2s2);
        em.persist(petStore1);
        em.persist(petStore2);
        em.getTransaction().commit();
    }

    @Test
    public void testCreateStore(){
        persistStoreWithAddressesWithAnimalsAndProducts();

        TypedQuery<PetStore> query = em.createQuery("SELECT p FROM PetStore p ORDER BY p.id ASC", PetStore.class);
        List<PetStore> petStores = query.getResultList();

        assertNotNull(petStores);
        assertEquals(2, petStores.size());
        assertEquals(petStores.get(0), petStore1);
        assertEquals(petStores.get(1), petStore2);
    }

    @Test
    public void testGetAllPetStores(){
        persistStoreWithAddressesWithAnimalsAndProducts();

        TypedQuery<PetStore> query = em.createQuery("SELECT p FROM PetStore p ORDER BY p.id ASC", PetStore.class);
        List<PetStore> petStores = query.getResultList();

        assertNotNull(petStores);
        assertEquals(2, petStores.size());

        assertEquals("petStore 1", petStores.get(0).getName());
        assertEquals("manager pet strore 1", petStores.get(0).getManagerName());
        assertEquals(address1, petStores.get(0).getAddress());
        assertEquals("123a", petStores.get(0).getAddress().getNumber());
        assertEquals("street1", petStores.get(0).getAddress().getStreet());
        assertEquals("1522a", petStores.get(0).getAddress().getZipCode());
        assertEquals("city 1", petStores.get(0).getAddress().getCity());

        assertEquals("petStore 2", petStores.get(1).getName());
        assertEquals("manager pet strore 2", petStores.get(1).getManagerName());
        assertEquals(address2, petStores.get(1).getAddress());
        assertEquals("123b", petStores.get(1).getAddress().getNumber());
        assertEquals("street2", petStores.get(1).getAddress().getStreet());
        assertEquals("1522b", petStores.get(1).getAddress().getZipCode());
        assertEquals("city 2", petStores.get(1).getAddress().getCity());
    }

    @Test
    public void testGetStoreById(){
        persistStoreWithAddressesWithAnimalsAndProducts();

        PetStore foundedPetStore1 = em.find(PetStore.class, petStore1.getId());
        PetStore foundedPetStore2 = em.find(PetStore.class, petStore2.getId());

        assertNotNull(foundedPetStore1);
        assertEquals("petStore 1", foundedPetStore1.getName());
        assertEquals("manager pet strore 1", foundedPetStore1.getManagerName());
        assertEquals(address1,foundedPetStore1.getAddress());
        assertEquals("123a", foundedPetStore1.getAddress().getNumber());
        assertEquals("street1", foundedPetStore1.getAddress().getStreet());
        assertEquals("1522a", foundedPetStore1.getAddress().getZipCode());
        assertEquals("city 1", foundedPetStore1.getAddress().getCity());

        assertNotNull(foundedPetStore2);
        assertEquals("petStore 2", foundedPetStore2.getName());
        assertEquals("manager pet strore 2", foundedPetStore2.getManagerName());
        assertEquals(address2,foundedPetStore2.getAddress());
        assertEquals("123b", foundedPetStore2.getAddress().getNumber());
        assertEquals("street2", foundedPetStore2.getAddress().getStreet());
        assertEquals("1522b", foundedPetStore2.getAddress().getZipCode());
        assertEquals("city 2", foundedPetStore2.getAddress().getCity());
    }

    @Test
    public void testUpdatePetStore(){
        persistStoreWithAddressesWithAnimalsAndProducts();

        Address newAddress1 = new Address();
        newAddress1.setNumber("xxxa");
        newAddress1.setStreet("new street 1");
        newAddress1.setZipCode("1522a");
        newAddress1.setCity("new city 1");

        Address newAddress2 = new Address();
        newAddress2.setNumber("yyyb");
        newAddress2.setStreet("new street 2");
        newAddress2.setZipCode("1522b");
        newAddress2.setCity("new city 2");

        petStore1.setName("new petStore1");
        petStore1.setManagerName("new manager petStore1");
        petStore1.setAddress(newAddress1);

        petStore2.setName("new petStore2");
        petStore2.setManagerName("new manager petStore2");
        petStore2.setAddress(newAddress2);

        em.getTransaction().begin();
        em.merge(petStore1);
        em.merge(petStore2);
        em.getTransaction().commit();

        PetStore foundPetStore1 = em.find(PetStore.class, petStore1.getId());
        PetStore foundPetStore2 = em.find(PetStore.class, petStore2.getId());

        assertNotNull(foundPetStore1);
        assertEquals("new petStore1", foundPetStore1.getName());
        assertEquals("new manager petStore1", foundPetStore1.getManagerName());
        assertEquals("xxxa", foundPetStore1.getAddress().getNumber());
        assertEquals("new street 1", foundPetStore1.getAddress().getStreet());
        assertEquals("1522a", foundPetStore1.getAddress().getZipCode());
        assertEquals("new city 1", foundPetStore1.getAddress().getCity());

        assertNotNull(foundPetStore2);
        assertEquals("new petStore2", foundPetStore2.getName());
        assertEquals("new manager petStore2", foundPetStore2.getManagerName());
        assertEquals("yyyb", foundPetStore2.getAddress().getNumber());
        assertEquals("new street 2", foundPetStore2.getAddress().getStreet());
        assertEquals("1522b", foundPetStore2.getAddress().getZipCode());
        assertEquals("new city 2", foundPetStore2.getAddress().getCity());
    }

    @Test
    public void testDeletePetStore(){
        persistStoreWithAddressesWithAnimalsAndProducts();

        em.getTransaction().begin();
        em.remove(petStore1);
        em.remove(petStore2);
        em.getTransaction().commit();

        PetStore foundPerStore1 = em.find(PetStore.class, petStore1.getId());
        PetStore foundPerStore2 = em.find(PetStore.class, petStore2.getId());

        assertNull(foundPerStore1);
        assertNull(foundPerStore2);

        TypedQuery<PetStore> query1 = em.createQuery("SELECT p FROM PetStore p ORDER BY p.id ASC", PetStore.class);
        List<PetStore> petStores = query1.getResultList();

        TypedQuery<Address> query2 = em.createQuery("SELECT a FROM Address a ORDER BY a.id ASC", Address.class);
        List<Address> addresses = query2.getResultList();

        assertNotNull(petStores);
        assertEquals(0, petStores.size());

        assertNotNull(addresses);
        assertEquals(0, petStores.size());
    }

    @Test
    public void testaddProductsToPetStores(){
        persistStoreWithAddressesWithAnimalsAndProducts();

        //add products to petStore1
        petStore1.addProduct(product1s1);
        petStore1.addProduct(product2s1);
        petStore1.addProduct(product3s1);

        //add products to petStore2
        petStore2.addProduct(product1s2);
        petStore2.addProduct(product3s2);

        assertNotNull(petStore1.getProducts());
        assertEquals(3, petStore1.getProducts().size());

        assertNotNull(petStore2.getProducts());
        assertEquals(2, petStore2.getProducts().size());

        //products in store 1
        assertEquals("code-001", petStore1.getProducts().get(0).getCode());
        assertEquals("product 1", petStore1.getProducts().get(0).getLabel());
        assertEquals("Accessory", petStore1.getProducts().get(0).getType().getTypeValue());
        assertEquals(1500.00, petStore1.getProducts().get(0).getPrice());

        assertEquals("code-002", petStore1.getProducts().get(1).getCode());
        assertEquals("product 2", petStore1.getProducts().get(1).getLabel());
        assertEquals("Food", petStore1.getProducts().get(1).getType().getTypeValue());
        assertEquals(500.00, petStore1.getProducts().get(1).getPrice());

        assertEquals("code-003", petStore1.getProducts().get(2).getCode());
        assertEquals("product 3", petStore1.getProducts().get(2).getLabel());
        assertEquals("Cleaning", petStore1.getProducts().get(2).getType().getTypeValue());
        assertEquals(180.00, petStore1.getProducts().get(2).getPrice());

        // products in store2
        assertEquals("code-001", petStore2.getProducts().get(0).getCode());
        assertEquals("product 1", petStore2.getProducts().get(0).getLabel());
        assertEquals("Accessory", petStore2.getProducts().get(0).getType().getTypeValue());
        assertEquals(1500.00, petStore2.getProducts().get(0).getPrice());

        assertEquals("code-003", petStore2.getProducts().get(1).getCode());
        assertEquals("product 3", petStore2.getProducts().get(1).getLabel());
        assertEquals("Cleaning", petStore2.getProducts().get(1).getType().getTypeValue());
        assertEquals(180.00, petStore2.getProducts().get(1).getPrice());
    }

    @Test
    public void testAddAnimalsToPetStores(){
        persistStoreWithAddressesWithAnimalsAndProducts();

        //add animals to petStore1
        petStore1.addAnimal(animals1);
        petStore1.addAnimal(cat1s1);
        petStore1.addAnimal(cat2s1);

        //add animals to petStore2
        petStore2.addAnimal(animals2);
        petStore2.addAnimal(cat1s2);
        petStore2.addAnimal(cat2s2);
        petStore2.addAnimal(fish1s2);
        petStore2.addAnimal(fish2s2);

        assertNotNull(petStore1.getAnimals());
        assertEquals(3, petStore1.getAnimals().size());

        assertNotNull(petStore2.getAnimals());
        assertEquals(5, petStore2.getAnimals().size());


        //animals in store 1
        assertEquals("Mon Oct 18 00:00:00 WET 2010", petStore1.getAnimals().get(0).getBirth().toString());
        assertEquals("white", petStore1.getAnimals().get(0).getColor());

        assertEquals("Mon Oct 15 00:00:00 WET 2012", petStore1.getAnimals().get(1).getBirth().toString());
        assertEquals("orange", petStore1.getAnimals().get(1).getColor());
        assertEquals("1236546", ((Cat)petStore1.getAnimals().get(1)).getChipId());

        assertEquals("Mon Oct 18 00:00:00 WET 2010", petStore1.getAnimals().get(2).getBirth().toString());
        assertEquals("white", petStore1.getAnimals().get(2).getColor());
        assertEquals("888545", ((Cat)petStore1.getAnimals().get(2)).getChipId());

        //animals in store 2
        assertEquals("Mon Oct 18 00:00:00 WET 2010", petStore2.getAnimals().get(0).getBirth().toString());
        assertEquals("white", petStore2.getAnimals().get(0).getColor());

        assertEquals("Mon Oct 15 00:00:00 WET 2012", petStore2.getAnimals().get(1).getBirth().toString());
        assertEquals("orange", petStore2.getAnimals().get(1).getColor());
        assertEquals("1236546", ((Cat)petStore2.getAnimals().get(1)).getChipId());

        assertEquals("Mon Oct 18 00:00:00 WET 2010", petStore2.getAnimals().get(2).getBirth().toString());
        assertEquals("white", petStore2.getAnimals().get(2).getColor());
        assertEquals("888545", ((Cat)petStore2.getAnimals().get(2)).getChipId());

        assertEquals("Tue Dec 15 00:00:00 WEST 2020", petStore2.getAnimals().get(3).getBirth().toString());
        assertEquals("red", petStore2.getAnimals().get(3).getColor());
        assertEquals("Fresh Water", ((Fish)petStore2.getAnimals().get(3)).getLivingEnv().getFishLivValue());

        assertEquals("Wed Nov 18 00:00:00 WEST 2020", petStore2.getAnimals().get(4).getBirth().toString());
        assertEquals("red", petStore2.getAnimals().get(4).getColor());
        assertEquals("Sea Water", ((Fish)petStore2.getAnimals().get(4)).getLivingEnv().getFishLivValue());
    }

}
