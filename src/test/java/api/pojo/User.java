package api.pojo;

public class User {

    private String name;
    private String username;
    private String email;
    private String phone;

    // Constructor
    public User(String name, String username, String email, String phone) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    // Getters (RestAssured los usa)
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
}
