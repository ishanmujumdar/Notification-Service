package com.notificationservice.repository.sql;

import com.notificationservice.entity.SMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface SMSRepository extends JpaRepository<SMS, Long>{

}
