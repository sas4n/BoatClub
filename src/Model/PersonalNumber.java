package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PersonalNumber {

    private LocalDate date;
    // private DateFormat birthday = new DateFormat();
       // private int checksum;
    private boolean valid;
    private String birthDate;
    private String personalNumber;
    private Last3DigitsBeforeChecksum last3DigitsBeforeChecksum;
    private Checksum checksum;

        public PersonalNumber(){
        }
        public PersonalNumber(LocalDate newDate , Last3DigitsBeforeChecksum last3DigitsBeforeChecksum,Checksum checksum){
           setDate(newDate);
           setLast3DigitsBeforeChecksum(last3DigitsBeforeChecksum);
           setChecksum(checksum);
            //date =LocalDate.parse(id.substring(0,8), DateTimeFormatter.BASIC_ISO_DATE);
           // setChecksum(id.substring(9));
           // checksum = Integer.parseInt(id.substring(9));
           // this.id = id;
        }

    private void setLast3DigitsBeforeChecksum(Last3DigitsBeforeChecksum last3DigitsBeforeChecksum) {
            this.last3DigitsBeforeChecksum = last3DigitsBeforeChecksum;
    }


    public PersonalNumber(String dataFromRegistry){
            setPersonalNumber(dataFromRegistry);
        }

    private void setDate(LocalDate newDate) {
            this.date = newDate;
    }

    public void setPersonalNumber(String personalNumber) {
       // if(!validID(personalNumber)){
       //     throw new IllegalArgumentException("Invalid personal number");
       // }
        this.personalNumber = personalNumber;
    }

    public LocalDate getDate(){
            return this.date;
    }

        public boolean validChecksum(){
            String birth = date.format(DateTimeFormatter.BASIC_ISO_DATE);
            int ch2 = Integer.parseInt(birth.substring(2,3)) * 2;
            int ch3 = Integer.parseInt(birth.substring(3,4));
            int ch4 = Integer.parseInt(birth.substring(4,5)) * 2;
            int ch5 = Integer.parseInt(birth.substring(5,6));
            int ch6 = Integer.parseInt(birth.substring(6,7)) * 2;
            int ch7 = Integer.parseInt(birth.substring(7,8));
            int ch9 = Integer.parseInt(last3DigitsBeforeChecksum.toString().substring(0,1)) * 2;
            int ch10 = Integer.parseInt(last3DigitsBeforeChecksum.toString().substring(1,2));
            int ch11 = Integer.parseInt(last3DigitsBeforeChecksum.toString().substring(2,3))* 2;
            int sum = ch2 / 10 + ch2 % 10 + ch3 / 10 + ch3 % 10 + ch4 / 10 + ch4 % 10 + ch5 / 10 + ch5 % 10 + ch6 / 10 + ch6 % 10
                    + ch7 / 10 + ch7 % 10 + ch9 / 10 + ch9 % 10 + ch10 / 10 + ch10 % 10 + ch11 / 10 + ch11 % 10;
            int chech = (10 - sum % 10) % 10;
            int checksum1 = this.checksum.getNumber();
            if (checksum1 % 10 == chech){
                return true;
            }
            else
                return false;
        }
    private void setChecksum(Checksum checksum) {

        this.checksum = checksum;
        if(!validChecksum())
            throw new IllegalArgumentException("Invalid Personal number");
    }

    public int getyear(){
            return date.getYear();
    }

    public String getBirthDate() {
        return date.format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    public Last3DigitsBeforeChecksum getLast3DigitsBeforeChecksum(){
            return this.last3DigitsBeforeChecksum;
    }

    @Override
    public boolean equals(Object obj){
            if(obj instanceof PersonalNumber){
                PersonalNumber newPersonalNumber = (PersonalNumber)obj;
                return this.date.compareTo(newPersonalNumber.getDate())==0 && this.last3DigitsBeforeChecksum.equals(newPersonalNumber.getLast3DigitsBeforeChecksum())
                        &&this.checksum.equals(newPersonalNumber.getChecksum());
            }
            return false;
    }

    public Checksum getChecksum(){
            return this.checksum;
    }

    public String getPersonalNumber(){
            return date.format(DateTimeFormatter.BASIC_ISO_DATE) + this.last3DigitsBeforeChecksum.toString() +this.checksum.toString();
    }
}
