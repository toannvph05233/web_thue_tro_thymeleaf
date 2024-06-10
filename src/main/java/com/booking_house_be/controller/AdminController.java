package com.booking_house_be.controller;

import com.booking_house_be.entity.Account;
import com.booking_house_be.entity.Owner;
import com.booking_house_be.entity.Role;
import com.booking_house_be.repository.IAccountRepo;
import com.booking_house_be.repository.IOwnerRepo;
import com.booking_house_be.service.IAccountService;
import com.booking_house_be.service.IOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Value("${fileImg}")
    private String fileImgPath;

    @Autowired
    private IAccountRepo accountRepo;

    @Autowired
    IOwnerRepo ownerRepo;

    @GetMapping()
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView("/admin/admin");
        try {
            modelAndView.addObject("accounts", accountRepo.findByRoleName("ROLE_USER"));
            modelAndView.addObject("owners", ownerRepo.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping("/accounts/block/{id}")
    public ResponseEntity<?> block(@PathVariable int id) {
        Account account = accountRepo.getById(id);
        if (account.getStatus().equals("Đang hoạt động")) {
            account.setStatus("Đã khóa");
        } else {
            account.setStatus("Đang hoạt động");
        }
        accountRepo.save(account);
        return ResponseEntity.ok().body(Map.of("success", true));
    }

    @PostMapping("/owners/active/{id}")
    public ResponseEntity<?> blockOwners(@PathVariable int id) {
        Owner owner = ownerRepo.getById(id);
        Account account = accountRepo.getById(owner.getAccount().getId());
        if (owner.getStatus().equals("Chờ xác nhận")) {
            owner.setStatus("Đã xác nhận");
            account.setStatus("Đang hoạt động");
            Role role = new Role();
            role.setId(3);
            account.setRole(role);
        } else {
            owner.setStatus("Chờ xác nhận");
            account.setStatus("Đã khóa");
        }
        accountRepo.save(account);
        ownerRepo.save(owner);
        return ResponseEntity.ok().body(Map.of("success", true));
    }

    @PostMapping("/registerOwner")
    public String registerOwner(@ModelAttribute Owner owner, @RequestParam MultipartFile frontsideFile, @RequestParam MultipartFile backsideFile) throws IOException {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepo.findByUsername(userDetails.getUsername());
        Owner owner1 = ownerRepo.getOwnerByAccount(account.getId());
        if (owner1 == null) {
            String nameFront = frontsideFile.getOriginalFilename();
            String nameBack = backsideFile.getOriginalFilename();
            FileCopyUtils.copy(frontsideFile.getBytes(), new File(fileImgPath + nameFront));
            FileCopyUtils.copy(backsideFile.getBytes(), new File(fileImgPath + nameBack));
            owner.setFrontside("/images/" + nameFront);
            owner.setBackside("/images/" + nameBack);

            owner.setFirstname(account.getFirstname());
            owner.setLastname(account.getLastname());
            owner.setAccount(account);
            owner.setAvatar(account.getAvatar());
            owner.setAddress(account.getAddress());
            owner.setDistrict(account.getDistrict());
            owner.setPhone(account.getPhone());
            owner.setWard(account.getWard());
            owner.setEmail(account.getEmail());
            owner.setStatus("Chờ xác nhận");
            ownerRepo.save(owner);
        }
        return "redirect:/houses";
    }

}
