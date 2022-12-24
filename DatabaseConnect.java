package my.uum;

import java.sql.*;

/**
 * This class is to link to SQLite database so that the data can be inserted, deleted, and selected.
 *
 * @author Tan Xue Lee (277407)
 */

public class DatabaseConnect {

    /**
     * This method is used to connect with database
     *
     * @return connection
     */

    public static Connection connect() {
        String url = "jdbc:sqlite:C:/sqlite/assignment2.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    /**
     * This method is used to insert the users' data into tbl_booking.
     */
    public void insertBooking(String user_name, String ic_no, String staff_id, String phone_no, String email, String room_id,
                       String booking_purpose, String booking_date, String booking_time) {
        String sqlInsert = "INSERT INTO tbl_booking (ic_no, staff_id, user_name, phone_no, email, room_id, booking_purpose, booking_date, booking_time) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnect.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert)) {
            preparedStatement.setString(1, ic_no);
            preparedStatement.setString(2, staff_id);
            preparedStatement.setString(3, user_name);
            preparedStatement.setString(4, phone_no);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, room_id);
            preparedStatement.setString(7, booking_purpose);
            preparedStatement.setString(8, booking_date);
            preparedStatement.setString(9, booking_time);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to delete the users' data from tbl_booking.
     */

    public void deleteBooking(String ic_no) {
            String sqlDelete = "DELETE FROM tbl_booking WHERE ic_no = ?";
            try (Connection conn = DatabaseConnect.connect();
                 PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete)) {
                preparedStatement.setString(1, ic_no);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * This method is used to select all details from tbl_booking and tbl_rooms for display.
     *
     * @return bookingData
     */

    public String displayList () {
        String bookingData = "";
        String sqlList = "SELECT tbl_booking.staff_id, tbl_booking.user_name, tbl_booking.phone_no, tbl_booking.email," +
                "tbl_booking.room_id, room_description, maximum_capacity, tbl_booking.booking_purpose, tbl_booking.booking_date," +
                "tbl_booking.booking_time FROM tbl_booking INNER JOIN tbl_rooms ON tbl_rooms.room_id WHERE tbl_booking.room_id = tbl_rooms.room_id";

        try (Connection conn = DatabaseConnect.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlList)) {

            while (rs.next()) {
                bookingData +=  "User Details:" +
                        "\n1. Name: " + rs.getString("user_name") +
                        "\n2. Staff ID: " + rs.getString("staff_id") +
                        "\n3. Phone Number: " + rs.getString("phone_no") +
                        "\n4. Email: " + rs.getString("email") +
                        "\n\nRoom Details:" +
                        "\n1. Room ID: " + rs.getString("room_id") +
                        "\n2. Room Description: " + rs.getString("room_description") +
                        "\n3. Maximum Capacity: " + rs.getString("maximum_capacity") +
                        "\n\nBooking Details:" +
                        "\n1. Booking Purpose: " + rs.getString("booking_purpose") +
                        "\n2. Booking Date: " + rs.getString("booking_date") +
                        "\n3. Booking Time: " + rs.getString("booking_time") +
                        "\n---------------------------------------------------------------------\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingData;
    }
}
