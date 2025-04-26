package com.test.bkk.backend.controller;

import com.test.bkk.backend.dto.User;
import com.test.bkk.backend.service.UserService;
import jakarta.validation.Valid;

// import org.apache.el.stream.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;



@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //  GET /users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET /users/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        Optional<User> userOpt = userService.getUserById(userId);
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error("User not found"));
        }
    }

    // POST /users
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(success("User created successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error("Invalid input: " + e.getMessage()));
        }
    }

    // PUT /users/{userId}
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @Valid @RequestBody User user) {
        try {
            userService.updateUser(userId, user);
            return ResponseEntity.ok(success("User updated successfully"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error("Invalid input: " + e.getMessage()));
        }
    }

    // DELETE /users/{userId}
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error(e.getMessage()));
        }
    }

    // Utility methods for consistent responses
    private static Object error(String message) {
        return new ErrorResponse(message);
    }

    private static Object success(String message) {
        return new SuccessResponse(message);
    }

    // DTO classes for response messages
    record ErrorResponse(String error) {}
    record SuccessResponse(String message) {}

}
