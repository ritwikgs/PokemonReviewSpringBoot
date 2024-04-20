package com.pokemonreview.api.services;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokeomPaginatedResponseDto;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokeomPaginatedResponseDto getAllPokemons(int pageNo, int pageSize);
    PokemonDto getPokemonDetailsById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);

    String deletePokemon(int id);
}
