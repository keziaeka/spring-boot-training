package com.example.demofive.demofive.repository;

import com.example.demofive.demofive.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    //JPQL query to find a customer by phone number
    @Query("SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber")
    Customer findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    //Native SQL query to find a customer by email
    //Nama kolom di DB harus sesuai dengan yang ada di entity?
    @Query(value = "SELECT * FROM Customer c WHERE c.email = :email", nativeQuery = true)
    Customer findbyEmail(String email);

    Page<Customer> findByCustomerName(String name, Pageable pageable);

    //Lazy loading untuk menghindari N+1 problem
    //Data baru diambil ketika dibutuhkan
    //Default pada @ManyToOne dan @OneToOne adalah Lazy loading
    //Saat ambil customer, bank accountnya tidak diambil sekaligus. baru akan diambil saat dipanggil getBankAccount()

    //Eager loading untuk mengambil data terkait sekaligus
    //Data diambil sekaligus saat query utama dieksekusi
    //Default pada @OneToMany dan @ManyToMany adalah Eager loading
    //Saat ambil customer, ambil juga bank accountnya

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.bankAccountList")
    List<Customer> findAllWithAccounts();

}
