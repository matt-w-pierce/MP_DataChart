package com.mattpierce.mwp_datachart.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import java.io.Serializable;
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
        // TODO: Make it so that they can also select a file that is saved locally on their device

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
            }
        });

        // TODO: Write initializeView function. Should focus on setting up ListView
        initializeView();

        // TODO: Write connectDatasets function. Should include createDataMappings
        numConnections = connectDatasets();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.data_select_activity, menu);
        menu.findItem(R.id.menu_next).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_next) nextScreen();
        return true;
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

    private void nextScreen() {

        // Checks to see if any devices have been selected, if not, displays dialog
        if (selected_connections.size() == 0) {
            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.no_devices_dialog_title));
            builder.setMessage(getString(R.string.no_devices_dialog_body));

            // add a button
            builder.setPositiveButton("OK", null);

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        else {
            if(dataVerification()) {
                // If verification is passed, then it should pass the datasets onto the next screen
                Intent intent = new Intent(DataSelectActivity.this, GraphActivity.class);
                intent.putExtra(getString(R.string.connections_extra_name), (Serializable) selected_connections);
                startActivity(intent);
            }
            else {
                // If the verification fails, then it should display an error message
                Log.e("Verification Failed", "nextScreen: The data verification failed");
            }
        }
    }

    private boolean dataVerification() {
        // TODO: This should conduct the data verification process.
        return true;
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
