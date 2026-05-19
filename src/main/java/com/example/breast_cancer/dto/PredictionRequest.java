// PredictionRequest.java
package com.example.breast_cancer.dto;

import lombok.Data;

// Anotasi @Data dari Lombok akan secara otomatis membuat getter, setter, toString, dll.
@Data
public class PredictionRequest {
    // Nama variabel disesuaikan agar valid di Java
    private String faktorRisiko;
    private String benjolanDiPayudara;
    private String kecepatanTumbuh; // "Kecepatan Tumbuh dengan/ tanpa Rasa Sakit"
    private String nippleDischarge;
    private String retraksiPuttingSusu;
    private String krusta;
    private String dimpling;
    private String peauDorange; // "peau d'orange"
    private String ulserasi;
    private String venektasi;
    private String benjolanKetiak;
    private String edemaLengan;
    private String nyeriTulang;
    private String sesak;
}