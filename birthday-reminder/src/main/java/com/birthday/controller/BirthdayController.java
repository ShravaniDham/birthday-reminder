package com.birthday.controller;

import com.birthday.dto.BirthdayDTO;
import com.birthday.service.BirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/birthdays")
public class BirthdayController {

    @Autowired
    private BirthdayService birthdayService;

    @GetMapping
    public ResponseEntity<List<BirthdayDTO>> getAllBirthdays() {
        return ResponseEntity.ok(birthdayService.getAllBirthdays());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BirthdayDTO> getBirthdayById(@PathVariable Long id) {
        return ResponseEntity.ok(birthdayService.getBirthdayById(id));
    }

    @PostMapping
    public ResponseEntity<BirthdayDTO> createBirthday(@RequestBody BirthdayDTO birthdayDTO) {
        BirthdayDTO created = birthdayService.createBirthday(birthdayDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BirthdayDTO> updateBirthday(@PathVariable Long id, @RequestBody BirthdayDTO birthdayDTO) {
        return ResponseEntity.ok(birthdayService.updateBirthday(id, birthdayDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBirthday(@PathVariable Long id) {
        birthdayService.deleteBirthday(id);
        return ResponseEntity.noContent().build();
    }
}