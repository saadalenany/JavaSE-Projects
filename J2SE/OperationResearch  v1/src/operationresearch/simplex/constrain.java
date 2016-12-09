package operationresearch.simplex;

import java.util.ArrayList;
import javafx.scene.control.TextField;

public class constrain {

    private float consrain[];
    private String operator;
    private float rightHand;

    private int arraysize;

    public constrain(int size) {
        this.arraysize = size;
        this.consrain = new float[arraysize];

    }

    public float[] getConsrain() {
        return consrain;
    }

    public void setConsrain(float[] consrain) {
        this.consrain = consrain;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public float getRightHand() {
        return rightHand;
    }

    public void setRightHand(float rightHand) {
        this.rightHand = rightHand;
    }

    public void setConstarinValues( ArrayList<TextField> txts) {
        for (int i = 0; i < arraysize; i++) {
            consrain[i] = Float.parseFloat(txts.get(i).getText());

        }

    }
    
    public void showElemnts(){
    for(int i=0;i<arraysize;i++){
        System.out.print(consrain[i]+"");
        if(i!=arraysize-1){
            System.out.print(" + ");
        }
    
    }
    
        System.out.println("  "+operator+"  "+rightHand);
    
    }

}
