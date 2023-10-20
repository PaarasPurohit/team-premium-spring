package com.nighthawk.spring_portfolio.mvc.astronomy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DataParserService {

    @Autowired
    public DataParserService() {
    }

    public List<CelestialData> parseData(String externalData) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Parse the JSON response and convert it into a list of CelestialData
            JsonNode jsonNode = objectMapper.readTree(externalData);
            List<CelestialData> celestialDataList = new ArrayList<>();

            if (jsonNode.has("data") && jsonNode.get("data").has("table") && jsonNode.get("data").get("table").has("rows")) {
                JsonNode rows = jsonNode.get("data").get("table").get("rows");

                for (JsonNode row : rows) {
                    CelestialData celestialData = objectMapper.treeToValue(row, CelestialData.class);
                    celestialDataList.add(celestialData);
                }
            }

            return celestialDataList;
        } catch (JsonProcessingException e) {
            // Handle JSON parsing exceptions
            e.printStackTrace();
            return Collections.emptyList();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
