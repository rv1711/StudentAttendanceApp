package abrv0765.studentattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StudentList extends AppCompatActivity {

    Button pres;
    Button absent;
    TextView attend,name,facno,enno;
    TextView fill;
    BufferedReader br;
    ImageButton next,prev;
    int iterator=0;int len=0;
    students r;
    String fd;
    File myExternalFile;
    ArrayList<students> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_student);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        fd = df.format(c);

        name = (TextView)findViewById(R.id.name_student);
        facno = (TextView)findViewById(R.id.stud_fac);
        enno = (TextView)findViewById(R.id.stud_enno);
        pres = (Button)findViewById(R.id.stud_present);
        absent = (Button) findViewById(R.id.stud_absent);
        attend = (TextView)findViewById(R.id.percent_attend);
        prev = (ImageButton) findViewById(R.id.prev);
        next = (ImageButton) findViewById(R.id.next);

        String filename = "student.txt";
        String filepath = "MyFileStorage";
       myExternalFile =new File(getExternalFilesDir(filepath), filename);
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(in));

            String str;
            while ((str=br.readLine())!=null)
            {
                formatString(str);
                len++;
            }
            in.close();

        } catch (IOException e) {
            Log.e("GFG","Not opening");
        }


        getValues(iterator);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iterator<len-1) {
                    iterator++;
                    getValues(iterator);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Last Student",Toast.LENGTH_LONG).show();
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iterator>0) {
                    iterator--;
                    getValues(iterator);
                }
                else {
                    Toast.makeText(getApplicationContext(),"First Student",Toast.LENGTH_LONG).show();
                }
            }
        });

        pres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r.setPres();
                list.set(iterator,r);
                getValues(iterator);
                up();
            }
        });
        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r.setAbsent();
                list.set(iterator,r);
                getValues(iterator);
                up();
            }
        });

    }


    void formatString(String dr)
    {
        String str="";
        int i=0;
        int temp=0;
        fill = name;
        String n="",f="",e="",fd1;
        int p=0,a=0;
        while(i<dr.length())
        {
            if(dr.charAt(i)=='_')
            {
                temp++;
                if(temp==1)
                {
                    n=str;
                }
                else if(temp==2)
                {
                    f=str;
                }
                else if (temp==3)
                {
                    e=str;
                }
                else if (temp==4)
                {
                    p= Integer.parseInt(str);
                }
                else if (temp==5)
                {
                    a=Integer.parseInt(str);
                }
                str = "";
                i++;
                continue;
            }
            str = str+dr.charAt(i);

            i++;
            if (i==dr.length())
            {
                fd1=str;
                list.add(new students(n,f,e,p,a,fd1));
            }
        }
    }
    void getValues(int i)
    {

        r=list.get(i);
        name.setText(""+r.getName());
        facno.setText(""+r.getFac());
        enno.setText(""+r.getEn());
        pres.setText(""+r.getPres());
        absent.setText(""+r.getAbsent());
        attend.setText(""+r.getPer());
    }
    String convert()
    {
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<len;i++)
        {
            String jh="";
            students f;
            f=list.get(i);
            jh = f.getName()+"_"+f.getFac()+"_"+f.getEn()+"_"+f.getPres()+"_"+f.getAbsent()+"_"+f.getDate();
            sb.append(jh);
            sb.append("\n");
        }
        return sb.toString();
    }

    void up()
    {

        try {
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            String query;
            query = convert();
            fos.write(query.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
