package com.nulp.railway.controller;

import com.nulp.railway.annotations.validator.ValidId;
import com.nulp.railway.dto.UserDto;
import com.nulp.railway.mapper.UserMapper;
import com.nulp.railway.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";
    private static final String LOG_MESSAGE_WITH_USER_ID = "Endpoint - {}({}) call";
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/current-user")
    public ResponseEntity<UserDto> getCurrentUser() {
        log.debug(LOG_MESSAGE, "getCurrentUser");
        var user = userMapper.toDto(userService.getCurrentUser());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        log.debug(LOG_MESSAGE, "getAllUsers");
        var users = userMapper.toDtoList(userService.getAll());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getById(@PathVariable @ValidId Long userId) {
        log.debug(LOG_MESSAGE_WITH_USER_ID, "getUserById", userId);
        var user = userMapper.toDto(userService.getById(userId));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto userDto) {
        log.debug(LOG_MESSAGE, "createUser");
        var user = userService.save(userMapper.toEntity(userDto));
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto userDto, @RequestParam @ValidId Long userId) {
        log.debug(LOG_MESSAGE_WITH_USER_ID, "updateUser", userId);
        var user = userService.update(userMapper.toEntity(userDto), userId);
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable @ValidId Long userId) {
        log.debug(LOG_MESSAGE_WITH_USER_ID, "deleteUserById", userId);
        userService.deleteById(userId);
    }

}