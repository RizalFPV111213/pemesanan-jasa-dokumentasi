package Diqita.Rizal.Perancangan_Sistem_Aplikasi.controller;

import Diqita.Rizal.Perancangan_Sistem_Aplikasi.Util.JpaUtil;
import Diqita.Rizal.Perancangan_Sistem_Aplikasi.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//http://localhost:8080/customers/find?id=2

@WebServlet("/customers/find")
public class CustomerFindServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = JpaUtil.getEntityManagerFactory(); // Inisialisasi EntityManagerFactory
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("id"); // Mengambil ID pelanggan dari parameter
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Customer customer = entityManager.find(Customer.class, customerId); // Mencari pelanggan berdasarkan ID

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();

            if (customer != null) {
                // Menampilkan detail pelanggan jika ditemukan
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("    <title>Detail Pelanggan</title>");
                out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
                out.println("</head>");
                out.println("<body>");
                out.println("    <div class=\"container mt-5\">");
                out.println("        <h1>Detail Pelanggan</h1>");
                out.println("        <table class=\"table table-bordered\">");
                out.println("            <tr><th>ID</th><td>" + customer.getId() + "</td></tr>");
                out.println("            <tr><th>Nama</th><td>" + customer.getName() + "</td></tr>");
                out.println("        </table>");
                out.println("        <a href=\"/customers/index.html\" class=\"btn btn-secondary\">Kembali ke Daftar Pelanggan</a>");
                out.println("    </div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                // Menampilkan pesan jika pelanggan tidak ditemukan
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("    <title>Pelanggan Tidak Ditemukan</title>");
                out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
                out.println("</head>");
                out.println("<body>");
                out.println("    <div class=\"container mt-5\">");
                out.println("        <h1>Pelanggan Tidak Ditemukan</h1>");
                out.println("        <p>ID pelanggan \"" + customerId + "\" tidak ditemukan.</p>");
                out.println("        <a href=\"/customers/index.html\" class=\"btn btn-secondary\">Kembali ke Daftar Pelanggan</a>");
                out.println("    </div>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close(); // Menutup EntityManager
        }
    }

    @Override
    public void destroy() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close(); // Menutup EntityManagerFactory saat servlet dihancurkan
        }
    }
}