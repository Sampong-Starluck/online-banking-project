package org.sampong.onlinebanking.customer.service.implement;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.sampong.onlinebanking.customer.controller.dto.req.CustomerPageRequest;
import org.sampong.onlinebanking.customer.model.Customer;
import org.sampong.onlinebanking.customer.repository.CustomerRepository;
import org.sampong.onlinebanking.customer.service.CustomerService;
import org.sampong.onlinebanking.customer.service.mapper.CustomerServiceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerServiceMapper mapper;

    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.of(customerRepository.findByIdAndStatusTrue(id)).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Customer not found !!"));
    }

    @Override
    public Optional<List<Customer>> findAllList() {
        return Optional.of(customerRepository.findAllByStatusTrue()).orElse(Optional.empty());
    }

    @Override
    public Customer addNew(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateObj(Customer customer) {
        val oldCustomer = findById(customer.getId());
        if (oldCustomer.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Customer not found !!!");
        }
        var updateCustomer = mapper.from(customer, oldCustomer.get());
        return customerRepository.save(updateCustomer);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<Customer> findAllPage(CustomerPageRequest customerPageRequest) {
        return customerRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Expression<String> firstName = cb.upper(cb.trim(root.get("firstName").as(String.class)));
            Expression<String> lastName = cb.upper(cb.trim(root.get("lastName").as(String.class)));

            // Search by exact customerName (fullName)
            if (customerPageRequest.getCustomerName() != null
                    && !customerPageRequest.getCustomerName().isEmpty()) {
                Expression<String> fullName = cb.concat(cb.concat(firstName, " "), lastName);
                predicates.add(cb.like(fullName, "%" + customerPageRequest.getCustomerName().toUpperCase(Locale.getDefault()) + "%"));
            }

            // Generic "query" search (fullName OR phoneNumber)
            if (customerPageRequest.getQuery() != null && !customerPageRequest.getQuery().isEmpty()) {

                String searchValue = "%" + customerPageRequest.getQuery().toUpperCase(Locale.getDefault()) + "%";
                Expression<String> fullName = cb.concat(cb.concat(firstName, " "), lastName);
                Expression<String> phoneNumber = cb.upper(cb.trim(root.get("phoneNumber").as(String.class)));

                // (fullName LIKE ? OR phoneNumber LIKE ?)
                Predicate fullNameLike = cb.like(fullName, searchValue);
                Predicate phoneLike = cb.like(phoneNumber, searchValue);

                predicates.add(cb.or(fullNameLike, phoneLike));
            }

            // Always filter by status = true
            predicates.add(cb.equal(root.get("status"), true));

            return cb.and(predicates.toArray(new Predicate[0]));
        }, PageRequest.of(
                customerPageRequest.getPage(),
                customerPageRequest.getSize(),
                Sort.by(Sort.Direction.DESC, "id")
        ));
    }
}
