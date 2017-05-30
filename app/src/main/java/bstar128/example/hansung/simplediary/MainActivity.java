package bstar128.example.hansung.simplediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText edit;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date=(DatePicker)findViewById(R.id.datep);
        edit=(EditText)findViewById(R.id.edit_1);
        but=(Button)findViewById(R.id.but_1);
    }
}
