package sk.juvius.ulet.commands.login.model;

public class User {

    private final int id;
    private final String name;
    private final String hash;

    public User(int id, String name, String hash) {
        this.id = id;
        this.name = name;
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public String getHash() {
        return hash;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
