package com.fpt.mock.api;

import com.fpt.mock.dto.IndexProductDto;
import com.fpt.mock.exception.GlobalRequestException;
import com.fpt.mock.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/v1/products")
    public List<IndexProductDto> getProducts(@RequestParam int limit,
                                             @RequestParam int page,
                                             @Nullable @RequestParam String category) {
        try {
            if(category == null || category.equals("")) {
                return productService.getIndexProducts(limit, page);
            }
            else {
                return productService.getIndexProducts(limit, page, category);
            }
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/v1/products/q")
    public List<IndexProductDto> getProductsByName(@RequestParam int limit,
                                                   @RequestParam int page,
                                                   @RequestParam String category) {
        try {
            return productService.getIndexProductsByName(limit, page, category);
        }
        catch(Exception exception) {
            throw new GlobalRequestException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
