package co.com.ceiba.mobile.pruebadeingreso.view;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import co.com.ceiba.mobile.pruebadeingreso.PageObject;
import co.com.ceiba.mobile.pruebadeingreso.R;


public class MainActivityTest {

    private PageObject pageObject;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);


    @Before
    public void before() {
        pageObject = new PageObject();
    }


    @Test
    public void filterList() throws InterruptedException {
        pageObject.sleep(3);

        pageObject.writeEdit(R.id.editTextSearch, "Leanne");
        pageObject.matchEdit(R.id.editTextSearch, "Leanne");
    }
}