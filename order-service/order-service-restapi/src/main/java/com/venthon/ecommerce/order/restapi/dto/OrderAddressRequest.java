package com.venthon.ecommerce.order.restapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record OrderAddressRequest(
        @NonNull
        @Size(max=20)
        String street,

        @NonNull
        @Size(max=10)
        String postalCode,

        @NotNull
        @Size(max = 30)
        String city
) {
}
