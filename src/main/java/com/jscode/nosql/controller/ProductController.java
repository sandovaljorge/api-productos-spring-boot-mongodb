package com.jscode.nosql.controller;

import com.jscode.nosql.entity.Product;
import com.jscode.nosql.models.ProductDto;
import com.jscode.nosql.service.ProductService;
import com.jscode.nosql.utils.dto.MessageDto;
import com.jscode.nosql.utils.exception.AttributeException;
import com.jscode.nosql.utils.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
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
    public ResponseEntity<Product> getOne(@PathVariable("id") int id)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(this.productService.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MessageDto> save(@Valid @RequestBody ProductDto dto)
            throws AttributeException {
        Product product = this.productService.save(dto);
        String message = "product " + product.getName() + " have been saved.";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@Valid @PathVariable("id") int id,@RequestBody ProductDto dto)
            throws ResourceNotFoundException, AttributeException {
        Product product = this.productService.update(id, dto);
        String message = "product " + product.getName() + " have been updated.";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") int id)
            throws ResourceNotFoundException {
        Product product = this.productService.delete(id);
        String message = "product " + product.getName() + " have been deleted.";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
