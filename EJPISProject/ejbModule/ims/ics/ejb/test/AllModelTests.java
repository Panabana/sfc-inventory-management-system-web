package ims.ics.ejb.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ CustomerTest.class, EmployeeTest.class, PurchaseTest.class })
public class AllModelTests {

}
