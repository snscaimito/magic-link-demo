package com.example.magiclinkapi;

import java.util.UUID;

public class UserEntity {

  private String email;
  private UUID userId;
  private boolean confirmed;

  public UserEntity(String email, UUID randomUUID) {
    this.email = email;
    this.userId = randomUUID;
  }

  public String getEmail() {
    return email;
  }

  public UUID getUserId() {
    return userId;
  }

  public boolean isConfirmed() {
    return confirmed;
  }

  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }

}
