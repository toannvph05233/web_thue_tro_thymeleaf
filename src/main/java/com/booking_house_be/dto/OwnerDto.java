package com.booking_house_be.dto;

import com.booking_house_be.entity.Account;
import com.booking_house_be.entity.House;
import com.booking_house_be.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OwnerDto {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String province;
    private String district;
    private String ward;
    private String frontside;
    private String backside;
    private String status;
    private Account account;
    private String avatar;
    private String description;
    private String service;
    private double price;
    private int sale;

    public Owner getOwner() {
        return new Owner(id, firstname, lastname, email, phone, address, province, district, ward, frontside,
                backside, status, account, avatar);
    }

    public House getHouse(){
        return new House(0,lastname + " " + firstname,address,province,service,description,price,sale,avatar,"Đang hoạt động", LocalDate.now(), LocalDate.now(),account, null );
    }
}
