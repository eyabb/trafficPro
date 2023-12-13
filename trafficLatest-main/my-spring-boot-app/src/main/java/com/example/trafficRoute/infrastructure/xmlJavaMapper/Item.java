package com.example.trafficRoute.infrastructure.xmlJavaMapper;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Item {

    @XmlElement(name = "labelle")
    private String labelle;

    private int accidents;
    private int tues;
    private int blesses;
}

