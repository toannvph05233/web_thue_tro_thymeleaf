package com.booking_house_be.controller;

import com.booking_house_be.entity.Account;
import com.booking_house_be.service.IAccountService;
import com.booking_house_be.service.impl.EmailService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/forgot")
public class ForgotPasswordController {
    @Autowired
    IAccountService accountService;
    @Autowired
    EmailService emailService;

    @PostMapping()
    public ResponseEntity<?> requestPasswordReset(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");

        // Kiểm tra xem email có tồn tại trong cơ sở dữ liệu không
        Account account = accountService.getAccountByEmail(email);
        if (account == null) {
            return ResponseEntity.badRequest().body("Email không tồn tại");
        }

        // Tạo mã reset password và lưu vào cơ sở dữ liệu
        String newPassword = RandomString.make(10);
        account.setPassword(newPassword);
        accountService.save(account);

        // Gửi email chứa mã reset password
        String emailContent = "Mật khẩu mới của bạn là: " + newPassword;
        emailService.sendEmail(email, "Yêu cầu gửi lại mật khẩu", emailContent);

        return ResponseEntity.ok("Email đã được gửi");
    }
}
