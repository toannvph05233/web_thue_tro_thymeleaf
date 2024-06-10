package com.booking_house_be.service;

import com.booking_house_be.entity.Image;

import java.util.List;

public interface IImageService {
    List<Image> findAllByHouseId(int houseId);
}
