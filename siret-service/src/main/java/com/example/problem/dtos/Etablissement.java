package com.example.problem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etablissement {
  private long id;
  private String siren;
  private Date date_creation;
  private String fullName;
  private String numero_tva_intra;

  public String[] converString() {
    return new String[] { Long.toString(id) ,  siren , date_creation.toString() , fullName,numero_tva_intra };
  }


}
