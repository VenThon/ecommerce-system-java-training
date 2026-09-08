package com.example.ecommerce.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount ) {

    // ចំនួនទឹកប្រាក់ស្មើរសូន្យ
    public final static Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    //Money bigger than Zero
    public boolean isGreaterThanZero() {
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    // ពិនិត្យទឹកប្រាក់ធំជាងចំនួនទឹកប្រាក់ដែលបានបញ្ចូល
    public boolean isGreaterThan(Money money) {
        return this.amount != null &&
                this.amount.compareTo(money.getAmount()) > 0;
    }

    //Add Money
    public Money add(Money money) {
        return new Money(setScale(this.amount.add(money.getAmount())));
    }



    //ដកលុយ
    public Money subtract(Money money) {
        return new  Money(setScale(this.amount.subtract(money.getAmount())));
    }

    //គុណទឹកលុយតាមចំនួនទឹកលុយ

    public Money multiply(int multiplier) {
        return new Money(setScale(this.amount.multiply(new BigDecimal(multiplier))));
    }

    // ការកំណត់ចំនួនខ្ទង់ក្រោយក្បៀសសម្រាប់ទឹកប្រាក់ (ជ្រើសរើស២ខ្ទង់)
    private BigDecimal setScale(BigDecimal inputAmount) {
        return inputAmount.setScale(2, RoundingMode.HALF_EVEN);
    }


}
