package com.example.users.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table( name = "Users" )
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 8, max = 15, message = "EL username debe encontrarse entre 5 y 100 caracteres")
    private String username;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 8, max = 10, message = "La password debe contener almenos 8 characters maximo 10")
    private String password;

    // GET
    public Long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    // SET

    public void setUsername( String username ){
        this.username = username;
    }

    public void setPassword ( String pass ){
        this.password = pass;
    }



    
}
