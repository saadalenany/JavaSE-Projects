

package operationresearch.Duality.InnerPackage;

import java.util.ArrayList;


public class DecisionVars {
    
    private String type;
    private ArrayList<Float>decisionVars;
    private String operator;

    public DecisionVars() {
       decisionVars=new ArrayList<Float>();
        
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
    
    public void addVar(float v){
    decisionVars.add(v);
    
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    
    
    
    
    
}
