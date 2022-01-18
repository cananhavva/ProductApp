package com.canan.productapp.services;

import com.canan.productapp.entity.Product;
import com.canan.productapp.repository.ProductRepository;
import com.canan.productapp.resource.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional< Product > productDb = this.productRepository.findById(product.getId());

        if (productDb.isPresent()) {
            Product productUpdate = productDb.get();
            productUpdate.setId(product.getId());
            productUpdate.setName(product.getName());
            productUpdate.setDescription(product.getDescription());
            productRepository.save(productUpdate);
            return productUpdate;
        } else {

            NotFoundException("Record not found with id : " + product.getId());
        }
        return product;
    }

    private void NotFoundException(String s) {
    }

    @Override
    public List < Product > getAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) {

        Optional < Product > productDb = this.productRepository.findById(productId);

        if (productDb.isPresent()) {
            return productDb.get();
        } else {
            try {
                throw new ResourceNotFoundException("Record not found with id : " + productId);
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteProduct(long productId) {
        Optional < Product > productDb = this.productRepository.findById(productId);

        if (productDb.isPresent()) {
            this.productRepository.delete(productDb.get());
        } else {
            try {
                throw new ResourceNotFoundException("Record not found with id : " + productId);
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
