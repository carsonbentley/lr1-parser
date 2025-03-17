package parser;

import java.util.*;

public class State implements Comparable<State> {
    private Set<Item> itemSet;
    private List<Item> items;
    private int name;

    public State(int name) {
        this.itemSet = new HashSet<>();
        this.items = new ArrayList<>();
        this.name = name;
    }

    public State() {
        this(0);
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        ArrayList<Item> sortedList = new ArrayList<>(items);
        sortedList.sort(Comparator.comparingInt(Item::hashCode));
        for (Item item : sortedList) {
            hash = 37 * hash + Objects.hashCode(item);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (items.size() != other.items.size()) {
            return false;
        }
        for (Item item : items) {
            if (!other.itemSet.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name + ": " + items.toString();
    }

    @Override
    public int compareTo(State o) {
        return new Integer(this.name).compareTo(new Integer(o.name));
    }

    // Helper function to add item to the state
    public boolean addItem(Item item){
        if(this.itemSet.add(item)){
            this.itemSet.add(item);
            this.items.add(item);
            return true;
        }
        return false;
    }

    //Helper function to get the set of all items in a state
    public Set<Item> getItems() {
        return itemSet;
    }
    public List<Item> getItemList() {
        return items;
    }

    public Integer size() {
        return itemSet.size();
    }

}
