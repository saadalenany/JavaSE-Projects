package Server.controllers;

import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class Validations {

    public void ValidateNumber(TextField tf){
        tf.textProperty().addListener(e -> {
            if (!tf.getText().matches("\\d*")) {    //if not match a number character
                tf.setText(tf.getText().replaceAll("[^\\d]", ""));//replace it with nothing
            }
        });
    }

    public void ValidateName(TextField tf){
        tf.textProperty().addListener(e -> {
            if (tf.getText().matches("[\\!-\\@\\[-\\`\\{-\\~]")) { //matches number or symbol
                tf.setText(tf.getText().replaceAll("[\\!-\\@\\[-\\`\\{-\\~]", ""));//replace it with nothing
            }
        });
    }

    public void ValidatePhone(TextField tf){
        tf.textProperty().addListener(e -> {
            if (tf.getText().matches("[\\!-\\*\\:-\\~ \\, \\/]+")){    //match phone number if doesn't contain 0->9 . - +
                tf.setText(tf.getText().replaceAll("[\\!-\\*\\:-\\~ \\, \\/]+", ""));//replace it with nothing
            }
        });
    }

    public void ValidateEmail(TextField tf){
        tf.textProperty().addListener(e -> {
            if (tf.getText().matches("[ \\!-\\* \\, \\/\\:-\\?\\[-\\^\\`\\{-\\~]+")){    //matches if does contain space , symbols except for @ + - _ .
                tf.setText(tf.getText().replaceAll("[ \\!-\\* \\, \\/\\:-\\?\\[-\\^\\`\\{-\\~]+", ""));//replace it with nothing
            }
        });
    }

}
