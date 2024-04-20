package com.pokemonreview.api.services.impl;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setType((pokemonDto.getType()));
        pokemon.setName((pokemonDto.getName()));

        Pokemon newPokemon = pokemonRepository.save(pokemon);
        return mapToDto(newPokemon);
    }

    @Override
    public List<PokemonDto> getAllPokemons() {
        List<Pokemon> allPokemons = pokemonRepository.findAll();
        return allPokemons.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
    }

    @Override
    public PokemonDto getPokemonDetailsById(int id) {
        Pokemon pokemon = pokemonRepository.findById((long) id).orElseThrow(() -> new PokemonNotFoundException("Pokemon with this id could not be found"));
        return mapToDto(pokemon);

    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemonToUpdate = pokemonRepository.findById((long) id).orElseThrow(() -> new PokemonNotFoundException("Cannot update pokemon with this id"));
        pokemonToUpdate.setName(pokemonDto.getName());
        pokemonToUpdate.setType(pokemonDto.getType());

        Pokemon updatedPokemon = pokemonRepository.save(pokemonToUpdate);
        return mapToDto(updatedPokemon);
    }

    @Override
    public String deletePokemon(int id) {
        Pokemon pokemonToDelete = pokemonRepository.findById((long) id).orElseThrow(() -> new PokemonNotFoundException("The pokemon you are trying to delete is not found in the records"));
        pokemonRepository.deleteById((long) pokemonToDelete.getId());
        return "Successfully deleted the pokemon";
    }

    private PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }
}
