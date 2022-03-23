package com.fpt.mock.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@TypeDefs({
    @TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
    )
})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "name", columnDefinition = "text")
    private String name;

    @Column(name = "category", columnDefinition = "text")
    private String category;

    @Column(name = "price", columnDefinition = "double precision")
    private Double price;

    @Column(name = "discount", columnDefinition = "double precision")
    private Double discount;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "thumbnailImage", columnDefinition = "text")
    private String thumbnailImage;

    @Type(type = "string-array")
    @Column(name = "otherImages", columnDefinition = "text[]")
    private String[] otherImages;

    public String getThumbnailImage() {
        return "/products/" + thumbnailImage;
    }

    public String[] getOtherImages() {
        return Arrays.stream(otherImages).map(image -> "/products/" + image).toArray(String[]::new);
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
