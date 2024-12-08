package com.db0612.PGPB.repository;

import com.db0612.PGPB.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
    // Các truy vấn có thể thêm vào nếu cần thiết
}
