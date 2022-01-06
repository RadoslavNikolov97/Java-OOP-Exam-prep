package spaceStation.models.bags;

import java.util.ArrayList;
import java.util.Collection;

public class Backpack implements Bag{
    Collection<String> items;

    public Backpack() {
        items = new ArrayList<>();
    }

    @Override
    public Collection<String> getItems() {
        return items;
    }
}
