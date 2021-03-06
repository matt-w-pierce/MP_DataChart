package com.mattpierce.mwp_datachart.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.mattpierce.mwp_datachart.R;
import com.mattpierce.mwp_datachart.objects.DatasetConnection;

import java.util.List;

public class GraphActivity extends AppCompatActivity {

    private List<DatasetConnection> datasets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        // TODO: File layout
        /*  1. Read in the VALIDATED datasets that were passed from previous
            2. Figure out how many datasets there are
            3. Read the data from the datasets into arrays or DataPoints
            4. Figure out what the min/max values from ALL of the datasets
            5. Create the graph using the min/max values for x and y axis
            6. Populate the graph with the values from all of the datasets
            7. Provide a settings menu that does the following:
                a. Allows for the scaling of the graph, both x and y-axis
                b. Be able to turn on/off any of the included datasets
        */

        // Reading in the validated datasets that were passed from previous
        final Intent intent = getIntent();
        datasets = (List<DatasetConnection>) intent.getSerializableExtra(getString(R.string.connections_extra_name));

        initializeGraph();

        // TODO: Read in the data from the dataset connections

    }

    private void initializeGraph() {
        // TODO: Initialize charts
    }
}
