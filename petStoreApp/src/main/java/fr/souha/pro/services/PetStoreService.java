package fr.souha.pro.services;

import fr.souha.pro.entities.Animal;
import fr.souha.pro.entities.PetStore;
import fr.souha.pro.entities.Product;
import fr.souha.pro.exceptions.petStores.PetStoreNotFoundException;
import fr.souha.pro.exceptions.petStores.PetStoreServiceException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PetStoreService extends AbstractService {

    public PetStore createPetStore(PetStore petStore) throws PetStoreServiceException {
        try {
            em.getTransaction().begin();
            if (petStore.getAddress() != null) {
                petStore.setAddress(em.merge(petStore.getAddress()));
            }
            em.persist(petStore);
            em.getTransaction().commit();
            return petStore;
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new PetStoreServiceException("Failed to create pet store");
        }

    }

    public List<PetStore> getAllPetStores() throws PetStoreServiceException{
        try {
            TypedQuery<PetStore> query = em.createQuery("SELECT p FROM PetStore p order by p.id asc", PetStore.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new PetStoreServiceException("Failed to fetch pet stores");
        }
    }

    public PetStore findPetStoreById(Long id) throws PetStoreNotFoundException, PetStoreServiceException {
        try {
            PetStore petStore = em.find(PetStore.class, id);
            if (petStore == null) {
                throw new PetStoreNotFoundException("Pet store not found with ID: " + id);
            }
            return petStore;
        }catch(PetStoreNotFoundException e){
            throw e;
        } catch (Exception e) {
            throw new PetStoreServiceException("Failed to find pet store by ID: " + id);
        }
    }

    public PetStore updatePetStore(PetStore updatedPetStore) throws PetStoreNotFoundException, PetStoreServiceException {
        try {
            PetStore existingPetStore = em.find(PetStore.class, updatedPetStore.getId());
            if (existingPetStore == null) {
                throw new PetStoreNotFoundException("Pet store not found with ID: " + updatedPetStore.getId());
            }

            em.getTransaction().begin();
            PetStore newPetStore = em.merge(updatedPetStore);
            em.getTransaction().commit();
            return newPetStore;
        }catch (PetStoreNotFoundException e)  {
            throw e;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PetStoreServiceException("Failed to update pet store with ID: " + updatedPetStore.getId());
        }
    }

    public PetStore deletePetStore(Long id) throws PetStoreNotFoundException, PetStoreServiceException {
        try {
            PetStore petStoreToDelete = em.find(PetStore.class, id);
            if (petStoreToDelete == null) {
                throw new PetStoreNotFoundException("pet store not found with ID: " + id);
            }
            em.getTransaction().begin();
            em.remove(petStoreToDelete);
            em.getTransaction().commit();
            return petStoreToDelete;
        }catch(PetStoreNotFoundException e){
            throw e;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PetStoreServiceException("Failed to delete pet store with ID: " + id);
        }
    }

    public List<PetStore> findPetStoresByName(String name) throws PetStoreServiceException {
        try {
            TypedQuery<PetStore> query = em.createQuery("SELECT p FROM PetStore p WHERE p.name = :name", PetStore.class);
            query.setParameter("name", name);
            return query.getResultList();
        } catch (Exception e) {
            throw new PetStoreServiceException("Failed to find pet stores by manager name");
        }
    }

    public List<PetStore> findPetStoresByManagerName(String managerName) throws PetStoreServiceException {
        try {
            TypedQuery<PetStore> query = em.createQuery("SELECT p FROM PetStore p WHERE p.managerName = :name", PetStore.class);
            query.setParameter("name", managerName);
            return query.getResultList();
        } catch (Exception e) {
            throw new PetStoreServiceException("Failed to find pet stores by manager name");
        }
    }

    public Product addProductToStore(Long petStoreId, Product product){
        try{
            PetStore petStore = findPetStoreById(petStoreId);
            petStore.addProduct(product);
            return product;
        }catch(PetStoreNotFoundException e){
            throw e;
        }catch(Exception e){
            throw new PetStoreServiceException("Failed to add product to petStore  with ID: " + petStoreId);
        }
    }

    public List<Product> getProductsByPetStoreId(Long petStoreId) throws PetStoreServiceException {
        try {
            PetStore petStore = findPetStoreById(petStoreId);
            return petStore.getProducts();
        }catch(PetStoreNotFoundException e){
            throw e;
        } catch (Exception e) {
            throw new PetStoreServiceException("Failed to get products for pet store with ID: " + petStoreId);
        }
    }

    public Animal addAnimalToStore(Long petStoreId, Animal animal){
        try{
            PetStore petStore = findPetStoreById(petStoreId);
            petStore.addAnimal(animal);
            return animal;
        }catch(PetStoreNotFoundException e){
            throw e;
        }catch(Exception e){
            throw new PetStoreServiceException("Failed to add animal to petStore  with ID: " + petStoreId);
        }
    }

    public List<Animal> getAnimalsByPetStoreId(Long petStoreId) throws PetStoreServiceException {
        try {
            PetStore petStore = findPetStoreById(petStoreId);
            return petStore.getAnimals();
        } catch (Exception e) {
            throw new PetStoreServiceException("Failed to get animals for pet store with ID: " + petStoreId);
        }
    }

    public Long countPetStores() throws PetStoreServiceException {
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM PetStore p", Long.class);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new PetStoreServiceException("Failed to count pet stores");
        }
    }

}
