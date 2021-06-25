<?php
    require_once 'connection.php';
    $response = array();

    if($_SERVER['REQUEST_METHOD'] == 'POST'){

        $mk = $_POST["mk"];
        $judul = $_POST["judul"];
        $deskripsi = $_POST["deskripsi"];
        $masuk = $_POST["masuk"];
        $deadline = $_POST["deadline"];

        $perintah = "INSERT INTO tugas (mk, judul, deskripsi, masuk, deadline) VALUES('$mk','$judul','$deskripsi','$masuk', '$deadline')";
        $eksekusi = mysqli_query($con, $perintah);
        $cek      = mysqli_affected_rows($con);

        if($cek > 0){
            $response["kode"] = 1;
            $response["pesan"] = "Simpan Data Berhasil";
        }
        else{
            $response["kode"] = 0;
            $response["pesan"] = "Gagal Menyimpan Data";
        }
    }
    else{
        $response["kode"] = 0;
        $response["pesan"] = "Tidak Ada Post Data";
    }

    echo json_encode($response);
    mysqli_close($con);
?>