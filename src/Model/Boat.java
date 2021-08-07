package Model;


public class Boat {

    private BoatType type;
    private double length;

   //Constructor
    public Boat(BoatType type, double length) {
        setType(type);
        setLength(length);
    }

    //return the type
    public BoatType getType() {
        return type;
    }

    // set the type
    public void setType(BoatType type) {
        this.type = type;

    }

    //return length of boat
    public double getLength() {
        return length;
    }

    //set the boat length if length is more than 70 or zero and less throws error
    public void setLength(double length) {
        if(length > 70 || length <= 0)
            throw new IllegalArgumentException("Boat length should be between 1 and 70 meters");
        this.length = length;
    }

}
