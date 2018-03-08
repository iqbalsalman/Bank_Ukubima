package iqbal.salman.bank.ukubima.bankukubima.Service;

import iqbal.salman.bank.ukubima.bankukubima.entity.user.User;

public interface UserService {

    public User findUserByEmail(String email);
    public User saveUser(User user);
}
