package iqbal.salman.bank.ukubima.bankukubima.Repository;

import iqbal.salman.bank.ukubima.bankukubima.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
