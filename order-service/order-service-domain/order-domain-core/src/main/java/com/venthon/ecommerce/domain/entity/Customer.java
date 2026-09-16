package com.venthon.ecommerce.domain.entity;

import com.example.ecommerce.domain.entity.AggregaeRoot;
import com.example.ecommerce.domain.valueobject.CustomerId;

public class Customer extends AggregaeRoot<CustomerId> {
    private final String username;
    private  final String familyName;
    private  final  String givenName;


    //Code Generate by Getter

    public String getUsername() {
        return username;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenName() {
        return givenName;
    }


    // Code Generate by builder

    private Customer(Builder builder) {
        // id = builder.id;
        super.setId(builder.id);
        username = builder.username;
        familyName = builder.familyName;
        givenName = builder.givenName;
    }

    public static final class Builder {
        private CustomerId id;
        private String username;
        private String familyName;
        private String givenName;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(CustomerId val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder familyName(String val) {
            familyName = val;
            return this;
        }

        public Builder givenName(String val) {
            givenName = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }



}
