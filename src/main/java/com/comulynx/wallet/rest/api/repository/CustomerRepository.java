package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(String customerId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Customer c WHERE c.customerId = :customerId")
	int deleteCustomerByCustomerId(@Param("customerId") String customer_id);

    @Transactional
    @Modifying
    @Query("UPDATE Customer c SET c.firstName = :firstName WHERE c.customerId = :customerId")
    int updateCustomerByCustomerId(@Param("firstName") String firstName, @Param("customerId")String customer_id);
	
    @Query("SELECT c FROM Customer c WHERE c.email LIKE '%gmail%'")
    List<Customer> findAllCustomersWhoseEmailContainsGmail();

    boolean existsByCustomerId(String customerId);


}
