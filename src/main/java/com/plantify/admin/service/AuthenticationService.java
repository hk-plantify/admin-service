package com.plantify.admin.service;

public interface AuthenticationService {

    boolean validateAdminRole(String authorizationHeader);
    String getRole(String authorizationHeader);
}
