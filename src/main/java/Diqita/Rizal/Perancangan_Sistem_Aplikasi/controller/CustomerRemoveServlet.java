package Diqita.Rizal.Perancangan_Sistem_Aplikasi.controller;

import Diqita.Rizal.Perancangan_Sistem_Aplikasi.Util.JpaUtil;
import Diqita.Rizal.Perancangan_Sistem_Aplikasi.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customers/remove")
public class CustomerRemoveServlet extends HttpServlet {

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

            if (customer != null) {
                // Konfirmasi penghapusan
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().println("<!DOCTYPE html>");
                resp.getWriter().println("<html lang=\"en\">");
                resp.getWriter().println("<head>");
                resp.getWriter().println("    <meta charset=\"UTF-8\">");
                resp.getWriter().println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                resp.getWriter().println("    <title>Hapus Pelanggan</title>");
                resp.getWriter().println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
                resp.getWriter().println("</head>");
                resp.getWriter().println("<body>");
                resp.getWriter().println("    <div class=\"container mt-5\">");
                resp.getWriter().println("        <h1>Hapus Pelanggan</h1>");
                resp.getWriter().println("        <p>Apakah Anda yakin ingin menghapus pelanggan dengan ID: " + customerId + "?</p>");
                resp.getWriter().println("        <form action='remove' method='post'>");
                resp.getWriter().println("            <input type='hidden' name='id' value='" + customerId + "'/>");
                resp.getWriter().println("            <button type='submit' class='btn btn-danger'>Hapus</button>");
                resp.getWriter().println("            <a href='/customers/index.html' class='btn btn-secondary'>Kembali</a>");
                resp.getWriter().println("        </form>");
                resp.getWriter().println("    </div>");
                resp.getWriter().println("</body>");
                resp.getWriter().println("</html>");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found");
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

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Customer customer = entityManager.find(Customer.class, customerId); // Mencari pelanggan berdasarkan ID
            if (customer != null) {
                entityManager.remove(customer); // Menghapus pelanggan
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
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to remove customer");
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