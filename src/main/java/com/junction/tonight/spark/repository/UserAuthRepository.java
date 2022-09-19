package com.junction.tonight.spark.repository;

import com.junction.tonight.spark.domain.StayTime;
import com.junction.tonight.spark.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName :  com.junction.tonight.spark.repository
 * fileName : UserAuthRepository
 * author :  shinmj
 * date : 22. 9. 19.
 * description :
 */
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
}
