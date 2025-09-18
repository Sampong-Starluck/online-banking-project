package org.sampong.onlinebanking.customer.controller.dto.res;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber) {
}
