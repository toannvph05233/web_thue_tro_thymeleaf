package com.booking_house_be.dto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BookingDto {
    private String houseName;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
