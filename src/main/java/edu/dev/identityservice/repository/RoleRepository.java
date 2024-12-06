package edu.dev.identityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.dev.identityservice.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
