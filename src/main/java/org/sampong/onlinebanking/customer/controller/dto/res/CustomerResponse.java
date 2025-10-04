package org.sampong.onlinebanking.customer.controller.dto.res;

import java.time.LocalDate;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Integer age,
        String customerFullName,
        String refNo,
        LocalDate dateOfBirth
) {
}
