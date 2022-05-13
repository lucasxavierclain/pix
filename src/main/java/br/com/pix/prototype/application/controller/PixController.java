package br.com.pix.prototype.application.controller;


import br.com.pix.prototype.application.dtos.UserDTO;
import br.com.pix.prototype.domain.PixService;
import br.com.pix.prototype.domain.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/pix")
public class PixController {


    @Autowired
    PixService service;



    @PostMapping(value = "/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) throws Exception {
            return ResponseEntity.status(HttpStatus.OK).body(new UserDTO(service.addUser(user)));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<UserDTO> listUser(@PathVariable("id") String id) throws Exception {

        User user = service.listUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(new UserDTO(user));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> modifyUser(User user){
        return ResponseEntity.ok(new UserDTO(service.modifyUser(user)));
    }

}
