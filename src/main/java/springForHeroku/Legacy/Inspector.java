package springForHeroku.Legacy;

public class Inspector {

    public boolean inspect(String role) {
        return role.equals("ADMIN");
    }
}
