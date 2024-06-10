package com.booking_house_be.dto;

import com.booking_house_be.entity.Account;
import com.booking_house_be.entity.Image;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HouseDto {
    private int id;
    private String name;
    private String address;
    private String province;
    private String district;
    private String ward;
    private String houseNumber;
    private int bedroom;
    private int bathroom;
    private String description;
    private String facility;
    private double area;
    private double price;
    private int sale;
    private String thumbnail;
    private List<Image> images;
    private List<Image> imagesDelete;
    private Account owner;
    private LocalDate createAt;
    private String status;
}

