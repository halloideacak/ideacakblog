package com.ideacak.warteg;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// pintu masuk dari jaringan: menerima permintaan HTTP, meneruskannya ke KasirService
@RestController
@RequestMapping("/api")
public class KasirController {
    private final KasirService kasirService;

    public KasirController(KasirService kasirService) {
        this.kasirService = kasirService;
    }

    @GetMapping("/menu")
    public List<MenuResponse> daftarMenu() {
        return kasirService.getDaftarMenu();
    }

    @GetMapping("/menu/{nomor}")
    public MenuResponse satuMenu(@PathVariable int nomor) {
        return kasirService.getMenu(nomor);
    }

    @PostMapping("/pesanan")
    @ResponseStatus(HttpStatus.CREATED)
    public Struk pesan(@RequestBody PesananRequest request) throws PesananTidakValidException {
        return kasirService.layani(request);
    }
}
