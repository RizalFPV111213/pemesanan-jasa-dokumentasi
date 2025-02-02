<?php

echo "Hello World";

$connect = mysqli_connect('localhost', 'root', '', 'digita.id');

    if (!$connect) {
        die("Connection failed: " . mysqli_connect_error());
    }else{
         echo "Connected successfully";
    }


//karena form menggunakan method post kita gunakan $_POST
$nama = $_GET['nama']; //index didalamnya sesuai dengan input name yang ada di form
$email = $_GET['email'];
$phone = $_GET['phone'];
$booking_date = $_GET['booking_date'];
$order_package = $_GET['order_package'];
$message = $_GET['message'];


echo "<pre>";
var_dump($nama);
var_dump($email);
var_dump($phone);
var_dump($booking_date);
var_dump($message);
var_dump($order_package);


$insert = mysqli_query($connect,"INSERT INTO orders SET nama='$nama', email='$email', phone='$phone', booking_date='$booking_date', message='$message', order_package='$order_package'"); 

var_dump($insert);

if($insert) 
    header('Location:index.php'); //Jika berhasil akan di arahkan ke halaman list.p`hp
else
    echo 'Input data gagal'; //jika gagal akan keluar pesan tersebut

?>