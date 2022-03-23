package com.fpt.mock.repository;

import com.fpt.mock.dto.IndexProductDto;
import com.fpt.mock.entity.Product;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("""
        select new com.fpt.mock.dto.IndexProductDto(p.name, p.price, p.discount, p.thumbnailImage)
        from Product p where p.category like :category
    """)
    List<IndexProductDto> findIndexProducts(String category, Pageable pageable);

    @Query("""
        select new com.fpt.mock.dto.IndexProductDto(p.name, p.price, p.discount, p.thumbnailImage)
        from Product p
    """)
    List<IndexProductDto> findIndexProducts(Pageable pageable);

}
