<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateBookingsTable extends Migration
{
    public function up()
    {
        Schema::create('bookings', function (Blueprint $table) {
            $table->id(); // ID unik untuk setiap booking
            $table->string('nama'); // Nama lengkap
            $table->string('email'); // Alamat email
            $table->string('phone'); // Nomor telepon
            $table->string('Order'); // Paket yang dipilih
            $table->date('booking_date'); // Tanggal booking
            $table->text('message')->nullable(); // Pesan tambahan (opsional)
            $table->timestamps(); // Kolom untuk created_at dan updated_at
        });
    }

    public function down()
    {
        Schema::dropIfExists('bookings');
    }
}

