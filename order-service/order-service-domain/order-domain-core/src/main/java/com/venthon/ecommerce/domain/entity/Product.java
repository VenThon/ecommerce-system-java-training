package com.venthon.ecommerce.domain.entity;

import com.example.ecommerce.domain.entity.BaseEntity;
import com.example.ecommerce.domain.valueobject.Money;
import com.example.ecommerce.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {

    private  final String name;
    private  final Money price;

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    private Product(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        price = builder.price;
    }


    public static final class Builder {
        private ProductId id;
        private String name;
        private Money price;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ProductId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
