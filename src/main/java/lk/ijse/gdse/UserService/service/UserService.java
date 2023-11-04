package lk.ijse.gdse.UserService.service;

import lk.ijse.gdse.UserService.dto.UserDTO;

public interface UserService {
    UserDTO saveUser(UserDTO user);
    UserDTO getSelectedUser(String id);
    void updateUser(UserDTO user);
    void  deleteUser(String id);
}
