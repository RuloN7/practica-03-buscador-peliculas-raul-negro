package com.example.init.service;

import com.example.init.model.PeliculasResponseDTO;

import java.util.List;

public interface PeliculasService {

    List<PeliculasResponseDTO> getPeliculas(String preferencia);

}
