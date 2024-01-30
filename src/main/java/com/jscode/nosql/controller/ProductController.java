package com.jscode.nosql.controller;

import com.jscode.nosql.entity.Product;
import com.jscode.nosql.models.ProductDto;
import com.jscode.nosql.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(this.productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") int id){
        return new ResponseEntity<>(this.productService.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductDto dto){
        return new ResponseEntity<>(this.productService.save(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id,@RequestBody ProductDto dto){
        return new ResponseEntity<>(this.productService.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") int id){
        return new ResponseEntity<>(this.productService.delete(id), HttpStatus.OK);
    }
}
