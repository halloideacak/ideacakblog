package com.ideacak.warteg;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// mengubah exception kasir jadi jawaban HTTP yang jelas, di satu tempat untuk semua controller
@RestControllerAdvice
public class PenanganError {

    @ExceptionHandler(MenuTidakDitemukanException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public PesanError menuTidakDitemukan(MenuTidakDitemukanException e) {
        return new PesanError(e.getMessage());
    }

    // StokTidakCukupException juga turunan PesananTidakValidException, tapi Spring memilih
    // handler dengan tipe yang paling dekat, jadi stok habis dijawab 409, bukan 400
    @ExceptionHandler(StokTidakCukupException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public PesanError stokTidakCukup(StokTidakCukupException e) {
        return new PesanError(e.getMessage());
    }

    @ExceptionHandler(PesananTidakValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PesanError pesananTidakValid(PesananTidakValidException e) {
        return new PesanError(e.getMessage());
    }
}
