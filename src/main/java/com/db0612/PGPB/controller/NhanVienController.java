package com.db0612.PGPB.controller;

import com.db0612.PGPB.dto.NhanVienDTO;
import com.db0612.PGPB.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/nhanvien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/{maNV}")
    public ResponseEntity<NhanVienDTO> getNhanVienById(@PathVariable String maNV) {
        NhanVienDTO nhanVienDTO = nhanVienService.getNhanVienById(maNV);
        return nhanVienDTO != null ? ResponseEntity.ok(nhanVienDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<NhanVienDTO>> getAllNhanVien() {
        List<NhanVienDTO> nhanViens = nhanVienService.getAllNhanVien();
        return ResponseEntity.ok(nhanViens);
    }

    @PostMapping
    public ResponseEntity<NhanVienDTO> createNhanVien(@RequestBody NhanVienDTO nhanVienDTO) {
        NhanVienDTO createdNhanVien = nhanVienService.createNhanVien(nhanVienDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNhanVien);
    }

    @PutMapping("/{maNV}")
    public ResponseEntity<NhanVienDTO> updateNhanVien(@PathVariable String maNV, @RequestBody NhanVienDTO nhanVienDTO){
        NhanVienDTO updatedNhanVien = nhanVienService.updateNhanVien(maNV, nhanVienDTO);
        return updatedNhanVien != null ? ResponseEntity.ok(updatedNhanVien) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{maNV}")
    public ResponseEntity<Void> deleteNhanVien(@PathVariable String maNV){
        boolean isDeleted = nhanVienService.deleteNhanVien(maNV);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
