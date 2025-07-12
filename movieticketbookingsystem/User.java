public class User {
    private final String id;
    private final String name;
    

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return name;
    }
}
