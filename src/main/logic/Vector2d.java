package logic;

import java.util.ArrayList;
import java.util.Objects;

public class Vector2d {
    final public int x, y;

    Vector2d() {
        x = 0;
        y = 0;
    }

    Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    boolean precedes(Vector2d point) {
        return this.x <= point.x && this.y <= point.y;
    }

    boolean follows(Vector2d point) {
        return this.x >= point.x && this.y >= point.y;
    }

    Vector2d upperRight(Vector2d point) {
        int x = this.x;
        int y = this.y;
        if (point.x > x) x = point.x;
        if (point.y > y) y = point.y;
        return new Vector2d(x, y);
    }

    Vector2d lowerLeft(Vector2d point) {
        int x = this.x;
        int y = this.y;
        if (point.x <= x) x = point.x;
        if (point.y <= y) y = point.y;
        return new Vector2d(x, y);
    }

    Vector2d add(Vector2d point) {
        return new Vector2d(this.x + point.x, this.y + point.y);
    }

    Vector2d subtract(Vector2d point) {
        return new Vector2d(this.x - point.x, this.y - point.y);
    }

    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return that.x == this.x && that.y == this.y;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }

    Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    public static Vector2d generatePosition(int paramX, int paramY, AbstractWorldMap map) {
        int n = map.getUpperRight().x * map.getUpperRight().y;
        while (n > 0) {
            int x, y;
            Vector2d vector;
            x = (int) (Math.random() * (map.getUpperRight().x - paramX));
            x += paramX * 3 / 4;
            y = (int) (Math.random() * (map.getUpperRight().y - paramY));
            y += paramY * 3 / 4;
            vector = new Vector2d(x, y);
            if (!map.isOccupied(vector)) {
                return vector;
            }
            n--;
        }
        throw new ExceptionInInitializerError("Nie udalo sie znalezc pozycji dla zwierzecia");
    }

    public static Vector2d generateFreeSpace(Vector2d pos, AbstractWorldMap map) {
        ArrayList<Vector2d> positions = new ArrayList<Vector2d>();
        OptionsParser.getAllDirectionsVectors().forEach(direction -> positions.add(direction.toUnitVector().add(pos)));
        ArrayList<Vector2d> result = new ArrayList<>(positions);
        for (Vector2d elem : positions) {
            if (!map.canMoveTo(elem)) {
                result.remove(elem);
            }
        }
        if (result.size() == 0) {
            return Vector2d.generatePosition(0, 0, map);
        }
        else{
            return result.get((int)(Math.random()*(result.size()-1)));
        }
    }
}
