package core;

import com.github.dvdme.ForecastIOLib.*;

public class Weather {
    public Weather() {
        ForecastIO fio = new ForecastIO("a8537e2c9e7fc7f26911f0851c3f96ff");
        fio.setUnits(ForecastIO.UNITS_SI);
        fio.setLang(ForecastIO.LANG_RUSSIAN);
        fio.getForecast("50.3268766", "26.6384447");


        FIOCurrently currently = new FIOCurrently(fio);
        //Print currently data
        System.out.println("\nCurrently\n");
        String [] f  = currently.get().getFieldsArray();
        for(int i = 0; i<f.length;i++)
            System.out.println(f[i]+": "+currently.get().getByKey(f[i]));

    }
}
