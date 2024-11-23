package Diqita.Rizal.Perancangan_Sistem_Aplikasi.Servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/checkConnection")
public class DatabaseConnectionServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        // Inisialisasi EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("PerancanganSistem");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            // Cek koneksi dengan melakukan query sederhana
            entityManager.getTransaction().begin();
            long count = entityManager.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
            entityManager.getTransaction().commit();

            // Jika koneksi berhasil
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Koneksi ke Database Berhasil!</h1>");
            resp.getWriter().println("<p>Jumlah Produk: " + count + "</p>");
        } catch (Exception e) {
            // Jika terjadi kesalahan
            resp.setContentType("text/html");
            resp.getWriter().println("<h1>Koneksi ke Database Gagal!</h1>");
            resp.getWriter().println("<p>Kesalahan: " + e.getMessage() + "</p>");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void destroy() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}