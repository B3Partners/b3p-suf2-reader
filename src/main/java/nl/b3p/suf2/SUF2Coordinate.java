package nl.b3p.suf2;

import java.awt.geom.Point2D;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2Coordinate {

    public double x;
    public double y;
    private Tag tag = null;

    public enum Tag {

        I1("eerste punt van een object"),
        I2("rechtlijnige verbinding met het vorige punt"),
        I4("cirkelboogverbinding met het vorige punt");
        String description;

        private Tag(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public SUF2Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public SUF2Coordinate(double x, double y, Tag tag) {
        this.x = x;
        this.y = y;
        this.tag = tag;
    }

    public void deleteTag() {
        tag = null;
    }

    public Tag getTag() {
        return tag;
    }

    public boolean hasTag() {
        return tag != null;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Coordinaat(" + x + ", " + y + ")" + (tag == null ? "" : " tag:" + tag.toString() + " (" + tag.getDescription() + ")");
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point2D.Double toPoint2D() {
        return new Point2D.Double(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SUF2Coordinate) {
            SUF2Coordinate compare = (SUF2Coordinate) o;
            return (getX() == compare.getX() && getY() == compare.getY());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 59 * hash + this.tag.hashCode();
        return hash;
    }
}
