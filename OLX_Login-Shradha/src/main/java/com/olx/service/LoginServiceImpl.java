package com.olx.service;

import com.olx.dto.User;
import com.olx.entity.LoginDocument;
import com.olx.repository.BlackListTokenRepo;
import com.olx.repository.UserRepository;
import com.olx.utils.LoginConverterUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    BlackListTokenRepo blackListTokenRepo;

   
    
    @Override
    public ResponseEntity<Boolean> logout(String authToken) {
    	LoginDocument userBlackListTokenDocument = new LoginDocument();
    	userBlackListTokenDocument.setToken(authToken);
    	blackListTokenRepo.save(userBlackListTokenDocument);
    	return new ResponseEntity<Boolean>(true,HttpStatus.OK);
    }

    @Override
    public User registerUser(User user) {
        try {
            user.setRole("ROLE_USER");
            return LoginConverterUtil.convertEntityToDto(modelMapper, userRepository.save(LoginConverterUtil.convertDtoToEntity(modelMapper, user)));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getUserInfo(String username) {
        return LoginConverterUtil.convertEntityToDto(modelMapper, userRepository.findByUsername(username));
    }

	/*
	 * @Override public boolean validateLogin(String authToken) { BlackListedToken
	 * blacklistedToken = blackListTokenRepo.findBlackListedToken(authToken); return
	 * blacklistedToken != null; }
	 */
}
