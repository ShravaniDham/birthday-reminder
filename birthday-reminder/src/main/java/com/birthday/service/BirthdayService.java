package com.birthday.service;

import com.birthday.dto.BirthdayDTO;
import com.birthday.model.Birthday;
import com.birthday.model.User;
import com.birthday.repository.BirthdayRepository;
import com.birthday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BirthdayService {

    @Autowired
    private BirthdayRepository birthdayRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private BirthdayDTO convertToDTO(Birthday birthday) {
        BirthdayDTO dto = new BirthdayDTO();
        dto.setId(birthday.getId());
        dto.setName(birthday.getName());
        dto.setBirthDate(birthday.getBirthDate());
        dto.setBirthYear(birthday.getBirthYear());
        dto.setRelationship(birthday.getRelationship());
        return dto;
    }

    public List<BirthdayDTO> getAllBirthdays() {
        User currentUser = getCurrentUser();
        return birthdayRepository.findByUser(currentUser)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BirthdayDTO getBirthdayById(Long id) {
        User currentUser = getCurrentUser();
        Birthday birthday = birthdayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Birthday not found"));

        if (!birthday.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Access denied");
        }

        return convertToDTO(birthday);
    }

    public BirthdayDTO createBirthday(BirthdayDTO birthdayDTO) {
        User currentUser = getCurrentUser();

        Birthday birthday = new Birthday();
        birthday.setName(birthdayDTO.getName());
        birthday.setBirthDate(birthdayDTO.getBirthDate());
        birthday.setBirthYear(birthdayDTO.getBirthYear());
        birthday.setRelationship(birthdayDTO.getRelationship());
        birthday.setUser(currentUser);

        Birthday saved = birthdayRepository.save(birthday);
        return convertToDTO(saved);
    }

    public BirthdayDTO updateBirthday(Long id, BirthdayDTO birthdayDTO) {
        User currentUser = getCurrentUser();
        Birthday birthday = birthdayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Birthday not found"));

        if (!birthday.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Access denied");
        }

        birthday.setName(birthdayDTO.getName());
        birthday.setBirthDate(birthdayDTO.getBirthDate());
        birthday.setBirthYear(birthdayDTO.getBirthYear());
        birthday.setRelationship(birthdayDTO.getRelationship());

        Birthday updated = birthdayRepository.save(birthday);
        return convertToDTO(updated);
    }

    public void deleteBirthday(Long id) {
        User currentUser = getCurrentUser();
        Birthday birthday = birthdayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Birthday not found"));

        if (!birthday.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Access denied");
        }

        birthdayRepository.delete(birthday);
    }
}