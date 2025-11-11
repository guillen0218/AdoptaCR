package com.adoptacr.service.impl;

import com.adoptacr.dao.MascotaDao;
import com.adoptacr.domain.Mascota;
import com.adoptacr.service.MascotaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaDao mascotaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> buscarMascotas(String filtro) {
        return mascotaDao.findByRazaContainingIgnoreCaseOrUbicacionContainingIgnoreCase(filtro, filtro);
        // Si filtro vacío, devolverá todas por coincidencia vacía
    }
}
