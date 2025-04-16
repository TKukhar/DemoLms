package loan.management.module.lms.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegistrationRequest {
    private String url;
    private String name;
    private String username;
    private String password;
}
