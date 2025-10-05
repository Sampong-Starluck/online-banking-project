package org.sampong.onlinebanking.customer.controller.dto.req;

import java.time.LocalDate;

public record CustomerRequest (
    Long id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    Integer age,
    LocalDate dateOfBirth
) {

}
