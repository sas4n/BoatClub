package Model.searchRule;

import Model.Member;

public class SearchNameStartWithString implements ISearchingStrategy {

    private String input;

    public SearchNameStartWithString(String newSearch){
        this.input  = newSearch;
    }
    @Override
    public boolean searchMembers(Member member) {
        return member.getName().startsWith(input);
    }
}
