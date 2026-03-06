async function calculate() {
    const filePath   = document.getElementById("filePath").value.trim();
    const outputPath = document.getElementById("outputPath").value.trim();
    const errorBox   = document.getElementById("errorBox");
    const resultsSection = document.getElementById("resultsSection");
    const resultsBody    = document.getElementById("resultsBody");
    const btn            = document.getElementById("calculateBtn");

    // Clear previous results
    errorBox.style.display   = "none";
    resultsSection.style.display = "none";
    resultsBody.innerHTML    = "";

    // Validate inputs
    if (!filePath) {
        errorBox.textContent = "Please enter an input file path!";
        errorBox.style.display = "block";
        return;
    }

    if (!outputPath) {
        errorBox.textContent = "Please enter an output file path!";
        errorBox.style.display = "block";
        return;
    }

    // Disable button while processing
    btn.disabled = true;
    btn.textContent = "Processing...";

    try {
        const formData = new FormData();
        formData.append("filePath", filePath);
        formData.append("outputPath", outputPath);

        const response = await fetch("/calculate", {
            method: "POST",
            body: formData
        });

        if (!response.ok) {
            const errorText = await response.text();
            errorBox.textContent = errorText;
            errorBox.style.display = "block";
            return;
        }

        const students = await response.json();

        // Populate results table
        for (const student of students) {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${student.name}</td>
                <td>${student.id}</td>
                <td>${student.score.toFixed(1)}</td>
                <td>${student.grade}</td>
            `;
            resultsBody.appendChild(row);
        }

        resultsSection.style.display = "block";

    } catch (error) {
        errorBox.textContent = "Something went wrong: " + error.message;
        errorBox.style.display = "block";
    } finally {
        btn.disabled = false;
        btn.textContent = "Calculate Grades";
    }
}