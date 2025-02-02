<?php

$connect = mysqli_connect('localhost', 'root', '', 'digita.id');

if (!$connect) {
    die("Connection failed: " . mysqli_connect_error());
}else{
    // echo "Connected successfully";
}


?>


@extends('layouts.user')

@section('header')
    <style>
        #hero{
            background: url('{{asset('user/images/contact.png')}}') top center;
            background-repeat: no-repeat;
            width:100%;
            background-size:cover;
        }
    </style>    
@endsection

@section('hero')
    <h1>Photography Digita.id</h1>
    <h2></h2>
@endsection

@section('content')
<div class="service-photographer">
    <!-- Service Section -->
    <div class="service-section py-5">
        <div class="container">
            <h2 class="text-center mb-5">Layanan Photography Kami</h2>
            <div class="row">
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">Wedding Photography</h5>
                            <p class="card-text">Mengabadikan momen spesial pernikahan Anda dengan sentuhan profesional</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">Product Photography</h5>
                            <p class="card-text">Foto produk berkualitas tinggi untuk kebutuhan bisnis dan e-commerce</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">Event Photography</h5>
                            <p class="card-text">Dokumentasi acara dan event dengan hasil yang memuaskan</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Price Section -->
    <div class="price-section py-5 bg-light">
        <div class="container">
            <h2 class="text-center mb-5">Paket Harga</h2>
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="card">
                        <div class="card-body text-center">
                            <h4>Basic Package</h4>
                            <h3 class="text-success">Rp 1.500.000</h3>
                            <p>4 Jam Sesi Shoot<br>
                            1 Menit VIdeo Edit<br>
                            3x Revisi<br>
                            Soft Copy File</p>
                            <form action="{{ route('booking.view') }}" method="GET">
                                @csrf
                                <input type="hidden" name="package" value="Basic Package">
                                <button type="submit" class="btn btn-success">Pilih Paket</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="card">
                        <div class="card-body text-center">
                            <h4>Standard Package</h4>
                            <h3 class="text-success">Rp 2.500.000</h3>
                            <p>6 Jam Sesi Shoot<br>
                            1 Menit Teaser Video<br>
                            3 Menit Highlight Video<br>
                            3x Revisi<br>
                            Soft Copy File</p>
                            <button type="button" class="btn btn-success pilih-paket" data-package="Basic Package">Pilih Paket</button>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="card">
                        <div class="card-body text-center">
                            <h4>Premium Package</h4>
                            <h3 class="text-success">Rp 4.000.000</h3>
                            <p>8 Jam Sesi Shoot<br>
                            1 Menit Teaser Video<br>
                            3 Menit Highlight Video<br>
                            10 Menit Video Liputan<br>
                            3x Revisi<br>
                            Soft Copy File</p>
                            <button type="button" class="btn btn-success pilih-paket" data-package="Basic Package">Pilih Paket</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="bookingModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="bookingModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="bookingModalLabel">Booking Now</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Tutup</button>
                    <button type="button" class="btn btn-success" id="kirim-booking">Kirim Booking</button>
                    <div id="hasil-kirim" style="display: none;">
                        <p id="pesan-sukses" style="color: green;"></p>
                        <p id="pesan-error" style="color: red;"></p>
                    </div>
                </div>
              
            </div>
        </div>
    </div>
</div>




<?php



?>
<style>
.card-price {
    font-size: 1.5rem;
    font-weight: bold;
    color: #28a745;
}
.hero-section {
    min-height: 400px;
    display: flex;
    align-items: center;
}
.form-select {
    width: 100%;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #212529;
    background-color: #fff;
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}

.form-select:focus {
    border-color: #86b7fe;
    outline: 0;
    box-shadow: 0 0 0 0.25rem rgba(13,110,253,.25);
}
</style>
@endsection