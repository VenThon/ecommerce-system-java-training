package com.venthon.ecommerce.domain.entity;

import com.example.ecommerce.domain.entity.BaseEntity;
import com.example.ecommerce.domain.valueobject.CreditHistoryId;
import com.example.ecommerce.domain.valueobject.CustomerId;
import com.example.ecommerce.domain.valueobject.Money;
import com.example.ecommerce.domain.valueobject.TransactionType;

public class CreditHistory extends BaseEntity<CreditHistoryId> {

    private final CustomerId customerId;
    private final Money amount;
    private final TransactionType transactionType;

    // Constructor used by Builder
    private CreditHistory(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        amount = builder.amount;
        transactionType = builder.transactionType;
    }


    // Get customer id
    public CustomerId getCustomerId() {
        return customerId;
    }


    // Get transaction amount
    public Money getAmount() {
        return amount;
    }

    // Get transaction type (DEBIT or CREDIT)
    public TransactionType getTransactionType() {
        return transactionType;
    }


    // Start building a CreditHistory object
    public static Builder builder() {
        return new Builder();
    }



    public static final class Builder {
        private CreditHistoryId id;
        private CustomerId customerId;
        private Money amount;
        private TransactionType transactionType;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(CreditHistoryId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder amount(Money val) {
            amount = val;
            return this;
        }

        public Builder transactionType(TransactionType val) {
            transactionType = val;
            return this;
        }

        public CreditHistory build() {
            return new CreditHistory(this);
        }
    }
}
