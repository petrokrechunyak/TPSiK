package subject.lab_4;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LongStringConverter;

import java.util.Comparator;
import java.util.stream.Collectors;

public class Lab4Controller {
    @FXML
    private Button askDelete;
    @FXML
    private Label askDeleteLabel;
    @FXML
    private Button askDeleteNoButton;
    @FXML
    private Button askDeleteYesButton;
    @FXML
    private TextField addAddressField;
    @FXML
    private TextField addCourierField;
    @FXML
    private CheckBox addFragileField;
    @FXML
    private TextField addPhoneField;
    @FXML
    private TableColumn<Delivery, String> deleteAddressColumn;
    @FXML
    private TableColumn<Delivery, String> deleteCourierColumn;
    @FXML
    private TableColumn<Delivery, Boolean> deleteDeleteColumn;
    @FXML
    private TableColumn<Delivery, Boolean> deleteFragileColumn;
    @FXML
    private TableColumn<Delivery, Long> deleteIdColumn;
    @FXML
    private TableColumn<Delivery, String> deletePhoneColumn;
    @FXML
    private TableView<Delivery> deleteTable;
    @FXML
    private TableColumn<Delivery, String> editAddressColumn;
    @FXML
    private TableColumn<Delivery, String> editCourierColumn;
    @FXML
    private TableColumn<Delivery, Boolean> editFragileColumn;
    @FXML
    private TableColumn<Delivery, Long> editIdColumn;
    @FXML
    private TableColumn<Delivery, String> editPhoneColumn;
    @FXML
    private TableView<Delivery> editTable;
    @FXML
    private TableColumn<Delivery, String> searchAddressColumn;
    @FXML
    private TextField searchAddressField;
    @FXML
    private TableColumn<Delivery, String> searchCourierColumn;
    @FXML
    private TextField searchCourierField;
    @FXML
    private TableColumn<Delivery, Boolean> searchFragileColumn;
    @FXML
    private ComboBox<String> searchFragileField;
    @FXML
    private TableColumn<Delivery, Long> searchIdColumn;
    @FXML
    private TableColumn<Delivery, String> searchPhoneColumn;
    @FXML
    private TextField searchPhoneField;
    @FXML
    private TableView<Delivery> searchTable;
    @FXML
    private TableColumn<Delivery, String> showAddressColumn;
    @FXML
    private TableColumn<Delivery, String> showCourierColumn;
    @FXML
    private TableColumn<Delivery, Boolean> showFragileColumn;
    @FXML
    private TableColumn<Delivery, Long> showIdColumn;
    @FXML
    private TableColumn<Delivery, String> showPhoneColumn;
    @FXML
    private TableView<Delivery> showTable;
    @FXML
    private Label addErrorLabel;
    @FXML
    private Label editPhoneLabel;
    @FXML
    private Label successLabel;

    private ObservableList<Delivery> deliveries;

    private ObservableList<Delivery> searchDeliveries;

