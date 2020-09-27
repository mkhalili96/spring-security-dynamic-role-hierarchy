package net.mkhalili96.dynamic_role_hierarchy.model.service.impl;

import net.mkhalili96.dynamic_role_hierarchy.common.exception.customExceptions.security.*;
import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;
import net.mkhalili96.dynamic_role_hierarchy.model.repository.RoleRepository;
import net.mkhalili96.dynamic_role_hierarchy.model.repository.UserRepository;
import net.mkhalili96.dynamic_role_hierarchy.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Transactional
    @Override
    public User save(User user) throws Exception {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserNameAlreadyExistException(user.getUsername());
        }

        user.setId(null);
        user.setUserRolesSet(null);
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(User user) throws Exception {

        if (user.getId() != null) {
            User u = userRepository.findById(user.getId()).orElseThrow(() -> new UserIdNotFoundException(user.getId()));
            if (!user.getUsername().equals(u.getUsername()))
                throw new UserNameCantChangeException();
            user.setUserRolesSet(u.getUserRolesSet());
            user.setPassword(u.getPassword());
            user.setEnabled(u.getEnabled());
            user.setAccountNonExpired(u.getAccountNonExpired());
            user.setCredentialsNonExpired(u.getCredentialsNonExpired());
            user.setAccountNonLocked(u.getAccountNonLocked());

            return userRepository.save(user);
        } else {
            throw new UserIdNotFoundException(new Long(0));
        }
    }

    @Transactional
    @Override
    public User changePassword(User user) throws Exception {
        if (user.getId() != null){
            User dbUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserIdNotFoundException(user.getId()));
            String hashedPassword = new BCryptPasswordEncoder(9).encode(user.getPassword());

            if (!dbUser.getPassword().equals(hashedPassword)){
                dbUser.setPassword(hashedPassword);
                return userRepository.save(dbUser);
            }else {
                throw new SamePasswordException();
            }
        }else {
            throw new UserIdNotFoundException(new Long(0));
        }
    }


    @Transactional
    @Override
    public void delete(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserIdNotFoundException(userId));
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void active(Long userId, Boolean active) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserIdNotFoundException(userId));
        user.setEnabled(active);
        userRepository.save(user);
    }

    @Override
    public User getUser(Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new UserIdNotFoundException(userId));
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        return Optional.of(userRepository.findByUsername(username)).orElseThrow(() -> new UserNameNotFoundException(username));
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<User> getAllActiveUsers() throws Exception {
        return userRepository.findAllByIsEnabled(true);
    }

    @Override
    public List<User> getAllDeActiveUsers() throws Exception {
        return userRepository.findAllByIsEnabled(false);
    }

}
