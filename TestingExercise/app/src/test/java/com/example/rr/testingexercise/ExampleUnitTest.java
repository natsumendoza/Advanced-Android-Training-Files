package com.example.rr.testingexercise;

import android.util.Log;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void date_isCorrect() {

        String expectedDate = "1987/12/03";
        String actualDate = "1987/12/04";

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = null;
        Date date2 = null;

        try {
            date1 = format.parse(expectedDate);
            date2 = format.parse(actualDate);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(date1, MainActivity.getFormattedDate("1987/12/03"));

    }

    @Test
    public void date_isFormattedSimple() {

        String expectedDate = "December 03, 1987";
        String actualDate = "1987/12/03";

        try {
            assertEquals(expectedDate, MainActivity.getSimpleFormattedDate(new SimpleDateFormat("yyyy/MM/dd").parse(actualDate)).toString());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}