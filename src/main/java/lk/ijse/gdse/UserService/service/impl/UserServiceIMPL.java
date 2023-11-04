package lk.ijse.gdse.UserService.service.impl;

import lk.ijse.gdse.UserService.dto.UserDTO;
import lk.ijse.gdse.UserService.entity.User;
import lk.ijse.gdse.UserService.exception.InvalidException;
import lk.ijse.gdse.UserService.exception.NotFoundException;
import lk.ijse.gdse.UserService.persistance.UserDAO;
import lk.ijse.gdse.UserService.service.UserService;
import lk.ijse.gdse.UserService.util.DataTypeConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceIMPL implements UserService {

    @Autowired
    private DataTypeConversion dataTypeConversion;
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDTO saveUser(UserDTO user) {
        return dataTypeConversion.getUserDTO(userDAO
                .save(dataTypeConversion.getUserEntity(user)));
    }

    @Override
    public UserDTO getSelectedUser(String id) {
        if(!userDAO.existsById(id)) throw new InvalidException("User not found");
        return dataTypeConversion.getUserDTO((userDAO.getUserByUserId(id)));
    }

    @Override
    public void updateUser(UserDTO user) {
        Optional<User> tmpUser = userDAO.findById(user.getUserId());
        if(!tmpUser.isPresent()) throw new NotFoundException("User Not found");
        tmpUser.get().setFirstName(user.getFirstName());
        tmpUser.get().setLastName(user.getLastName());
        tmpUser.get().setGender(user.getGender());
        tmpUser.get().setUserNIC(user.getUserNIC());
        tmpUser.get().setEmail(user.getEmail());
        tmpUser.get().setAddress(user.getAddress());
        tmpUser.get().setContactNumber(user.getContactNumber());
        tmpUser.get().setProfilePicture(user.getProfilePicture());
        tmpUser.get().setNICFrontPhoto(user.getNicFrontPhoto());
        tmpUser.get().setNICBackPhoto(user.getNicBackPhoto());

    }

    @Override
    public void deleteUser(String id) {
        if (!userDAO.existsById(id)) throw new NotFoundException("Invalid user");
        userDAO.deleteByUserId(id);
    }
}
