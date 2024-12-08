package com.db0612.PGPB.service;

import com.db0612.PGPB.dto.NhanVienDTO;
import com.db0612.PGPB.model.NhanVien;
import com.db0612.PGPB.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public NhanVienDTO getNhanVienById(String maNV) {
        Optional<NhanVien> nhanVien = nhanVienRepository.findById(maNV);
        return nhanVien.map(this::convertToDTO).orElse(null);
    }

    public List<NhanVienDTO> getAllNhanVien(){
        List<NhanVien> nhanViens = nhanVienRepository.findAll();
        return nhanViens.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NhanVienDTO createNhanVien(NhanVienDTO nhanVienDTO){
        NhanVien nhanVien = convertToEntity(nhanVienDTO);
        NhanVien savedNhanVien = nhanVienRepository.save(nhanVien);
        return convertToDTO(savedNhanVien);
    }

    public NhanVienDTO updateNhanVien(String maNV, NhanVienDTO nhanVienDTO){
        Optional<NhanVien> existingNhanVien = nhanVienRepository.findById(maNV);
        if(existingNhanVien.isPresent()) {
            NhanVien nhanVien = existingNhanVien.get();
            nhanVien.setHoTen(nhanVienDTO.getHoTen());
            nhanVien.setChucVu(nhanVienDTO.getChucVu());
            nhanVien.setSoDienThoai(nhanVienDTO.getSoDienThoai());
            nhanVien.setEmail(nhanVienDTO.getEmail());
            nhanVien.setNgayVaoLam(nhanVienDTO.getNgayVaoLam());
            nhanVien.setLuong(nhanVienDTO.getLuong());
            NhanVien updatedNhanVien = nhanVienRepository.save(nhanVien);
            return  convertToDTO(updatedNhanVien);
        }
        return null;
    }

    public boolean deleteNhanVien(String maNV) {
        if(nhanVienRepository.existsById(maNV)) {
            nhanVienRepository.deleteById(maNV);
            return true;
        }
        return false;
    }

    private NhanVienDTO convertToDTO(NhanVien nhanVien){
        NhanVienDTO dto = new NhanVienDTO();
        dto.setMaNV(nhanVien.getMaNV());
        dto.setHoTen(nhanVien.getHoTen());
        dto.setChucVu(nhanVien.getChucVu());
        dto.setSoDienThoai(nhanVien.getSoDienThoai());
        dto.setEmail(nhanVien.getEmail());
        dto.setNgayVaoLam(nhanVien.getNgayVaoLam());
        dto.setLuong(nhanVien.getLuong());
        return dto;
    }

    public NhanVien convertToEntity(NhanVienDTO dto){
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(dto.getMaNV());
        nhanVien.setHoTen(dto.getHoTen());
        nhanVien.setChucVu(dto.getChucVu());
        nhanVien.setSoDienThoai(dto.getSoDienThoai());
        nhanVien.setEmail(dto.getEmail());
        nhanVien.setNgayVaoLam(dto.getNgayVaoLam());
        nhanVien.setLuong(dto.getLuong());
        return nhanVien;
    }
}
