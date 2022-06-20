package com.example.problem.Controllers;

import com.example.problem.Services.SiretService;
import com.example.problem.dtos.Etablissement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/siret")
public class SiretControllers {


  @Autowired
  private SiretService siretService;


  @GetMapping(value = "/list")
  public List<Etablissement> response()  {
     return this.siretService.retriveData();
  }











}
