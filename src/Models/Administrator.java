package Models;

public class Administrator extends User{
    public Administrator(String id, String name, String password, String confirmPassword, String type) {
        super(id, name, password, confirmPassword, type);
    }
}
