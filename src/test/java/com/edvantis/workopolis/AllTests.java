package com.edvantis.workopolis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ VacanciesControllerTest.class, HomeControllerTest.class, LoginControllerTest.class })
public class AllTests {

}
