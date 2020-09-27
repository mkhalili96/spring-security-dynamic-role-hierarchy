package net.mkhalili96.dynamic_role_hierarchy.model.service;

import net.mkhalili96.dynamic_role_hierarchy.model.entity.User;

import java.util.List;

public interface UserService {
    public User save(User user) throws Exception;

    public User update(User user) throws Exception;

    public void delete(Long userId) throws Exception;

    public void active(Long userId, Boolean active) throws Exception;

    public User getUser(Long userId) throws Exception;

    public User getUserByUsername(String username) throws Exception;

    public List<User> getAllUsers() throws Exception;

    public List<User> getAllActiveUsers() throws Exception;

    public List<User> getAllDeActiveUsers() throws Exception;

    public User changePassword(User user) throws Exception;
}
