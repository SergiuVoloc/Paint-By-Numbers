package com.ecommerce.ecommerce.modules.order;

import com.ecommerce.ecommerce.modules.address.Address;
import com.ecommerce.ecommerce.modules.discount.Discount;
import com.ecommerce.ecommerce.modules.orderItem.OrderItem;
import com.ecommerce.ecommerce.modules.orderStatus.OrderStatus;
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


    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "orderStatus_id")
    private OrderStatus orderStatus;

    @OneToMany(
            targetEntity = OrderItem.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<OrderItem> items = new ArrayList<>();

    public Order(){}

    public Order(float delivery_fee, float amount, Address address, User user, Discount discount, OrderStatus orderStatus) {
        this.created_at = new Date();
        this.modified = new Date();
        this.delivery_fee = delivery_fee;
        this.amount = amount;
        this.address = address;
        this.user = user;
        this.discount = discount;
        this.orderStatus = orderStatus;
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

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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
                ", address=" + address +
                ", user=" + user +
                ", discount=" + discount +
                ", orderStatus=" + orderStatus +
                ", items=" + items +
                '}';
    }
}
