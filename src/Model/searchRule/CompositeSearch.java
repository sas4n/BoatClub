package Model.searchRule;

import Model.Member;

import java.util.ArrayList;

public class CompositeSearch implements ISearchingStrategy {

    private ArrayList<ISearchingStrategy> searchRules;

    public CompositeSearch(){
        searchRules = new ArrayList<>();
    }

    public void add(ISearchingStrategy searchMethod){
        searchRules.add(searchMethod);
    }
    @Override
    public boolean searchMembers(Member member) {
        boolean resultRule = true;
        for(ISearchingStrategy searchMethod : searchRules){
            resultRule &= searchMethod.searchMembers(member);
        }
        return resultRule;
    }
}
