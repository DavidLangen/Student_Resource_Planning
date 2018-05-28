package com.david.Service;

import com.david.Entity.LoginUser;
import com.david.Exceptions.ResourceNotFoundException;
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

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    private static int PAGEROW_SIZE = 5;

    @Override
    public UserDetails loadUserByUsername(String username) {
        LoginUser loginUser = userRepository.findByUsername(username);

        if (loginUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserAdapter(loginUser);
    }

    public Page<LoginUser> getAllUser(int page){
        return userRepository.findAll(PageRequest.of(page, PAGEROW_SIZE));
    }

    public LoginUser getUserById(long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
    }

    public void saveUser(LoginUser user){
        userRepository.save(user);
    }

    @PostConstruct
    private void initUser() {
        saveUser(new LoginUser("max","hart","ROLE_USER", true));
        saveUser(new LoginUser("david","password","ROLE_ADMIN"));
    }

    public class UserAdapter implements UserDetails {
        private static final long serialVersionUID = -1360188483928178893L;
        private LoginUser user;
        public UserAdapter(LoginUser user) {
            this.user = user;
        }
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new Vector<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return authorities;
        }
        @Override
        public String getPassword() {
            return user.getPassword();
        }
        @Override
        public String getUsername() {
            return user.getUsername();
        }
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }
        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }
        @Override
        public boolean isEnabled() {
            return true;
        }

        public boolean isLocked(){
            return user.isLocked();
        }
    }


}


