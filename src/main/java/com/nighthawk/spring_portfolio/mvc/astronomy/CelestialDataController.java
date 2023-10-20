package com.nighthawk.spring_portfolio.mvc.astronomy;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/celestial-data")
public class CelestialDataController {

    @Autowired
    private CelestialDataService celestialDataService;

    @GetMapping("/fetch-and-store")
    public ResponseEntity<String> fetchAndStoreData() {
        try {
            String data = fetchDataFromExternalAPI();

            // Parse the JSON data into a CelestialData object
            ObjectMapper objectMapper = new ObjectMapper();
            CelestialData celestialData = objectMapper.readValue(data, CelestialData.class);

            // Store the CelestialData object
            celestialDataService.storeData(celestialData);

            return ResponseEntity.ok("Data fetched and stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch and store data.");
        }
    }

    // Sample endpoint to get celestial data by ID
    @GetMapping("/{id}")
    public ResponseEntity<CelestialData> getCelestialDataById(@PathVariable String id) {
        CelestialData data = celestialDataService.getDataById(id);
        if (data != null) {
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Sample endpoint to list all celestial data
    @GetMapping("/list")
    public ResponseEntity<List<CelestialData>> listAllCelestialData() {
        List<CelestialData> dataList = celestialDataService.getAllData();
        return ResponseEntity.ok(dataList);
    }

    private String fetchDataFromExternalAPI() throws IOException, InterruptedException {
        // Define the API endpoint and headers
        String apiUrl = "https://astronomy.p.rapidapi.com/api/v2/bodies/positions?latitude=33.775867&longitude=-84.39733&from_date=2017-12-20&to_date=2017-12-21&elevation=166&time=12%3A00%3A00";
        String apiKey = "8401db6433msh3a46dd5bf23ad2ep19a280jsn48536a994246";
        String apiHost = "astronomy.p.rapidapi.com";

        // Create an HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", apiHost)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        // Send the HTTP request and retrieve the response
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body(); // Return the response body as data
        } else {
            throw new RuntimeException("Failed to fetch data from the external API");
        }
    }
}