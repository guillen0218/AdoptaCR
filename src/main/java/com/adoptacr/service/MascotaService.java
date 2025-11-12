package com.adoptacr.service;

import com.adoptacr.domain.Mascota;
import java.util.List;

public interface MascotaService {
    List<Mascota> buscarMascotas(String filtro);
    Mascota guardar(Mascota mascota);
}
