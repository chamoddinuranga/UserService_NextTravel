package lk.ijse.gdse.UserService.api;

import jakarta.validation.Valid;
import lk.ijse.gdse.UserService.dto.UserDTO;
import lk.ijse.gdse.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    UserDTO saveCustomer(@Valid @RequestBody UserDTO userDTO, Errors errors) {
        //Validation
        if(errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errors
                    .getFieldErrors().get(0).getDefaultMessage());
        }
        return userService.saveUser(userDTO);
    }

    @GetMapping(value = "{id:[A-Fa-f0-9\\-]{36}}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> getSelectedCustomer(@Valid  @PathVariable String id) {
        UserDTO selectedCustomer = userService.getSelectedUser(id);
        return selectedCustomer != null ? ResponseEntity.ok(selectedCustomer) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id:[A-Fa-f0-9\\-]{36}}")
    void deleteUser(@Valid @PathVariable String id) {
        userService.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "{id:[A-Fa-f0-9\\-]{36}}" )
    void updateUser(@Valid @PathVariable String id, @RequestBody UserDTO user, Errors errors) {
        //Validation
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            errors.getFieldErrors().get(0).getDefaultMessage());
        }
        user.setUserId(id);
        userService.updateUser(user);
    }
}
