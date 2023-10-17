package com.nighthawk.spring_portfolio.mvc.astronomy;

public class CelestialObject {

    private String id;
    private String name;
    private String type;

    public CelestialObject() {
        // Default constructor
    }

    public CelestialObject(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Getter and Setter methods for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CelestialObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
