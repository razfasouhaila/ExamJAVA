package fr.souha.pro.exceptions.products;

public class InvalidProductException extends RuntimeException{
    public InvalidProductException(String message){
        super(message);
    }
}
