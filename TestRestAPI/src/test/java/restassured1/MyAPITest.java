package restassured1;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MyAPITest {

    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeClass
    public void setUp(){
        ExtentSparkReporter httmlReporter=new ExtentSparkReporter("reports/testReport.html");
        extent=new ExtentReports();
        extent.attachReporter(httmlReporter);
    }

    @Test(priority=1)
    public void testPost(){
        HashMap hm =new HashMap();
        hm.put("name","Pradeep");
        hm.put("email","p@gmail.com");
        hm.put("phone","900089098");
        hm.put("city","Kolkata");

        String[] courses={"Python","Java"};
        hm.put("courses",courses);
        test=extent.createTest("httpGetRequest").assignCategory("POST");
       Response response =given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("http://localhost:3000/employee");


        test.log(Status.PASS,"Employee created successfully");
        test.log(Status.INFO,"Response Header: "+response.getHeaders());
        test.log(Status.INFO,"Response Body: "+response.getBody());
    }
    @Test(priority=2)
    public void testGet(){
        test=extent.createTest("httpGetRequest").assignCategory("GET");

        Response response=given()
                .when()
                .get("http://localhost:3000/employee/Pradeep");


        test.log(Status.PASS,"Employee retrieved successfully");
        test.log(Status.INFO,"Response Header: "+response.getHeaders());
        test.log(Status.INFO,"Response Body: "+response.getBody());
    }

    @Test(priority=4)
    public void deleteResource(){
        test=extent.createTest("httpGetRequest").assignCategory("DELETE");
        given()
                .when()
                .delete("http://localhost:3000/employee/2fXL9PcF6E4");
        test.log(Status.PASS,"Employee Deleted successfully");
    }


    @AfterClass
    public void tearDown(){
        extent.flush();
    }

}
