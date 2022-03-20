package com.fpt.mock.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "code", columnDefinition = "text")
    private String code;

    @Column(name = "name", columnDefinition = "text")
    private String name;

    @Column(name = "price", columnDefinition = "double precision")
    private String price;

    @Column(name = "images", columnDefinition = "text[]")
    private String[] images;
    
}
