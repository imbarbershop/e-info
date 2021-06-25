<?php
    require_once 'connection.php';
    $response = array();

    if($_SERVER['REQUEST_METHOD'] == 'POST'){

        $id_tugas = $_POST["id_tugas"];

        $perintah = "DELETE FROM tugas WHERE id_tugas = '$id_tugas'";
        $eksekusi = mysqli_query($con, $perintah);
        $cek      = mysqli_affected_rows($con);

        if($cek > 0){
            $response["kode"] = 1;
            $response["pesan"] = "Data Berhasil Dihapus";
        }
        else{
            $response["kode"] = 0;
            $response["pesan"] = "Gagal Menghapus Data";
        }
    }
    else{
        $response["kode"] = 0;
        $response["pesan"] = "Tidak Ada Post Data";
    }

    echo json_encode($response);
    mysqli_close($con);
?>