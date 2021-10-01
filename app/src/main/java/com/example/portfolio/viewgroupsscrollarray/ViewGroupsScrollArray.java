package com.example.portfolio.viewgroupsscrollarray;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portfolio.R;

import java.util.ArrayList;
import java.util.List;

public class ViewGroupsScrollArray extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    private static final String TAG = ViewGroupsScrollArray.class.getSimpleName();
    private static final String text = "ScrollView: A view group that allows the view hierarchy placed " +
            "within it to be scrolled. Scroll view may have only one direct child placed within it. " +
            "To add multiple views within the scroll view, make direct child you add a view group, " +
            "for example Linear Layout, and place additional views within that LinearLayout.";

    private Spinner spinner;
    private List<String> dataSource;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_groups_scroll_array);
        TextView textView = (TextView) findViewById(R.id.textScrollView);
        spinner = (Spinner) findViewById(R.id.spinner);

        String newText = text;
        for (int i = 1; i <= 5; i++) {
            newText += "\n\n" + text;
        }

        textView.setText(newText);

        dataSource = getSource();
        arrayAdapter = getAdapter();
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

    }

    private List<String> getSource() {
        List<String> androids = new ArrayList<String>();
        androids.add("Cupcake");
        androids.add("Donut");
        androids.add("Gingerbread");
        androids.add("Eclair");
        androids.add("KitKat");
        androids.add("Lollipop");
        androids.add("Marshmallow");
        androids.add("Nougat");
        androids.add("Oreo");
        androids.add("Pie");

        return androids;
    }

    private ArrayAdapter<String> getAdapter() {
        return new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataSource);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "Item selected: " + position);
        Toast.makeText(this, "Selected: " + dataSource.get(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}