package com.dscatalog.dscatalog.resources;

import com.dscatalog.dscatalog.dto.EmailDTO;
import com.dscatalog.dscatalog.dto.UserDTO;
import com.dscatalog.dscatalog.dto.UserInsertDTO;
import com.dscatalog.dscatalog.dto.UserUpdateDTO;
import com.dscatalog.dscatalog.services.AuthService;
import com.dscatalog.dscatalog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private AuthService service;

    @PostMapping(value = "/recover-token")
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailDTO body){
        service.createRecoverToken(body);
        return ResponseEntity.noContent().build();
    }
}
