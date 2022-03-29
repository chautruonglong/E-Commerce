package com.fpt.mock.dto;

import com.fpt.mock.entity.Order;
import com.fpt.mock.entity.Product;
import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class OrderProductDto {

    private UUID orderId;
    private UUID productId;
    private String name;
    private String category;
    private String price;
    private String discount;
    private String description;
    private String thumbnailImage;
    private String[] otherImages;
    private Date createdAt;
    private Date updatedAt;

    public OrderProductDto(Product product, Order order) {
        orderId = order.getId();
        createdAt = order.getCreatedAt();
        updatedAt = order.getUpdatedAt();

        productId = product.getId();
        name = product.getName();
        category = product.getCategory();
        price = product.getPrice();
        discount = product.getDiscount();
        description = product.getDescription();
        thumbnailImage = product.getThumbnailImage();
        otherImages = product.getOtherImages();
    }

}
