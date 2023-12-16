package fr.souha.pro.exceptions.petStores;

public class PetStoreNotFoundException extends RuntimeException {

    public PetStoreNotFoundException(String message){
        super(message);
    }

}
