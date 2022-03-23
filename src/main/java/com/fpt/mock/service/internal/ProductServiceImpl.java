package com.fpt.mock.service.internal;

import com.fpt.mock.dto.IndexProductDto;
import com.fpt.mock.repository.ProductRepository;
import com.fpt.mock.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<IndexProductDto> getIndexProducts(int limit, int page, String category) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, limit, sort);
        return productRepository.findIndexProducts(category, pageable);
    }

    @Override
    public List<IndexProductDto> getIndexProducts(int limit, int page) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, limit, sort);
        return productRepository.findIndexProducts(pageable);
    }

}
