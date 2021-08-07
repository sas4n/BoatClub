package Model;

public class Checksum {
    private int number;

    public Checksum(int newNumber){
        setNumber(newNumber);
        }

    private void setNumber(int newNumber) {
        if(newNumber<0 || newNumber>9)
            throw new IllegalArgumentException("wrong input for checksum");
        this.number = newNumber;

    }

    public int getNumber(){
        return this.number;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Checksum){
            Checksum digit = (Checksum) obj;
            return this.number == digit.getNumber();
        }
        return false;
    }

    @Override
    public String toString(){
        return this.number+"";
    }
}

