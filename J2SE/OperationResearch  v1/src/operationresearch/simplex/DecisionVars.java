package operationresearch.simplex;

import java.util.ArrayList;

public class DecisionVars {

    private String type;
    private ArrayList<Float> decisionVars;

    public DecisionVars() {
        decisionVars = new ArrayList<>();

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Float> getDecisionVars() {
        return decisionVars;
    }

    public void setDecisionVars(ArrayList<Float> decisionVars) {
        this.decisionVars = decisionVars;
    }

    public void addVar(float v) {
        decisionVars.add(v);

    }

}
