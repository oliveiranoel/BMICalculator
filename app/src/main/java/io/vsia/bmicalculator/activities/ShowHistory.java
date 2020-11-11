package io.vsia.bmicalculator.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import io.vsia.bmicalculator.R;
import io.vsia.bmicalculator.business.BMIDatabaseHelper;
import io.vsia.bmicalculator.model.Bmi;

public class ShowHistory extends AppCompatActivity {

    BMIDatabaseHelper bmiDatabaseHelper = new BMIDatabaseHelper(this);
    ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);

        editText = findViewById(R.id.editText);

        listView = findViewById(R.id.listView);
        List<Bmi> entries = bmiDatabaseHelper.showAllEntries();

        if (!entries.isEmpty()) {
            ArrayAdapter<Bmi> listAdapter = new ArrayAdapter<Bmi>(this, android.R.layout.simple_list_item_1, entries);
            listView.setAdapter(listAdapter);
        } else {
            String toastText = "Keine Einträge in der Liste vorhanden";
            Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onDeleteHistory(View view) {
        bmiDatabaseHelper.deleteAllEntries();
        listView.setAdapter(null);
    }

    public void onFilter(View view) {

        String name = editText.getText().toString();

        List<Bmi> bmiList = bmiDatabaseHelper.showFilteredEntries(name);

        ArrayAdapter<Bmi> listAdapter = new ArrayAdapter<Bmi>(this, android.R.layout.simple_list_item_1, bmiList);
        listView.setAdapter(listAdapter);
    }
}
