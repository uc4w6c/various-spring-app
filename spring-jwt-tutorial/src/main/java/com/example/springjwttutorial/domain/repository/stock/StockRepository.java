package com.example.springjwttutorial.domain.repository.stock;

import com.example.springjwttutorial.domain.dto.character.CharacterEntity;
import com.example.springjwttutorial.domain.dto.stock.StockEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockRepository {
  List<CharacterEntity> getUserCharacters(int user_id);
  void save(List<StockEntity> stocks);
}
