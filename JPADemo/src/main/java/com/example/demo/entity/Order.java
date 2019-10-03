package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="orders")

public class Order {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="order_id")
    private int orderId;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="customer_address")
    private String customerAddress;

    @Column(name="created_time")
    private Timestamp createdTime;


    @OneToMany(mappedBy = "order"  , cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
    private List<OrderItem> orderItem; // reverse side

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + orderId +
                ", customer_name='" + customerName + '\'' +
                ", customer_address='" + customerAddress + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
