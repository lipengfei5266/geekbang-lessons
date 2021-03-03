package org.geektimes.projects.user.service;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 * @author lpf
 * @date 2021/3/3 23:10
 */
public class UserServiceImpl implements UserService, ObjectFactory {

    private DatabaseUserRepository databaseUserRepository;

    protected void setRepository() {
        try {
            Context cxt = new InitialContext();
            this.databaseUserRepository = (DatabaseUserRepository) cxt.lookup("java:comp/env/jndi/databaseUserRepository");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean register(User user) {
        this.databaseUserRepository.save(user);
        return true;
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setRepository();
        return userService;
    }
}
