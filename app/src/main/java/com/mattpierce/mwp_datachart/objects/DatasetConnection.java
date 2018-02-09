package com.mattpierce.mwp_datachart.objects;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.mattpierce.mwp_datachart.data.JSONLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mattpierce on 8/23/17.
 */

public class DatasetConnection implements Serializable {

    public static final int RAW_JSON_CONNECTION = 0;
    public static final int API_JSON_CONNECTION = 1;

    private DatasetMapping datasetMapping;
    private String jsonDataSource;
    private int connectionType;
    private ArrayList<Entry> data;
    private JSONLoader jl = new JSONLoader();

    public DatasetConnection(DatasetMapping datasetMapping, String jsonDataSource, int connectionType) {
        this.datasetMapping = datasetMapping;
        this.jsonDataSource = jsonDataSource;
        this.connectionType = connectionType;
    }

    public DatasetMapping getDatasetMapping() {
        return datasetMapping;
    }

    public void setDatasetMapping(DatasetMapping datasetMapping) {
        this.datasetMapping = datasetMapping;
    }

    public String getJsonDataSource() {
        return jsonDataSource;
    }

    public void setJsonDataSource(String jsonDataSource) {
        this.jsonDataSource = jsonDataSource;
    }

    public int getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }

    public ArrayList<Entry> loadData() {
        data.clear();
        String jsonStr;

        if (jsonDataSource == null) {
            // This means that the JSON string was never set
        }

        if (connectionType == API_JSON_CONNECTION) {
            jsonDataSource = jl.makeServiceCall(jsonDataSource, JSONLoader.GET);
        }

        JSONArray jsonObj = null;
        if (jsonDataSource != null) {
            try {
                jsonObj = new JSONArray(jsonDataSource);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the URL");
        }

        try {
            for (int i=0; i < jsonObj.length(); i++) {
                JSONObject item = jsonObj.getJSONObject(i);

                int x_value = item.getInt(datasetMapping.getFieldForX());
                int y_value = item.getInt(datasetMapping.getFieldForY());

                Entry newEntry = new Entry(x_value, y_value);
                data.add(newEntry);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }
}
