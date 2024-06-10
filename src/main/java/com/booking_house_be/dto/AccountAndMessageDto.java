package com.booking_house_be.dto;

import com.booking_house_be.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAndMessageDto {
    private Account account;
    private int countUnreadMessage;
}
