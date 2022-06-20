package com.example.problem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtablissementDTO {
   private long id;
   private String siren;
   private Date date_creation;
   private UniteLegaleDTO unite_legale;

}
