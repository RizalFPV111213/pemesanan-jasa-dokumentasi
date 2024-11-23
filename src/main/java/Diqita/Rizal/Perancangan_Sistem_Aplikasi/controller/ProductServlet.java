package Diqita.Rizal.Perancangan_Sistem_Aplikasi.controller;

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

@WebServlet("/products/produks")
public class ProductServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        this.entityManagerFactory = JpaUtil.getEntityManagerFactory(); // Anda perlu mengimplementasikan JpaUtil
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);

            entityManager.persist(product); // Simpan produk ke database

            transaction.commit();
            resp.sendRedirect("produks"); // Redirect setelah sukses
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback jika terjadi kesalahan
            }
            e.printStackTrace(); // Log kesalahan
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add product");
        } finally {
            entityManager.close(); // Tutup EntityManager
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Mengatur tipe konten respons
        resp.setContentType("text/html;charset=UTF-8");

        // Mengambil PrintWriter untuk menulis respons
        PrintWriter out = resp.getWriter();
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
        out.println("    <title>Produk Ditambahkan</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class=\"container mt-5\">");
        out.println("        <div class=\"alert alert-success\" role=\"alert\">");
        out.println("            <h1 class=\"display-4\">Sukses</h1>");
        out.println("            <p>Produk telah berhasil ditambahkan!</p>");
        out.println("            <a href=\"/products/index.html\" class=\"btn btn-primary\">Kembali ke Daftar Produk</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("    <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\"></script>");
        out.println("    <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js\"></script>");
        out.println("    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void destroy() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close(); // Tutup EntityManagerFactory saat servlet dihapus
        }
    }
}