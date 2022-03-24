package com.fpt.mock.dto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndexProductDto {

    private UUID id;
    private String name;
    private double price;
    private double discount;
    private String thumbnailImage;

    public String getThumbnailImage() {
        return "/products/" + thumbnailImage;
    }

    public String getPrice() {
        Locale locale = new Locale("nv", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(price);
    }

    public String getDiscount() {
        Locale locale = new Locale("nv", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(discount);
    }

}
