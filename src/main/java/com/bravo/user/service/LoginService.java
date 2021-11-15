package com.bravo.user.service;

import com.bravo.user.dao.model.Profile;
import com.bravo.user.dao.repository.ProfileRepository;
import com.bravo.user.exception.ForbiddenException;
import com.bravo.user.model.dto.LoginDto;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private final EncryptService encryptService;
  private final ProfileRepository profileRepository;

  public LoginService(EncryptService encryptService, ProfileRepository profileRepository) {
    this.encryptService = encryptService;
    this.profileRepository = profileRepository;
  }

  public void login(final LoginDto request){
    final String encryptedPassword = encryptService.encrypt(request.getPassword());
    final Profile profile = profileRepository.findByUsername(request.getUsername());

    if(profile == null || !profile.getPassword().equals(encryptedPassword)){
      throw new ForbiddenException("'username' or 'password' is invalid");
    }
  }
}
