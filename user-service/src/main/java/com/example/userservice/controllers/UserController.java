package com.example.userservice.controllers;

import com.example.userservice.entities.User;
import com.example.userservice.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('managers')")
    public User create(@Valid @RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public User get(@NotNull @PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<User> listAll() {
        return userService.listAll();
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('managers')")
    public User update(@PathVariable Long id, @Valid @RequestBody User input) throws BadRequestException {
        if (!Objects.equals(id, input.getId())) {
            throw new BadRequestException("Id from url does not match input!");
        }
        return userService.update(input);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('managers')")
    public User delete(@NotNull @PathVariable Long id) {
        return userService.delete(id);
    }
}
