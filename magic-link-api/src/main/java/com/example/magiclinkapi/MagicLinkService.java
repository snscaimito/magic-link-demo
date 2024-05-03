package com.example.magiclinkapi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MagicLinkService {
  private final Logger LOGGER = LoggerFactory.getLogger(MagicLinkService.class);

  private final List<UserEntity> database = new ArrayList<>();

  public UserEntity saveUser(String email) {
    LOGGER.info("Saving user with email: {}", email);
    UserEntity user = new UserEntity(email, UUID.randomUUID());

    database.add(user);
    return user;
  }

  public void confirmUser(String userId) {
    LOGGER.info("Confirming user with userId: {}", userId);

    UUID uuid = UUID.fromString(userId);

    UserEntity user = database.stream().filter(u -> u.getUserId().equals(uuid)).findFirst().orElseThrow();
    user.setConfirmed(true);
  }

  public boolean isUserConfirmed(String email) {
    boolean confirmed = database.stream().anyMatch(u -> u.getEmail().equals(email) && u.isConfirmed());
    LOGGER.info("User with email: {} is confirmed: {}", email, confirmed);
    return confirmed;
  }

  public String preAuthenticate(String email) {
    LOGGER.info("Pre-authenticating user with email: {}", email);

    UserEntity user = database.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElseThrow();

    if (user.isConfirmed()) {
      UUID magicLinkId = UUID.randomUUID();
      user.setMagicLinkId(magicLinkId);

      return String.format("/api/login/%s", magicLinkId.toString());
    } else {
      throw new RuntimeException("User is not confirmed");
    }
  }

  public boolean authenticate(UUID uuid) {
    try {
      UserEntity user = database.stream().filter(u -> u.getMagicLinkId().equals(uuid)).findFirst().orElseThrow();
      LOGGER.info("Authenticating user with email: {}", user.getEmail());

      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
