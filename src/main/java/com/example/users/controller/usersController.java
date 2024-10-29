package com.example.users.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.users.service.UsersService;

import jakarta.validation.Valid;
import com.example.users.exception.ResourceNotFoundException;
import com.example.users.model.Users;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.users.model.Login;



@RestController
@RequestMapping("api/users")
public class usersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> getUsers() {
        List<Users> users = usersService.obtenerListadoUsuarios();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Users> ingresarUsuario(@Valid @RequestBody Users product) {
        Users nuevoUsuario = usersService.guardarUsuario(product);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody Login login) {

        Users users = usersService.obtenerUsuarioByUsername(login.getUsername())
            .orElseThrow(() -> new ResourceNotFoundException("User " + login.getUsername() + " not found"));

        if(users.getPassword().equals(login.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Login failed: Incorrect password");
        }
        
   
        
    }

    @GetMapping("/{id}")
     public ResponseEntity<Users> obtenerUsuario(@PathVariable Long id) {
        Users users = usersService.obtenerUsuario(id)
            .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        
        usersService.obtenerUsuario(id)
            .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));

        usersService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateProduct(@PathVariable Long id, @RequestBody Users payload) {

        Users users = usersService.obtenerUsuario(id)
            .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));

        if (payload.getUsername() != null) {
            users.setUsername(payload.getUsername());
        }
        if (payload.getPassword() != null) {
            users.setPassword(payload.getPassword());
        }

        Users userUpdate = usersService.guardarUsuario(users);
        return ResponseEntity.ok(userUpdate);
    }
    
}
