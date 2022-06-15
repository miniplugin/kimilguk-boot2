package com.herokuapp.kimilgukboot2.domain.simple_users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SimpleUsersRepository extends JpaRepository<SimpleUsers, Long> {
	@Query("SELECT p FROM SimpleUsers p where p.username = :username")
	SimpleUsers findByName(@Param("username")String username);
}
