package bookstore.dto.request;

import jakarta.annotation.Nonnull;

public record LoginRequest(
        @Nonnull
        String username,
        @Nonnull
        String password
) {
}
