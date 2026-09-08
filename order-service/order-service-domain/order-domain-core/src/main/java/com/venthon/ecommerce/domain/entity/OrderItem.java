package com.venthon.ecommerce.domain.entity;

import com.example.ecommerce.domain.entity.BaseEntity;
import com.example.ecommerce.domain.valueobject.Money;
import com.example.ecommerce.domain.valueobject.OrderId;
import com.venthon.ecommerce.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {

    // ផ្ទុកព័ត៌មានជាប្រវត្ដិរបស់ product ដែលមិនអាចកែប្រែបាន (historic snapshot)
    private final Product product;

    private final Integer quantity;

    private final Money price;

    private final Money subTotal;

    // can change after order has been created
    private OrderId orderId;

    private OrderItem(Builder builder) {
        super.setId(builder.id);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
        orderId = builder.orderId;
    }


    // initialize order item
    void initializeOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }


    // តើតម្លៃត្រឹមត្រូវដែរឬទេ?
    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(product.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }


    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private OrderItemId id;
        private Product product;
        private Integer quantity;
        private Money price;
        private Money subTotal;
        private OrderId orderId;

        private Builder() {
        }

        public Builder id(OrderItemId val) {
            id = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
