package com.venthon.ecommerce.domain.entity;

import com.example.ecommerce.domain.entity.AggregaeRoot;
import com.example.ecommerce.domain.valueobject.CreditEntryId;
import com.example.ecommerce.domain.valueobject.CustomerId;
import com.example.ecommerce.domain.valueobject.Money;

public class CreditEntry extends AggregaeRoot<CreditEntryId> {

    private final CustomerId customerId;
    private Money totalCreditAmount;

    //Generate form
    //    public CreditEntry(CustomerId customerId, Money totalCreditAmount) {
    //        this.customerId = customerId;
    //        this.totalCreditAmount = totalCreditAmount;
    //    }

    // Constructor used by Builder
    private CreditEntry(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        totalCreditAmount = builder.totalCreditAmount;
    }

    // Add credit to customer's current credit
    public void addCreditAmount(Money amount) {
        if (amount == null) {
            throw new IllegalArgumentException(
                    "Credit amount cannot be null"
            );
        }
        this.totalCreditAmount = this.totalCreditAmount.add(amount);
    }


    // Subtract credit from customer's current credit
    public void subtractCreditAmount(Money amount) {
        if (amount == null) {
            throw new IllegalArgumentException(
                    "Credit amount cannot be null"
            );
        }
        this.totalCreditAmount = this.totalCreditAmount.subtract(amount);
    }

    // Getters
    public CustomerId getCustomerId() {
        return customerId;
    }

    public Money getTotalCreditAmount() {
        return totalCreditAmount;
    }

    // Start Builder
    public static Builder builder() {
        return new Builder();
    }


    //Generate from builder
    public static final class Builder {
        private CreditEntryId id;
        private CustomerId customerId;
        private Money totalCreditAmount;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(CreditEntryId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder totalCreditAmount(Money val) {
            totalCreditAmount = val;
            return this;
        }

        public CreditEntry build() {
            return new CreditEntry(this);
        }
    }
}
