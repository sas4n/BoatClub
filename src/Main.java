import Controller.MainController;
import Model.*;
import Model.searchRule.CompositeSearch;
import Model.searchRule.ISearchingStrategy;
import Model.searchRule.SearchByAge;
import Model.searchRule.SearchNameStartWithString;
//import Util.Checksum;
import View.StartMenu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        //System.out.print(PersonalNumber.DigitsForChecksum);

        BoatClub boatClub = new BoatClub();
        PersonalNumber pn1=new PersonalNumber(LocalDate.parse("19811228", DateTimeFormatter.BASIC_ISO_DATE), new Last3DigitsBeforeChecksum(987),new Checksum(4));
        boatClub.addNewMember(boatClub.creatMember("nisa",pn1));
        PersonalNumber pn2=new PersonalNumber(LocalDate.parse("19970818",DateTimeFormatter.BASIC_ISO_DATE), new Last3DigitsBeforeChecksum(642),new Checksum(5));
        boatClub.addNewMember(boatClub.creatMember("Ju",pn2));
        PersonalNumber pn3=new PersonalNumber(LocalDate.parse("19670919",DateTimeFormatter.BASIC_ISO_DATE), new Last3DigitsBeforeChecksum(953),new Checksum(0));
        boatClub.addNewMember(boatClub.creatMember("tiba",pn3));
        boatClub.save();
        StartMenu menu=new StartMenu();
        ISearchingStrategy search = new SearchByAge(1990);
        ISearchingStrategy search1 = new SearchNameStartWithString("ni");

        CompositeSearch cm = new CompositeSearch();
        cm.add(search);
        //cm.add(search1);
        MainController user = new MainController(boatClub);
        user.memberAction(menu,cm);
    }
}
