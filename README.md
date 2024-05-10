# sbteslawencon

swagger:
[swagger](http://localhost:8080/swagger-ui.html)

flow
1. user harus memasukkan loker di api [insertLoker](http://localhost:8080/sbteslawencon/api/v1/loker/insert) 
2. user memasukkan data pribadi, data deposit dan nomorLoker yang tersedia di api [insertBookingLoker](http://localhost:8080/sbteslawencon/api/v1/bookingLoker/insert)
  - merubah status loker menjadi not booked
  - merubah status_booking bookingLoker menjadi not booked
3. jika ada password yang tertera setelah hit api insertBookingLoker, masukkan nomorLoker dan password pada api [useLoker](http://localhost:8080/sbteslawencon/api/v1/loker/useLoker)
  - merubah status loker menjadi booked
  - merubah status_booking booking menjadi saving
  - disini terdapat pengecekan pelepasan loker jika salah password 3 kali
4. jika ingin melakukan proses pengembalian loker pada api ini [returning](http://localhost:8080/sbteslawencon/api/v1/bookingLoker/return)
  - merubah status loker menjadi not booked
  - merubah status_booking menjadi not booked    
  - disini terdapat pengecekan keterlambatan pengembalian loker dan denda loker
