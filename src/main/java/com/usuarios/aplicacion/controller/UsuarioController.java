package com.usuarios.aplicacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import com.usuarios.aplicacion.model.Usuario;
import com.usuarios.aplicacion.service.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public CollectionModel<EntityModel<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        List<EntityModel<Usuario>> usuarioResources = usuarios.stream()
                .map(usuario -> EntityModel.of(usuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(usuario.getId())).withSelfRel()
                )).collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios());
        CollectionModel<EntityModel<Usuario>> resources = CollectionModel.of(usuarioResources, linkTo.withRel("usuarios"));
        
        return resources;
    }
    
    @GetMapping("/{id}")
    public EntityModel<Usuario> getUsuarioById(@PathVariable Long id){
        Optional <Usuario> usuario = usuarioService.getUsuarioById(id);

        if(usuario.isPresent()){
            return EntityModel.of(usuario.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));   
        } else {
            throw new UsuarioNotFoundException("Usuario not found with id: "+ id);
        }
    }
    
    @PostMapping
    public EntityModel<Usuario> createUsuario(@RequestBody Usuario usuario){
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        return EntityModel.of(createdUsuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(createdUsuario.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));
    }

    @PutMapping("/{id}")
    public EntityModel<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
        return EntityModel.of(updatedUsuario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("all-usuarios"));
    }
    
//     @PutMapping("/{id}")
//     public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @Validated @RequestBody Usuario usuario, BindingResult result){
//     if(result.hasErrors()){
//         List<FieldError> errors = result.getFieldErrors();
//         List<String> errorMessages = new ArrayList<>();
//         for(FieldError error : errors){
//             errorMessages.add(error.getField() + ": " + error.getDefaultMessage());
//         }
//         log.error("Errores de validación al actualizar usuario con ID {}", id);
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Errores de validación "+errorMessages));
//     }
//     Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
//     if(updatedUsuario == null){
//         log.error("Error al actualizar usuario con ID {}", id);
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el usuario con ID " + id));
//     }
//     return ResponseEntity.ok(updatedUsuario);
// }


@DeleteMapping("/{id}")
public void deleteUsuario(@PathVariable Long id) {
    usuarioService.deleteUsuario(id);
}


//     @DeleteMapping("/{id}")
//     public ResponseEntity<Object> deleteUsuario(@PathVariable Long id){
//     Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
//     if(usuario.isEmpty()){
//         log.error("No se encontró el usuario con ID {}", id);
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el usuario con ID " + id));
//     }
//     usuarioService.deleteUsuario(id); 
//     log.info("Usuario con ID {} eliminado correctamente", id);          
//     return ResponseEntity.ok().build();
// }

    
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
