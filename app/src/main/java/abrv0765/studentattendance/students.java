package abrv0765.studentattendance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by risha on 04/03/2018.
 */

public class students {
    String name,fac,en,date;
    int pres,absent;
    int tempp=0,tempa=0;
    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    String fd = df.format(c);
    students(String n,String f,String e,int p,int a,String da)
    {
        name=n;fac=f;en=e;pres=p;absent=a;date = da;
    }
    void setPres()
    {

        if (absent>0&&tempp==0) {
            if (fd.equals(date)) {

                absent--;
            }
            date = fd;
            pres++;
            tempp=1;
            tempa=0;
        }
    }
    void setAbsent()
    {
        if (pres>0&&tempa==0) {
            if (fd.equals(date)) {
                    pres--;
            }
            date = fd;
            absent++;
            tempa=1;
            tempp=0;
        }
    }
    String getName()
    {
        return name;
    }
    String getFac()
    {
        return fac;
    }
    String getEn()
    {
        return en;
    }
    int getPres()
    {
        return pres;
    }
    int getAbsent()
    {
        return absent;
    }
    int getPer()
    {
        int k;
        k=(pres)*100/(pres+absent);
        return k;
    }
    String getDate()
    {
        return date;
    }

}
