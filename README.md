# Tes REST API

Proyek ini adalah contoh sederhana otomatisasi pengujian REST API menggunakan Java, Maven, TestNG, dan Rest-Assured.

## Dependensi

*   [Rest Assured](https://rest-assured.io/): DSL Java untuk menyederhanakan pengujian layanan berbasis REST.
*   [TestNG](https://testng.org/): Kerangka kerja pengujian untuk Java.
*   [Jackson Databind](https://github.com/FasterXML/jackson-databind): Pustaka Java untuk pemrosesan JSON.
*   [SLF4J](https://www.slf4j.org/): Fasad logging sederhana untuk Java.

## Cara Menjalankan Tes

Untuk menjalankan tes, Anda dapat menggunakan perintah Maven berikut:

```bash
mvn clean test
```

Perintah ini akan mengeksekusi tes yang ditentukan di direktori `src/test/java`. Hasil tes akan tersedia di direktori `target/surefire-reports`.

## Tes yang Diimplementasikan

Tes-tes berikut telah diimplementasikan dalam proyek ini:

### Tes API Login

*   `loginTest`: Tes ini memverifikasi bahwa pengguna dapat berhasil masuk ke aplikasi dan menerima token otentikasi.

### Tes API Produk

*   `findAllTest`: Tes ini memverifikasi bahwa semua produk dapat diambil dari API.
*   `createTest`: Tes ini memverifikasi bahwa produk baru dapat dibuat.
*   `productDetailTest`: Tes ini memverifikasi bahwa detail produk tertentu dapat diambil.
*   `updateProductTest`: Tes ini memverifikasi bahwa produk dapat diperbarui.
*   `deleteProductTest`: Tes ini memverifikasi bahwa produk dapat dihapus.
