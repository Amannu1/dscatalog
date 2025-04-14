package com.dscatalog.dscatalog.repositories;

import com.dscatalog.dscatalog.entities.PasswordRecover;
import com.dscatalog.dscatalog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRecoverRepository extends JpaRepository<PasswordRecover, Long> {


}
