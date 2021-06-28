package com.example.kazanneft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class EditOrCreateActivity extends AppCompatActivity {
    private EditText txtName, txtDepth, txtCapacity, txtRockLayers, txtFrom, txtTo;
    private Button btnAddLayer, btnSubmit, btnBack;
    private ListView listLayers;
    private Spinner spinnerLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_create);
        viewBinding();
    }

    private void viewBinding() {
        txtName = (EditText) findViewById(R.id.txtName);
        txtDepth = (EditText) findViewById(R.id.txtDepth);
        txtCapacity = (EditText) findViewById(R.id.txtCapacity);
        txtRockLayers = (EditText) findViewById(R.id.txtRockLayers);
        txtFrom = (EditText) findViewById(R.id.txtFrom);
        txtTo = (EditText) findViewById(R.id.txtTo);
        btnAddLayer = (Button) findViewById(R.id.btnAddLayer);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnBack = (Button) findViewById(R.id.btnBack);
        listLayers = (ListView) findViewById(R.id.listLayers);
        spinnerLayer = (Spinner) findViewById(R.id.spinnerLayer);
    }
}