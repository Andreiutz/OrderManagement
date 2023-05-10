package com.order_management.ordermanagement.model;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "orders")
public class Order implements Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "agent_id")
    private String agentId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "courier")
    @Enumerated(EnumType.STRING)
    private CourierType courierType;

    public Order() {}

    public Order(Integer integer, String agentId, Integer productId, Integer quantity, String clientId, PaymentMethod paymentMethod, CourierType courierType) {
        this.id = integer;
        this.agentId = agentId;
        this.productId = productId;
        this.quantity = quantity;
        this.clientId = clientId;
        this.paymentMethod = paymentMethod;
        this.courierType = courierType;
    }

    public Order(String agentId, Integer productId, Integer quantity, String clientId, PaymentMethod paymentMethod, CourierType courierType) {
        this.agentId = agentId;
        this.productId = productId;
        this.quantity = quantity;
        this.clientId = clientId;
        this.paymentMethod = paymentMethod;
        this.courierType = courierType;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CourierType getCourierType() {
        return courierType;
    }

    public void setCourierType(CourierType courierType) {
        this.courierType = courierType;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer integer) {
        this.id = integer;
    }
}
