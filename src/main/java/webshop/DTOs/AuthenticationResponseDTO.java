package webshop.DTOs;

import java.time.LocalDateTime;

public class AuthenticationResponseDTO {
    private String email;
    private LocalDateTime now;

    public AuthenticationResponseDTO(String email, LocalDateTime now) {
        this.email = email;
        this.now = now;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }
}
