package edu.dev.identityservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.dev.identityservice.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	
	@Query("SELECT r FROM Role r JOIN FETCH r.permissions")
	List<Role> findAllRolesWithPermissions();
}
