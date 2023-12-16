package fr.souha.pro.services;

import fr.souha.pro.entities.Address;
import fr.souha.pro.exceptions.addresses.AddressNotFoundException;
import fr.souha.pro.exceptions.addresses.AddressServiceException;
import jakarta.persistence.*;

import java.util.List;

public class AddressService extends AbstractService {



    public Address createAddress(Address address) throws AddressServiceException{
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            return address;
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new AddressServiceException("Failed to create address");
        }

    }

    public List<Address> getAllAddresses() throws AddressServiceException{
        try {
            TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a order by a.id asc", Address.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new AddressServiceException("Failed to fetch addresses");
        }
    }

    public Address findAddrssById(Long id) throws AddressNotFoundException, AddressServiceException {
        try {
            Address address = em.find(Address.class, id);
            if (address == null) {
                throw new AddressNotFoundException("Address not found with ID: " + id);
            }
            return address;
        }catch(AddressNotFoundException e) {
            throw e;
        }catch (Exception e) {
            throw new AddressServiceException("Failed to find address by ID: " + id);
        }
    }

    public Address updateAddress(Address updatedAddress) throws AddressNotFoundException, AddressServiceException {
        try {
            Address existingAddress = em.find(Address.class, updatedAddress.getId());
            if (existingAddress == null) {
                throw new AddressNotFoundException("Address not found with ID: " + updatedAddress.getId());
            }

            em.getTransaction().begin();
            Address newAddress = em.merge(updatedAddress);
            em.getTransaction().commit();
            return newAddress;
        }catch(AddressNotFoundException e){
            throw e;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AddressServiceException("Failed to update address with ID: " + updatedAddress.getId());
        }
    }

    public Address deleteAddress(Long id) throws AddressNotFoundException, AddressServiceException {
        try {
            Address addressToDelete = em.find(Address.class, id);
            if (addressToDelete == null) {
                throw new AddressNotFoundException("Address not found with ID: " + id);
            }
            em.getTransaction().begin();
            em.remove(addressToDelete);
            em.getTransaction().commit();
            return addressToDelete;
        }catch(AddressNotFoundException e) {
            throw e;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new AddressServiceException("Failed to delete address with ID: " + id);
        }
    }

}
