package com.example.init.controller;

import com.example.init.model.PeliculasResponseDTO;
import com.example.init.service.PeliculasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PeliculasController {

    private final PeliculasService peliculasService;

    public PeliculasController(PeliculasService peliculasService) {
        this.peliculasService = peliculasService;
    }

    @GetMapping("getPeliculas")
    public ResponseEntity<List<PeliculasResponseDTO>> getPeliculas(@RequestParam("preferencia") String preferencia) {
        return ResponseEntity.ok(peliculasService.getPeliculas(preferencia));
    }

}
