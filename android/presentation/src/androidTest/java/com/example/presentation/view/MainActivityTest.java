package com.example.presentation.view;

import androidx.test.rule.ActivityTestRule;

import com.example.presentation.PageObject;
import com.example.presentation.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


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

        pageObject.escribirEdit(R.id.name, "Leanne");
    }
}