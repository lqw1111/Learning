/*
Enhance the Inventory class to have a add/remove/size/countByType method and passes the tests.
    - Data Types and Modifiers must stay unchanged
    - The inventory must group parts by type
    - The inventory must not add parts with existing name
	(ex. if part "a" exists, should not add any more parts with the same name)
    - Enhance TableParts and ChairParts getDescription() method to pass their tests
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


enum PartsType {
    CHAIR_TYPE, TABLE_TYPE
}

class Inventory implements Storage {
    HashMap<PartsType, List<Part>> storage;

    Inventory() {
        this.storage = new HashMap<>();
    }

    @Override
    public void add(Part part) {
        if (storage.get(part.type) == null || !containsName(storage.get(part.getType()), part.getName())) {
            List<Part> parts = storage.getOrDefault(part.type, new ArrayList<>());
            parts.add(part);
            storage.put(part.type, parts);
        }
    }

    @Override
    public void remove(PartsType type, String partName) {
        if (storage.containsKey(type) && containsName(storage.get(type), partName)) {
            List<Part> parts = storage.get(type).stream().filter(x -> !x.getName().equals(partName)).collect(Collectors.toList());
            storage.put(type, parts);
        }
    }

    @Override
    public int size() {
        return storage.size();
    }


    @Override
    public int countByType(PartsType type) {
        return storage.get(type).size();
    }

    @Override
    public Integer countByType(Class<? extends Part> part) {
        if (part == TableParts.class) {
            return storage.get(PartsType.TABLE_TYPE).size();
        } else if (part.isInstance(ChairParts.class)) {
            return storage.get(PartsType.CHAIR_TYPE).size();
        }
        return -1;
    }

    @Override
    public void remove(Class<? extends Part> part, String partName) {
        if (storage.containsKey(part)) {
            List<Part> parts = storage.get(part);
            if (this.containsName(parts, partName)) {
                parts.remove(part);
            }
        }
    }

    @Override
    public boolean containsName(List<Part> list, String name) {
        for (Part part : list) {
            if (part.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}

interface Storage {
    void add(Part part);

    void remove(PartsType type, String partName);

    int size();

    int countByType(PartsType type);

    Integer countByType(Class<? extends Part> part);

    void remove(Class<? extends Part> part, String partName);

    boolean containsName(final List<Part> list, final String name);
}

abstract class Part {
    protected PartsType type;
    protected String name;

    Part(PartsType type, String name) {
        this.name = name;
        this.type = type;
    }

    public PartsType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return "";
    }
}

class ChairParts extends Part {

    ChairParts(String name) {
        super(PartsType.CHAIR_TYPE, name);
    }

    @Override
    public PartsType getType() {
        return super.type;
    }

    @Override
    public String getDescription() {
        return "(" + this.name + ")";
    }
}

class TableParts extends Part {

    TableParts(String name) {
        super(PartsType.TABLE_TYPE, name);
    }

    @Override
    public PartsType getType() {
        return super.type;
    }

    @Override
    public String getDescription() {
        return "((" + this.name + "))";
    }


}

class Factory {
    Part createTable(String name) {
        return new TableParts(name);
    }

    Part createChair(String name) {
        return new ChairParts(name);
    }
}

class Main1 {
    public static void main(String[] args) {
        run(new Inventory(), new Factory());
    }

    public static void run(Inventory storage, Factory factory) {

        storage.add(factory.createTable("a"));

        storage.add(factory.createChair("a"));
        storage.add(factory.createChair("b"));
        storage.add(factory.createChair("b"));
        storage.add(factory.createChair("c"));

        assertEqual(storage.size(), 2);
        assertEqual(storage.countByType(PartsType.TABLE_TYPE), 1);
        assertEqual(storage.countByType(PartsType.CHAIR_TYPE), 3);

        storage.remove(PartsType.TABLE_TYPE, "a");
        storage.remove(PartsType.TABLE_TYPE, "a");
        assertEqual(storage.countByType(PartsType.TABLE_TYPE), 0);

        Part newChair = factory.createChair("newChair");
        Part newTable = factory.createTable("newTable");

        assertEqual(newChair.getDescription(), "(newChair)");
        assertEqual(newTable.getDescription(), "((newTable))");

        storage.add(newChair);
        assertEqual(storage.countByType(PartsType.CHAIR_TYPE), 4);
        storage.remove(ChairParts.class, "newChair");
        assertEqual(storage.countByType(ChairParts.class), 3);


        System.out.println("done.");

    }

    static void assertEqual(Object o1, Object o2) {
        if (o1 == o2 || o1.equals(o2)) {
            return;
        }
        throw new RuntimeException("Assertion failed " + o1.toString() + " is not equal to " + o2.toString());
    }

}