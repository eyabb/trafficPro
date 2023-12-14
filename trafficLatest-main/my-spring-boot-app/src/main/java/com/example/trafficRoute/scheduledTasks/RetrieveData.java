package com.example.trafficRoute.scheduledTasks;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * RetrieveData
 */
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.trafficRoute.infrastructure.service.XmlService;

import java.io.IOException;

@Component
public class RetrieveData {
    private final XmlService xmlService;

    @Autowired
    public RetrieveData(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    // @Scheduled(cron = "* 0 0 * 1 1") 
    @Scheduled(cron = "0 */3 * * * *")

    public void executeYearlyTask() {
        String command = "curl -JLk https://onsr.nat.tn/onsr/dataXml/cause2023.xml -o src/main/resources/cause2023.xml"; 

        try {
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Yearly2 job executed successfully");
                this.xmlService.saveXmlDataToDatabase();
            
            } else {
                System.out.println("Yearly job execution failed");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error executing yearly job: " + e.getMessage());
        }
    }
}

