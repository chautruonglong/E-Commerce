package com.fpt.mock.api;

import com.fpt.mock.dto.IndexProductDto;
import com.fpt.mock.dto.ProductCreationDto;
import com.fpt.mock.dto.ProductUpdateDto;
import com.fpt.mock.entity.Product;
import com.fpt.mock.exception.GlobalRequestException;
import com.fpt.mock.service.ProductService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<IndexProductDto>> getProducts(@RequestParam int limit,
                                                             @RequestParam int page,
                                                             @Nullable @RequestParam String category) {
        try {
            if(category == null || category.equals("")) {
                return ResponseEntity.ok(productService.getIndexProducts(limit, page));
            }
            else {
                return ResponseEntity.ok(productService.getIndexProducts(limit, page, category));
            }
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v1/products/q")
    public ResponseEntity<List<IndexProductDto>> getProductsByName(@RequestParam int limit,
                                                                   @RequestParam int page,
                                                                   @RequestParam String category) {
        try {
            return ResponseEntity.ok(productService.getIndexProductsByName(limit, page, category));
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v1/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        try {
            return ResponseEntity.ok(productService.getProduct(id));
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/v1/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/api/v1/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> postProduct(@Valid @ModelAttribute ProductCreationDto productCreationDto) {
        try {
            Product product = productService.createProduct(productCreationDto);
            return ResponseEntity.created(URI.create("/api/v1/products/" + product.getId()))
                .body(product);
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/api/v1/products/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @Valid @ModelAttribute ProductUpdateDto productCreationDto) {
        try {
            return ResponseEntity.ok(productService.updateProduct(id, productCreationDto));
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
