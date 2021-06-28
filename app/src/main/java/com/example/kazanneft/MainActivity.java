package com.example.kazanneft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView txtMainCapacity;
    private Button btnEdit;
    private ListView listWell;
    private FloatingActionButton fabAddWell;
    private Spinner spinnerWell;
    private MainActivity temp;

    ArrayList<HashMap<String, String>> rockTypeList;
    ArrayList<HashMap<String, String>> wellList;
    ArrayList<HashMap<String, String>> wellLayerList;
    ArrayList<HashMap<String, String>> wellTypeList;
    HashMap<String, String> tempHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        temp = this;
        setContentView(R.layout.activity_main);
        viewBinding();
        rockTypeList = new ArrayList<>();
        wellList = new ArrayList<>();
        wellLayerList = new ArrayList<>();
        wellTypeList = new ArrayList<>();
        readRockTypes();
        readWellLayers();
        readWells();
        readWellTypes();
        ArrayList<String> wellNameList = new ArrayList<>();
        for (HashMap<String, String> i : wellList) {
            wellNameList.add(i.get("WellName"));
        }

        ArrayAdapter<String> wellNameAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, wellNameList);
        wellNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWell.setAdapter(wellNameAdapter);
        spinnerWell.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spinnerWell.getSelectedItem().toString();
                List<WellLayer> wellLayers = new ArrayList<>();
                String wellId = null;
                String gasOilDepth = null;
                String capacity = null;
                String rockTypeId;
                String rockTypeName = null;
                String backgroundColor = null;
                String startPoint;
                String endPoint;
                for (HashMap<String, String> i : wellList) {
                    if (selected.equals(i.get("WellName"))) {
                        wellId = i.get("ID");
                        gasOilDepth = i.get("GasOilDepth");
                        capacity = i.get("Capacity");
                    }
                }
                for (HashMap<String, String> i : wellLayerList) {
                    assert wellId != null;
                    if (wellId.equals(i.get("WellID"))) {
                        rockTypeId = i.get("RockTypeID");
                        startPoint = i.get("StartPoint");
                        endPoint = i.get("EndPoint");
                        for (HashMap<String, String> j : rockTypeList) {
                            assert rockTypeId != null;
                            if (rockTypeId.equals(j.get("ID"))) {
                                rockTypeName = j.get("Name");
                                backgroundColor = j.get("BackgroundColor");
                            }
                        }
                        wellLayers.add(new WellLayer(rockTypeName, startPoint, endPoint, backgroundColor));
                    }
                }
                wellLayers.add(new WellLayer("Oil / Gas", null, null, "#000000"));
                txtMainCapacity.setText(capacity);
                WellLayerAdapter adapter = new WellLayerAdapter(temp, R.layout.well_list_item, wellLayers);
                listWell.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String readJSONFromAssets(String filename) {
        String json = null;
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private void readRockTypes() {
        try {
            JSONObject obj = new JSONObject(readJSONFromAssets("rocktypes.json"));
            JSONArray jArray = obj.getJSONArray("rocktypes");
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject iObj = jArray.getJSONObject(i);

                String id = iObj.getString("ID");
                String name = iObj.getString("Name");
                String backgroundColor = iObj.getString("BackgroundColor");

                tempHash = new HashMap<>();
                tempHash.put("ID", id);
                tempHash.put("Name", name);
                tempHash.put("BackgroundColor", backgroundColor);

                rockTypeList.add(tempHash);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void readWells() {
        try {
            JSONObject obj = new JSONObject(readJSONFromAssets("wells.json"));
            JSONArray jArray = obj.getJSONArray("wells");

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject iObj = jArray.getJSONObject(i);

                String id = iObj.getString("ID");
                String wellTypeID = iObj.getString("WellTypeID");
                String wellName = iObj.getString("WellName");
                String gasOilDepth = iObj.getString("GasOilDepth");
                String capacity = iObj.getString("Capacity");

                tempHash = new HashMap<>();
                tempHash.put("ID", id);
                tempHash.put("WellTypeID", wellTypeID);
                tempHash.put("WellName", wellName);
                tempHash.put("GasOilDepth", gasOilDepth);
                tempHash.put("Capacity", capacity);

                wellList.add(tempHash);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void readWellLayers() {
        try {
            JSONObject obj = new JSONObject(readJSONFromAssets("welllayers.json"));
            JSONArray jArray = obj.getJSONArray("welllayers");

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject iObj = jArray.getJSONObject(i);

                String id = iObj.getString("ID");
                String wellID = iObj.getString("WellID");
                String rockTypeID = iObj.getString("RockTypeID");
                String startPoint = iObj.getString("StartPoint");
                String endPoint = iObj.getString("EndPoint");

                tempHash = new HashMap<>();
                tempHash.put("ID", id);
                tempHash.put("WellID", wellID);
                tempHash.put("RockTypeID", rockTypeID);
                tempHash.put("StartPoint", startPoint);
                tempHash.put("EndPoint", endPoint);

                wellLayerList.add(tempHash);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void readWellTypes() {
        try {
            JSONObject obj = new JSONObject(readJSONFromAssets("welltypes.json"));
            JSONArray jArray = obj.getJSONArray("welltypes");

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject iObj = jArray.getJSONObject(i);

                String id = iObj.getString("ID");
                String name = iObj.getString("Name");
                tempHash = new HashMap<>();
                tempHash.put("ID", id);
                tempHash.put("Name", name);

                wellTypeList.add(tempHash);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void viewBinding() {
        txtMainCapacity = (TextView) findViewById(R.id.txtMainCapacity);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        listWell = (ListView) findViewById(R.id.listWell);
        fabAddWell = (FloatingActionButton) findViewById(R.id.fabAddWell);
        spinnerWell = (Spinner) findViewById(R.id.spinnerWell);
    }

}