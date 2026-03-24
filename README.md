# 🎓 Grade Calculator

A cross-mode Grade Calculator desktop application built with **Kotlin**, designed to help university lecturers and administrators efficiently process and assign grades to students.

The application accepts student data from multiple file formats, automatically calculates letter grades based on a predefined grading scale, and exports the final results to a formatted Excel file.

---

## 📸 Preview

| Console Mode | Web GUI Mode |
|---|---|
| Terminal-based interaction | Browser-based interface at `localhost:7070` |

---

## ✨ Features

- 🖥️ **Dual Mode** — Choose between Console Mode or Web GUI Mode at startup
- 📂 **Multi-format Input** — Supports CSV, Excel (`.xlsx`), and TXT input files
- 🎓 **Automatic Grading** — Assigns letter grades based on the university grading scale
- 📊 **Excel Export** — Results are exported to a clean, formatted `.xlsx` file
- 🌐 **Browser-based GUI** — Web interface accessible via any browser at `http://localhost:7070`

---

## 📊 Grading Scale

| Grade | Score Range |
|-------|------------|
| A     | 80 – 100   |
| B+    | 70 – 79    |
| B     | 60 – 69    |
| C+    | 55 – 59    |
| C     | 50 – 54    |
| D+    | 45 – 49    |
| D     | 40 – 44    |
| F     | 0 – 39     |

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Kotlin 2.0.21 | Main programming language |
| Gradle 8.14 | Build tool |
| Apache Commons CSV | CSV file parsing |
| Apache POI | Excel file reading and writing |
| Javalin 6.4.0 | Embedded web server for GUI mode |
| Jackson | JSON serialisation |
| Java 21 | JVM runtime |

---

## 📁 Project Structure

```
grade_calculator/
├── build.gradle.kts
├── settings.gradle.kts
└── src/
    ├── main/
    │   ├── kotlin/
    │   │   └── com/gradecalculator/
    │   │       ├── Main.kt                  ← Entry point & startup menu
    │   │       ├── model/
    │   │       │   └── Student.kt           ← Student data class
    │   │       ├── grading/
    │   │       │   └── GradeCalculator.kt   ← Grading scale logic
    │   │       ├── parser/
    │   │       │   ├── CsvParser.kt         ← CSV file reader
    │   │       │   ├── ExcelParser.kt       ← Excel file reader
    │   │       │   └── TxtParser.kt         ← TXT file reader
    │   │       ├── exporter/
    │   │       │   └── ExcelExporter.kt     ← Excel results exporter
    │   │       ├── console/
    │   │       │   └── ConsoleMode.kt       ← Console mode UI
    │   │       └── web/
    │   │           └── WebServer.kt         ← Javalin web server
    │   └── resources/
    │       └── static/
    │           ├── index.html               ← Web GUI page
    │           ├── style.css                ← Styling
    │           └── app.js                   ← Browser logic
    └── test/
        └── kotlin/
```

---

## 🚀 How to Run

### Prerequisites
- Java 21 installed — download from [https://adoptium.net](https://adoptium.net)
- IntelliJ IDEA (optional but recommended)

### Clone the repository
```bash
git clone https://github.com/Beri-Pamela/grade_calculator.git
cd grade_calculator
```

### Run the application (Windows)
```bash
.\gradlew.bat run --console=plain
```

### Run the application (Mac/Linux)
```bash
./gradlew run --console=plain
```

---

## 📂 Input File Format

Your input file must have these three columns: `name`, `id`, `score`

### CSV / TXT example
```
name,id,score
Alice Johnson,STU001,92.5
Bob Smith,STU002,74.0
Carol White,STU003,63.5
```

### Excel (.xlsx) example
| name | id | score |
|---|---|---|
| Alice Johnson | STU001 | 92.5 |
| Bob Smith | STU002 | 74.0 |

---

## 🖥️ Console Mode

1. Run the app and select **1** for Console Mode
2. Enter the full path to your input file
3. View the results in the terminal
4. Enter the output path to save results as Excel

---

## 🌐 Web GUI Mode

1. Run the app and select **2** for Web GUI Mode
2. Open your browser and go to **http://localhost:7070**
3. Enter your input file path and output file path
4. Click **Calculate Grades** to see results

---

## 📤 Output

Results are exported as a formatted `.xlsx` Excel file with these columns:

| Name | ID | Score | Grade |
|---|---|---|---|
| Alice Johnson | STU001 | 92.5 | A |
| Bob Smith | STU002 | 74.0 | B+ |

---

## 👩‍💻 Author

**Beri Pamela**
- GitHub: [@Beri-Pamela](https://github.com/Beri-Pamela)

---

## 📝 License

This project was developed as a university assignment.
