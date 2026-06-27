# pbo-km-narkotika-Fatih.Abil.Farrel

## Deskripsi Proyek
Proyek ini merupakan sebuah aplikasi yang dikembangkan dengan menggunakan Java (JDK 11+) dan bertujuan untuk merancang serta menerapkan Sistem Manajemen Pengetahuan (KMS) dalam konteks Putusan Pengadilan Narkotika. Aplikasi ini secara menyeluruh menerapkan prinsip Pemrograman Berorientasi Objek (OOP), yang mencakup aspek seperti enkapsulasi, polimorfisme, dan pewarisan. Seluruh struktur kode diatur dengan menggunakan arsitektur MVC (Model-View-Controller) agar tanggung jawab antara pengelolaan data (Model), antarmuka pengguna (View), dan pengendalian logika (Controller) terpisah dengan baik. Sistem ini memanfaatkan dataset dari putusan pengadilan untuk kasus pidana narkotika (Pid. Sus) untuk mengelola data seperti identitas terdakwa, informasi barang bukti, pasal yang dilanggar, hingga keputusan vonis.
### Fitur Utama:
* **Manajemen Data (CRUD):** Kemampuan untuk menambah, menampilkan, mencari, dan menghapus data putusan pengadilan.
* **Pencarian & Filter:** Menyaring data putusan berdasarkan jenis narkotika, nama pengadilan, atau rentang vonis.
* **Statistik Ringkas:** Menampilkan rekapitulasi jumlah putusan, rata-rata vonis dan denda, serta distribusi jenis narkotika.
* **Validasi Input:** Penanganan *error* (Exception Handling) secara menyeluruh agar program tetap berjalan stabil.

## Cara Kompilasi
Proyek ini terstruktur dalam berbagai *package* sesuai arsitektur MVC (`model`, `view`, `controller`, `util`, `app`). 
**Menggunakan IDE (IntelliJ IDEA / Eclipse / NetBeans):**
1. Lakukan *clone* repository ini ke komputer lokal.
2. Buka IDE (IntelliJ IDEA), lalu pilih opsi **Open** dan arahkan ke folder utama repository proyek ini.
3. Pastikan *Project SDK* disetel pada Java JDK 11 atau versi yang lebih baru.
4. IDE akan secara otomatis mengenali struktur folder `src` dan melakukan kompilasi latar belakang.
5. Jika melakukan kompilasi manual via terminal di dalam *root directory* proyek:
   ```bash
   javac -d bin src/app/Main.java src/model/*.java src/controller/*.java src/view/*.java src/util/*.java

## Cara Menjalankan
1. Cari file Main.java yang berada di dalam package app.
2. Klik kanan pada file tersebut dan pilih Run 'Main.main()' pada IDE.
3. Aplikasi akan meluncurkan menu utama, dan Anda dapat mulai berinteraksi dengan sistem sesuai instruksi yang muncul di layar.
4. Jika menjalankan dari terminal setelah proses kompilasi manual di atas:
   ```bash
   java -cp bin app.Main

## Video Demo Aplikasi
(link vidio menyusul)

## Daftar Anggota Kelompok
| Nama Lengkap | NIM | Peran / Layer | Branch Git |
| :--- | :--- | :--- | :--- |
| Fathul Qolby | 202510370110014 | Knowledge / Database Engineer (Model) | `feature/model` |
| M. Shabil Dwi Nur Basyah | 202510370110055 | Backend / Controller Engineer (Controller) | `feature/controller` |
| M. Farrel Hafizh Al Bintang | 202510370110012 | GUI Designer / View Developer (View) | `feature/view` |

## Desain Antarmuka GUI (JavaFX)
Berikut adalah *wireframe* atau *mockup* tata letak aplikasi JavaFX kami. Aplikasi dibagi menjadi dua sisi utama: sisi kiri untuk manajemen data (pencarian dan tabel), dan sisi kanan untuk input data serta visualisasi statistik.

```text
+-----------------------------------------------------------------------------+
|                                                                             |
|            SISTEM MANAJEMEN PUTUSAN NARKOTIKA (Header Title)                |
|                                                                             |
+-----------------------------------------------------------------------------+
|                                      |                                      |
|  Pencarian: [______________________] |        TAMBAH DATA MANUAL            |
|  [ Cari Nomor ] [ Filter ] [ Reset ] |                                      |
|                                      |  Nomor Perkara : [________________]  |
| +----------------------------------+ |  Nama Terdakwa : [________________]  |
| | Nomor   | Nama   | Jenis | Vonis | |  Jenis Narkoba : [________________]  |
| +----------------------------------+ |  Vonis (Bulan) : [________________]  |
| | 123/Pid | Budi   | Sabu  | 12    | |  Berat (Gram)  : [________________]  |
| | 456/Pid | Andi   | Ganja | 24    | |                                      |
| |         |        |       |       | |    [ SIMPAN DATA PUTUSAN ]           |
| |         |        |       |       | |                                      |
| |         |        |       |       | +--------------------------------------+
| |         |        |       |       | |                                      |
| |         |        |       |       | |        STATISTIK KASUS               |
| |         |        |       |       | |         ( PIE CHART )                |
| |         |        |       |       | |             ___                      |
| |         |        |       |       | |           /     \                    |
| |         |        |       |       | |          |       |                   |
| |         |        |       |       | |           \ ___ /                    |
| +----------------------------------+ |                                      |
|                                      |                                      |
+-----------------------------------------------------------------------------+
