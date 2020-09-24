package com.marketplace.order.models;

import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.EmbeddedId;
import java.util.Date;

@NodeEntity
public class OrderEntity {

    @EmbeddedId
    private OrderID id;

    private long price;

    private Date orderDate;

    private Date shipmentDate;

    private String status;

    public OrderEntity() {
    }

    public OrderID getId() {
        return id;
    }

    public void setId(OrderID id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
