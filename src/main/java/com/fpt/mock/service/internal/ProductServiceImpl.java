package com.fpt.mock.service.internal;

import com.fpt.mock.dto.IndexProductDto;
import com.fpt.mock.dto.ProductCreationDto;
import com.fpt.mock.entity.Product;
import com.fpt.mock.repository.ProductRepository;
import com.fpt.mock.service.ProductService;
import com.fpt.mock.util.FileUtil;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FileUtil fileUtil;

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

    @Override
    public List<IndexProductDto> getIndexProductsByName(int limit, int page, String key) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, limit, sort);
        return productRepository.findIndexProductsByName(key, pageable);
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new RuntimeException("Product not in database"));
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Product createProduct(ProductCreationDto productCreationDto) throws IOException {
        String fileName = fileUtil.writeToDiskFromBuffer(productCreationDto.getThumbnailImage());

        Product product = Product.builder()
            .name(productCreationDto.getName())
            .category(productCreationDto.getCategory())
            .price(productCreationDto.getPrice())
            .discount(productCreationDto.getDiscount())
            .description(productCreationDto.getDescription())
            .thumbnailImage(fileName)
            .otherImages(new String[0])
            .build();

        return productRepository.save(product);
    }

}
