package org.sampong.onlinebanking.customer.controller.dto.req;

public record CustomerRequest (
    Long id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber
) {

}
