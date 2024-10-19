package Diqita.Rizal.Perancangan_Sistem_Aplikasi.Servlet;

import Diqita.Rizal.Perancangan_Sistem_Aplikasi.Util.JpaUtil;
import Diqita.Rizal.Perancangan_Sistem_Aplikasi.model.Product;
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


@WebServlet(urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {

        // Inisialisasi EntityManagerFactory saat servlet diinisialisasi
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Mengatur respons agar menggunakan format HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Mengambil parameter dari permintaan
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        double price = Double.parseDouble(priceStr);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Membuat objek Product
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);

            // Menyimpan objek Product ke database
            entityManager.persist(product);
            transaction.commit();

            out.println("<h2>Product created successfully!</h2>");
            out.println("<p>Product Name: " + product.getName() + "</p>");
            out.println("<p>Product Price: $" + product.getPrice() + "</p>");
            out.println("<a href='index.html'>Back to Home</a>");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            out.println("<h2>Error occurred while creating product: " + e.getMessage() + "</h2>");
            out.println("<a href='product_create.html'>Try Again</a>");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void destroy() {
        // Menutup EntityManagerFactory saat servlet dihancurkan
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}

