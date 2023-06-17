package org.java.blissful.auth.repo;

import org.java.blissful.pojo.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long>{

}
