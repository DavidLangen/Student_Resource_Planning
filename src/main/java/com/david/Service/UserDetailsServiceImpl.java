package com.david.Service;

import com.david.Entity.LoginUser;
import com.david.Entity.UserAdapter;
import com.david.Exceptions.ResourceNotFoundException;
import com.david.Global.UserRoles;
import com.david.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * This service is a Wrapper-Class for the User Repository.
 * It´s wrapped Repository Methods and do some extra actions.
 * @author David Langen
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * The User Repository, which is strongly linked with the database
     */
    @Autowired
    private UserRepo userRepository;

    /**
     * Determines the size of pages used in the pagination for the user.
     */
    private static int PAGEROW_SIZE = 5;

    /**
     * This service method find details about an User from the database.
     * @param username of the User
     * @throws UsernameNotFoundException
     * @return UserDetails of the searched User
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        LoginUser loginUser = userRepository.findByUsername(username);

        if (loginUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserAdapter(loginUser);
    }

    /**
     * This service method returns all LoginUser in the database,
     * wrapped with the page class.
     * @param page the current page number
     * @return sorted LoginUser list of the current page
     */
    public Page<LoginUser> getAllUser(int page){
        return userRepository.findAllByRoleEquals(UserRoles.USER.toString(), PageRequest.of(page, PAGEROW_SIZE));
    }

    /**
     * This service method find a LoginUser by his id or throws an exception.
     * @param id of the searched User
     * @throws ResourceNotFoundException
     * @return searched LoginUser object
     */
    public LoginUser getUserById(long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
    }

    /**
     * This service method save a LoginUser in the database.
     * @param user to be saved
     */
    public void saveUser(LoginUser user){
        userRepository.save(user);
    }

    /**
     * Created LoginUser by starting the Application
     */
    @PostConstruct
    private void initUser() {
        saveUser(new LoginUser("max","hart",UserRoles.USER, true));
        saveUser(new LoginUser("david","password",UserRoles.ADMIN));
    }
}


