package br.com.dicasdeumdev.api.resources;

import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id),UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).toList());
    }

    @PostMapping
    public ResponseEntity<UserDTO> create (@RequestBody UserDTO obj){
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(service.create(obj).getId()).toUri()).build();
    }

}
