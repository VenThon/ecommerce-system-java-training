package com.example.ecommerce.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private  final BigDecimal amount;

    public final static Money ZERO = new Money(BigDecimal.ZERO);

    //    check money > 0
    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isGreaterThanZero() {
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money money) {
        return this.amount != null &&
                this.amount.compareTo(money.getAmount()) > 0;
    }

    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.getAmount())));
    }

    public Money subtract(Money money) {
        return new  Money(setScale(this.amount.subtract(money.getAmount())));
    }


    // គុណចំនួនទឹកប្រាក់ (គិតជាចំនួនដង)
    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(new BigDecimal(multiplier))));
    }

    // ការកំណត់ចំនួនខ្ទង់ក្រោយក្បៀសសម្រាប់ទឹកប្រាក់ (ជ្រើសរើស២ខ្ទង់)
    private BigDecimal setScale(BigDecimal inputAmount) {
        return inputAmount.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Money money)) return false;
        return Objects.equals(setScale(amount), setScale(money.amount));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(setScale(amount));
    }

}
