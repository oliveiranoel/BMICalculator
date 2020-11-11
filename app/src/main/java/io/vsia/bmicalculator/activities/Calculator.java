package io.vsia.bmicalculator.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.vsia.bmicalculator.R;
import io.vsia.bmicalculator.business.BMICalculator;
import io.vsia.bmicalculator.business.BMIDatabaseHelper;
import io.vsia.bmicalculator.model.Bmi;

public class Calculator extends AppCompatActivity {

    EditText name;
    EditText gewicht;
    EditText groesse;
    RadioGroup geschlecht;
    TextView result;
    BMICalculator bmiCalculator = new BMICalculator();
    Bmi person =  new Bmi();
    BMIDatabaseHelper bmiDatabaseHelper = new BMIDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        name = findViewById(R.id.editText_name);
        gewicht = findViewById(R.id.editText3_gewicht);
        groesse = findViewById(R.id.editText2_groesse);
        geschlecht = findViewById(R.id.radio_group);
        result = findViewById(R.id.textView7_result);
    }

    public void onCalcBMI(View view) {
        person.setName(this.name.getText().toString());

        //überprüfung ob Name einen Wert hat
        if (person.getName().length() == 0) {
            String toastText = "Bitte Name eingeben";
            Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
            toast.show();

        } else {
            try {
                person.setGewicht(Double.parseDouble(this.gewicht.getText().toString()));
                person.setGroesse(Double.parseDouble(this.groesse.getText().toString()));

                int id = geschlecht.getCheckedRadioButtonId();

                // überprüfe ob Gewicht oder Grösse = 0 ist
                if (person.getGewicht() == 0 || person.getGroesse() == 0) {
                    String toastText = "Kein Wert darf 0 sein";
                    Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
                    toast.show();

                // überprüfe ob Geschlecht angewählt ist
                } else if (id == -1) {
                    String toastText = "Bitte Geschlecht auswählen";
                    Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
                    toast.show();

                } else {
                    RadioButton radioButton;
                    radioButton = findViewById(id);

                    person.setBmi(bmiCalculator.calculateBMI(person.getGewicht(), person.getGroesse()));

                    bmiDatabaseHelper.createNewEntry(person);

                    Intent intent = new Intent(this, ShowResult.class);
                    intent.putExtra("bmi", person.getBmi());
                    intent.putExtra("result", String.valueOf(person.getBmi()));
                    startActivity(intent);
                }

            } catch (NumberFormatException e) {
                String toastText = "Bitte gültigen Wert eingeben";
                Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void onCallHistory(View view) {
        List<Bmi> entries = bmiDatabaseHelper.showAllEntries();

        if (entries.isEmpty()) {
            String toastText = "Keine Einträge in der Liste vorhanden";
            Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Intent intent = new Intent(this, ShowHistory.class);
            startActivity(intent);
        }

    }
}
