-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2026 at 09:33 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `logistik_center`
--

-- --------------------------------------------------------

--
-- Table structure for table `aset`
--

CREATE TABLE `aset` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `tipe` enum('ruangan','barang') DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `lokasi` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `aset`
--

INSERT INTO `aset` (`id`, `nama`, `tipe`, `stok`, `lokasi`) VALUES
(1, 'Ruang R101', 'ruangan', 1, 'Gedung A'),
(2, 'Ruang R102', 'ruangan', 1, 'Gedung A'),
(3, 'Ruang Lab Komputer 1', 'ruangan', 1, 'SBS'),
(4, 'Ruang Meeting 1', 'ruangan', 1, 'Gedung A'),
(5, 'Kamera DSLR Canon', 'barang', 19, 'Ruang Logistik'),
(6, 'Proyektor Epson', 'barang', 9, 'Ruang Logistik'),
(7, 'Tripod Kamera', 'barang', 10, 'Ruang Logistik'),
(8, 'Speaker Portable', 'barang', 4, 'Ruang Logistik'),
(9, 'Laptop Peminjaman', 'barang', 8, 'Ruang Logistik'),
(10, 'Mikrofon Wireless', 'barang', 6, 'Ruang Logistik'),
(11, 'Kabel HDMI 5m', 'barang', 37, 'Ruang Logistik'),
(12, 'Kursi Lipat', 'barang', 30, 'Ruang Logistik'),
(13, 'Speaker FOH Baritone 15RC', 'barang', 2, 'Gudang Audio'),
(14, 'Speaker FOH Delay DBR10', 'barang', 6, 'Gudang Audio'),
(15, 'Subwoofer 18 inch Ashley', 'barang', 2, 'Gudang Audio'),
(16, 'Mixer Yamaha MGP-24', 'barang', 1, 'Gudang Audio'),
(17, 'Microphone Ashley Pro-1', 'barang', 4, 'Gudang Audio'),
(18, 'Mimbar', 'barang', 1, 'Gudang Aula'),
(19, 'Videotron 5 x 2.5 meter', 'barang', 1, 'Aula'),
(20, 'Fresnel', 'barang', 5, 'Gudang Lighting'),
(21, 'Kursi Sofa 3 Orang', 'barang', 3, 'Gudang Aula'),
(22, 'Kursi Sofa 2 Orang', 'barang', 1, 'Gudang Aula'),
(23, 'Kursi Sofa 1 Orang', 'barang', 1, 'Gudang Aula'),
(24, 'Meja Event', 'barang', 3, 'Gudang Aula'),
(25, 'Kursi Operator', 'barang', 5, 'Gudang Aula'),
(26, 'TV LG 75 Inch', 'barang', 1, 'Aula'),
(27, 'Kursi Besar Tiger', 'barang', 50, 'Gudang Aula'),
(28, 'Ruang Kelas', 'ruangan', 10, 'Gedung A'),
(29, 'TV Kelas', 'barang', 10, 'Gedung Akademik'),
(30, 'Kabel HDMI', 'barang', 20, 'Gudang'),
(31, 'Kursi Kelas', 'barang', 200, 'Gudang'),
(32, 'Kabel Roll 5 Meter', 'barang', 3, 'Gudang Listrik'),
(33, 'Speaker Portable', 'barang', 1, 'Gudang Audio'),
(34, 'Microphone Wireless', 'barang', 2, 'Gudang Audio'),
(35, 'KTT1.02', 'ruangan', 1, 'Gedung A'),
(36, 'KTT1.03', 'ruangan', 1, 'Gedung A'),
(37, 'KTT1.04', 'ruangan', 1, 'Gedung A'),
(38, 'KTT1.05', 'ruangan', 1, 'Gedung A'),
(39, 'KTT1.06', 'ruangan', 1, 'Gedung A'),
(40, 'KTT1.07', 'ruangan', 1, 'Gedung A'),
(41, 'KTT1.08', 'ruangan', 1, 'Gedung A'),
(42, 'KTT1.09', 'ruangan', 1, 'Gedung A'),
(43, 'KTT1.16', 'ruangan', 1, 'Gedung A'),
(44, 'KTT1.17', 'ruangan', 1, 'Gedung A'),
(45, 'KTT1.18', 'ruangan', 1, 'Gedung A'),
(46, 'KTT1.19', 'ruangan', 1, 'Gedung A'),
(47, 'KTT1.20', 'ruangan', 1, 'Gedung A'),
(48, 'KTT1.21', 'ruangan', 1, 'Gedung A'),
(49, 'KTT1.22', 'ruangan', 1, 'Gedung A'),
(50, 'KTT 1.27 B', 'ruangan', 1, 'Gedung A'),
(51, 'KTT A-1.03', 'ruangan', 1, 'Gedung A'),
(52, 'KTT A-1.04', 'ruangan', 1, 'Gedung A'),
(53, 'KTT A-1.06', 'ruangan', 1, 'Gedung A'),
(54, 'KTT A-1.07', 'ruangan', 1, 'Gedung A'),
(55, 'KTT B-1.05', 'ruangan', 1, 'Gedung A'),
(56, 'KTT B-1.06', 'ruangan', 1, 'Gedung A'),
(57, 'KTT B-1.07', 'ruangan', 1, 'Gedung A'),
(58, 'KTT B-1.08', 'ruangan', 1, 'Gedung A'),
(59, 'KTT2.02', 'ruangan', 1, 'Gedung A'),
(60, 'KTT2.03', 'ruangan', 1, 'Gedung A'),
(61, 'KTT2.04', 'ruangan', 1, 'Gedung A'),
(62, 'KTT2.05', 'ruangan', 1, 'Gedung A'),
(63, 'KTT2.06', 'ruangan', 1, 'Gedung A'),
(64, 'KTT2.07', 'ruangan', 1, 'Gedung A'),
(65, 'KTT2.08', 'ruangan', 1, 'Gedung A'),
(66, 'KTT2.09', 'ruangan', 1, 'Gedung A'),
(67, 'KTT2.15', 'ruangan', 1, 'Gedung A'),
(68, 'KTT2.16', 'ruangan', 1, 'Gedung A'),
(69, 'KTT2.17', 'ruangan', 1, 'Gedung A'),
(70, 'KTT2.18', 'ruangan', 1, 'Gedung A'),
(71, 'KTT2.19', 'ruangan', 1, 'Gedung A'),
(72, 'KTT2.20', 'ruangan', 1, 'Gedung A'),
(73, 'KTT2.23', 'ruangan', 1, 'Gedung A'),
(74, 'KTT2.24', 'ruangan', 1, 'Gedung A'),
(75, 'KTT2.25', 'ruangan', 1, 'Gedung A'),
(76, 'KTT2.26', 'ruangan', 1, 'Gedung A'),
(77, 'KTT2.27', 'ruangan', 1, 'Gedung A'),
(78, 'KTT2.28', 'ruangan', 1, 'Gedung A'),
(79, 'KTT2.35', 'ruangan', 1, 'Gedung A'),
(80, 'KTT2.36', 'ruangan', 1, 'Gedung A'),
(81, 'SE LAB', 'ruangan', 1, 'SBS'),
(82, 'APDEV LAB.', 'ruangan', 1, 'SBS'),
(83, 'CPROG LAB.', 'ruangan', 1, 'SBS'),
(84, 'SWENG LAB.', 'ruangan', 1, 'SBS'),
(85, 'BEST LAB.', 'ruangan', 1, 'SBS'),
(86, 'DIGITAL LAB.', 'ruangan', 1, 'SBS'),
(87, 'DATAI LAB.', 'ruangan', 1, 'SBS'),
(88, 'LAB.FTIB.PROG', 'ruangan', 1, 'SBS'),
(89, 'LAB.FTIB.MODEL', 'ruangan', 1, 'SBS'),
(90, 'LAB.JARKOM', 'ruangan', 1, 'SBS'),
(91, 'CYCLD LAB.', 'ruangan', 1, 'SBS'),
(92, 'QBI LAB.', 'ruangan', 1, 'SBS'),
(93, 'CAM LAB.', 'ruangan', 1, 'SBS');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  `aset_id` int(11) DEFAULT NULL,
  `tgl_mulai` date DEFAULT NULL,
  `tgl_selesai` date DEFAULT NULL,
  `status` varchar(30) NOT NULL,
  `jumlah` int(11) DEFAULT 1,
  `nama_unit` varchar(100) DEFAULT NULL,
  `nama_kegiatan` varchar(150) DEFAULT NULL,
  `no_hp` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id`, `user_id`, `unit_id`, `aset_id`, `tgl_mulai`, `tgl_selesai`, `status`, `jumlah`, `nama_unit`, `nama_kegiatan`, `no_hp`) VALUES
(22, 2, 18, 11, '2026-01-10', '2026-01-10', 'ditolak', 1, NULL, 'Seminar', '6555453546546'),
(23, 2, 6, 20, '2026-01-10', '2026-01-10', 'selesai', 1, NULL, 'Seminar', '6555453546546'),
(24, 2, 17, 20, '2026-01-09', '2026-01-09', 'selesai', 1, NULL, 'Seminar', '6555453546546'),
(25, 2, 8, 20, '2026-01-09', '2026-01-09', 'selesai', 1, NULL, 'Lomba', '6555453546546'),
(26, 2, 4, 27, '2026-01-09', '2026-01-09', 'ditolak', 1, NULL, 'Workshop', '6555453546546'),
(27, 2, 18, 82, '2026-01-12', '2026-01-12', 'selesai', 1, NULL, 'Seminar', '6555453546546');

-- --------------------------------------------------------

--
-- Table structure for table `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id` int(11) NOT NULL,
  `peminjaman_id` int(11) DEFAULT NULL,
  `tgl_kembali` date DEFAULT NULL,
  `kondisi` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `unit_peminjam`
--

CREATE TABLE `unit_peminjam` (
  `id` int(11) NOT NULL,
  `nama_unit` varchar(150) NOT NULL,
  `jenis` enum('UKM','HIMA','FAKULTAS','UNIT') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `unit_peminjam`
--

INSERT INTO `unit_peminjam` (`id`, `nama_unit`, `jenis`) VALUES
(1, 'UKM Ikatan Mahasiswa Sulawesi (IMS)', 'UKM'),
(2, 'UKM Ikatan Mahasiswa Nusa Tenggara Timur (IM NTT)', 'UKM'),
(3, 'UKM Keluarga Mahasiswa Sumatera (KMS)', 'UKM'),
(4, 'UKM Paduan Suara Vox Auream', 'UKM'),
(5, 'UKM Telkom Art', 'UKM'),
(6, 'UKM Badminton', 'UKM'),
(7, 'UKM E-Sport', 'UKM'),
(8, 'UKM Creativity on Digital Environment in Room of Technology (CODER)', 'UKM'),
(9, 'UKM Robotika', 'UKM'),
(10, 'UKM Punggawa Inspiratif', 'UKM'),
(11, 'UKM UKKI', 'UKM'),
(12, 'UKM UKKK', 'UKM'),
(13, 'UKM KMK St. Feligon', 'UKM'),
(14, 'UKM Mahapala Telkom University Surabaya (Mahitkom)', 'UKM'),
(15, 'UKM Entrepreneur Community (ECTS)', 'UKM'),
(16, 'UKM Nippon Bunka-Bu', 'UKM'),
(17, 'Dewan Perwakilan Mahasiswa (DPM)', 'UKM'),
(18, 'Badan Eksekutif Mahasiswa (BEM)', 'UKM'),
(19, 'Himpunan Mahasiswa Teknik Elektro', 'HIMA'),
(20, 'Himpunan Mahasiswa Teknik Telekomunikasi', 'HIMA'),
(21, 'Himpunan Mahasiswa Teknik Komputer', 'HIMA'),
(22, 'Himpunan Mahasiswa Teknik Industri', 'HIMA'),
(23, 'Himpunan Mahasiswa Sistem Informasi', 'HIMA'),
(24, 'Himpunan Mahasiswa Teknik Logistik', 'HIMA'),
(25, 'Himpunan Mahasiswa Informatika', 'HIMA'),
(26, 'Himpunan Mahasiswa Rekayasa Perangkat Lunak', 'HIMA'),
(27, 'Himpunan Mahasiswa Teknologi Informasi', 'HIMA'),
(28, 'Himpunan Mahasiswa Sains Data', 'HIMA'),
(29, 'Himpunan Mahasiswa Bisnis Digital', 'HIMA'),
(30, 'Teknik Elektro', 'FAKULTAS'),
(31, 'Teknik Telekomunikasi', 'FAKULTAS'),
(32, 'Teknik Komputer', 'FAKULTAS'),
(33, 'Teknik Industri', 'FAKULTAS'),
(34, 'Sistem Informasi', 'FAKULTAS'),
(35, 'Teknik Logistik', 'FAKULTAS'),
(36, 'Informatika', 'FAKULTAS'),
(37, 'Rekayasa Perangkat Lunak', 'FAKULTAS'),
(38, 'Teknologi Informasi', 'FAKULTAS'),
(39, 'Sains Data', 'FAKULTAS'),
(40, 'Bisnis Digital', 'FAKULTAS'),
(41, 'LPPM', 'UNIT'),
(42, 'Akademik', 'UNIT'),
(43, 'Kemahasiswaan', 'UNIT'),
(44, 'SDM', 'UNIT'),
(45, 'Keuangan', 'UNIT'),
(46, 'COE', 'UNIT'),
(47, 'SEKPIM', 'UNIT'),
(48, 'UKM Pojok Statistik (POSTIK)', 'UKM');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('mahasiswa','dosen','staff','admin') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `role`) VALUES
(1, 'Admin Sistem', 'admin@admin.com', 'admin123', 'admin'),
(2, 'M Fery', 'mferyardiansyah@student.telkomuniveristy.ac.id', '123456', 'mahasiswa'),
(3, 'Aura Aulia', 'auraaulia@student.telkomuniversity.ac.id', '123412', 'mahasiswa'),
(4, 'Mahasiswa 1', 'mahasiswa1@student.telkomuniversity.ac.id', '123456', 'mahasiswa'),
(5, 'Mahasiswa 2', 'mahasiswa2@student.telkomuniversity.ac.id', '123456', 'mahasiswa'),
(6, 'Mahasiswa 3', 'mahasiswa3@student.telkomuniversity.ac.id', '123456', 'mahasiswa'),
(7, 'Dosen 1', 'dosen1@telkomuniversity.ac.id', '123456', 'dosen'),
(8, 'Dosen 2', 'dosen2@telkomuniversity.ac.id', '123456', 'dosen'),
(9, 'Dosen 3', 'dosen3@telkomuniversity.ac.id', '123456', 'dosen');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aset`
--
ALTER TABLE `aset`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `aset_id` (`aset_id`),
  ADD KEY `fk_peminjaman_unit` (`unit_id`);

--
-- Indexes for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id`),
  ADD KEY `peminjaman_id` (`peminjaman_id`);

--
-- Indexes for table `unit_peminjam`
--
ALTER TABLE `unit_peminjam`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aset`
--
ALTER TABLE `aset`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `pengembalian`
--
ALTER TABLE `pengembalian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `unit_peminjam`
--
ALTER TABLE `unit_peminjam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `fk_peminjaman_unit` FOREIGN KEY (`unit_id`) REFERENCES `unit_peminjam` (`id`),
  ADD CONSTRAINT `peminjaman_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `peminjaman_ibfk_2` FOREIGN KEY (`aset_id`) REFERENCES `aset` (`id`);

--
-- Constraints for table `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD CONSTRAINT `pengembalian_ibfk_1` FOREIGN KEY (`peminjaman_id`) REFERENCES `peminjaman` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
