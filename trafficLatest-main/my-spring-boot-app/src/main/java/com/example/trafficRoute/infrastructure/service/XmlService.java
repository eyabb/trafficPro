package com.example.trafficRoute.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.trafficRoute.infrastructure.database.repository.ItemSchemaRepository;
import com.example.trafficRoute.infrastructure.database.schema.ItemSchema;
import com.example.trafficRoute.infrastructure.xmlJavaMapper.Item;
import com.example.trafficRoute.infrastructure.xmlJavaMapper.Items;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@Service
public class XmlService {
  private final ItemSchemaRepository itemRepository;

  @Autowired
  public XmlService(ItemSchemaRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public void saveXmlDataToDatabase() {
    try {
      File xmlFile = new ClassPathResource("cause2023.xml").getFile();
      if (xmlFile.exists() && xmlFile.length() > 0) {
        System.out.println("File exists and is not empty");
        // You can proceed with further operations using xmlFile
      } else {
        System.out.println("File doesn't exist or is empty");
      }
      System.out.println("am I even here");
      JAXBContext jaxbContext = JAXBContext.newInstance(Items.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      Items xmlData = (Items) jaxbUnmarshaller.unmarshal(xmlFile);

      for (Item item : xmlData.getItemList()) {
        ItemSchema itemSchema = new ItemSchema();
        itemSchema.setLabelle(item.getLabelle());
        itemSchema.setAccidents(item.getAccidents());
        itemSchema.setTues(item.getTues());
        itemSchema.setBlesses(item.getBlesses());
        itemRepository.save(itemSchema);
      }
    } catch (JAXBException | IOException e) {
      e.printStackTrace();
    }
  }
}
