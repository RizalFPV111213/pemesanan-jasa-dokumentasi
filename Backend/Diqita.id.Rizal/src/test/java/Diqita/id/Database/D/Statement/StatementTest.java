package Diqita.id.Database.D.Statement;

import Diqita.id.Database.D.ConnectionUtil.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Import yang benar

public class StatementTest { // Ganti nama kelas

    @Test
    void testStatement() throws SQLException {
        try (Connection testConnection = ConnectionUtil.getDataSource().getConnection();
             Statement testStatement = testConnection.createStatement();
             ResultSet showSQL = testStatement.executeQuery("SELECT * FROM users;")) {

            while (showSQL.next()) {
                // Mengambil nilai dari kolom
                int userId = showSQL.getInt("user_id"); // Menggunakan nama kolom yang benar
                String name = showSQL.getString("name"); // Menambahkan kolom lain jika perlu
                String email = showSQL.getString("email"); // Menambahkan kolom lain jika perlu

                // Menampilkan hasil
                System.out.println("User ID: " + userId + ", Name: " + name + ", Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Menangani pengecualian SQL
        }
    }
}