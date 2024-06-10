package com.booking_house_be;

import com.booking_house_be.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BookingHouseBeApplication {
//    @Autowired
//    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(BookingHouseBeApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void sendEmail() {
//        emailService.sendEmail("huhuh8918@gmail.com", "aaa", "aaa");
//    }
}
