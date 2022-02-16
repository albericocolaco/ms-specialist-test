package br.com.arcls.msspecialisttest.service;

import br.com.arcls.msspecialisttest.domain.dto.*;
import br.com.arcls.msspecialisttest.domain.exception.NotFoundException;
import br.com.arcls.msspecialisttest.domain.mapper.UserEditMapper;
import br.com.arcls.msspecialisttest.domain.mapper.UserViewMapper;
import br.com.arcls.msspecialisttest.domain.model.User;
import br.com.arcls.msspecialisttest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserEditMapper userEditMapper;
    private final UserViewMapper userViewMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserView create(CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new ValidationException("Passwords don't match!");
        }
        if (request.getAuthorities() == null) {
            request.setAuthorities(new HashSet<>());
        }

        User user = userEditMapper.create(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);

        return userViewMapper.toUserView(user);
    }

    @Transactional
    public UserView update(ObjectId id, UpdateUserRequest request) {
        User user = getById(id);
        userEditMapper.update(request, user);

        user = userRepository.save(user);

        return userViewMapper.toUserView(user);
    }

    @Transactional
    public UserView upsert(CreateUserRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isEmpty()) {
            return create(request);
        } else {
            UpdateUserRequest updateUserRequest = new UpdateUserRequest();
            updateUserRequest.setFullName(request.getFullName());
            return update(optionalUser.get().getId(), updateUserRequest);
        }
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(format("User with username - %s, not found", username))
                );
    }

    private User getById(ObjectId id){
        final Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && user.get().isEnabled()) {
            return user.get();
        } else {
            throw new NotFoundException(User.class, id);
        }
    }

}
