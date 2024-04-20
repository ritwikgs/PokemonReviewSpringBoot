package com.pokemonreview.api.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PokemonDto {
    private int id;
    private String name;
    private String type;
}
