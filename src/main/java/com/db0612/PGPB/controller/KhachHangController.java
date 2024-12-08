package com.db0612.PGPB.controller;

import com.db0612.PGPB.dto.KhachHangDTO;
import com.db0612.PGPB.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/{maKH}")
    public ResponseEntity<KhachHangDTO> getKhachHangById(@PathVariable String maKH) {
        KhachHangDTO khachHangDTO = khachHangService.getKhachHangById(maKH);
        return khachHangDTO != null ? ResponseEntity.ok(khachHangDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<KhachHangDTO>> getAllKhachHang() {
        List<KhachHangDTO> khachHangs = khachHangService.getAllKhachHang();
        return ResponseEntity.ok(khachHangs);
    }

    @PostMapping
    public ResponseEntity<KhachHangDTO> createKhachHang(@RequestBody KhachHangDTO khachHangDTO) {
        KhachHangDTO createdKhachHang = khachHangService.createKhachHang(khachHangDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdKhachHang);
    }

    @PutMapping("/{maKH}")
    public ResponseEntity<KhachHangDTO> updateKhachHang(@PathVariable String maKH, @RequestBody KhachHangDTO khachHangDTO) {
        KhachHangDTO updatedKhachHang = khachHangService.updateKhachHang(maKH, khachHangDTO);
        return updatedKhachHang != null ? ResponseEntity.ok(updatedKhachHang) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{maKH}")
    public ResponseEntity<Void> deleteKhachHang(@PathVariable String maKH) {
        boolean isDeleted = khachHangService.deleteKhachHang(maKH);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
