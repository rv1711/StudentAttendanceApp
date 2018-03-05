package abrv0765.studentattendance;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_teacher);

        final TextView tch_name = (TextView)findViewById(R.id.teacher_name);
        TextView tch_course = (TextView)findViewById(R.id.teacher_course);
        TextView tch_courseno = (TextView)findViewById(R.id.teacher_courseno);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String fd = df.format(c);
        TextView date = (TextView)findViewById(R.id.date);
        date.setText(""+fd);


        String filename = "teacher.txt";
        String filepath = "MyFileStorage";
        StringBuilder text = new StringBuilder();
        File myExternalFile=new File(getExternalFilesDir(filepath), filename);
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;

            strLine = br.readLine();
            tch_name.setText(strLine);
            strLine = br.readLine();
            tch_course.setText(strLine);
            strLine = br.readLine();
            tch_courseno.setText(strLine);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button btn_p = (Button)findViewById(R.id.proceed);
        btn_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,StudentList.class);
                startActivity(i);

            }
        });

    }
}
