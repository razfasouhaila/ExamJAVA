package fr.souha.pro.services;

import fr.souha.pro.entities.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddressTest {
    private AddressService addressService;
    private Address address1;
    private Address address2;

    @BeforeEach
    public void init() {
        addressService = new AddressService();
        address1 = new Address("123a", "street1", "1522a", "city 1");
        address2 = new Address("123b", "street2", "1522b", "city 2");
    }

    private void saveAddresses(){
        address1 = addressService.createAddress(address1);
        address2 = addressService.createAddress(address2);
    }

    @Test
    public void testCreateAddress(){
        saveAddresses();
        List<Address> addresses = addressService.getAllAddresses();

        assertNotNull(addresses);
        assertEquals(2, addresses.size());

        assertEquals("123a", address1.getNumber());
        assertEquals("street1", address1.getStreet());
        assertEquals("1522a", address1.getZipCode());
        assertEquals("city 1", address1.getCity());

        assertEquals("123b", address2.getNumber());
        assertEquals("street2", address2.getStreet());
        assertEquals("1522b", address2.getZipCode());
        assertEquals("city 2", address2.getCity());
    }

}
