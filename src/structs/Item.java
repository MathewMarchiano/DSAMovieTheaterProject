package structs;

public class Item extends KeyedItem<String> {

    private String name;

    public Item(String key, String name) {
        super(key);
        this.name = name;
    }

    public String toString() {
        return name + "[" + getKey() + "]";
    }
}
