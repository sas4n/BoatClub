package Model.searchRule;

import Model.BoatClub;
import Model.Member;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchByAge implements ISearchingStrategy {

    private int year;

    public SearchByAge(int input){
       this.year = input;
    }

    @Override
    public boolean searchMembers(Member member) {
        return (Integer.parseInt(member.getPersonalNumber().getPersonalNumber().substring(0,4))< year);
    }
}
