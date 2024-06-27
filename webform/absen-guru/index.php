<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulir Kehadiran Guru dan Pegawai</title>
    <style>
        body {
            padding: 20px;
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 600px;
            margin: auto;
        }
        .form-container {
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 20px;
        }
        .form-header {
            margin-bottom: 20px;
            text-align: center;
            font-size: 24px;
        }
        .form-footer {
            margin-top: 20px;
            text-align: center;
            font-size: 12px;
            color: #999;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-control {
            width: 100%;
            padding: 8px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .btn-submit {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-submit:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="form-container">
        <h2 class="form-header">Formulir Kehadiran Guru dan Pegawai</h2>

        <?php
        $message = '';
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $employee_name = $_POST['employee_name'];
            $attendance_date = date('Y-m-d');
            $attendance_status = 'Hadir';

            $servername = "localhost";
            $username = "root";
            $password = "";
            $dbname = "school_management_system_db";

            try {
                $conn = new mysqli($servername, $username, $password, $dbname);

                if ($conn->connect_error) {
                    throw new Exception("Connection failed: " . $conn->connect_error);
                }

                $stmt = $conn->prepare("INSERT INTO employee_attendances (employee_id, employee_attendance_date, employee_attendance_status) VALUES ((SELECT employee_id FROM employees WHERE employee_nip = ?), ?, ?)");
                $stmt->bind_param("sss", $employee_name, $attendance_date, $attendance_status);

                if ($stmt->execute()) {
                    $message = "success";
                } else {
                    $message = "error";
                }

                $stmt->close();
                $conn->close();
            } catch (mysqli_sql_exception $e) {
                $message = "error";
            } catch (Exception $e) {
                $message = "error";
            }
        }
        ?>

        <form action="" method="post">
            <div class="form-group">
                <label for="employee_name" class="form-label">NIP Guru atau Pegawai:</label>
                <input type="text" class="form-control" id="employee_name" name="employee_name" placeholder="Masukan NIP ..." required>
            </div>
            <button type="submit" class="btn-submit">Kirim</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const message = "<?php echo $message; ?>";
        if (message === "success") {
            Swal.fire({
                icon: 'success',
                title: 'Berhasil',
                text: 'Data berhasil disimpan!'
            });
        } else if (message === "error") {
            Swal.fire({
                icon: 'error',
                title: 'Gagal',
                text: 'Data gagal disimpan!'
            });
        }
    });
</script>
</body>
</html>
