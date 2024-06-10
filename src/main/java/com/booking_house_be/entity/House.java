package com.booking_house_be.entity;

import com.booking_house_be.dto.HouseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String province;
    @Column(columnDefinition = "TEXT")
    private String service;
    @Column(columnDefinition = "TEXT")
    private String description;
    private double price;
    private int sale;
    @Column(columnDefinition = "TEXT")
    private String thumbnail;
    private String status;
    private LocalDate createAt;
    private LocalDate updateAt;
    @ManyToOne
    private Account owner;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Image> images;

    public House(HouseDto houseDto) {
        this.id = houseDto.getId();
        this.name = houseDto.getName();
        this.address = houseDto.getAddress();
        this.province = houseDto.getProvince();
        this.description = houseDto.getDescription();
        this.price = houseDto.getPrice();
        this.sale = houseDto.getSale();
        this.thumbnail = houseDto.getThumbnail();
        this.owner = houseDto.getOwner();
        this.status = houseDto.getStatus();
    }


    public House(String name, String address, String province,  String service,  String description, double price, int sale, String thumbnail, String status, LocalDate createAt, LocalDate updateAt, Account owner) {
        this.name = name;
        this.address = address;
        this.province = province;
        this.service = service;
        this.description = description;
        this.price = price;
        this.sale = sale;
        this.thumbnail = thumbnail;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.owner = owner;
    }
}
