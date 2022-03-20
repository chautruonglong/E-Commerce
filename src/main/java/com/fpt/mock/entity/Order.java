package com.fpt.mock.entity;

import java.util.UUID;
import javax.persistence.Column;
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
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "productId", columnDefinition = "uuid")
    private UUID productId;

    @Column(name = "customerId", columnDefinition = "uuid")
    private UUID customerId;

}
