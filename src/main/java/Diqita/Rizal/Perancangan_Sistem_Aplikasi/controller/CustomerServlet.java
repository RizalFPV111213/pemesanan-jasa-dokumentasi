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
import java.util.List;

@WebServlet("/customers/insert")
public class CustomerServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = JpaUtil.getEntityManagerFactory(); // Menginisialisasi EntityManagerFactory
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Customer customer = new Customer();
            customer.setId(req.getParameter("id")); // Mengambil ID dari parameter
            customer.setName(req.getParameter("name")); // Mengambil nama dari parameter

            entityManager.persist(customer); // Menyimpan customer ke database

            transaction.commit();
            resp.sendRedirect("insert"); // Redirect ke daftar pelanggan setelah sukses
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback jika terjadi kesalahan
            }
            e.printStackTrace(); // Log kesalahan
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add customer");
        } finally {
            entityManager.close(); // Menutup EntityManager
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("    <title>Daftar Pelanggan</title>");
            out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class=\"container mt-5\">");
            out.println("        <h1 class=\"mb-4\">Daftar Pelanggan</h1>");
            out.println("        <table class=\"table table-bordered\">");
            out.println("            <thead class=\"thead-light\">");
            out.println("                <tr>");
            out.println("                    <th>ID</th>");
            out.println("                    <th>Nama</th>");
            out.println("                </tr>");
            out.println("            </thead>");
            out.println("            <tbody>");

            for (Customer customer : customers) {
                out.println("                <tr>");
                out.println("                    <td>" + customer.getId() + "</td>");
                out.println("                    <td>" + customer.getName() + "</td>");
                out.println("                </tr>");
            }

            out.println("            </tbody>");
            out.println("        </table>");
            out.println("        <h2>Tambah Pelanggan</h2>");
            out.println("        <form action='insert' method='post'>"); // Pastikan URL ini sesuai
            out.println("            <div class=\"form-group\">");
            out.println("                <label for=\"id\">ID:</label>");
            out.println("                <input type=\"text\" class=\"form-control\" id=\"id\" name=\"id\" required>");
            out.println("            </div>");
            out.println("            <div class=\"form-group\">");
            out.println("                <label for=\"name\">Nama:</label>");
            out.println("                <input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" required>");
            out.println("            </div>");
            out.println("            <button type=\"submit\" class=\"btn btn-primary\">Tambah</button>");
            out.println("        </form>");
            out.println("    </div>");
            out.println("    <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>");
            out.println("    <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\"></script>");
            out.println("    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace(); // Log kesalahan saat mengambil daftar pelanggan
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching customers");
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