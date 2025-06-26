## Resilience4j
- Library ringan di Java untuk menangani fault-tolerance (toleransi terhadap kegagalan) pada microservices atau sistem terdistribusi
- Gunanya untuk
    - Menjaga sistem tetap stabil meskipun ada gagal panggil API
    - Menghindari sistem down total karena salah satu layanan error
- Retry
    - Jika suatu call gagal, sistem akan mencoba ulang (retry) beberapa kali sebelum benar-benar dianggap gagal.
    - contoh kasus: Memanggil service eksternal
- Fallback
    - Jika sudah retry beberapa kali dan tetap gagal, sistem akan menjalankan fallback (cadangan logika) supaya user tetap dapat response

### Implemantasi Retry danFallback di SpringBoot