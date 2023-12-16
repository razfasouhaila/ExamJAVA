package fr.souha.pro.exceptions.products;

public class ProductServiceException extends RuntimeException{
    public ProductServiceException(String message){
        super(message);
    }
}
