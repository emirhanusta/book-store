package bookstore.dto.request;

public record SignUpRequest (
        String username,
        String password,
        String email
){
}
