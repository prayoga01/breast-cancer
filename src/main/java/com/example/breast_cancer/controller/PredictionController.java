// PredictionController.java
package com.example.breast_cancer.controller;

import com.example.breast_cancer.dto.PredictionRequest;
import com.example.breast_cancer.service.WekaPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PredictionController {

    @Autowired
    private WekaPredictionService predictionService;

    @PostMapping("/predict")
    public ResponseEntity<?> predict(@RequestBody PredictionRequest request) {
        try {
            String prediction = predictionService.predict(request);
            double accuracy = predictionService.getAccuracy();
            double riskPercentage = predictionService.getPredictionConfidence(request);
            
            // Mengembalikan hasil prediksi, akurasi model, dan confidence score
            return ResponseEntity.ok(Map.of(
                "prediction", prediction,
                "accuracy", accuracy,
                "confidenceScore", riskPercentage
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", "Gagal melakukan prediksi: " + e.getMessage()));
        }
    }
}



