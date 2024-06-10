package com.booking_house_be.service;

import com.booking_house_be.dto.HouseDto;
import com.booking_house_be.entity.House;
import com.booking_house_be.repository.IHouseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IHouseService {
    House findById(int id);

    List<House> getAllByOwnerId(int ownerId);


    Page<IHouseRepo.HouseInfo> findByOwnerIdAndNameAndStatus(int id, String name, String status, Pageable pageable);

    List<IHouseRepo.HouseInfo> findByOwnerId(int ownerId);

    Page<IHouseRepo.HouseInfo> findByOwnerId(int ownerId, Pageable pageable);


    House saveHouse(House house);

    Page<House> findAllByPriceRange(Pageable pageable, double minPrice, double maxPrice);

    Page<House> findHousesByNameAndPriceRange(Pageable pageable, String nameSearch, double minPrice, double maxPrice);

    Page<House> findHousesByNameAndPriceRangeAndLocal(Pageable pageable, String nameSearch, String province, double minPrice, double maxPrice);

    House findByIdAndOwnerId(int houseId, int ownerId);

    House updateStatus(int id, String status);

    List<Integer> getTopBookingHouseId();
}
