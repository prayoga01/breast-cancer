# 🎗️ Breast Cancer Prediction API
### REST API — Naïve Bayes Model (WEKA + Spring Boot)

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![WEKA](https://img.shields.io/badge/WEKA_3.8.6-0277BD?style=for-the-badge&logo=java&logoColor=white)

> REST API for breast cancer risk prediction using a pre-trained Naïve Bayes model, built on 225 electronic medical records from RSUP Prof. Dr. I.G.N.G Ngoerah, Bali. Used as the prediction backend for [Breast-Cancer-app](https://github.com/prayoga01/Breast-Cancer-app).

---

## 🏗️ Architecture

```
┌─────────────────────┐         ┌──────────────────────────────┐
│   Laravel (Web App) │──POST──▶│     Spring Boot (API)        │
│  Breast-Cancer-app  │◀──JSON──│  /api/predict                │
└─────────────────────┘         │                              │
                                │  Load model-breast-cancer    │
                                │  .model (on startup)         │
                                │         │                    │
                                │         ▼                    │
                                │  WEKA Naïve Bayes Classifier │
                                │  distributionForInstance()   │
                                └──────────────────────────────┘
```

**How It Works:**
1. **Model stored as `.model` file** — The Naïve Bayes model is pre-trained and stored in Java serialization (binary) format as `model-breast-cancer.model`
2. **Loaded on startup** — The model is loaded by `WekaPredictionService` via `@PostConstruct` — no probability recalculation at runtime
3. **`.arff` file as attribute header** — `dataset breastcancer 225 filter.arff` is used only to map input to the model's attribute structure, not for retraining
4. **Prediction flow:**
```
Input JSON → Map to WEKA Instance → classifier.classifyInstance()
→ Get probability distribution (distributionForInstance())
→ Return prediction + confidence score
```

---

## 🔌 API Endpoint

### `POST /api/predict`
Predicts breast cancer risk based on the patient's symptoms and risk factors.

**Request Body:**
```json
{
  "faktorRisiko": "Ya",
  "benjolanPayudara": "Ya",
  "kecepatanTumbuhMassa": "Cepat",
  "nippleDischarge": "Tidak",
  "retraksiPuting": "Tidak",
  "krusta": "Tidak",
  "dimpling": "Tidak",
  "peauDOrange": "Tidak",
  "ulserasi": "Tidak",
  "venektasi": "Tidak",
  "benjolanKetiak": "Ya",
  "edemaLengan": "Tidak",
  "nyeriTulang": "Tidak",
  "sesakNapas": "Tidak"
}
```

**Response Success (200):**
```json
{
  "prediction": "Risiko Tinggi",
  "confidence": 91.25,
  "accuracy": 87.556
}
```

---

## 🤖 Naïve Bayes Model

| Item | Detail |
|------|--------|
| Algorithm | Naïve Bayes (WEKA) |
| Model File | `model-breast-cancer.model` |
| Dataset | `breastcancer 225 filter.arff` (225 medical records — RSUP Ngoerah) |
| Accuracy | **87.556%** (10-fold cross-validation) |
| Target Class | High Risk / Normal |
| Confidence | `classifier.distributionForInstance()` |
| Loading Strategy | `@PostConstruct` on application startup |

---

## 🛠️ Tech Stack

| Component | Version |
|-----------|---------|
| Java | 21 |
| Spring Boot | 3.5.3 |
| Spring Boot Starter Web | 3.5.3 |
| WEKA | 3.8.6 (Stable) |
| Lombok | Latest |

---

## ⚙️ Installation & Setup

### Prerequisites
- Java 21
- Maven
- Model file: `model-breast-cancer.model`
- Dataset file: `breastcancer 225 filter.arff`

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/prayoga01/breast-cancer.git
cd breast-cancer

# 2. Make sure model & arff files are placed correctly
# Place them in: src/main/resources/models/
# - model-breast-cancer.model
# - breastcancer 225 filter.arff

# 3. Build the project
./mvnw clean install

# 4. Run the application
./mvnw spring-boot:run
```

API runs at `http://localhost:8080`

---

## 📁 Project Structure

```
breast-cancer/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/.../
│       │       ├── controller/
│       │       │   └── PredictionController.java  ← @PostMapping /api/predict
│       │       ├── service/
│       │       │   └── WekaPredictionService.java ← model loading + predict logic
│       │       └── dto/
│       │           └── PredictionRequest.java     ← 13 symptom attributes
│       └── resources/
│           ├── models/
│           │   ├── model-breast-cancer.model
│           │   └── breastcancer 225 filter.arff
│           └── application.properties
└── pom.xml
```

---

## 🔗 Related

- Web Application (Laravel): [Breast-Cancer-app](https://github.com/prayoga01/Breast-Cancer-app)

---

## 👨‍💻 Developer

**Yoga Pratama**
- GitHub: [@prayoga01](https://github.com/prayoga01)

---

## 📝 License

This project was developed for breast cancer screening research at RSUP Prof. Dr. I.G.N.G Ngoerah, Bali.
