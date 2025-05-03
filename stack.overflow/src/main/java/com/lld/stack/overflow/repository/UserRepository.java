package com.lld.stack.overflow.repository;


import com.lld.stack.overflow.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByName(@Param("name") String userName);

}
