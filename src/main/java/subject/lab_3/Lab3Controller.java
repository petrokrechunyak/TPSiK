package subject.lab_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Lab3Controller {
    @FXML
    private Label errorLabel;

    @FXML
    private Label perimeterLabel;

    @FXML
    private Label squareLabel;

    @FXML
    private TextField x1EditText;

    @FXML
    private TextField x2EditText;

    @FXML
    private TextField x3EditText;

    @FXML
    private TextField y1EditText;

    @FXML
    private TextField y2EditText;

    @FXML
    private TextField y3EditText;

    @FXML
    private TextField lastnameField;

    @FXML
    private Label done;

    @FXML
    private Label errorField2;

    @FXML
    public void onFindClick(ActionEvent event) {

        double x1, x2, x3, y1, y2, y3;
        try {
            x1 = Double.parseDouble(x1EditText.getText());
            y1 = Double.parseDouble(y1EditText.getText());
            x2 = Double.parseDouble(x2EditText.getText());
            y2 = Double.parseDouble(y2EditText.getText());
            x3 = Double.parseDouble(x3EditText.getText());
            y3 = Double.parseDouble(y3EditText.getText());

        } catch (NumberFormatException e) {
            errorLabel.setText("Якесь із полей пусте! Будь ласка заповність всі поля");
            errorLabel.setOpacity(1);
            perimeterLabel.setText("");
            squareLabel.setText("");
            return;
        }

        double A = widthByDotes(x1, y1, x2, y2);
        double B = widthByDotes(x2, y2, x3, y3);
        double C = widthByDotes(x1, y1, x3, y3);

        errorLabel.setOpacity(0);

        perimeterLabel.setText("Периметр трикутника: " + Math.ceil(perimeter(A, B, C) * 100) / 100);
        squareLabel.setText("Площа трикутника: " + Math.ceil(square(A, B, C) * 100) / 100);
    }

    public static double widthByDotes(double x1, double y1, double x2, double y2) {
        return Math.ceil(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) * 1000) / 1000;
    }

    public static double perimeter(double A, double B, double C) {
        return Math.ceil((A + B + C) * 1000) / 1000;
    }

    public static double square(double A, double B, double C) {
        double p = (A + B + C) / 2;
        return Math.ceil(Math.sqrt(p * (p - A) * (p - B) * (p - C)) * 1000) / 1000;
    }

    // Exercise 2

    @FXML
    public void onFindClassmateClick(ActionEvent event) {
        String lastname = lastnameField.getText().trim();
        String firstname = classmate(lastname);
        if(firstname == null) {
            errorField2.setText("В групі немає студента з таким прізвищем!");
            done.setText("");
        } else {
            errorField2.setText("");
            done.setText("В групі є студент з прізвищем " + lastname + ". Це: " + firstname);
        }

    }

    public static String classmate(String lastname) {
        switch (lastname) {
            case "Беліч":
            case "Григоращук":
                return "Олександр";
            case "Глинський":
                return "Петро";
            case "Ісопеску":
            case "Софроній":
                return "Михайло";
            case "Кожокар":
                return "Діана";
            case "Няйко":
                return "Андрій";
            case "Пепін":
                return "Євген";
            case "Топало":
                return "Василь";
            default:
                return null;
        }
    }
}