package lk.ijse.gdse.UserService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDTO implements Serializable {
    @Null(message="User code will auto generate")
    private String userId;
    @NotNull(message = "User name cannot be empty")
    @Pattern(regexp = "[A-Za-z]+")
    private String firstName;
    @NotNull(message = "User name cannot be empty")
    @Pattern(regexp = "[A-Za-z]+")
    private String lastName;
    private String gender;
    @NotNull(message = "User NIC cannot be empty")
    @Pattern(regexp = "^(\\d{12}|\\d{9}[vV])$")
    private String userNIC;
    @NotNull(message = "User email cannot be empty")
    @Email
    private String email;
    @NotNull(message = "User address cannot be empty")
    private String address;
    @NotNull(message = "User Contact Number cannot be empty")
    @Pattern(regexp = "^(7[0-9]{8}|[89][0-9]{8})$")
    private String contactNumber;
    @NotNull(message = "User profile picture cannot be empty")
    private byte profilePicture;
    private byte nicFrontPhoto;
    private byte nicBackPhoto;
}
