package com.booking_house_be.controller;

import com.booking_house_be.dto.BookingDto;
import com.booking_house_be.dto.CheckoutDTO;
import com.booking_house_be.dto.SearchRequest;
import com.booking_house_be.entity.Account;
import com.booking_house_be.entity.Booking;
import com.booking_house_be.entity.House;
import com.booking_house_be.entity.Review;
import com.booking_house_be.service.IAccountService;
import com.booking_house_be.service.IBookingService;
import com.booking_house_be.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    IBookingService bookingService;
    @Autowired
    IHouseService houseService;
    @Autowired
    IAccountService accountService;

    @GetMapping
    public ModelAndView listBooking(@RequestParam(defaultValue = "all") String status) {
        switch (status) {
            case "all":
                status = null;
                break;
            case "cxn":
                status = "chờ xác nhận";
                break;
            case "dxn":
                status = "đã xác nhận";
                break;
            case "dx":
                status = "đã xong";
                break;
            case "dh":
                status = "đã hủy";
                break;
        }

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.getAccountByUsername(userDetails.getUsername());
        ModelAndView modelAndView = new ModelAndView("booking/bookingUser");
        modelAndView.addObject("bookings", bookingService.getByIdAccountAndStatus(account.getId(), status));
        return modelAndView;
    }

    @PostMapping
    public String bookHouse(@ModelAttribute Booking booking, @RequestParam String time, @RequestParam int idHouse) {
        // Định nghĩa định dạng của chuỗi ngày giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // Chuyển đổi chuỗi thành đối tượng LocalDateTime với định dạng đã định nghĩa
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        House house = houseService.findById(idHouse);
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.getAccountByUsername(userDetails.getUsername());

        booking.setStartTime(dateTime);
        booking.setHouse(house);
        booking.setAccount(account);
        booking.setStatus("chờ xác nhận");
//        booking.set
        booking.setCreate_at(LocalDateTime.now());
        try {
            bookingService.bookingHouse(booking);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/bookings";
    }

    @PostMapping("/cancel-booking/{id}")
    public ResponseEntity<?> cancelBookingUser(@PathVariable int id, @RequestParam(defaultValue = "") String message) {
        try {
            Booking booking = bookingService.findById(id);
            booking.setResult(message);
            String toEmail = booking.getHouse().getOwner().getEmail();
            String contentTitle = "Khách hàng đã hủy lịch thuê nhà";
            bookingService.cancelBooking(booking, toEmail, contentTitle, message);
            return ResponseEntity.ok().body(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    @PostMapping("/result-booking/{id}")
    public ResponseEntity<?> resultBookingUser(@PathVariable int id, @RequestBody Map<String,String> obj) {
        try {
            String message = obj.get("result");
            Booking booking = bookingService.findById(id);
            booking.setResult(message);
            String toEmail = booking.getHouse().getOwner().getEmail();
            String contentTitle = "Khách hàng đã trả kết quả về lịch xem nhà";
            bookingService.resultBooking(booking, toEmail, contentTitle, message);
            return ResponseEntity.ok().body(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "message", e.getMessage()));
        }
    }

//    @GetMapping("/cancel-booking/{id}")
//    public String cancelBookingUser(@PathVariable int id, @RequestParam(defaultValue = "") String message) {
//        try {
//            Booking booking = bookingService.findById(id);
//            String toEmail = booking.getHouse().getOwner().getEmail();
//            String contentTitle = "Khách hàng đã hủy lịch xem nhà";
//            bookingService.cancelBooking(booking, toEmail, contentTitle, message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/bookings";
//    }

    @PostMapping("/checkin/{id}")
    public ResponseEntity<?> checkin(@PathVariable int id) {
        Booking booking = bookingService.findById(id);
        if (booking.getStatus().equals("Chờ khám")) {
            booking.setStatus("Đang khám");
            bookingService.save(booking);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Lịch đặt không ở trạng thái chờ khám");
        }
    }

    @PostMapping("/wait/{id}")
    public ResponseEntity<?> waitOwnerConfirmBooking(@PathVariable int id) {
        try {
            Booking booking = bookingService.waitOwnerConfirmBooking(id);
            if (booking != null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Lịch đặt thuê không ở trạng thái chờ nhận phòng");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }

    @PostMapping("/checkout/{id}")
    public ResponseEntity<?> checkout(@PathVariable int id, @RequestBody CheckoutDTO checkoutDTO) {
        Booking booking = bookingService.findById(id);
        if (booking.getStatus().equals("Đang khám")) {
            booking.setResult(checkoutDTO.getResult());
            booking.setStatus("Khám xong");
            bookingService.save(booking);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Lịch đặt không ở trạng thái đang khám");
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookingById(@PathVariable int id) {
        bookingService.deleteById(id);
        return ResponseEntity.ok("Đã xoá thành công");
    }

    @GetMapping("/house/{houseId}")
    public ResponseEntity<?> getBookingsByHouseId(@PathVariable int houseId) {
        try {
            return ResponseEntity.ok(bookingService.findAllByHouseIdAndStatus(houseId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }


    @GetMapping("/{ownerId}/week")
    private List<Double> getDailyRevenueByOwnerAndWeek(
            @PathVariable int ownerId,
            @Param(value = "month") int month,
            @Param(value = "year") int year,
            @Param(value = "startDay") int startDay,
            @Param(value = "endDay") int endDay) {
        return bookingService.getDailyRevenueByOwnerAndWeek(ownerId, month, year, startDay, endDay);
    }

    @PostMapping("/{ownerId}/search")
    private Page<Booking> searchBookingsByOwnerId(
            @PathVariable int ownerId,
            @RequestBody SearchRequest requestData,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        String nameSearch = requestData.getNameSearch();
        String status = requestData.getStatus();
        LocalDateTime selectedDateStart = requestData.getSelectedDateStart();
        Pageable pageable;
        String sortBy = "create_at";
        Sort sort = Sort.by(Sort.Order.desc(sortBy));
        pageable = PageRequest.of(page, size, sort);
        return bookingService.findByHouseAndStartTimeAndEndTimeAndStatus(ownerId, nameSearch, status, selectedDateStart, pageable);
    }


//    @GetMapping
//    public ResponseEntity<?> getAll() {
//        return new ResponseEntity<>(bookingService.getAll(), HttpStatus.OK);
//    }

    @GetMapping("/getByIdAccount/{idAccount}")
    public ResponseEntity<?> getByIdAccount(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size,
                                            @PathVariable int idAccount) {
        Pageable pageable;
        String sortBy = "create_at";
        Sort sort = Sort.by(Sort.Order.desc(sortBy));
        pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(bookingService.getByIdAccount(pageable, idAccount), HttpStatus.OK);
    }

    @PostMapping("/getByIdAccount/{idAccount}")
    public ResponseEntity<?> getHistoryRentalAccount(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "5") int size,
                                                     @PathVariable int idAccount
            , @RequestBody BookingDto bookingDto) {
        Pageable pageable;
        String sortBy = "create_at";
        Sort sort = Sort.by(Sort.Order.desc(sortBy));
        pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(bookingService.getRentalHistoryIdAccount(pageable
                , idAccount
                , bookingDto.getHouseName()
                , bookingDto.getStartTime()
                , bookingDto.getStatus()), HttpStatus.OK);
    }


    @PostMapping("/reviews")
    public ResponseEntity<?> createReview(@RequestBody Review review) {
        try {
            return ResponseEntity.ok(bookingService.createReview(review));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }

    @GetMapping("/getSpending/{idAccount}")
    public ResponseEntity<?> getSpendingAccount(@PathVariable int idAccount) {
        return ResponseEntity.ok(bookingService.getSpendingUser(idAccount));
    }
}
