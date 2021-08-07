package Model;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Registry {
    private PrintWriter saver=null;

    //take a file path and return the string of what is written in the text
    public String verboseList(String filePath){
        String result = "";
        try {
            Scanner reader = new Scanner(new File(filePath));
            result+= reader.nextLine()+"\n";
            while (reader.hasNextLine()){
                result+= reader.nextLine()+"\n";
            }
            reader.close();
        }catch(Exception e){
            try {
                saver = new PrintWriter(new File(filePath));//if text file is not there this will make it first
            }catch (Exception ignored){

            }
        }
        return result;
    }

    //return an iterable of memebrs which loaded from text file
    public Iterable<Member> loadFromSavedFile(BoatClub boatClub, String result){
       // BoatClub boatClub1 = boatClub;
        ArrayList<Member> members = new ArrayList<>();
        String[] eachLines = result.split("[\\r\\n]+");//separate each line and put them in and array of string
        if(!eachLines[0].trim().isEmpty()){//In order to check if file is empty or not to not get error of index out boundary in line 62 (we have an array of length 1 which contains \\r\\n
           for (String lines : eachLines) {
               String[] parameters = lines.split(":");//separate each word and put them in an array
               //PersonalNumber personalNumber = new PersonalNumber(parameters[1]);
               PersonalNumber personalNumber = new PersonalNumber(LocalDate.parse(parameters[1].substring(0,8), DateTimeFormatter.BASIC_ISO_DATE),new Last3DigitsBeforeChecksum(Integer.parseInt(parameters[1].substring(8,11))),
                       new Checksum(Integer.parseInt(parameters[1].substring(11))));
               Member member = boatClub.makeMemberForLoadingInStartOfProgram(parameters[0], personalNumber,parameters[2]);
               member.setNumbersOfBoatsOwnByAMember(Integer.parseInt(parameters[3]));
               for (int i = 4; i < parameters.length - 1; i = i + 2) {
                     member.registerNewBoat(BoatType.valueOf(parameters[i]),Double.parseDouble(parameters[i+1]));
               }
               members.add(member);
               //boatClub1.addNewMember(member);
             //  boatClub.setMembers(members);
           }
       }
        return members;
    }

    //save or update text file each time we use save in start menu
    public void updateRegistryFile(BoatClub boatClub){
        File file = new File("SaveFile.txt");
        try {
            saver = new PrintWriter(file);
            for(Member member : boatClub.getAllMembersLocally()) {
                saver.print(member.getName() + ":" + member.changePersonalNumberToStringForSave() + ":"
                        + member.getMemberID() + ":" + member.numberOfBoats());
                for (Boat boat : member.boatsOwnedByMember()) {
                    saver.print(":" + boat.getType() + ":" + boat.getLength());
                }
                saver.println();
            }
            saver.close();

        }catch(Exception ignored) {

        }
    }
}
