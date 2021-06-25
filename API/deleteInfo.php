<?php
    require_once 'connection.php';
    $response = array();

    if($_SERVER['REQUEST_METHOD'] == 'POST'){

        $id_info = $_POST["id_info"];

        $perintah = "DELETE FROM info WHERE id_info = '$id_info'";
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