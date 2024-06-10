package com.booking_house_be.controller;

import com.booking_house_be.entity.Account;
import com.booking_house_be.entity.House;
import com.booking_house_be.entity.Image;
import com.booking_house_be.entity.Owner;
import com.booking_house_be.repository.IHouseRepo;
import com.booking_house_be.repository.IImageRepo;
import com.booking_house_be.service.IAccountService;
import com.booking_house_be.service.IHouseService;
import com.booking_house_be.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@CrossOrigin("*")
@RequestMapping("/houses")
public class HouseController {
    @Value("${fileImg}")
    private String fileImgPath;

    @Autowired
    private IHouseService houseService;

    @Autowired
    IAccountService accountService;

    @Autowired
    IImageRepo imageRepo;

    @GetMapping()
    public ModelAndView findHousesByPriceRange(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "12") int size,
            @RequestParam(value = "nameSearch", defaultValue = "") String nameSearch,
            @RequestParam(value = "province", defaultValue = "") String province,
            @RequestParam(value = "minPrice", defaultValue = "0") double minPrice,
            @RequestParam(value = "maxPrice", defaultValue = "900000000") double maxPrice) {
        province = province.replace("_", " ");
        ModelAndView modelAndView = new ModelAndView("index");
        if (maxPrice == 0) {
            maxPrice = Double.MAX_VALUE;
        }
        Pageable pageable = PageRequest.of(page, size);
        if (nameSearch.trim().equals("") && province.equals("")) {
            modelAndView.addObject("houses", houseService.findAllByPriceRange(pageable, minPrice, maxPrice));
        } else if (province.equals("")) {
            modelAndView.addObject("houses", houseService.findHousesByNameAndPriceRange(pageable, nameSearch, minPrice, maxPrice));
        } else {
            modelAndView.addObject("houses", houseService.findHousesByNameAndPriceRangeAndLocal(pageable, nameSearch, province, minPrice, maxPrice));
        }
        return modelAndView;
    }


    @GetMapping("/{houseId}")
    public ModelAndView getById(@PathVariable int houseId) {
        ModelAndView modelAndView = new ModelAndView("houseDetail");
        try {
            modelAndView.addObject("house", houseService.findById(houseId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping
    public String create(@ModelAttribute House house, @RequestParam List<MultipartFile> imgFiles) throws IOException {
        List<Image> images = new ArrayList<>();
        for (MultipartFile imgFile : imgFiles) {
            String name = imgFile.getOriginalFilename();
            assert name != null;
            if (!name.equals("")) {
                FileCopyUtils.copy(imgFile.getBytes(), new File(fileImgPath + name));
                Image image = new Image();
                image.setUrl("/images/" + name);
                imageRepo.save(image);
                images.add(image);
            }
        }
        if (house.getId() != 0) {
            House houseOld = houseService.findById(house.getId());
            images = houseOld.getImages();
            house.setCreateAt(houseOld.getCreateAt());
        } else {
            if (images.size() == 0) {
                Image image = new Image();
                image.setUrl("https://bandon.vn/uploads/posts/thiet-ke-nha-tro-dep-2020-bandon-0.jpg");
                imageRepo.save(image);
                images.add(image);
            }
            house.setCreateAt(LocalDate.now());
        }
        house.setThumbnail(images.get(0).getUrl());
        house.setImages(images);
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountService.getAccountByUsername(userDetails.getUsername());
        house.setOwner(account);
        house.setUpdateAt(LocalDate.now());
        houseService.saveHouse(house);

        return "redirect:/owner";
    }

    @GetMapping("/api/{houseId}")
    public ResponseEntity<?> getByIdAPI(@PathVariable int houseId) {
        try {
            return ResponseEntity.ok(houseService.findById(houseId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }


    @GetMapping("/owner/search/{ownerId}")
    public Page<IHouseRepo.HouseInfo> findByOwnerIdAndNameContains(@PathVariable int ownerId,
                                                                   @RequestParam("name") String name,
                                                                   @RequestParam("status") String status,
                                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return houseService.findByOwnerIdAndNameAndStatus(ownerId, name, status, pageable);
    }

    @GetMapping("/owner/revenue/{ownerId}")
    public List<IHouseRepo.HouseInfo> findByOwnerId(@PathVariable int ownerId) {
        return houseService.findByOwnerId(ownerId);
    }

    @GetMapping("/owner/listHouse/{ownerId}")
    public Page<IHouseRepo.HouseInfo> findByOwnerIdAndNameContains(@PathVariable int ownerId,
                                                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return houseService.findByOwnerId(ownerId, pageable);
    }


    @PutMapping("/owner/{houseId}")
    public House updateStageStatus(@PathVariable int houseId, @RequestParam("status") String status) {
        return houseService.updateStatus(houseId, status);
    }

//    @GetMapping("/top5")
//    public List<House> getTopBookingHouse() {
//        List<Integer> houseId = houseService.getTopBookingHouseId();
//        List<House> houses = new ArrayList<>();
//        for (int i = 0; i < houseId.size(); i++) {
//            houses.add(houseService.findById(houseId.get(i)));
//            if (i == 5) break;
//        }
//        return houses;
//    }

}