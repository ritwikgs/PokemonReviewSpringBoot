package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokeomPaginatedResponseDto;
import com.pokemonreview.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {
    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("pokemon")
    public ResponseEntity<List<PokeomPaginatedResponseDto>> getPokemons(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue="10", required=false) int pageSize) {
        return new ResponseEntity(pokemonService.getAllPokemons(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemonDetail(@PathVariable int id) {
        return ResponseEntity.ok(pokemonService.getPokemonDetailsById(id));
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        return new ResponseEntity<>(pokemonService.createPokemon((pokemonDto)), HttpStatusCode.valueOf(200));
    }

    @PutMapping("pokemon/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable int id) {
        return ResponseEntity.ok(pokemonService.updatePokemon(pokemonDto, id));
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable int id) {
            return ResponseEntity.ok(pokemonService.deletePokemon(id));
    }
}

