package com.kober.encryptingapi.entity.services;

import com.kober.encryptingapi.entity.Model.User;
import com.kober.encryptingapi.entity.dto.UserRespDTO;
import com.kober.encryptingapi.entity.dto.UserDTO;
import com.kober.encryptingapi.entity.exceptions.UserNotFoundException;
import com.kober.encryptingapi.entity.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncryptService encryptService;

    public User insertUser (UserDTO userDTO) {
        User user = new User();

        String userDocumentHashed = encryptService.encryptData(userDTO.userDocument());
        String passwordHashed = encryptService.encryptData(userDTO.password());

        user.setUserName(userDTO.userName());
        user.setUserDocument(userDocumentHashed);
        user.setAccountNumber(userDTO.accountNumber());
        user.setPassword(passwordHashed);

        userRepository.save(user);

        return user;

    }

    public UserRespDTO findUserById (Long id)  throws UserNotFoundException{
        User user = userRepository.findById(id)
                .orElseThrow(() ->new UserNotFoundException(id));

        String userDocumentHashed = encryptService.decryptData(user.getUserDocument());

        UserRespDTO userRespDTO = new UserRespDTO(user.getId(), user.getUserName(), userDocumentHashed,
                user.getAccountNumber());

        return userRespDTO;
    }

    @Transactional //dispensa o uso do save
    public User updateUser (UserDTO data, Long id) throws UserNotFoundException {
        User updateUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (!data.userName().isEmpty()) {
            updateUser.setUserName(data.userName());
        }
        if (!data.userDocument().isEmpty()) {
            updateUser.setUserDocument(encryptService.encryptData(data.userDocument()));
        }
        if (!data.accountNumber().isEmpty()) {
            updateUser.setAccountNumber(data.accountNumber());
        }
        if (!data.password().isEmpty()) {
            updateUser.setPassword(encryptService.encryptData(data.password()));
        }
        return updateUser;
   }


    public void deleteUser (Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);

    }




}
