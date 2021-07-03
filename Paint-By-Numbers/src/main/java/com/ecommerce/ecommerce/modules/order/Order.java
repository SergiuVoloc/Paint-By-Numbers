package com.ecommerce.ecommerce.modules.order;


import com.ecommerce.ecommerce.modules.orderItem.OrderItem;
import com.ecommerce.ecommerce.modules.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="order_app")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Date created_at, modified;
    private float delivery_fee, amount;
    private final String currency = "USD";
    private final String method = "PayPal";
    private final String description = "Payment to PaintByNumbers.com";
    private final String intent = "ORDER";



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @OneToMany(
            targetEntity = OrderItem.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<OrderItem> items = new ArrayList<>();

    public Order(){}

    public Order(float delivery_fee, float amount, User user) {
        this.created_at = new Date();
        this.modified = new Date();
        this.delivery_fee = delivery_fee;
        this.amount = amount;
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMethod() {
        return method;
    }

    public String getIntent() {
        return intent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public float getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(float delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public void addItem(OrderItem item){
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", modified=" + modified +
                ", delivery_fee=" + delivery_fee +
                ", amount=" + amount +
                ", user=" + user +
                ", items=" + items +
                '}';
    }
}
