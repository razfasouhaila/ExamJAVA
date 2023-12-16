package fr.souha.pro.entities;

import fr.souha.pro.enums.ProdType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTest {
    private Product product1s1;
    private Product product2s1;
    private Product product3s1;
    private Product product1s2;
    private Product product2s2;
    private Product product3s2;
    private Product product1s1s2;
    private Product product2s1s2;
    private Product product3s1s2;
    private PetStore petStore1;
    private PetStore petStore2;
    private static EntityManager em;
    private static EntityManagerFactory emf;

    @BeforeEach
    public void init(){
        emf = Persistence.createEntityManagerFactory("pet-store-app-souhaila");
        em = emf.createEntityManager();

        product1s1 = new Product("code-101", "product 1", ProdType.ACCESSORY, 1500.00);
        product2s1 = new Product("code-102", "product 2", ProdType.FOOD, 500.00);
        product3s1 = new Product("code-103", "product 3", ProdType.CLEANING, 180.00);

        product1s2 = new Product("code-201", "product 1", ProdType.ACCESSORY, 1500.00);
        product2s2 = new Product("code-202", "product 2", ProdType.FOOD, 500.00);
        product3s2 = new Product("code-203", "product 3", ProdType.CLEANING, 180.00);

        product1s1s2 = new Product("code-501", "product 4", ProdType.ACCESSORY, 1900.00);
        product2s1s2 = new Product("code-502", "product 5", ProdType.FOOD, 300.00);
        product3s1s2 = new Product("code-503", "product 6", ProdType.CLEANING, 190.00);

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

    private void persistProducts(){
        em.getTransaction().begin();
        em.persist(product1s1);
        em.persist(product2s1);
        em.persist(product3s1);
        em.persist(product1s2);
        em.persist(product2s2);
        em.persist(product3s2);
        em.persist(product1s1s2);
        em.persist(product2s1s2);
        em.persist(product3s1s2);
        em.getTransaction().commit();
    }

    private void persisteStores(){
        em.getTransaction().begin();
        em.persist(petStore1);
        em.persist(petStore1);
        em.getTransaction().commit();
    }

    private void persistProductsAndStoresAndAddProductToPetStores(){
        persistProducts();
        persisteStores();
        petStore1.addProduct(product1s1);
        petStore1.addProduct(product2s1);
        petStore1.addProduct(product3s1);
        petStore2.addProduct(product1s2);
        petStore2.addProduct(product2s2);
        petStore2.addProduct(product3s2);
        petStore1.addProduct(product1s1s2);
        petStore1.addProduct(product2s1s2);
        petStore1.addProduct(product3s1s2);
        petStore2.addProduct(product1s1s2);
        petStore2.addProduct(product2s1s2);
        petStore2.addProduct(product3s1s2);
    }

    @Test
    public void testCreateProduct(){
        persistProducts();

        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.id ASC", Product.class);
        List<Product> products = query.getResultList();

        assertEquals(9, products.size());
        assertEquals(product1s1, products.get(0));
        assertEquals(product2s1, products.get(1));
        assertEquals(product3s1, products.get(2));
    }

    @Test
    public void testGetAllProducts(){
        persistProducts();

        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.id ASC", Product.class);
        List<Product> products = query.getResultList();

        assertNotNull(products);
        assertEquals(9, products.size());

        assertEquals("code-101", products.get(0).getCode());
        assertEquals("product 1", products.get(0).getLabel());
        assertEquals("Accessory", products.get(0).getType().getTypeValue());
        assertEquals(1500.00, products.get(0).getPrice());

        assertEquals("code-102", products.get(1).getCode());
        assertEquals("product 2", products.get(1).getLabel());
        assertEquals("Food", products.get(1).getType().getTypeValue());
        assertEquals(500.00, products.get(1).getPrice());

        assertEquals("code-103", products.get(2).getCode());
        assertEquals("product 3", products.get(2).getLabel());
        assertEquals("Cleaning", products.get(2).getType().getTypeValue());
        assertEquals(180.00, products.get(2).getPrice());
    }

    @Test
    public void testGetProductById(){
        persistProducts();

        Product foundedProduct1 = em.find(Product.class, product1s1.getId());
        Product foundedProduct2 = em.find(Product.class, product2s1.getId());

        assertNotNull(foundedProduct1);
        assertEquals("code-101", product1s1.getCode());
        assertEquals("product 1", product1s1.getLabel());
        assertEquals("Accessory", product1s1.getType().getTypeValue());
        assertEquals(1500.00, product1s1.getPrice());

        assertNotNull(foundedProduct2);
        assertEquals("code-102", product2s1.getCode());
        assertEquals("product 2", product2s1.getLabel());
        assertEquals("Food", product2s1.getType().getTypeValue());
        assertEquals(500.00, product2s1.getPrice());
    }

    @Test
    public void testUpdateProduct(){
        persistProducts();

        product1s1.setCode("xxx-111");
        product1s1.setPrice(980.00);
        product1s1.setLabel("updated product 1");

        product2s1.setCode("yyy-222");
        product2s1.setPrice(750.00);
        product2s1.setLabel("updated product 2");

        Product foundedProduct1 = em.find(Product.class, product1s1.getId());
        Product foundedProduct2 = em.find(Product.class, product2s1.getId());

        assertNotNull(foundedProduct1);
        assertEquals("xxx-111", product1s1.getCode());
        assertEquals("updated product 1", product1s1.getLabel());
        assertEquals("Accessory", product1s1.getType().getTypeValue());
        assertEquals(980.00, product1s1.getPrice());

        assertNotNull(foundedProduct2);
        assertEquals("yyy-222", product2s1.getCode());
        assertEquals("updated product 2", product2s1.getLabel());
        assertEquals("Food", product2s1.getType().getTypeValue());
        assertEquals(750.00, product2s1.getPrice());
    }

    @Test
    public void testDeleteProduct(){
        persistProducts();

        em.getTransaction().begin();
        em.remove(product1s1);
        em.remove(product2s1);
        em.getTransaction().commit();

        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.id ASC", Product.class);
        List<Product> products = query.getResultList();

        assertNotNull(products);
        assertEquals(7, products.size());

        assertEquals("code-103", products.get(0).getCode());
        assertEquals("product 3", products.get(0).getLabel());
        assertEquals("Cleaning", products.get(0).getType().getTypeValue());
        assertEquals(180.00, products.get(0).getPrice());
    }

    @Test
    public void testRelationManyToManyBetweenProductAndStore(){
        persistProductsAndStoresAndAddProductToPetStores();

        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.id ASC", Product.class);
        List<Product> products = query.getResultList();

        assertNotNull(products);
        assertEquals(9, products.size());

        assertEquals(1, products.get(0).getPetStores().size());
        assertEquals("petStore 1", products.get(0).getPetStores().get(0).getName());
        assertEquals("manager pet strore 1", products.get(0).getPetStores().get(0).getManagerName());

        assertEquals(1, products.get(1).getPetStores().size());
        assertEquals("petStore 1", products.get(1).getPetStores().get(0).getName());
        assertEquals("manager pet strore 1", products.get(1).getPetStores().get(0).getManagerName());

        assertEquals(1, products.get(2).getPetStores().size());
        assertEquals("petStore 1", products.get(2).getPetStores().get(0).getName());
        assertEquals("manager pet strore 1", products.get(2).getPetStores().get(0).getManagerName());

        assertEquals(1, products.get(3).getPetStores().size());
        assertEquals("petStore 2", products.get(3).getPetStores().get(0).getName());
        assertEquals("manager pet strore 2", products.get(3).getPetStores().get(0).getManagerName());

        assertEquals(1, products.get(4).getPetStores().size());
        assertEquals("petStore 2", products.get(4).getPetStores().get(0).getName());
        assertEquals("manager pet strore 2", products.get(4).getPetStores().get(0).getManagerName());

        assertEquals(1, products.get(5).getPetStores().size());
        assertEquals("petStore 2", products.get(5).getPetStores().get(0).getName());
        assertEquals("manager pet strore 2", products.get(5).getPetStores().get(0).getManagerName());

        assertEquals(2, products.get(6).getPetStores().size());
        assertEquals("petStore 1", products.get(6).getPetStores().get(0).getName());
        assertEquals("manager pet strore 1", products.get(6).getPetStores().get(0).getManagerName());

        assertEquals("petStore 2", products.get(6).getPetStores().get(1).getName());
        assertEquals("manager pet strore 2", products.get(6).getPetStores().get(1).getManagerName());

        assertEquals(2, products.get(7).getPetStores().size());
        assertEquals("petStore 1", products.get(7).getPetStores().get(0).getName());
        assertEquals("manager pet strore 1", products.get(7).getPetStores().get(0).getManagerName());

        assertEquals("petStore 2", products.get(7).getPetStores().get(1).getName());
        assertEquals("manager pet strore 2", products.get(7).getPetStores().get(1).getManagerName());

        assertEquals(2, products.get(8).getPetStores().size());
        assertEquals("petStore 1", products.get(8).getPetStores().get(0).getName());
        assertEquals("manager pet strore 1", products.get(8).getPetStores().get(0).getManagerName());

        assertEquals("petStore 2", products.get(8).getPetStores().get(1).getName());
        assertEquals("manager pet strore 2", products.get(8).getPetStores().get(1).getManagerName());
    }

}
