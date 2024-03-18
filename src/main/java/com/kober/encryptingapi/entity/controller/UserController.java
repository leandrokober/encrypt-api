package com.kober.encryptingapi.entity.controller;

import com.kober.encryptingapi.entity.Model.User;
import com.kober.encryptingapi.entity.dto.UserRespDTO;
import com.kober.encryptingapi.entity.dto.UserDTO;
import com.kober.encryptingapi.entity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User>  insertUser (@RequestBody UserDTO userDTO) {
        User newUser = userService.insertUser(userDTO);

        return ResponseEntity.ok(newUser);

    }

    @GetMapping("{id}")
    public ResponseEntity<UserRespDTO> findUserById (@PathVariable Long id) {

       UserRespDTO userRespDTO = userService.findUserById(id);

       return ResponseEntity.ok(userRespDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser (@RequestBody UserDTO userDTO, @PathVariable Long id) {
        User updatedUser = userService.updateUser(userDTO, id);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }


}
