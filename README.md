# backend-IJAL


1. mvn package
2. mvn spring-boot:run

![img.png](img.png)

git add .
git commit -m " message nya apa "
git log --oneline
git push

CRUD database
CustomerServlet = create
CustomerFindServlet = read
CustomerUpdateServlet = update
CustomerRemoveServlet = delete


============================test ===============

1. CREATE TABLE clients (
   id INT AUTO_INCREMENT PRIMARY KEY,       -- Kolom ID unik untuk setiap client
   namaPengguna VARCHAR(100) NOT NULL,      -- Nama pengguna
   phoneNumber VARCHAR(15) NOT NULL,        -- Nomor handphone
   email VARCHAR(100) NOT NULL,             -- Alamat email
   domisili VARCHAR(100) NOT NULL            -- Domisili
   );
2.