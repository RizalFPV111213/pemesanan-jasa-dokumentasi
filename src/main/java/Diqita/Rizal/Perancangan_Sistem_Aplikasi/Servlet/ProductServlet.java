package Diqita.Rizal.Perancangan_Sistem_Aplikasi.Servlet;

import Diqita.Rizal.Perancangan_Sistem_Aplikasi.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/produks")
public class ProductServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("PerancanganSistem"); // Ganti dengan unit
                                                                                            // persisten Anda
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Menampilkan daftar produk
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<Product> products = entityManager.createQuery("FROM Product", Product.class).getResultList();

            resp.setContentType("text/html");
            resp.getWriter().println("<!DOCTYPE html>");
            resp.getWriter().println("<html lang='en'>");
            resp.getWriter().println("<head>");
            resp.getWriter().println("<meta charset='UTF-8'>");
            resp.getWriter().println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            resp.getWriter().println("<title>Daftar Produk</title>");
            resp.getWriter().println("</head>");
            resp.getWriter().println("<body>");
            resp.getWriter().println("<h1>Daftar Produk</h1>");
            resp.getWriter().println("<ul>");
            for (Product product : products) {
                resp.getWriter().println("<li>" + product.getName() + " - $" + product.getPrice() + "</li>");
            }
            resp.getWriter().println("</ul>");
            resp.getWriter().println("</body>");
            resp.getWriter().println("</html>");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void destroy() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}