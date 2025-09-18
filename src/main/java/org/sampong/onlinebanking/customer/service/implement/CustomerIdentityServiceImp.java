package org.sampong.onlinebanking.customer.service.implement;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.sampong.onlinebanking.customer.model.CustomerIdentification;
import org.sampong.onlinebanking.customer.repository.CustomerIdentifyRepository;
import org.sampong.onlinebanking.customer.service.CustomerIdentityService;
import org.sampong.onlinebanking.customer.service.mapper.CustomerIdServiceMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerIdentityServiceImp implements CustomerIdentityService {

    private final CustomerIdentifyRepository customerIdentifyRepository;
    private final CustomerIdServiceMapper mapper;

    @Override
    public Optional<CustomerIdentification> findById(Long id) {
        return Optional.ofNullable(customerIdentifyRepository.findByIdAndStatusTrue(id))
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @Override
    public Optional<List<CustomerIdentification>> findAllList() {
        return Optional.ofNullable(customerIdentifyRepository.findAllByStatusTrue()).orElse(Optional.empty());
    }

    @Override
    public CustomerIdentification addNew(CustomerIdentification customerIdentification) {
        return customerIdentifyRepository.save(customerIdentification);
    }

    @Override
    public CustomerIdentification updateObj(CustomerIdentification customerIdentification) {
        var old = findById(customerIdentification.getId());
        if (old.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        var update = mapper.from(customerIdentification, old.get());
        return customerIdentifyRepository.save(update);
    }

    @Override
    public void delete(Long id) {
        customerIdentifyRepository.delete(id);
    }
}
