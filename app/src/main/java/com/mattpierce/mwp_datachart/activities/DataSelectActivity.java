package com.mattpierce.mwp_datachart.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.mattpierce.mwp_datachart.R;
import com.mattpierce.mwp_datachart.objects.DatasetConnection;
import com.mattpierce.mwp_datachart.objects.DatasetMapping;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DataSelectActivity extends AppCompatActivity {

    private int numConnections;
    private ArrayList<DatasetConnection> connections = new ArrayList<>();
    private DatasetListAdapter adapter;
    private List<DatasetConnection> selected_connections = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_select);

        // Initialize the view
        // Connect to datasets
        // Display a list of datasets
        // When datasets are selected, add them to a list to pass to graph activity
        // When "Next"is clicked, pass onto the graph activity

        adapter = new DatasetListAdapter();
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final DatasetConnection connection = adapter.getConnection(i);
                if (connection == null) return;

                CheckBox checkBox = (CheckBox) view.findViewById(R.id.dataset_checkBox);
                if (checkBox.isChecked()) {
                    // Uncheck the box and remove the device from the array
                    checkBox.setChecked(false);
                    selected_connections.remove(connection);
                } else {
                    // Check the box and add the device to the array
                    checkBox.setChecked(true);
                    selected_connections.add(connection);
                }

                /*
                final Intent intent = new Intent(MainActivity.this, DeviceDetailActivity.class);
                intent.putExtra(getResources().getString(R.string.EXTRAS_DEVICE_NAME), device.getName());
                intent.putExtra(getResources().getString(R.string.EXTRAS_DEVICE_ADDRESS), device.getAddress());
                if (isScanning) {
                    scanLeDevice(false);
                }
                startActivity(intent);
                */
            }
        });

        // TODO: Write initializeView function. Should focus on setting up ListView
        initializeView();

        // TODO: Write connectDatasets function. Should include createDataMappings
        numConnections = connectDatasets();

    }

    private int connectDatasets() {
        // This is where you can create all of your datasets
        // TODO: First, create the data mappings
        DatasetMapping datasetMapping = new DatasetMapping("xValue", "yValue");

        String conStr1 = "www.helloWorld.com";
        DatasetConnection datasetConnection1 = new DatasetConnection(datasetMapping, conStr1, DatasetConnection.RAW_JSON_CONNECTION);

        String conStr2 = "www.whatisthis.com";
        DatasetConnection datasetConnection2 = new DatasetConnection(datasetMapping, conStr2, DatasetConnection.API_JSON_CONNECTION);

        connections.add(datasetConnection1);
        connections.add(datasetConnection2);

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
            super(DataSelectActivity.this, R.layout.cell_main_dataset, connections);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.cell_main_dataset, parent, false);
            }

            DatasetConnection currentConn = connections.get(position);

            TextView connStrText = (TextView) itemView.findViewById(R.id.connectionString);
            TextView connTypeText = (TextView) itemView.findViewById(R.id.connectionType);

            final String connStr = currentConn.getJsonDataSource();
            final String connType = Integer.toString(currentConn.getConnectionType());

            connStrText.setText(connStr);
            connTypeText.setText(connType);

            return itemView;
        }
        DatasetConnection getConnection(int position) { return connections.get(position); }
    }
}
