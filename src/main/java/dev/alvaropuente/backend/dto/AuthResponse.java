package dev.alvaropuente.backend.dto;

public class AuthResponse {
	private String token;
	private String username;
	private Long id_user;

	public AuthResponse() {
		super();
	}

	public AuthResponse(String token, String username, Long id_user) {
		super();
		this.token = token;
		this.username = username;
		this.id_user = id_user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    public static class AuthResponseBuilder {
        private String token;
        private String username;
        private Long id_user;

        public AuthResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthResponseBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AuthResponseBuilder id_user(Long id_user) {
            this.id_user = id_user;
            return this;
        }

        public AuthResponse build() {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(this.token);
            authResponse.setUsername(this.username);
            authResponse.setId_user(this.id_user);
            return authResponse;
        }
    }
}
