package View;

import Model.*;
import Model.searchRule.ISearchingStrategy;
import Util.UserChoiceInStartMenu;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StartMenu extends menu {

    private boolean isLoggedIn;

    //show the start menu
    public void showInstruction() {

        System.out.println("Welcome to Boat Club\n" +
                "-----------------------\n" +
                "Press 1 to add a new member\n" +
                "Press 2 to login\n"+
                "Press 3 to go to member menu\n" +
                "Press 4 to see the instruction\n" +
                "Press 5 to search for (a) member(s)\n" +
                "Press 6 to save\n" +
                "Press 7 to quit");
    }


    //take user input and return a suitable enum related to user input
    public UserChoiceInStartMenu getUserInputInStartMenu() {
        UserChoiceInStartMenu choice = null;

            String userInput = userStringInput();
            switch (userInput) {
                case "1":
                    choice = UserChoiceInStartMenu.ADD_NEW_MEMBER;
                    break;
                case "2":
                    choice = UserChoiceInStartMenu.LOG_IN;
                    break;
                case "3":
                    choice = UserChoiceInStartMenu.MEMBER_MENU;
                    break;
                case "4":
                    choice = UserChoiceInStartMenu.SEE_INSTRUCTION;
                    break;
                case "5":
                    choice = UserChoiceInStartMenu.SEARCH;
                    break;
                case "6":
                    choice = UserChoiceInStartMenu.SAVE;
                    break;
                case "7":
                    choice = UserChoiceInStartMenu.QUIT;
                    break;
                default:
                    showError();
            }
        return choice;
    }

    //if user use undefined option in start menu this error will be shown
     private void showError(){
        System.out.println("You need to enter the correct number\n");
     }

    //ask user to enter name and personal number to create that member and return it
    public Member showInstructionOfCreateMember(BoatClub boatClub) {
        Member member = null;
        boolean valid = false;
        String input = "";
            System.out.print("In order to add or update a member you have to enter following information : \n" +
                    "Please enter user name: ");
            String name = userStringInput();
        do {
            System.out.print("Please enter personal number in yyyy-mm-dd-checksum (without dash) format \n" +
                    "Or press q to go back to main menu: ");
            //long personalNumber = correctLong();
            input= userStringInput();
            try {
                if(input.equalsIgnoreCase("q"))
                    break;
               // ValidDigitForChecksum first = new ValidDigitForChecksum(Integer.parseInt(personalNumber.substring(8,10)));
                PersonalNumber personalNumberEntered = new PersonalNumber(LocalDate.parse(input.substring(0,8),DateTimeFormatter.BASIC_ISO_DATE),
                        new Last3DigitsBeforeChecksum(Integer.parseInt(input.substring(8,11))),new Checksum(Integer.parseInt(input.substring(11))));
                //boatClub.isPersonalNumberExisted(personalNumberEntered);
                member = boatClub.creatMember(name,personalNumberEntered);
               // System.out.println(first.toString());
                System.out.println(member.getPersonalNumber().getPersonalNumber());
                    // boatClub.addNewMember(member);
                     valid = true;
            }catch(IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }catch (DateTimeException ex){
                System.out.println(ex.getMessage());
            }
            //catch (Exception ex){
              //  System.err.println(ex.getMessage());
            //}

        }while (member==null || input.equalsIgnoreCase("q"));
        return member;
    }

    public Member showLogInMenu(){
        Member member = null;
        System.out.print("Please enter your username : ");
        String name = userStringInput();
        System.out.print("Please enter your password : ");
        String password = userStringInput();
        member = new Member(name,password);
        return member;
    }

    public void showLoginStatus(BoatClub boatClub){
        if (boatClub.getIsLoggedIn()){
            System.out.println("Logged in successfully");
        }
        else{
            System.out.println("Wrong or invalid username/password");
        }
    }

    //show confirmation message after adding that member
    public void confirmationMsg(Member member){
        if(member!=null)
              System.out.println("\n" + member.getName() +" is added\n");
    }

    //after adding a member asking if user wants to add more member or not and act upon that
    public boolean userWantsToAddMoreMember(Member member) {
        String answer ="";
        if(member != null) {
            do {
                System.out.println("Do you want to add another member\n" +
                        "Press \"yes\" to add a new member or \"no\" to go back to start menu");
                answer = userStringInput();
            } while (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"));
        }
        return answer.equalsIgnoreCase("yes");
    }

    //show the instruction of how program works only in parts that might be not clear when user start the program
    public void showInstructionOfProgram(){
        System.out.println("For checking the list of members whether in detail or just a compact list you can go\n" +
                "to member menu and choose which kind of list you want to see\n\n" +
                "For see a specific member's information or update or delete a member information\n" +
                "you need to go to \"member menu\" and then \"compact list\" then choose\n" +
                "index number of that member and choose your desired option\n\n" +
                "For adding or updating or deleting a member's boat you need to first go to a \"specific member's\n" +
                "information\" then choose your desired option there\n\n" +
                "For saving information you need to save all changes you made before you quit the program.\n");
        goBackToStartMenu();
    }

    public void showSearchMenu(BoatClub boatClub , ISearchingStrategy search){
        for(Member member : boatClub.searchForMember(search))
            System.out.println(member.getName() + ", " + member.getPersonalNumber().getPersonalNumber());
        goBackToStartMenu();
    }

    //show the confirmation message after saving the program
    public void existPersonalNumber(){
        System.out.println("This personal number already exist");
    }
    public void showSaveMsg(){
        System.out.println("The information is saved");
    }
    public void showLoggedInMsg(){System.out.println("You are already logged in");}
    }