    @FXML
    public void initialize() {
        DeliveryService.createTable();
        deliveries = DeliveryService.readAll();
        deliveries.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        Delivery._id = deliveries.get(deliveries.size() - 1).getId();
        prepareTables();

        searchPhoneField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchDeliveries = deliveries.stream().filter(x -> x.getPhone().contains(newValue))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            clearFieldsForSearch(searchPhoneField);
            searchTable.setItems(searchDeliveries);
            searchTable.refresh();
        });
        searchAddressField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchDeliveries = deliveries.stream().filter(x -> x.getAddress().contains(newValue))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            clearFieldsForSearch(searchAddressField);
            searchTable.setItems(searchDeliveries);
            searchTable.refresh();
        });
        searchCourierField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchDeliveries = deliveries.stream().filter(x -> x.getCourier().contains(newValue))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            clearFieldsForSearch(searchCourierField);
            searchTable.setItems(searchDeliveries);
            searchTable.refresh();
        });
    }

    private void clearFieldsForSearch(Control textField) {
        if(searchPhoneField != textField)
            searchPhoneField.setText("");
        if(searchAddressField != textField)
            searchAddressField.setText("");
        if(searchCourierField != textField)
            searchCourierField.setText("");
        if(searchFragileField != textField)
            searchFragileField.setValue("");
    }

    public void addDelivery(ActionEvent event) {
        deliveries.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));

        String phone = addPhoneField.getText().trim();
        String address = addAddressField.getText().trim();
        String courier = addCourierField.getText().trim();
        Boolean fragile = addFragileField.isSelected();
        if (phone.equals("") || address.equals("") || courier.equals("")) {
            addErrorLabel.setText("Всі поля повинні бути заповнені!");
            addErrorLabel.setVisible(true);
            return;
        }
        try {
            Integer.parseInt(phone);
        } catch (NumberFormatException e) {
            addErrorLabel.setText("В поле \"номер телефону\" можна вводити тільки числа!");
            addErrorLabel.setVisible(true);
            return;
        }
        addErrorLabel.setVisible(false);
        deliveries.sort(Comparator.comparingInt(x -> x.getId().intValue()));
        Delivery delivery = new Delivery(++Delivery._id, phone, address, courier, fragile);
        DeliveryService.add(delivery);
        deliveries.add(delivery);
        addPhoneField.setText("");
        addAddressField.setText("");
        addCourierField.setText("");
        addFragileField.setSelected(false);
        update();
        successLabel.setText("Дані успішно додано!");
        successLabel.setVisible(true);
    }

    public void editPhone(TableColumn.CellEditEvent<Delivery, String> deliveryStringCellEditEvent) {
        try {
            Integer.parseInt(deliveryStringCellEditEvent.getNewValue());
            editPhoneLabel.setVisible(false);
            deliveryStringCellEditEvent.getTableView().getItems()
                    .get(deliveryStringCellEditEvent.getTablePosition().getRow()).setPhone(deliveryStringCellEditEvent.getNewValue());
            DeliveryService.update(deliveryStringCellEditEvent.getRowValue());
        } catch (NumberFormatException e) {
            editPhoneLabel.setText("В поле \"номер телефону\" можна вводити тільки числа!");
            editPhoneLabel.setVisible(true);
        }

    }

    public void editAddress(TableColumn.CellEditEvent<Delivery, String> deliveryStringCellEditEvent) {
        deliveryStringCellEditEvent.getTableView().getItems()
                .get(deliveryStringCellEditEvent.getTablePosition().getRow()).setAddress(deliveryStringCellEditEvent.getNewValue());
        DeliveryService.update(deliveryStringCellEditEvent.getRowValue());
    }

    public void editCourier(TableColumn.CellEditEvent<Delivery, String> deliveryStringCellEditEvent) {
        deliveryStringCellEditEvent.getTableView().getItems()
                .get(deliveryStringCellEditEvent.getTablePosition().getRow()).setCourier(deliveryStringCellEditEvent.getNewValue());
        DeliveryService.update(deliveryStringCellEditEvent.getRowValue());
    }

    public void askDeleteYes(ActionEvent event) {
        deliveries.stream().filter(Delivery::getDelete).forEach(DeliveryService::delete);
        deliveries = DeliveryService.readAll();
        deleteTable.setItems(deliveries);
        System.out.println(deliveries);
        update();
        askDeleteNo(event);
    }

    public void searchFragile(ActionEvent event) {
        String value = searchFragileField.getValue();
        clearFieldsForSearch(searchFragileField);
        if(value.equals("Так")) {
            searchDeliveries = deliveries.stream().filter(Delivery::getFragile)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        } else if(value.equals("Ні")) {
            searchDeliveries = deliveries.stream().filter(x -> !x.getFragile())
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        } else {
            searchDeliveries = deliveries;
        }
        searchTable.setItems(searchDeliveries);
        searchTable.refresh();
    }

    public void update() {
        editTable.setItems(deliveries);
        deleteTable.setItems(deliveries);
        showTable.setItems(deliveries);
        searchTable.setItems(deliveries);
        editTable.refresh();
        deleteTable.refresh();
        showTable.refresh();
        searchTable.refresh();
        successLabel.setVisible(false);
        clearFieldsForSearch(null);
    }

    // end main
    private void prepareTables() {
        editTable.setItems(deliveries);
        deleteTable.setItems(deliveries);
        showTable.setItems(deliveries);
        searchTable.setItems(deliveries);

        searchFragileField.getItems().addAll("", "Так", "Ні");
        prepareTable(editIdColumn, editPhoneColumn, editAddressColumn, editCourierColumn, editFragileColumn);

        // make editable
        editIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        editPhoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        editAddressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        editCourierColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        editFragileColumn.setCellFactory(column -> new CheckBoxTableCell<>());

        prepareTable(deleteIdColumn, deletePhoneColumn, deleteAddressColumn, deleteCourierColumn, deleteFragileColumn);
        deleteDeleteColumn.setCellValueFactory(cellData -> {
            Delivery cellValue = cellData.getValue();
            Boolean property = cellValue.getDelete();
            BooleanProperty booleanProperty = new SimpleBooleanProperty(property);

            // Add listener to handler change
            booleanProperty.addListener((observable, oldValue, newValue) -> {
                cellValue.setDelete(newValue);
                deliveries.stream().filter(x -> x.getId().equals(cellValue.getId())).forEach(x -> x = cellValue);
            });
            return booleanProperty;
        });
        deleteDeleteColumn.setCellFactory(column -> new CheckBoxTableCell<>());
        deleteFragileColumn.setCellFactory(CheckBoxTableCell.forTableColumn(deleteFragileColumn));


        prepareTable(showIdColumn, showPhoneColumn, showAddressColumn, showCourierColumn, showFragileColumn);
        showFragileColumn.setCellFactory(CheckBoxTableCell.forTableColumn(showFragileColumn));
        prepareTable(searchIdColumn, searchPhoneColumn, searchAddressColumn, searchCourierColumn, searchFragileColumn);
        searchFragileColumn.setCellFactory(CheckBoxTableCell.forTableColumn(searchFragileColumn));

    }

    private void prepareTable(TableColumn<Delivery, Long> editIdColumn, TableColumn<Delivery, String> editPhoneColumn, TableColumn<Delivery, String> editAddressColumn, TableColumn<Delivery, String> editCourierColumn, TableColumn<Delivery, Boolean> editFragileColumn) {
        editIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        editPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        editAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        editCourierColumn.setCellValueFactory(new PropertyValueFactory<>("courier"));
        editFragileColumn.setCellValueFactory(cellData -> {
            Delivery cellValue = cellData.getValue();
            Boolean property = cellValue.getFragile();
            BooleanProperty booleanProperty = new SimpleBooleanProperty(property);

            // Add listener to handler change
            booleanProperty.addListener((observable, oldValue, newValue) -> {
                cellValue.setFragile(newValue);
                deliveries.stream().filter(x -> x.getId().equals(cellValue.getId())).forEach(x -> x = cellValue);
                DeliveryService.update(cellValue);
            });
            return booleanProperty;
        });
    }

    public void askDelete(ActionEvent event) {
        askDeleteLabel.setVisible(true);
        askDeleteNoButton.setVisible(true);
        askDeleteYesButton.setVisible(true);
        askDelete.setVisible(false);
    }

    public void askDeleteNo(ActionEvent event) {
        askDeleteLabel.setVisible(false);
        askDeleteNoButton.setVisible(false);
        askDeleteYesButton.setVisible(false);
        askDelete.setVisible(true);
    }

}