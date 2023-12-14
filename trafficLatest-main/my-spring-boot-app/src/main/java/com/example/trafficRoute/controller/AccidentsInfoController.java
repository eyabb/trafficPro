package com.example.trafficRoute.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trafficRoute.infrastructure.service.XmlService;
import com.example.trafficRoute.infrastructure.database.schema.ItemSchema;

@RestController
@RequestMapping("/accidents")
@CrossOrigin
public class AccidentsInfoController {
  @Autowired
    private XmlService xmlService;

  @GetMapping
  public List<ItemSchema> getAllAdherants() {
    return xmlService.getAccidentsInfo();
  }

  
}
