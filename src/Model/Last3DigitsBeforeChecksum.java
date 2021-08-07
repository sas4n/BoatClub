package Model;

public class Last3DigitsBeforeChecksum {
    private Checksum firstDigit;
    private Checksum secondDigit;
    private Checksum thirdDigit;
    private Checksum fourthDigit;
    private int digits;

   /* public Last3DigitsBeforeChecksum(ValidDigitForChecksum firstDigit, ValidDigitForChecksum secondDigit, ValidDigitForChecksum thirdDigit, ValidDigitForChecksum fourthDigit){
        setFirstDigit(firstDigit);
        setSecondDigit(secondDigit);
        setThirdDigit(thirdDigit);
        setFourthDigit(fourthDigit);
    }*/
     public Last3DigitsBeforeChecksum(int newNumber){
        setChecksum(newNumber);
     }

    private void setChecksum(int newNumber) {
        if(newNumber<0 || newNumber>999)
            throw new IllegalArgumentException("Invalid personal number check last 4 digits");
        this.digits = newNumber;
    }

    public int getInteger(){
        return this.digits;
    }

   /* private void setFirstDigit(ValidDigitForChecksum firstDigit) {
        this.firstDigit = firstDigit;
    }

    private void setSecondDigit(ValidDigitForChecksum secondDigit) {
        this.secondDigit = secondDigit;
    }

    private void setThirdDigit(ValidDigitForChecksum thirdDigit) {
        this.thirdDigit = thirdDigit;
    }

    private void setFourthDigit(ValidDigitForChecksum fourthDigit) {
        this.fourthDigit = fourthDigit;
    }

    public ValidDigitForChecksum getFirstDigit(){
        return this.firstDigit;
    }

    public ValidDigitForChecksum getSecondDigit(){
        return this.secondDigit;
    }

    public ValidDigitForChecksum getThirdDigit(){
        return this.thirdDigit;
    }

    public ValidDigitForChecksum getFourthDigit(){
        return this.fourthDigit;
    }*/


    @Override
    public boolean equals(Object obj){
        if(obj instanceof Last3DigitsBeforeChecksum && obj != null){
            Last3DigitsBeforeChecksum newLast3DigitsBeforeChecksum = (Last3DigitsBeforeChecksum)obj;
            return this.digits == newLast3DigitsBeforeChecksum.getInteger();
        }
        return false;
    }

   // public boolean equals(Checksum checksum){
     //   return this.checksum == checksum.getChecksumInteger();
  //  }

    @Override
    public String toString(){
        return this.digits+"";
    }

}
