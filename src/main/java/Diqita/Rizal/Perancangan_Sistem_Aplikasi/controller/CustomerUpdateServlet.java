package Diqita.Rizal.Perancangan_Sistem_Aplikasi.controller;

import Diqita.Rizal.Perancangan_Sistem_Aplikasi.Util.JpaUtil;
import Diqita.Rizal.Perancangan_Sistem_Aplikasi.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/customers/update")
public class CustomerUpdateServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = JpaUtil.getEntityManagerFactory(); // Menginisialisasi EntityManagerFactory
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
                // Menampilkan form untuk memperbarui nama pelanggan
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("    <title>Update Pelanggan</title>");
                out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
                out.println("</head>");
                out.println("<body>");
                out.println("    <div class=\"container mt-5\">");
                out.println("        <h1>Update Pelanggan</h1>");
                out.println("        <form action='update' method='post'>"); // Form untuk mengupdate nama
                out.println("            <input type='hidden' name='id' value='" + customer.getId() + "'/>"); // Menyimpan ID
                out.println("            <div class=\"form-group\">");
                out.println("                <label for=\"name\">Nama:</label>");
                out.println("                <input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" value='" + customer.getName() + "' required>");
                out.println("            </div>");
                out.println("            <button type=\"submit\" class=\"btn btn-primary\">Update</button>");
                out.println("        </form>");
                out.println("        <a href=\"/customers/index.html\" class=\"btn btn-secondary\">Kembali</a>");
                out.println("    </div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<h2>Pelanggan tidak ditemukan</h2>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close(); // Menutup EntityManager
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("id"); // Mengambil ID dari parameter
        String newName = req.getParameter("name"); // Mengambil nama baru dari parameter

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Customer customer = entityManager.find(Customer.class, customerId); // Mencari pelanggan berdasarkan ID
            if (customer != null) {
                customer.setName(newName); // Memperbarui nama
                entityManager.merge(customer); // Menggabungkan perubahan
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found");
                return;
            }

            transaction.commit();
            resp.sendRedirect("index.html"); // Redirect ke daftar pelanggan setelah sukses
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback jika terjadi kesalahan
            }
            e.printStackTrace(); // Log kesalahan
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update customer");
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