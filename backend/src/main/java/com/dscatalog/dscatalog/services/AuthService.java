package com.dscatalog.dscatalog.services;

import com.dscatalog.dscatalog.dto.EmailDTO;
import com.dscatalog.dscatalog.dto.NewPasswordDTO;
import com.dscatalog.dscatalog.entities.PasswordRecover;
import com.dscatalog.dscatalog.entities.User;
import com.dscatalog.dscatalog.repositories.PasswordRecoverRepository;
import com.dscatalog.dscatalog.repositories.UserRepository;
import com.dscatalog.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Value("${email.password-recover.uri}")
    private String recoverUri;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void createRecoverToken(EmailDTO body) {

        User user = userRepository.findByEmail(body.getEmail());
        if(user == null) {
            throw new ResourceNotFoundException("Email not found");
        }

        String token = UUID.randomUUID().toString();

        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(body.getEmail());
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
        entity = passwordRecoverRepository.save(entity);

        String text = "Access the link to recover your password\n\n"
                + recoverUri + token + ". Validation of " + tokenMinutes + " minutes";
        emailService.sendEmail(body.getEmail(), "Password recovery", text);
    }

    @Transactional
    public void saveNewPassword(@Valid NewPasswordDTO body) {
        List<PasswordRecover> result =  passwordRecoverRepository.searchValidTokens(body.getToken(), Instant.now());
        if(result.size() == 0){
            throw new ResourceNotFoundException("Invalid token");
        }

        User user = userRepository.findByEmail(result.get(0).getEmail());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        user = userRepository.save(user);
    }


}
