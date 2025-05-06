package com.giovana.DividiAqui.service;

import com.giovana.DividiAqui.model.User;
import com.giovana.DividiAqui.repository.UserRepository;
import com.giovana.DividiAqui.service.port.UserDomainServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Implementação do serviço de domínio para a entidade User.
 */
@AllArgsConstructor
@Service
public class UserDomainServiceImpl implements UserDomainServicePort {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID idUser) {
        return userRepository.findById(idUser);    }

    @Override
    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUserById(UUID idUser) {
        userRepository.deleteById(idUser);
    }
}