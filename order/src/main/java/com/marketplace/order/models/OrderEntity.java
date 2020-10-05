package com.marketplace.order.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@NodeEntity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long orderId;

    private long price;

    private Date orderDate;

    private Date shipmentDate;

    private String status;

    private long buyerId;

    private List<String> productId;

    private long addressId;

    private long sellerId;

    @Relationship(type = "Belong_to", direction = Relationship.INCOMING)
    private ReviewEntity review;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return price == that.price &&
                buyerId == that.buyerId &&
                addressId == that.addressId &&
                sellerId == that.sellerId &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(orderDate, that.orderDate) &&
                Objects.equals(shipmentDate, that.shipmentDate) &&
                Objects.equals(status, that.status) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, price, orderDate, shipmentDate, status, buyerId, productId, addressId, sellerId, review);
    }
}
