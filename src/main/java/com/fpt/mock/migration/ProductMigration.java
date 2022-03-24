package com.fpt.mock.migration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.mock.entity.Product;
import com.fpt.mock.repository.ProductRepository;
import com.fpt.mock.util.FileUtil;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
record ProductMigration(FileUtil fileUtil, ProductRepository productRepository) {

    @PostConstruct
    public void migrate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<?, ?>[] productJsons = objectMapper.readValue(Paths.get("src/main/resources/static/data/tiki.json").toFile(), Map[].class);

        log.info("Found {} products from file", productJsons.length);

        List<Product> products = new LinkedList<>();

        for(Map<?, ?> productJson : productJsons) {
            String imageUrl = (String) productJson.get("thumbnailImage");
            String thumbnailImage = fileUtil.writeToDiskFromInternet(imageUrl);

            List<?> imageUrls = (List<?>) productJson.get("otherImages");
            List<String> otherImages = new LinkedList<>();

            for(Object image : imageUrls) {
                otherImages.add(fileUtil.writeToDiskFromInternet((String) image));
            }

            products.add(Product.builder()
                             .name((String) productJson.get("name"))
                             .category((String) productJson.get("category"))
                             .price(((Integer) productJson.get("price")).doubleValue())
                             .discount(((Integer) productJson.get("discount")).doubleValue())
                             .description((String) productJson.get("description"))
                             .thumbnailImage(thumbnailImage)
                             .otherImages(otherImages.toArray(String[]::new))
                             .build());

            log.info("Saved {}", imageUrl);
        }

        productRepository.saveAll(products);
    }

}
