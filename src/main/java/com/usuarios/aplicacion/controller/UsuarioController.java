package com.usuarios.aplicacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.usuarios.aplicacion.model.Usuario;
import com.usuarios.aplicacion.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios(){
        log.info("GET /usuarios");
        log.info("Retornando todos los usuarios");
        return usuarioService.getAllUsuarios();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable Long id){
        Optional <Usuario> usuario = usuarioService.getUsuarioById(id);

        if(usuario.isEmpty()){
            log.error("No se encontro el usuario con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontro el usuario con ID "+ id));
        }
        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping
    public ResponseEntity<Object> createUsuario(@RequestBody Usuario usuario){
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        if(createdUsuario == null){
            log.error("Error al crear al usuario {}", usuario);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error al crear al usuario"));
        }
        return ResponseEntity.ok(createdUsuario);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @Validated @RequestBody Usuario usuario, BindingResult result){
    if(result.hasErrors()){
        List<FieldError> errors = result.getFieldErrors();
        List<String> errorMessages = new ArrayList<>();
        for(FieldError error : errors){
            errorMessages.add(error.getField() + ": " + error.getDefaultMessage());
        }
        log.error("Errores de validación al actualizar usuario con ID {}", id);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Errores de validación "+errorMessages));
    }
    Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
    if(updatedUsuario == null){
        log.error("Error al actualizar usuario con ID {}", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el usuario con ID " + id));
    }
    return ResponseEntity.ok(updatedUsuario);
}

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);           
    }
    
    static class ErrorResponse{
        private final String message;

        public ErrorResponse(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }
    }

}
