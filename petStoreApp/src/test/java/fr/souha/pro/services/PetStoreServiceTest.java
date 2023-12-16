package fr.souha.pro.services;

import fr.souha.pro.entities.*;
import fr.souha.pro.enums.FishLivEnv;
import fr.souha.pro.enums.ProdType;
import fr.souha.pro.exceptions.petStores.PetStoreNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PetStoreServiceTest {

    private PetStoreService petStoreService;
    private AddressService addressService;
    private Address address1;
    private Address address2;
    private PetStore petStore1;
    private PetStore petStore2;
    private Product product1s1;
    private Product product2s1;
    private Product product3s1;
    private Product product1s2;
    private Product product2s2;
    private Product product3s2;
    private Product product1s1s2;
    private Product product2s1s2;
    private Product product3s1s2;
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
    public void init() {
        petStoreService = new PetStoreService();
        addressService = new AddressService();
        address1 = new Address("123a", "street1", "1522a", "city 1");
        address2 = new Address("123b", "street2", "1522b", "city 2");
        petStore1 = new PetStore("petStore 1", "manager pet strore 1", address1);
        petStore2 = new PetStore("petStore 2", "manager pet strore 2", address2);

        product1s1 = new Product("code-101", "product 1", ProdType.ACCESSORY, 1500.00);
        product2s1 = new Product("code-102", "product 2", ProdType.FOOD, 500.00);
        product3s1 = new Product("code-103", "product 3", ProdType.CLEANING, 180.00);

        product1s2 = new Product("code-201", "product 1", ProdType.ACCESSORY, 1500.00);
        product2s2 = new Product("code-202", "product 2", ProdType.FOOD, 500.00);
        product3s2 = new Product("code-203", "product 3", ProdType.CLEANING, 180.00);

        product1s1s2 = new Product("code-501", "product 4", ProdType.ACCESSORY, 1900.00);
        product2s1s2 = new Product("code-502", "product 5", ProdType.FOOD, 300.00);
        product3s1s2 = new Product("code-503", "product 6", ProdType.CLEANING, 190.00);
    }


    private void savePetStores(){
        address1 = addressService.createAddress(address1);
        address2 = addressService.createAddress(address2);
        petStore1 = petStoreService.createPetStore(petStore1);
        petStore2 = petStoreService.createPetStore(petStore2);
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

    @Test
    public void testCreatePetStores(){
        savePetStores();

        List<PetStore> petStores = petStoreService.getAllPetStores();

        assertNotNull(petStores);
        assertEquals(2, petStores.size());

        assertEquals(petStore1, petStores.get(0));
        assertEquals(petStore2, petStores.get(1));
    }

    @Test
    public void getAllPetStores(){
        savePetStores();
        List<PetStore> petStores = petStoreService.getAllPetStores();

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
    public void testGetPetStoreById(){
        savePetStores();

        PetStore foundedPetStore1 = petStoreService.findPetStoreById(petStore1.getId());
        PetStore foundedPetStore2 = petStoreService.findPetStoreById(petStore2.getId());

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
        savePetStores();

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
        
        petStoreService.updatePetStore(petStore1);
        petStoreService.updatePetStore(petStore2);

        PetStore foundedPetStore1 = petStoreService.findPetStoreById(petStore1.getId());
        PetStore foundedPetStore2 = petStoreService.findPetStoreById(petStore2.getId());


        assertNotNull(foundedPetStore1);
        assertEquals("new petStore1", foundedPetStore1.getName());
        assertEquals("new manager petStore1", foundedPetStore1.getManagerName());
        assertEquals("xxxa", foundedPetStore1.getAddress().getNumber());
        assertEquals("new street 1", foundedPetStore1.getAddress().getStreet());
        assertEquals("1522a", foundedPetStore1.getAddress().getZipCode());
        assertEquals("new city 1", foundedPetStore1.getAddress().getCity());

        assertNotNull(foundedPetStore2);
        assertEquals("new petStore2", foundedPetStore2.getName());
        assertEquals("new manager petStore2", foundedPetStore2.getManagerName());
        assertEquals("yyyb", foundedPetStore2.getAddress().getNumber());
        assertEquals("new street 2", foundedPetStore2.getAddress().getStreet());
        assertEquals("1522b", foundedPetStore2.getAddress().getZipCode());
        assertEquals("new city 2", foundedPetStore2.getAddress().getCity());
    }

    @Test
    public void testDeletePetStore(){
        savePetStores();

        petStoreService.deletePetStore(petStore1.getId());
        petStoreService.deletePetStore(petStore2.getId());


        assertThrows(PetStoreNotFoundException.class, () -> {
            petStoreService.findPetStoreById(petStore1.getId());
        });

        assertThrows(PetStoreNotFoundException.class, () -> {
            petStoreService.findPetStoreById(petStore2.getId());
        });

        List<PetStore> petStores = petStoreService.getAllPetStores();
        List<Address> addresses = addressService.getAllAddresses();

        assertNotNull(petStores);
        assertEquals(0, petStores.size());

        assertNotNull(addresses);
        assertEquals(0, petStores.size());
    }

    @Test
    public void testFindPetStoresByName(){
        savePetStores();
        List<PetStore> foundedPetStores1 = petStoreService.findPetStoresByName("petStore 1");
        List<PetStore> foundedPetStores2 = petStoreService.findPetStoresByName("petStore 2");

        assertNotNull(foundedPetStores1);

        assertEquals(1, foundedPetStores1.size());

        assertEquals("petStore 1", foundedPetStores1.get(0).getName());
        assertEquals("manager pet strore 1", foundedPetStores1.get(0).getManagerName());
        assertEquals(address1,foundedPetStores1.get(0).getAddress());
        assertEquals("123a", foundedPetStores1.get(0).getAddress().getNumber());
        assertEquals("street1", foundedPetStores1.get(0).getAddress().getStreet());
        assertEquals("1522a", foundedPetStores1.get(0).getAddress().getZipCode());
        assertEquals("city 1", foundedPetStores1.get(0).getAddress().getCity());


        assertNotNull(foundedPetStores2);

        assertEquals(1, foundedPetStores2.size());

        assertEquals("petStore 2", foundedPetStores2.get(0).getName());
        assertEquals("manager pet strore 2", foundedPetStores2.get(0).getManagerName());
        assertEquals(address2,foundedPetStores2.get(0).getAddress());
        assertEquals("123b", foundedPetStores2.get(0).getAddress().getNumber());
        assertEquals("street2", foundedPetStores2.get(0).getAddress().getStreet());
        assertEquals("1522b", foundedPetStores2.get(0).getAddress().getZipCode());
        assertEquals("city 2", foundedPetStores2.get(0).getAddress().getCity());

    }

    @Test
    public void testFindPetStoresByManagerName(){
        savePetStores();

        List<PetStore> foundedPetStores1 = petStoreService.findPetStoresByManagerName("manager pet strore 1");
        List<PetStore> foundedPetStores2 = petStoreService.findPetStoresByManagerName("manager pet strore 2");

        assertNotNull(foundedPetStores1);

        assertEquals(1, foundedPetStores1.size());

        assertEquals("petStore 1", foundedPetStores1.get(0).getName());
        assertEquals("manager pet strore 1", foundedPetStores1.get(0).getManagerName());
        assertEquals(address1,foundedPetStores1.get(0).getAddress());
        assertEquals("123a", foundedPetStores1.get(0).getAddress().getNumber());
        assertEquals("street1", foundedPetStores1.get(0).getAddress().getStreet());
        assertEquals("1522a", foundedPetStores1.get(0).getAddress().getZipCode());
        assertEquals("city 1", foundedPetStores1.get(0).getAddress().getCity());


        assertNotNull(foundedPetStores2);

        assertEquals(1, foundedPetStores2.size());

        assertEquals("petStore 2", foundedPetStores2.get(0).getName());
        assertEquals("manager pet strore 2", foundedPetStores2.get(0).getManagerName());
        assertEquals(address2,foundedPetStores2.get(0).getAddress());
        assertEquals("123b", foundedPetStores2.get(0).getAddress().getNumber());
        assertEquals("street2", foundedPetStores2.get(0).getAddress().getStreet());
        assertEquals("1522b", foundedPetStores2.get(0).getAddress().getZipCode());
        assertEquals("city 2", foundedPetStores2.get(0).getAddress().getCity());

    }

    @Test
    public void testAddProductToStore(){
        savePetStores();

        PetStore foundedPetStore1 = petStoreService.findPetStoreById(petStore1.getId());
        PetStore foundedPetStore2 = petStoreService.findPetStoreById(petStore2.getId());

        foundedPetStore1.addProduct(product1s1);
        foundedPetStore1.addProduct(product2s1);
        foundedPetStore1.addProduct(product3s1);

        foundedPetStore2.addProduct(product1s2);
        foundedPetStore2.addProduct(product3s2);

        assertNotNull(petStore1.getProducts());
        assertEquals(3, petStore1.getProducts().size());

        assertNotNull(petStore2.getProducts());
        assertEquals(2, petStore2.getProducts().size());

        //products in store 1
        assertEquals("code-101", petStore1.getProducts().get(0).getCode());
        assertEquals("product 1", petStore1.getProducts().get(0).getLabel());
        assertEquals("Accessory", petStore1.getProducts().get(0).getType().getTypeValue());
        assertEquals(1500.00, petStore1.getProducts().get(0).getPrice());

        assertEquals("code-102", petStore1.getProducts().get(1).getCode());
        assertEquals("product 2", petStore1.getProducts().get(1).getLabel());
        assertEquals("Food", petStore1.getProducts().get(1).getType().getTypeValue());
        assertEquals(500.00, petStore1.getProducts().get(1).getPrice());

        assertEquals("code-103", petStore1.getProducts().get(2).getCode());
        assertEquals("product 3", petStore1.getProducts().get(2).getLabel());
        assertEquals("Cleaning", petStore1.getProducts().get(2).getType().getTypeValue());
        assertEquals(180.00, petStore1.getProducts().get(2).getPrice());

        // products in store2
        assertEquals("code-201", petStore2.getProducts().get(0).getCode());
        assertEquals("product 1", petStore2.getProducts().get(0).getLabel());
        assertEquals("Accessory", petStore2.getProducts().get(0).getType().getTypeValue());
        assertEquals(1500.00, petStore2.getProducts().get(0).getPrice());

        assertEquals("code-203", petStore2.getProducts().get(1).getCode());
        assertEquals("product 3", petStore2.getProducts().get(1).getLabel());
        assertEquals("Cleaning", petStore2.getProducts().get(1).getType().getTypeValue());
        assertEquals(180.00, petStore2.getProducts().get(1).getPrice());
    }

    @Test
    public void testAddProductToStoreAutreMethodeAvecService(){
        savePetStores();

        petStoreService.addProductToStore(petStore1.getId(), product1s1);
        petStoreService.addProductToStore(petStore1.getId(), product2s1);
        petStoreService.addProductToStore(petStore1.getId(), product3s1);

        petStoreService.addProductToStore(petStore2.getId(), product1s2);
        petStoreService.addProductToStore(petStore2.getId(), product3s2);

        assertNotNull(petStore1.getProducts());
        assertEquals(3, petStore1.getProducts().size());

        assertNotNull(petStore2.getProducts());
        assertEquals(2, petStore2.getProducts().size());

        //products in store 1
        assertEquals("code-101", petStore1.getProducts().get(0).getCode());
        assertEquals("product 1", petStore1.getProducts().get(0).getLabel());
        assertEquals("Accessory", petStore1.getProducts().get(0).getType().getTypeValue());
        assertEquals(1500.00, petStore1.getProducts().get(0).getPrice());

        assertEquals("code-102", petStore1.getProducts().get(1).getCode());
        assertEquals("product 2", petStore1.getProducts().get(1).getLabel());
        assertEquals("Food", petStore1.getProducts().get(1).getType().getTypeValue());
        assertEquals(500.00, petStore1.getProducts().get(1).getPrice());

        assertEquals("code-103", petStore1.getProducts().get(2).getCode());
        assertEquals("product 3", petStore1.getProducts().get(2).getLabel());
        assertEquals("Cleaning", petStore1.getProducts().get(2).getType().getTypeValue());
        assertEquals(180.00, petStore1.getProducts().get(2).getPrice());

        // products in store2
        assertEquals("code-201", petStore2.getProducts().get(0).getCode());
        assertEquals("product 1", petStore2.getProducts().get(0).getLabel());
        assertEquals("Accessory", petStore2.getProducts().get(0).getType().getTypeValue());
        assertEquals(1500.00, petStore2.getProducts().get(0).getPrice());

        assertEquals("code-203", petStore2.getProducts().get(1).getCode());
        assertEquals("product 3", petStore2.getProducts().get(1).getLabel());
        assertEquals("Cleaning", petStore2.getProducts().get(1).getType().getTypeValue());
        assertEquals(180.00, petStore2.getProducts().get(1).getPrice());
    }

    @Test
    public void testGetProductsByPetStoreId(){
        savePetStores();

        List<PetStore> petStores = petStoreService.getAllPetStores();

        petStores.get(0).addProduct(product1s1);
        petStores.get(0).addProduct(product2s1);
        petStores.get(0).addProduct(product3s1);

        petStores.get(0).addProduct(product1s1s2);
        petStores.get(0).addProduct(product2s1s2);
        petStores.get(0).addProduct(product3s1s2);

        petStores.get(1).addProduct(product1s2);
        petStores.get(1).addProduct(product2s2);
        petStores.get(1).addProduct(product3s2);

        petStores.get(1).addProduct(product1s1s2);
        petStores.get(1).addProduct(product2s1s2);
        petStores.get(1).addProduct(product3s1s2);

        List<Product> products1 = petStoreService.getProductsByPetStoreId(petStore1.getId());
        List<Product> products2 = petStoreService.getProductsByPetStoreId(petStore2.getId());

        assertNotNull(products1);
        assertEquals(6, products1.size());

        assertEquals("code-101", products1.get(0).getCode());
        assertEquals("product 1", products1.get(0).getLabel());
        assertEquals("Accessory", products1.get(0).getType().getTypeValue());
        assertEquals(1500.00, products1.get(0).getPrice());

        assertEquals("code-102", products1.get(1).getCode());
        assertEquals("product 2", products1.get(1).getLabel());
        assertEquals("Food", products1.get(1).getType().getTypeValue());
        assertEquals(500.00, products1.get(1).getPrice());

        assertEquals("code-103", products1.get(2).getCode());
        assertEquals("product 3", products1.get(2).getLabel());
        assertEquals("Cleaning", products1.get(2).getType().getTypeValue());
        assertEquals(180.00, products1.get(2).getPrice());


        assertEquals("code-501", products1.get(3).getCode());
        assertEquals("product 4", products1.get(3).getLabel());
        assertEquals("Accessory", products1.get(3).getType().getTypeValue());
        assertEquals(1900.00, products1.get(3).getPrice());

        assertEquals("code-502", products1.get(4).getCode());
        assertEquals("product 5", products1.get(4).getLabel());
        assertEquals("Food", products1.get(4).getType().getTypeValue());
        assertEquals(300.00, products1.get(4).getPrice());

        assertEquals("code-503", products1.get(5).getCode());
        assertEquals("product 6", products1.get(5).getLabel());
        assertEquals("Cleaning", products1.get(5).getType().getTypeValue());
        assertEquals(190.00, products1.get(5).getPrice());

        assertNotNull(products2);
        assertEquals(6, products2.size());

        assertEquals("code-201", products2.get(0).getCode());
        assertEquals("product 1", products2.get(0).getLabel());
        assertEquals("Accessory", products2.get(0).getType().getTypeValue());
        assertEquals(1500.00, products2.get(0).getPrice());

        assertEquals("code-202", products2.get(1).getCode());
        assertEquals("product 2", products2.get(1).getLabel());
        assertEquals("Food", products2.get(1).getType().getTypeValue());
        assertEquals(500.00, products2.get(1).getPrice());

        assertEquals("code-203", petStore2.getProducts().get(2).getCode());
        assertEquals("product 3", petStore2.getProducts().get(2).getLabel());
        assertEquals("Cleaning", petStore2.getProducts().get(2).getType().getTypeValue());
        assertEquals(180.00, petStore2.getProducts().get(2).getPrice());

        assertEquals("code-501", products2.get(3).getCode());
        assertEquals("product 4", products2.get(3).getLabel());
        assertEquals("Accessory", products2.get(3).getType().getTypeValue());
        assertEquals(1900.00, products2.get(3).getPrice());

        assertEquals("code-502", products2.get(4).getCode());
        assertEquals("product 5", products2.get(4).getLabel());
        assertEquals("Food", products2.get(4).getType().getTypeValue());
        assertEquals(300.00, products2.get(4).getPrice());

        assertEquals("code-503", products2.get(5).getCode());
        assertEquals("product 6", products2.get(5).getLabel());
        assertEquals("Cleaning", products2.get(5).getType().getTypeValue());
        assertEquals(190.00, products2.get(5).getPrice());

    }


    //createAnimals()
    @Test
    public void testAddAnimalToStore() throws ParseException {
        savePetStores();
        createAnimals();

        PetStore foundedPetStore1 = petStoreService.findPetStoreById(petStore1.getId());
        PetStore foundedPetStore2 = petStoreService.findPetStoreById(petStore2.getId());

        foundedPetStore1.addAnimal(animals1);
        foundedPetStore1.addAnimal(cat1s1);
        foundedPetStore1.addAnimal(cat2s1);

        foundedPetStore2.addAnimal(animals2);
        foundedPetStore2.addAnimal(cat1s2);
        foundedPetStore2.addAnimal(cat2s2);
        foundedPetStore2.addAnimal(fish1s2);
        foundedPetStore2.addAnimal(fish2s2);

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

    @Test
    public void testAddAnimalToStoreAutreMethodeAvecService() throws ParseException {
        savePetStores();
        createAnimals();

        petStoreService.addAnimalToStore(petStore1.getId(), animals1);
        petStoreService.addAnimalToStore(petStore1.getId(), cat1s1);
        petStoreService.addAnimalToStore(petStore1.getId(), cat2s1);

        petStoreService.addAnimalToStore(petStore2.getId(), animals2);
        petStoreService.addAnimalToStore(petStore2.getId(), cat1s2);
        petStoreService.addAnimalToStore(petStore2.getId(), cat2s2);
        petStoreService.addAnimalToStore(petStore2.getId(), fish1s2);
        petStoreService.addAnimalToStore(petStore2.getId(), fish2s2);

        assertNotNull(petStore1.getAnimals());
        assertEquals(3, petStore1.getAnimals().size());

        //animals in store 1
        assertEquals("Mon Oct 18 00:00:00 WET 2010", petStore1.getAnimals().get(0).getBirth().toString());
        assertEquals("white", petStore1.getAnimals().get(0).getColor());

        assertEquals("Mon Oct 15 00:00:00 WET 2012", petStore1.getAnimals().get(1).getBirth().toString());
        assertEquals("orange", petStore1.getAnimals().get(1).getColor());
        assertEquals("1236546", ((Cat)petStore1.getAnimals().get(1)).getChipId());

        assertEquals("Mon Oct 18 00:00:00 WET 2010", petStore1.getAnimals().get(2).getBirth().toString());
        assertEquals("white", petStore1.getAnimals().get(2).getColor());
        assertEquals("888545", ((Cat)petStore1.getAnimals().get(2)).getChipId());


        assertNotNull(petStore2.getAnimals());
        assertEquals(5, petStore2.getAnimals().size());

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

    @Test
    public void testGetAnimalsByPetStoreId() throws ParseException {
        savePetStores();
        createAnimals();

        petStore1.addAnimal(animals1);
        petStore1.addAnimal(cat1s1);
        petStore1.addAnimal(cat2s1);

        petStore2.addAnimal(animals2);
        petStore2.addAnimal(cat1s2);
        petStore2.addAnimal(cat2s2);
        petStore2.addAnimal(fish1s2);
        petStore2.addAnimal(fish2s2);

        List<Animal> animalsStore1 = petStoreService.getAnimalsByPetStoreId(petStore1.getId());
        List<Animal> animalsStore2 = petStoreService.getAnimalsByPetStoreId(petStore2.getId());

        assertNotNull(animalsStore1);
        assertEquals(3, animalsStore1.size());

        //animals in store 1
        assertEquals("Mon Oct 18 00:00:00 WET 2010", animalsStore1.get(0).getBirth().toString());
        assertEquals("white", animalsStore1.get(0).getColor());

        assertEquals("Mon Oct 15 00:00:00 WET 2012", animalsStore1.get(1).getBirth().toString());
        assertEquals("orange", animalsStore1.get(1).getColor());
        assertEquals("1236546", ((Cat)animalsStore1.get(1)).getChipId());

        assertEquals("Mon Oct 18 00:00:00 WET 2010", animalsStore1.get(2).getBirth().toString());
        assertEquals("white", animalsStore1.get(2).getColor());
        assertEquals("888545", ((Cat)animalsStore1.get(2)).getChipId());


        //animals in store 2
        assertNotNull(animalsStore2);
        assertEquals(5, animalsStore2.size());

        assertEquals("Mon Oct 18 00:00:00 WET 2010", animalsStore2.get(0).getBirth().toString());
        assertEquals("white", animalsStore2.get(0).getColor());

        assertEquals("Mon Oct 15 00:00:00 WET 2012", animalsStore2.get(1).getBirth().toString());
        assertEquals("orange", animalsStore2.get(1).getColor());
        assertEquals("1236546", ((Cat)animalsStore2.get(1)).getChipId());

        assertEquals("Mon Oct 18 00:00:00 WET 2010", animalsStore2.get(2).getBirth().toString());
        assertEquals("white", animalsStore2.get(2).getColor());
        assertEquals("888545", ((Cat)animalsStore2.get(2)).getChipId());

        assertEquals("Tue Dec 15 00:00:00 WEST 2020", animalsStore2.get(3).getBirth().toString());
        assertEquals("red", animalsStore2.get(3).getColor());
        assertEquals("Fresh Water", ((Fish)animalsStore2.get(3)).getLivingEnv().getFishLivValue());

        assertEquals("Wed Nov 18 00:00:00 WEST 2020", animalsStore2.get(4).getBirth().toString());
        assertEquals("red", animalsStore2.get(4).getColor());
        assertEquals("Sea Water", ((Fish)animalsStore2.get(4)).getLivingEnv().getFishLivValue());

    }

    @Test
    public void testCountPetStores(){
        savePetStores();
        Long count = petStoreService.countPetStores();

        assertNotNull(count);
        assertEquals(2, count);
    }
}
