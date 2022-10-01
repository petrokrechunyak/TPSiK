package subject.lab_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import subject.library.Connector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryService {

    public static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS deliveries(id SERIAL PRIMARY KEY,phone varchar(20) NOT NULL,address varchar(255) NOT NULL,courier varchar(40) NOT NULL,fragile bool NOT NULL DEFAULT false)";

    public static final String READ_ALL_QUERY = "SELECT * FROM deliveries";
    public static final String READ_ALL_BY_VALUE_QUERY = "SELECT * FROM deliveries WHERE %s ~ ";

    public static final String ADD_QUERY = "INSERT INTO deliveries VALUES ('%d', '%s', '%s', '%s', '%b')";

    public static final String EDIT_QUERY = "UPDATE deliveries SET phone='%s', address='%s', courier='%s', fragile=%b WHERE id=%d";

    public static final String DELETE_QUERY = "DELETE FROM deliveries WHERE id=%d";

    public static void createTable() {
        try {
            Connector.executeUpdate(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ObservableList<Delivery> readAll() {
        return returnByQuery(READ_ALL_QUERY);
    }

    public static ObservableList<Delivery> readAllByColumnAndValue(String column, String value) {
        return returnByQuery(String.format(READ_ALL_BY_VALUE_QUERY + value, column));
    }

    public static ObservableList<Delivery> readAllByColumnAndValue(String column, Boolean value) {
        return returnByQuery(String.format(READ_ALL_BY_VALUE_QUERY + value, column));
    }

    public static ObservableList<Delivery> readAllByColumnAndValue(String column, Long value) {
        return returnByQuery(String.format(READ_ALL_BY_VALUE_QUERY + value, column));
    }

    public static boolean add(Delivery d) {
        String query = String.format(ADD_QUERY,
                d.getId(), d.getPhone(), d.getAddress(), d.getCourier(), d.getFragile());
        try {
            Connector.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public static boolean update(Delivery d) {
        String query = String.format(EDIT_QUERY,
                d.getPhone(), d.getAddress(), d.getCourier(), d.getFragile(), d.getId());
        try {
            Connector.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(Delivery d) {
        String query = String.format(DELETE_QUERY, d.getId());
        try {
            Connector.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    private static ObservableList<Delivery> listByResultSet(ResultSet result) {
        ObservableList<Delivery> list = FXCollections.observableArrayList();
        try {
            while (result.next()) {
                Delivery delivery = new Delivery(
                        result.getLong("id"),
                        result.getString("phone"),
                        result.getString("address"),
                        result.getString("courier"),
                        result.getBoolean("fragile")
                );
                list.add(delivery);
            }
            return list;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static ObservableList<Delivery> returnByQuery(String query) {
        try {
            ResultSet result = Connector.executeQuery(query);
            return listByResultSet(result);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}
