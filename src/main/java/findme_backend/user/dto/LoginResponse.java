package findme_backend.user.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String type = "Bearer";
}
