package com.mattpierce.mwp_datachart.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mattpierce.mwp_datachart.R;
import com.mattpierce.mwp_datachart.objects.DatasetConnection;
import com.mattpierce.mwp_datachart.objects.DatasetMapping;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DataSelectActivity extends AppCompatActivity {

    private int numConnections;
    private ArrayList<DatasetConnection> connections = new ArrayList<>();
    private DatasetListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_select);

        adapter = new DatasetListAdapter();
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        // Initialize the view
        // Connect to datasets
        // Display a list of datasets
        // When datasets are selected, add them to a list to pass to graph activity
        // When "Next"is clicked, pass onto the graph activity

        // TODO: Write initiailizeView function. Should focus on setting up ListView
        initializeView();

        // TODO: Write connectDatasets function. Should include createDataMappings
        numConnections = connectDatasets();
    }

    private int connectDatasets() {
        // This is where you can create all of your datasets
        // TODO: First, create the data mappings for the data connections
        DatasetMapping datasetMapping = new DatasetMapping("xValue", "yValue");

        // TODO: Second, create the dataset connections using the created mappings
        DatasetConnection datasetConnection = new DatasetConnection(datasetMapping, "www.helloworld.com", DatasetConnection.API_JSON_CONNECTION);

        // TODO: Third, add all of the database connections to the Array
        connections.add(datasetConnection);

        return connections.size();
    }

    private void initializeView() {
        // Do something to initialize the view here
    }

    // Custom list adapter that uses custom list cell to display data
    // Is shows all of the connected datasets and allows the user to choose one or multiple
    private class DatasetListAdapter extends ArrayAdapter<DatasetConnection> {
        DatasetListAdapter()
        {
            super(DataSelectActivity.this, R.layout.cell_dataselect_activity, connections);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.cell_dataselect_activity, parent, false);
            }

            DatasetConnection currentConn = connections.get(position);

            TextView urlText = (TextView) itemView.findViewById(R.id.url_connection);
            TextView xValueText = (TextView) itemView.findViewById(R.id.xValue_string);
            TextView yValueText = (TextView) itemView.findViewById(R.id.yValue_string);

            urlText.setText(currentConn.getJsonDataSource());
            xValueText.setText(currentConn.getDatasetMapping().getFieldForX());
            yValueText.setText(currentConn.getDatasetMapping().getFieldForY());

            return itemView;
        }

        // BluetoothDevice getDevice(int position) { return devices.get(position); }
    }
}
