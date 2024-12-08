package com.db0612.PGPB.service;

import com.db0612.PGPB.dto.KhachHangDTO;
import com.db0612.PGPB.model.KhachHang;
import com.db0612.PGPB.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    public KhachHangDTO getKhachHangById(String maKH) {
        Optional<KhachHang> khachHang = khachHangRepository.findById(maKH);
        return khachHang.map(this::convertToDTO).orElse(null);
    }

    public List<KhachHangDTO> getAllKhachHang() {
        List<KhachHang> khachHangs = khachHangRepository.findAll();
        return khachHangs.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public KhachHangDTO createKhachHang(KhachHangDTO khachHangDTO) {
        KhachHang khachHang = convertToEntity(khachHangDTO);
        KhachHang savedKhachHang = khachHangRepository.save(khachHang);
        return convertToDTO(savedKhachHang);
    }

    public KhachHangDTO updateKhachHang(String maKH, KhachHangDTO khachHangDTO) {
        Optional<KhachHang> existingKhachHang = khachHangRepository.findById(maKH);
        if (existingKhachHang.isPresent()) {
            KhachHang khachHang = existingKhachHang.get();
            khachHang.setHoTen(khachHangDTO.getHoTen());
            khachHang.setSoDienThoai(khachHangDTO.getSoDienThoai());
            khachHang.setEmail(khachHangDTO.getEmail());
            khachHang.setDiaChi(khachHangDTO.getDiaChi());
            khachHang.setNgayDangKy(khachHangDTO.getNgayDangKy());
            KhachHang updatedKhachHang = khachHangRepository.save(khachHang);
            return convertToDTO(updatedKhachHang);
        }
        return null;
    }

    public boolean deleteKhachHang(String maKH) {
        if (khachHangRepository.existsById(maKH)) {
            khachHangRepository.deleteById(maKH);
            return true;
        }
        return false;
    }

    private KhachHangDTO convertToDTO(KhachHang khachHang) {
        KhachHangDTO dto = new KhachHangDTO();
        dto.setMaKH(khachHang.getMaKH());
        dto.setHoTen(khachHang.getHoTen());
        dto.setSoDienThoai(khachHang.getSoDienThoai());
        dto.setEmail(khachHang.getEmail());
        dto.setDiaChi(khachHang.getDiaChi());
        dto.setNgayDangKy(khachHang.getNgayDangKy());
        return dto;
    }

    private KhachHang convertToEntity(KhachHangDTO dto) {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH(dto.getMaKH());
        khachHang.setHoTen(dto.getHoTen());
        khachHang.setSoDienThoai(dto.getSoDienThoai());
        khachHang.setEmail(dto.getEmail());
        khachHang.setDiaChi(dto.getDiaChi());
        khachHang.setNgayDangKy(dto.getNgayDangKy());
        return khachHang;
    }
}
