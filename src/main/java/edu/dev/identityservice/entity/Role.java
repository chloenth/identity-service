package edu.dev.identityservice.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role {
	@Id
	String name;
	String description;

	@ManyToMany
	@JoinTable(
		name = "role_permission", 
		joinColumns = @JoinColumn(name = "role_id"), 
		inverseJoinColumns = @JoinColumn(name = "permission_id")
	)
	Set<Permission> permissions;
}
