package fr.souha.pro.entities;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    private Address address1;
    private Address address2;
    private static EntityManager em;
    private static EntityManagerFactory emf;

    @BeforeEach
    public void init(){
        emf = Persistence.createEntityManagerFactory("pet-store-app-souhaila");
        em = emf.createEntityManager();
        address1 = new Address("123a", "street1", "1522a", "city 1");
        address2 = new Address("123b", "street2", "1522b", "city 2");
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

    private void persistAddresses(){
        em.getTransaction().begin();
        em.persist(address1);
        em.persist(address2);
        em.getTransaction().commit();
    }

    @Test
    public void testCreateAddress(){
        persistAddresses();

        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a ORDER BY a.id ASC", Address.class);
        List<Address> addresses = query.getResultList();

        assertEquals(2, addresses.size());
        assertEquals(address1, addresses.get(0));
        assertEquals(address2, addresses.get(1));
    }

    @Test
    public void testGetAllAddresses(){
        persistAddresses();

        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a ORDER BY a.id ASC", Address.class);
        List<Address> addresses = query.getResultList();

        assertNotNull(addresses);
        assertEquals(2, addresses.size());

        assertEquals("123a", addresses.get(0).getNumber());
        assertEquals("street1", addresses.get(0).getStreet());
        assertEquals("1522a", addresses.get(0).getZipCode());
        assertEquals("city 1", addresses.get(0).getCity());

        assertEquals("123b", addresses.get(1).getNumber());
        assertEquals("street2", addresses.get(1).getStreet());
        assertEquals("1522b", addresses.get(1).getZipCode());
        assertEquals("city 2", addresses.get(1).getCity());
    }

    @Test
    public void testFindAddressById(){
        persistAddresses();

        Address foundAddress1 = em.find(Address.class, address1.getId());
        Address foundAddress2 = em.find(Address.class, address2.getId());

        assertNotNull(foundAddress1);
        assertEquals("123a", foundAddress1.getNumber());
        assertEquals("street1", foundAddress1.getStreet());
        assertEquals("1522a", foundAddress1.getZipCode());
        assertEquals("city 1", foundAddress1.getCity());

        assertNotNull(foundAddress2);
        assertEquals("123b", foundAddress2.getNumber());
        assertEquals("street2", foundAddress2.getStreet());
        assertEquals("1522b", foundAddress2.getZipCode());
        assertEquals("city 2", foundAddress2.getCity());
    }

    @Test
    @DisplayName("Test Update Address")
    public void testUpdateAddress(){
        persistAddresses();
        address1.setNumber("111a");
        address1.setStreet("updated street 1");
        address1.setCity("updated city 1");

        address2.setNumber("222b");
        address2.setStreet("updated street 2");
        address2.setCity("updated city 2");

        em.getTransaction().begin();
        em.merge(address1);
        em.merge(address2);
        em.getTransaction().commit();

        Address foundAddress1 = em.find(Address.class, address1.getId());
        Address foundAddress2 = em.find(Address.class, address2.getId());

        assertNotNull(foundAddress1);
        assertEquals("111a", foundAddress1.getNumber());
        assertEquals("updated street 1", foundAddress1.getStreet());
        assertEquals("1522a", foundAddress1.getZipCode());
        assertEquals("updated city 1", foundAddress1.getCity());

        assertNotNull(foundAddress2);
        assertEquals("222b", foundAddress2.getNumber());
        assertEquals("updated street 2", foundAddress2.getStreet());
        assertEquals("1522b", foundAddress2.getZipCode());
        assertEquals("updated city 2", foundAddress2.getCity());
    }

    @Test
    public void testDeleteAddress(){
        persistAddresses();

        em.getTransaction().begin();
        em.remove(address1);
        em.remove(address2);
        em.getTransaction().commit();

        Address foundAddress1 = em.find(Address.class, address1.getId());
        Address foundAddress2 = em.find(Address.class, address2.getId());

        assertNull(foundAddress1);
        assertNull(foundAddress2);

        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a ORDER BY a.id ASC", Address.class);
        List<Address> addresses = query.getResultList();

        assertNotNull(addresses);
        assertEquals(0, addresses.size());
    }

}
