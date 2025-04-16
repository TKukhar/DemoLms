package loan.management.module.lms.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientRegistrationResponse {
    private Long id;
    private String url;
    private String name;
    private String username;
    private String password;
    private String token;
}

