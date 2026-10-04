package com.example.init.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeliculasResponseDTO {
    private String titulo;
    private String anio;
    private double adecuacion;
}
