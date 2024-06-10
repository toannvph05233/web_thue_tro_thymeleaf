package com.booking_house_be.controller;

import com.booking_house_be.dto.HouseDto;
import com.booking_house_be.entity.Account;
import com.booking_house_be.entity.Booking;
import com.booking_house_be.entity.House;
import com.booking_house_be.entity.Owner;
import com.booking_house_be.repository.IHouseRepo;
import com.booking_house_be.service.IAccountService;
import com.booking_house_be.service.IBookingService;
import com.booking_house_be.service.IHouseService;
import com.booking_house_be.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private IHouseService houseService;
    @Autowired
    IBookingService bookingService;

    @Autowired
    IAccountService accountService;

    @Autowired
    IOwnerService ownerService;

    @GetMapping()
    public ModelAndView getOwner() {
        ModelAndView modelAndView = new ModelAndView("owner/owner");
        try {
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Account account = accountService.getAccountByUsername(userDetails.getUsername());
            Owner owner = ownerService.getOwnerByAccount(account.getId());
            List<House> houses = houseService.getAllByOwnerId(account.getId());
            List<Booking> bookings = bookingService.getBookingByHouseOwnerId(account.getId());
            modelAndView.addObject("houses",houses);
            modelAndView.addObject("bookings",bookings);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }



    @PostMapping("/block")
    public String blockHouse(@RequestParam int id) {
        try {
            House house = houseService.findById(id);
            if (house.getStatus().equals("Đang hoạt động")){
                house.setStatus("Đã khóa");
            }else {
                house.setStatus("Đang hoạt động");
            }
            houseService.saveHouse(house);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/owner";
    }

    @PostMapping("/cancel-booking")
    public String cancelBookingOwner(@RequestParam int id, @RequestParam String message) {
        try {
            Booking booking = bookingService.findById(id);
            booking.setResult(message);
            String toEmail = booking.getAccount().getEmail();
            String contentTitle = "Chủ nhà đã hủy lịch xem nhà";
            bookingService.cancelBooking(booking, toEmail, contentTitle, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/owner";
    }

    @PostMapping("/success-booking")
    public String successBookingOwner(@RequestParam int id) {
        try {
            Booking booking = bookingService.findById(id);
            String toEmail = booking.getAccount().getEmail();
            String contentTitle = "Chủ nhà đã chấp nhận lịch xem nhà của bạn";
            bookingService.successBooking(booking, toEmail, contentTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/owner";
    }
}
