<?php


    $connect = mysqli_connect('localhost', 'root', '', 'digita.id');

    if (!$connect) {
        die("Connection failed: " . mysqli_connect_error());
    }else{
         //echo "Connected successfully";
    }

?>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Booking</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .booking-form {
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .booking-form h2 {
            text-align: center;
        }
        .booking-form input, 
        .booking-form select, 
        .booking-form button {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .booking-form button {
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }
        .booking-form button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<form action="{{ route('booking.insert') }}" method="GET">

<div class="booking-form">
    <h2>Booking Now</h2>
    <input type="text" placeholder="Nama Lengkap" required name="nama">
    <input type="email" placeholder="Email" required name="email">
    <input type="tel" placeholder="No. Telepon" required name="phone">
    <select required name="order_package">
        <option value="order_package">Paket Yang Dipilih</option>
        <option value="A">Basic Package - Rp 1.500.000 (4 Jam Sesi Foto) </option>
        <option value="B">Standard Package - Rp 2.500.000 (6 Jam Sesi Foto)</option>
        <option value="C">Premium Package - Rp 4.000.000 (8 Jam Sesi Foto)</option>
        <!-- Tambahkan pilihan paket lainnya di sini -->
    </select>
    <input type="date" placeholder="Tanggal Booking" required name="booking_date">
    <textarea placeholder="Pesan" name="message"></textarea>
    <button type="submit">Kirim Booking</button>
    <button type="button">Tutup</button>
</div>

</form>
</body>
</html>