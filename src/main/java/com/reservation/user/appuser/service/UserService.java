package com.reservation.user.appuser.service;

import com.reservation.exception.domain.EmailAlreadyTakenException;
import com.reservation.exception.domain.UserNotFoundException;
import com.reservation.user.appuser.dto.request.CreateUserRequest;
import com.reservation.user.appuser.dto.response.UserResponse;
import com.reservation.user.appuser.model.Role;
import com.reservation.user.appuser.model.User;
import com.reservation.user.appuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserResponse addUser(CreateUserRequest request){

        boolean userExist = userRepository.findByEmail(request.getEmail()).isPresent();

        if(userExist){
            throw new EmailAlreadyTakenException();
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                bCryptPasswordEncoder.encode(request.getPassword()),
                Role.ADMIN);

        userRepository.save(user);

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    public UserResponse deactivateUser(UUID id){

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setLocked(true);

        userRepository.save(user);

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

}
