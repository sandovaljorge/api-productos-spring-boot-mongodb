package com.jscode.nosql.service;

import com.jscode.nosql.entity.Product;
import com.jscode.nosql.models.ProductDto;
import com.jscode.nosql.repository.ProductRepository;
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

    public Product getOne(int id){
        return this.productRepository.findById(id).get();
    }

    public Product save(ProductDto dto){
        int id = autoIncrement();
        Product product = new Product(id,dto.getName(), dto.getPrice());
        return this.productRepository.save(product);
    }

    public Product update(int id, ProductDto dto){
        Product product = this.productRepository.findById(id).get();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return this.productRepository.save(product);
    }

    public Product delete(int id){
        Product product = this.productRepository.findById(id).get();
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
