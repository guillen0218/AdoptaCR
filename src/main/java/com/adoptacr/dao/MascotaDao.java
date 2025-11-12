package com.adoptacr.dao;

import com.adoptacr.domain.Mascota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaDao extends JpaRepository<Mascota, Long> {
    List<Mascota> findByRazaContainingIgnoreCaseOrUbicacionContainingIgnoreCase(String raza, String ubicacion);
}
