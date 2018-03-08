package iqbal.salman.bank.ukubima.bankukubima.Repository;

import iqbal.salman.bank.ukubima.bankukubima.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}
