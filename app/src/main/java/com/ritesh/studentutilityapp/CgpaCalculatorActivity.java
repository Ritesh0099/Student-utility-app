package com.ritesh.studentutilityapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CgpaCalculatorActivity extends AppCompatActivity {

    EditText grade1, credit1, grade2, credit2, grade3, credit3;
    Button calculateBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_cgpa_calculator);

            grade1 = findViewById(R.id.grade1);
            credit1 = findViewById(R.id.credit1);
            grade2 = findViewById(R.id.grade2);
            credit2 = findViewById(R.id.credit2);
            grade3 = findViewById(R.id.grade3);
            credit3 = findViewById(R.id.credit3);
            calculateBtn = findViewById(R.id.calculateBtn);
            resultText = findViewById(R.id.resultText);

            calculateBtn.setOnClickListener(v -> calculateCGPA());
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void calculateCGPA() {
        try {
            double g1 = Double.parseDouble(grade1.getText().toString());
            double c1 = Double.parseDouble(credit1.getText().toString());
            double g2 = Double.parseDouble(grade2.getText().toString());
            double c2 = Double.parseDouble(credit2.getText().toString());
            double g3 = Double.parseDouble(grade3.getText().toString());
            double c3 = Double.parseDouble(credit3.getText().toString());

            double totalGradePoints = (g1 * c1) + (g2 * c2) + (g3 * c3);
            double totalCredits = c1 + c2 + c3;

            if (totalCredits == 0) {
                resultText.setText("Total credits cannot be 0");
                return;
            }

            double cgpa = totalGradePoints / totalCredits;
            resultText.setText("CGPA: " + String.format("%.2f", cgpa));
        } catch (NumberFormatException e) {
            resultText.setText("Please enter valid numeric inputs.");
        }
    }
}
