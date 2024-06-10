package com.booking_house_be.service;

import com.booking_house_be.entity.Role;

import java.util.List;

public interface IRoleService {
    Role findById(int id);
    List<Role> findAll();
}

