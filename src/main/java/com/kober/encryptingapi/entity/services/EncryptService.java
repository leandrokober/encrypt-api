package com.kober.encryptingapi.entity.services;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

    @Autowired
    AES256TextEncryptor textEncryptor;

    public String encryptData(String data) {
        return textEncryptor.encrypt(data);
    }

    public String decryptData (String encryptedData) {
        return textEncryptor.decrypt(encryptedData);
    }


}