package uk.co.xdesign.munros.dto;

public class SortBy {
    public enum Property {HEIGHT_METER, NAME};
    public enum Direction {ASC, DESC};

    private Property property;
    private Direction direction = Direction.ASC;

    public boolean height() {
        return getProperty() != null && getProperty().equals(Property.HEIGHT_METER);
    }

    public boolean name() {
        return getProperty() != null && getProperty().equals(Property.NAME);
    }

    public boolean isDesc() {
        return getDirection() != null && getDirection().equals(Direction.DESC);
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
