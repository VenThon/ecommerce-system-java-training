package com.venthon.ecommerce.customer.domain.dto;

import java.util.UUID;

public record CustomerResult(
        UUID id,
        String familyName,
        String givenName,
        String email,
        String phoneNumber
) {
}
