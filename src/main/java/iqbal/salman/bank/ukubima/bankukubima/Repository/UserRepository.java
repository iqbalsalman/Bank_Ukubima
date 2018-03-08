package iqbal.salman.bank.ukubima.bankukubima.Repository;

import iqbal.salman.bank.ukubima.bankukubima.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
