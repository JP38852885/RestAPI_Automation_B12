package com.api.framework.stepDefinitions;

import com.api.framework.utilities.DBUtility;
import com.api.framework.utilities.TestContext;
import com.api.framework.utilities.commonMethods;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import pojos.Employee;
import pojos.GETResponse;
import pojos.POSTResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class employeeStepDef {
    TestContext testContext=new TestContext();
    DBUtility dbUtility=new DBUtility();
    ResultSet resultSet=null;
    Response response=null;
    @Given("i enter employeeGender value as {string} digits with {string} request")
    public void iEnterEmployeeGenderValueAsDigitsWithRequest(String inputValue, String method) {
        Faker faker = new Faker();
        String token="4b3f5a3e-2fba-4a78-bf49-68fc136073c0";
        if(method.equalsIgnoreCase("GET")){
        GETResponse getResponse=given().header("Authorization", "Bearer"+token).expect().defaultParser(Parser.JSON).
        when().get("http://localhost:8083/employee/find/by-id?id="+Integer.valueOf(inputValue)).as(GETResponse.class);
       System.out.print( getResponse.getEmployeeFirstName());

       testContext.set("fNmaefromGetRes",getResponse.getEmployeeFirstName() );

        }else if(method.equalsIgnoreCase("POST")){
            Employee employee=Employee.builder().
                    employeeId(22).
                    employeeFirstName(faker.name().firstName()).
                    employeeLastName(faker.name().lastName()).
                    employeeGender(inputValue).
                    employeePhone("1234567890").
                    isNewEmployee(true).build();
            testContext.set("EmpFirstName", employee.getEmployeeFirstName());
            response = (Response) given().header("Authorization", "Bearer"+token).
                    body(employee).
                    when().
                    post("/add").
                    then().
                    extract().
                    body();

            System.out.print(response.asString());
        }else if(method.equalsIgnoreCase("PUT")){

        }else if(method.equalsIgnoreCase("DELETE")){

        }
    }

    @Then("i verify Status code is {int}")
    public void iVerifyStatusCodeIs(int statusCode) {
        Assert.assertEquals(response.statusCode(),statusCode);
    }

    @And("i verify response time is below {int} sec")
    public void iVerifyResponseTimeIsBelowSec(int resTime) {
        Assert.assertTrue(response.timeIn(TimeUnit.SECONDS)<resTime);
    }

    @And("i verify response with DB with {string}")
    public void iVerifyResponseWithDBWith(String query) throws SQLException {
       resultSet=dbUtility.executeQuery(query);
        JSONArray jsonArray=commonMethods.covertIntoJsonArray(resultSet);
        JSONObject jsonObject= (JSONObject) jsonArray.get(0);
       Assert.assertTrue(jsonObject.get("emp_firstname").toString().equalsIgnoreCase( "fn1"));
    }

    @And("i verify response message as {string}")
    public void iVerifyResponseMessageAs(String message) {
        String act=testContext.get("EmpFirstName");
        String exp=testContext.get("fNmaefromGetRes");
       Assert.assertEquals(act,exp);
    }
}
