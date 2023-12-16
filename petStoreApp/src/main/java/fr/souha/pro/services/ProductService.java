package fr.souha.pro.services;

import fr.souha.pro.entities.PetStore;
import fr.souha.pro.entities.Product;
import fr.souha.pro.exceptions.products.ProductNotFoundException;
import fr.souha.pro.exceptions.products.ProductServiceException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductService extends AbstractService{
    public Product createProduct(Product product) throws ProductServiceException {
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            return product;
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw new ProductServiceException("Failed to create product");
        }

    }

    public List<Product> getAllProducts() throws ProductServiceException{
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p order by p.id asc", Product.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ProductServiceException("Failed to fetch products");
        }
    }

    public Product findProductById(Long id) {
        try {
            Product product = em.find(Product.class, id);
            if (product == null) {
                throw new ProductNotFoundException("Pet store not found with ID: " + id);
            }
            return product;
        }catch(ProductNotFoundException e){
            throw e;
        } catch (Exception e) {
            throw new ProductServiceException("Failed to find product by ID: " + id);
        }
    }

    public Product updateProduct(Product updatedProduct) throws ProductNotFoundException, ProductServiceException {
        try {
            Product existingProduct = em.find(Product.class, updatedProduct.getId());
            if (existingProduct == null) {
                throw new ProductNotFoundException("Product not found with ID: " + updatedProduct.getId());
            }

            em.getTransaction().begin();
            Product newProduct = em.merge(updatedProduct);
            em.getTransaction().commit();
            return newProduct;
        }catch (ProductNotFoundException e)  {
            throw e;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ProductServiceException("Failed to update Product with ID: " + updatedProduct.getId());
        }
    }

    public Product deleteProduct(Long id) throws ProductNotFoundException, ProductServiceException {
        try {
            Product productToDelete = em.find(Product.class, id);
            if (productToDelete == null) {
                throw new ProductNotFoundException("product not found with ID: " + id);
            }
            em.getTransaction().begin();
            em.remove(productToDelete);
            em.getTransaction().commit();
            return productToDelete;
        }catch(ProductNotFoundException e){
            throw e;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new ProductServiceException("Failed to delete product with ID: " + id);
        }
    }
}
