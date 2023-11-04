package lk.ijse.gdse.UserService.util;
import lk.ijse.gdse.UserService.dto.UserDTO;
import lk.ijse.gdse.UserService.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DataTypeConversion {

    private final ModelMapper modelMapper;

    DataTypeConversion(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

   public UserDTO getUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
    public User getUserEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}
