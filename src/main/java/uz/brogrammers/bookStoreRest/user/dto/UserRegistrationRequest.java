package uz.brogrammers.bookStoreRest.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegistrationRequest {
    @NotNull
    String username;
    @NotNull
    String password;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
}
