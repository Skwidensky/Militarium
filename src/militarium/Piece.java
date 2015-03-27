/*
 * Charles Swedensky
 * The year of the snek, two-thousand-and-fifteen
 * Metamorphosis
 */
package militarium;

/**
 *
 * @author Charles
 */
public class Piece {
    private String name;
    private String type;
    private int pos1;
    private int pos2;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the pos1
     */
    public int getTruePos1() {
        return pos1;
    }

    /**
     * @param pos1 the pos1 to set
     */
    public void setTruePos1(int pos1) {
        this.pos1 = pos1;
    }

    /**
     * @return the pos2
     */
    public int getTruePos2() {
        return pos2;
    }

    /**
     * @param pos2 the pos2 to set
     */
    public void setTruePos2(int pos2) {
        this.pos2 = pos2;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
