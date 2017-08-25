package com.mattpierce.mwp_datachart.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mattpierce.mwp_datachart.R;
import com.mattpierce.mwp_datachart.objects.DatasetConnection;

import java.util.ArrayList;

public class DataSelectActivity extends AppCompatActivity {

    private int numConnections;
    private ArrayList<DatasetConnection> connections = new ArrayList<>();
    private DatasetListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_select);

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
    }

    private void initializeView() {
        // Do something to initialize the view here
    }

    // Custom list adapter that uses custom list cell to display data
    // Is shows all of the connected datasets and allows the user to choose one or multiple
    private class DatasetListAdapter extends ArrayAdapter<DatasetConnection> {
        DatasetListAdapter()
        {
            super(DataSelectActivity.this, R.layout.cell_main_device, connections);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.cell_main_device, parent, false);
            }

            /*
            // Find the object to work with
            BluetoothDevice currentDevice = devices.get(position);

            TextView nameText = (TextView) itemView.findViewById(R.id.deviceName);
            final String deviceName = currentDevice.getName();
            if (deviceName != null && deviceName.length() > 0)
                nameText.setText(deviceName);
            else
                nameText.setText(R.string.unknown_device);

            TextView addressText = (TextView) itemView.findViewById(R.id.deviceAddress);
            addressText.setText(currentDevice.getAddress());

            // CheckBox box = (CheckBox) itemView.findViewById(R.id.device_checkBox);
            // box.setClickable(false);
            // box.setChecked(false);
            */

            return itemView;
        }

        // BluetoothDevice getDevice(int position) { return devices.get(position); }
    }
}
