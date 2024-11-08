package Models;

public class User {
    private String id;
    private String name;
    private String password;
    private String confirmPassword;
    private String type;

    public User(String id, String name, String password, String confirmPassword, String type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //toString
    @Override
    public String toString() {
        return "User id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Type: " + type + "\n" +
                "Print information success";
    }
}
