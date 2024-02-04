package com.jscode.nosql.service;

import com.jscode.nosql.entity.Product;
import com.jscode.nosql.models.ProductDto;
import com.jscode.nosql.repository.ProductRepository;
import com.jscode.nosql.utils.exception.AttributeException;
import com.jscode.nosql.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product getOne(int id) throws ResourceNotFoundException {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
    }

    public Product save(ProductDto dto) throws AttributeException {
        if(productRepository.existsByName(dto.getName()))
            throw new AttributeException("name already in use");
        int id = autoIncrement();
        Product product = new Product(id,dto.getName(), dto.getPrice());
        return this.productRepository.save(product);
    }

    public Product update(int id, ProductDto dto) throws ResourceNotFoundException, AttributeException {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));

        if(productRepository.existsByName(dto.getName()) && productRepository.findByName(dto.getName()).get().getId() != id)
            throw new AttributeException("name already in use");
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return this.productRepository.save(product);
    }

    public Product delete(int id) throws ResourceNotFoundException {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not Found"));
        this.productRepository.delete(product);
        return product;
    }


    //
    private int autoIncrement(){
        List<Product> products = this.productRepository.findAll();
        return products.isEmpty()? 1 :
                products
                        .stream()
                        .max(Comparator.comparing(Product::getId))
                        .get()
                        .getId() +1;
    }
}
