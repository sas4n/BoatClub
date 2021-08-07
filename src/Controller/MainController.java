package Controller;

        import Model.Boat;
        import Model.BoatClub;
        import Model.Member;
        import Model.searchRule.ISearchingStrategy;
        import Util.UserChoiceInBoatMenu;
        import Util.UserChoiceInMemberMenu;
        import Util.UserChoiceInStartMenu;

        import View.*;

        import java.awt.*;

public class MainController {

    private BoatClub boatClub;
    private MemberMenu memberMenu;
    private Member member;

    public MainController(BoatClub boatClub) {
        this.boatClub = boatClub;
    }

    //every scenarios would happen in this method
    public void memberAction(StartMenu menu, ISearchingStrategy search){
        boatClub.getAllMembersFromRegistry();//load all members' information from the text file in the beginning of program
        while(!actUponUserInputInStartMenu(menu,search)) ;
    }

    //handling all user choices in main menu
    private boolean actUponUserInputInStartMenu(StartMenu menu, ISearchingStrategy search) {
        menu.showInstruction();
        UserChoiceInStartMenu userChoice = menu.getUserInputInStartMenu();

        while (userChoice !=null) {
            switch (userChoice) {
                case ADD_NEW_MEMBER:

                    if (boatClub.getIsLoggedIn()){

                    //if the user must log in to create a member, so when text file is empty and can't log in, it can't run
                        userWantsToAddMember(menu);
                    }else
                        userNeedToLogIn(menu);
                    userChoice=null;//for exiting the loop after add a member
                    break;
                case LOG_IN:
                    if(!boatClub.getIsLoggedIn())
                       userWantsToLogin(menu);
                    menu.showLoggedInMsg();
                    userChoice=null;
                    break;
                case MEMBER_MENU:
                    actUponUserInputInMemberMenu();
                    userChoice=null;//for exit the loop since userchoice does not change after member menu closed so we always come back here and user choice would be same
                    break;
                case SEE_INSTRUCTION:
                    menu.showInstructionOfProgram();
                    userChoice=null;//for exit the loop since userchoice does not change after member menu closed so we always come back here and user choice would be same
                    break;
                case SEARCH:
                    menu.showSearchMenu(boatClub, search);
                    userChoice=null;//for exit the loop since userchoice does not change after member menu closed so we always come back here and user choice would be same
                    break;
                case SAVE:
                    boatClub.save();
                    userChoice=null;
                    menu.showSaveMsg();
                    break;
                case QUIT:
                    return true;
            }
        }
        return false;
    }


    private void userWantsToLogin(StartMenu startMenu){
        Member newMember = startMenu.showLogInMenu();
        boatClub.checkLogin(newMember);
        startMenu.showLoginStatus(boatClub);
    }
    private void userWantsToAddMember(StartMenu menu){
        Member newMember;
            do {
                 newMember = menu.showInstructionOfCreateMember(boatClub);
                    menu.confirmationMsg(boatClub.addNewMember(newMember));
            }while(menu.userWantsToAddMoreMember(newMember));
    }

    //handling all user choices in member menu
    private void actUponUserInputInMemberMenu() {
        memberMenu = new MemberMenu();
        // MemberMenu menu = new MemberMenu();
        boolean IWantToGoBack = false;
        while (!IWantToGoBack) {
            memberMenu.showInstruction();
            UserChoiceInMemberMenu userChoice = memberMenu.getUserInputInMemberMenu();
            switch (userChoice) {
                case COMPACT_LIST:
                    this.member = memberMenu.showCompactList(boatClub);//this boat club here show the list and assign the members to the boat club


                        actionOnCompactList();


                    // if(this.member!=null)
                    IWantToGoBack = true;
                    break;
                case VERBOSE_LIST:
                    memberMenu.showVerboseList(boatClub);
                    IWantToGoBack=true;
                    break;
                case QUIT:
                    IWantToGoBack = true;
                    break;
            }
        }
    }

    //handling all user choices in showing list of members
    private void actionOnCompactList(){
        //UserChoiceInMemberMenu choice = null;
        boolean goBack=false;
        UserChoiceInMemberMenu choice = this.memberMenu.getInputInCompactList();
        while(!goBack && choice!=null ){

            switch (choice){
                case DELETE:
                    if (boatClub.getIsLoggedIn()) {
                        memberMenu.showDeletedMemberConfirmationMsg(boatClub.deleteMember(this.member));
                    }else {
                        memberMenu.needToLogInMsg();
                    }
                    goBack = true;
                    break;
                case UPDATE:
                    if (boatClub.getIsLoggedIn()) {
                    memberMenu.showUpdateMemberMenu(member,boatClub);
                    }else {
                        memberMenu.needToLogInMsg();
                    }
                    //boatClub.updateMemberInformation(member, memberMenu.getName(), memberMenu.getPersonalNumber());
                    goBack = true;
                    break;
                case SPECIFIC_MEMBER:
                    memberMenu.showMemberInformation(member);
                    goBack = memberMenu.askUserForChooseAnOptionInBoatMenu(member);

                    actUponUserInputInBoatMenu();
                    choice = null;
                    // goBack = true;
                    break;
                case QUIT:
                    goBack =true;
                    break;
            }
        }
    }

    //handling all user choices for boat issues
    private void actUponUserInputInBoatMenu(){
        if (boatClub.getIsLoggedIn()) {
        UserChoiceInBoatMenu choice = memberMenu.getUserInputInBoatMenu();

        switch (choice){
            case ADD_NEW_BOAT:
                //member= boatClub.getMember(menu.ShowAccessToMember());
                Boat boat = memberMenu.showRegisterNewBoatMenu(member);
               // member.registerANewBoat(boat);
                memberMenu.showAddedBoatConfirmation(boat);
               // System.out.println(member.numberOfBoats());
                // choice = null;
                break;
            case CHANGE_BOAT_INFORMATION:
                // member= boatClub.getMember(menu.ShowAccessToMember());
                Boat boat1 = memberMenu.showDeleteOrChangeABoat(member,UserChoiceInBoatMenu.CHANGE_BOAT_INFORMATION);
              //  memberMenu.askUserToUpdateBoatData(boat1);
                if(boat1 !=null)
                    memberMenu.showUpdatedBoatConfirmation(boat1);
                break;
            case DELETE_BOAT:
                //member= boatClub.getMember(menu.ShowAccessToMember());
                Boat boat2 = memberMenu.showDeleteOrChangeABoat(member,UserChoiceInBoatMenu.DELETE_BOAT);
               // member.deleteBoat(boat2);
                if(boat2 != null)
                    memberMenu.showDeletedBoatConfirmation(boat2);
                break;
            case GO_BACK:
                break;
        }
        }else {
            memberMenu.needToLogInMsg();
        }
    }

    private void userNeedToLogIn(menu menu){
        menu.needToLogInMsg();
    }

}


