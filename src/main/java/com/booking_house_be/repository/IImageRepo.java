package com.booking_house_be.repository;
import com.booking_house_be.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IImageRepo extends JpaRepository<Image, Integer> {
}
