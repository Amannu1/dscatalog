package com.dscatalog.dscatalog.services;

import com.dscatalog.dscatalog.dto.EmailDTO;
import com.dscatalog.dscatalog.entities.User;
import com.dscatalog.dscatalog.repositories.UserRepository;
import com.dscatalog.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public void createRecoverToken(EmailDTO body) {

        User user = userRepository.findByEmail(body.getEmail());
        if(user == null) {
            throw new ResourceNotFoundException("Email not found");
        }
    }
}
