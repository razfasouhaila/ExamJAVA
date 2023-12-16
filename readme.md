# Projet Magasins d'Animaux

## Description

Ce projet Java utilise Maven pour la gestion des dépendances et met en œuvre JPA avec Hibernate pour l'interaction avec une base de données PostgreSQL. Il se concentre sur la modélisation des magasins d'animaux et des produits, offrant des fonctionnalités de gestion et de manipulation de données via des services dédiés.

## Dépendances

- **Hibernate Core 6.4.0.Final**: Intégration de la couche de persistance.
- **Hibernate JCache 6.2.5.Final**: Support pour la gestion du cache.
- **Ehcache 3.10.8**: Gestion du cache.
- **JAXB Runtime 4.0.3**: Pour la sérialisation/désérialisation XML.
- **PostgreSQL 42.7.1**: Base de données utilisée.
- **JUnit Jupiter API 5.8.2**: Pour les tests unitaires.

## Structure du Projet

### Packages

- **Enums**: FishLivEv, ProdType.
- **Exceptions**: PetStoreException, AddressException, ProductException.
- **Entités**: Address, Animal, Cat, Fish, PetStore, Product.

### Héritage

- **Relation d'héritage**: Animal et Cat, Animal et Fish.

## Services

### AbstractService

La classe `AbstractService` sert de base pour les services. Elle gère la configuration de l'EntityManagerFactory et de l'EntityManager pour simplifier l'accès aux opérations de persistance.

### AddressService

Le `AddressService` gère la gestion des adresses. Voici un aperçu des méthodes offertes :

- **createAddress**: Crée une nouvelle adresse dans la base de données.
- **getAllAddresses**: Récupère toutes les adresses enregistrées.
- **findAddressById**: Recherche une adresse spécifique par son ID.
- **updateAddress**: Met à jour une adresse existante.
- **deleteAddress**: Supprime une adresse par son ID.

### PetStoreService

Le `PetStoreService` s'occupe des opérations liées aux magasins d'animaux. Voici un résumé des méthodes :

- **createPetStore**: Crée un nouveau magasin d'animaux dans la base de données
- **getAllPetStores**: Récupère tous les magasins d'animaux enregistrés dans la base de données.
- **findPetStoreById**: Recherche un magasin d'animaux par son ID.
- **updatePetStore**: Met à jour un magasin d'animaux existant avec les informations du magasin mis à jour.
- **deletePetStore**: Supprime un magasin d'animaux par son ID.


### ProductService

Le `ProductService` gère les opérations CRUD pour les produits. Voici un aperçu des fonctionnalités :

- **createProduct**: Crée un nouveau produit dans la base de données.
- **getAllProducts**: Récupère tous les produits enregistrés dans la base de données.
- **findProductById**: Recherche un produit par son ID.
- **updateProduct**: Met à jour un produit existant avec les informations du produit mis à jour.
- **deleteProduct**: Supprime un produit par son ID.


## Tests

### Entités

Des tests unitaires sont effectués pour vérifier le bon fonctionnement des entités telles que Animal, Cat, Fish, etc.

### Services

Des tests de services sont effectués pour valider les fonctionnalités offertes par PetStoreService, AddressService, ProductService.

## Relations

- **Relation One-to-Many**: Entre PetStore et Animals.
- **Relation One-to-One**: Entre Address et PetStore.
- **Relation Many-to-Many**: Entre PetStores et Produits.
