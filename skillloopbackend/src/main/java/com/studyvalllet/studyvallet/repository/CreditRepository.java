package com.studyvalllet.studyvallet.repository;

import com.studyvalllet.studyvallet.entity.Credit;
import com.studyvalllet.studyvallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    List<Credit> findByUserOrderByCreatedAtDesc(User user);
}
