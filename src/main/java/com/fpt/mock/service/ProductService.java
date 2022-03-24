package com.fpt.mock.service;

import com.fpt.mock.dto.IndexProductDto;
import com.fpt.mock.entity.Product;
import java.util.List;

public interface ProductService {

    List<IndexProductDto> getIndexProducts(int limit, int page, String category);
    List<IndexProductDto> getIndexProducts(int limit, int page);
    List<IndexProductDto> getIndexProductsByName(int limit, int page, String key);
    Product getProduct(String id);

}
