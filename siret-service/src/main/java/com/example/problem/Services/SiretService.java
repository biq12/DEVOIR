package com.example.problem.Services;

import com.example.problem.dtos.Etablissement;
import com.example.problem.dtos.EtablissementDTO;
import com.example.problem.dtos.SiretDTO;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SiretService {

  @Autowired RestTemplate restTemplate;

  public List<String> getAllData() {
    List<String> items = Arrays.asList("31302979500017","41339442000033","41339442000090","41339442000116",
    "41776304200013","43438147100011","45251723800013","52170053400014","75254783600011","47962817400042",
     "97080195700014");
    return items;
  }

  public List<Etablissement> retriveData() {
    log.info("Initialize Retrive data from API ...");

    List<String> sirets =getAllData();

    String resourceUrl
        = "https://entreprise.data.gouv.fr/api/sirene/v3/etablissements/";

    List<SiretDTO> siretArray=new ArrayList<>();

    ResponseEntity<SiretDTO> responseEntity;

    for (String siret:sirets) {
      try{
        responseEntity =
            restTemplate.getForEntity(resourceUrl+siret, SiretDTO.class);

        if(responseEntity.getBody().getEtablissement()!=null){
          siretArray.add(responseEntity.getBody());
        }
      }catch (Exception e){
         log.warn("Problem retreive Data For {} "+siret);
      }
    }

    List<EtablissementDTO>  ETAB= siretArray.stream()
        .map(SiretDTO::getEtablissement)
        .collect(Collectors.toList());

    List<Etablissement>  list= copyDTOTOMODEL(ETAB);
    createCsvDataSimple(list);
    return list;

    }

  private List<Etablissement> copyDTOTOMODEL(List<EtablissementDTO> etablissements) {
    List<Etablissement> list=new ArrayList<>();
    for (EtablissementDTO etablissementdto:etablissements) {
       Etablissement entity=new Etablissement();
       entity.setId(etablissementdto.getId());
       entity.setSiren(etablissementdto.getSiren());
       entity.setDate_creation(etablissementdto.getDate_creation());
       String nom=etablissementdto.getUnite_legale().getNom();
       String prenom = etablissementdto.getUnite_legale().getPrenom_1();
       entity.setFullName(nom==null?"":nom + prenom==null?"":prenom);
       entity.setNumero_tva_intra(etablissementdto.getUnite_legale().getNumero_tva_intra());
       list.add(entity);
    }
    return list;
  }

  private static void createCsvDataSimple(List<Etablissement> etabs) {
    String[] header ={"id", "siren", "date_creation", "fullName","numero_tva_intra"};
    List<String[]>  csvData = new ArrayList<>();
    csvData.add(header);

    try {
      File file=new File("\\test\\test.csv");
      CSVWriter writer = new CSVWriter(new FileWriter(file));
      for (Etablissement etab:etabs) {
        writer.writeNext(etab.converString());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }









}





