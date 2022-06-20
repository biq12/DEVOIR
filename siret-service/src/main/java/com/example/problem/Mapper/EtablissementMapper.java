package com.example.problem.Mapper;

import com.example.problem.dtos.Etablissement;
import com.example.problem.dtos.EtablissementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EtablissementMapper {

  EtablissementMapper INSTANCE = Mappers.getMapper(EtablissementMapper.class);
  @Mapping(target = "unite_legale.nom", source = "unite_legale.nom"+"unite_legale.prenom_1")
  EtablissementDTO EtablissementDTOTOEtablissement(Etablissement e);
}

