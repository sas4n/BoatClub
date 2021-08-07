package Model.searchRule;

import Model.BoatClub;
import Model.Member;

public interface ISearchingStrategy {
     boolean searchMembers(Member member);
}
