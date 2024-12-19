package com.plantify.admin.controller;

import com.plantify.admin.domain.dto.request.UserRequest;
import com.plantify.admin.domain.dto.response.UserResponse;
import com.plantify.admin.domain.entity.Role;
import com.plantify.admin.global.response.ApiResponse;
import com.plantify.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/admin/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserResponse> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable Long userId, Model model) {
        UserResponse response = userService.getUser(userId);
        model.addAttribute("user", response);
        return "admin/user-detail";
    }

    @GetMapping("/{userId}/edit")
    public String editUserForm(@PathVariable Long userId, Model model) {
        UserResponse response = userService.getUser(userId);
        model.addAttribute("user", response);
        return "admin/user-edit";
    }

    @PostMapping("/{userId}/edit")
    public String updateUser(
            @PathVariable Long userId, @ModelAttribute UserRequest request) {
        userService.updateUser(userId, request);
        return "redirect:/v1/admin/users";
    }

    @PostMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/v1/admin/users";
    }
}
