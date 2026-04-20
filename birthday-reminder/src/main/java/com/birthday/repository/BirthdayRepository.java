package com.birthday.repository;

import com.birthday.model.Birthday;
import com.birthday.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {
    List<Birthday> findByUser(User user);
}