package com.example.springjwttutorial.domain.repository.character;

import com.example.springjwttutorial.infrastructure.dto.character.CharacterEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharacterRepository {
  List<CharacterEntity> getCharacters();
}
