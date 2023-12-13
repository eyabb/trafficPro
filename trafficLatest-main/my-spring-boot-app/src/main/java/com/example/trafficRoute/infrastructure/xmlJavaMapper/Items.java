
package com.example.trafficRoute.infrastructure.xmlJavaMapper;

import lombok.Data;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Items {

  @XmlElement(name = "item")
  private List<Item> itemList;

}
