package com.example.android_orm_lab9;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.android_orm_lab9.database.AppDatabase;
import com.example.android_orm_lab9.model.Person;
import com.example.android_orm_lab9.model.PersonDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addToDbButton, searchButton;
    private EditText nameInput, ageInput, searchInput;
    private CheckBox isActiveCB;
    private ListView resultListView;
    private ArrayList<String> dynamicList;
    private ArrayAdapter<String> listAdapter;
    private AppDatabase appDB;
    private PersonDAO personDAO;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing database
        appDB = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "persons-database").allowMainThreadQueries().build();
        personDAO = appDB.personDAO();

        //getting components from view
        //buttons
        addToDbButton = findViewById(R.id.addButton);
        searchButton = findViewById(R.id.searchButton);
        //text input fields
        nameInput = findViewById(R.id.nameEditText);
        ageInput = findViewById(R.id.ageEditText);
        searchInput = findViewById(R.id.searchByNameEditText);
        //checkbox
        isActiveCB = findViewById(R.id.isActiveCB);
        //listview
        resultListView = findViewById(R.id.resultListView);
        //listview utils
        dynamicList = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dynamicList);

        resultListView.setAdapter(listAdapter);
    }

    public void addToDBButton(View view) {
        String name = nameInput.getText().toString();
        String ageValue = ageInput.getText().toString();
        int age = Integer.parseInt(ageValue);
        boolean isActive = isActiveCB.isActivated();

        if (name.isEmpty() || ageValue.isEmpty()) {
            Toast warningToast = Toast.makeText(getApplicationContext(), "Please fill out the input fields", Toast.LENGTH_LONG);
            warningToast.show();
        } else {
            Person newPerson = new Person(age, name, isActive);
            personDAO.insertAll(newPerson);
            Toast saveSuccessToast = Toast.makeText(getApplicationContext(), "Person saved in DB", Toast.LENGTH_SHORT);
            saveSuccessToast.show();
        }
    }

    public void searchButton(View view) {
        String nameToBeSearchedBy = searchInput.getText().toString();
        if (nameToBeSearchedBy.isEmpty()) {
            Toast warningToast = Toast.makeText(getApplicationContext(), "Please enter the name you want to search", Toast.LENGTH_LONG);
            warningToast.show();
        } else {
            Person person = personDAO.findByName(nameToBeSearchedBy);
            dynamicList.clear();
            dynamicList.add(person.toString());
            listAdapter.notifyDataSetChanged();
        }
    }

    public void showAllButton(View view) {
        List<Person> personList = personDAO.getAll();
        if (personList.size() > 0) {
            dynamicList.clear();
            listAdapter.notifyDataSetChanged();
            for (Person person : personList) {
                dynamicList.add(person.toString());
                Log.i(TAG, "showAllButton:" + person.toString());
            }
            listAdapter.notifyDataSetChanged();
        }
    }
}