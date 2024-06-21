package com.study4.project2024.service.imp;

public interface UsersServiceImp {

    boolean addUser(String username, String password, String role);
    boolean checkLogin(String username, String password);

}