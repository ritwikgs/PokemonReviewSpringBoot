package com.pokemonreview.api.services;

import com.pokemonreview.api.dto.PokemonDto;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    List<PokemonDto> getAllPokemons();
    PokemonDto getPokemonDetailsById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);

    String deletePokemon(int id);
}
