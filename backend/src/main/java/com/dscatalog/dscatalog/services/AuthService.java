package com.dscatalog.dscatalog.services;

import com.dscatalog.dscatalog.dto.EmailDTO;
import com.dscatalog.dscatalog.entities.PasswordRecover;
import com.dscatalog.dscatalog.entities.User;
import com.dscatalog.dscatalog.repositories.PasswordRecoverRepository;
import com.dscatalog.dscatalog.repositories.UserRepository;
import com.dscatalog.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {

    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;

    @Transactional
    public void createRecoverToken(EmailDTO body) {

        User user = userRepository.findByEmail(body.getEmail());
        if(user == null) {
            throw new ResourceNotFoundException("Email not found");
        }

        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(body.getEmail());
        entity.setToken(UUID.randomUUID().toString());
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
        entity = passwordRecoverRepository.save(entity);
    }
}
