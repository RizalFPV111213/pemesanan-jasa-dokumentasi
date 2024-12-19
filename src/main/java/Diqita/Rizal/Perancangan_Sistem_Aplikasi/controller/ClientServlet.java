package Diqita.Rizal.Perancangan_Sistem_Aplikasi.controller;

import Diqita.Rizal.Perancangan_Sistem_Aplikasi.Util.JpaUtil;
import Diqita.Rizal.Perancangan_Sistem_Aplikasi.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Photographer/submitCustomer")
public class ClientServlet extends HttpServlet {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = JpaUtil.getEntityManagerFactory();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String namaPengguna = req.getParameter("namaPengguna");
        String phoneNumber = req.getParameter("phoneNumber");
        String email = req.getParameter("email");
        String domisili = req.getParameter("domisili");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Client client = new Client();
            client.setNamaPengguna(namaPengguna);
            client.setPhoneNumber(phoneNumber);
            client.setEmail(email);
            client.setDomisili(domisili);

            entityManager.persist(client);
            entityManager.getTransaction().commit();

            resp.sendRedirect("index.html"); // Ganti dengan halaman sukses Anda
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new ServletException("Error saving client", e);
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