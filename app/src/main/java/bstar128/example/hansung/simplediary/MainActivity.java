package bstar128.example.hansung.simplediary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText edit;
    Button but;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date=(DatePicker)findViewById(R.id.datep);
        edit=(EditText)findViewById(R.id.edit_1);
        but=(Button)findViewById(R.id.but_1);
        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    FileOutputStream fOut = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str=edit.getText().toString();
                    fOut.write(str.getBytes());
                    fOut.close();
                    Toast.makeText(MainActivity.this,"정상적으로"+fileName+"파일이 저장되었습니다.",Toast.LENGTH_LONG).show();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Calendar cal=Calendar.getInstance();//현재 참조값
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DATE);//date of month도 됨

        date.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                fileName=year+"_"+(month+1)+"_"+day+".txt";
                String data=readDiary(fileName);
                edit.setText(data);
                but.setEnabled(true);
            }
        });
    }
    public String readDiary(String fileName){
        String diaryStr=null;
        FileInputStream fIn=null;
        try {
            fIn = openFileInput(fileName);
            byte[]buf=new byte[500];
            fIn.read(buf);
            diaryStr=new String(buf).trim();
            but.setText("수정");
            fIn.close();
        }catch (FileNotFoundException e){
            edit.setHint("일기가 존재하지 않습니다.");
            but.setText("새로 저장");
        }catch (IOException e){

        }

        return diaryStr;
    }

}
