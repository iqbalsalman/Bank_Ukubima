package iqbal.salman.bank.ukubima.bankukubima.Service;

import iqbal.salman.bank.ukubima.bankukubima.Repository.RoleRepository;
import iqbal.salman.bank.ukubima.bankukubima.Repository.UserRepository;
import iqbal.salman.bank.ukubima.bankukubima.entity.user.Role;
import iqbal.salman.bank.ukubima.bankukubima.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.time.LocalDateTime;;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class Implementation {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Transactional(readOnly = false)
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setTanggal(Timestamp.valueOf(LocalDateTime.now()));
        return this.userRepository.save(user);
    }

    @Transactional
    public List<Role>findAll(){
       return this.roleRepository.findAll();
    }
}
