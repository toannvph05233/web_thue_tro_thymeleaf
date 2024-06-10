package com.booking_house_be.service.impl;

import com.booking_house_be.service.IImageService;
import com.booking_house_be.entity.Image;
import com.booking_house_be.repository.IImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepo imageRepo;
    @Override
    public List<Image> findAllByHouseId(int houseId) {
        return null;
    }
}
