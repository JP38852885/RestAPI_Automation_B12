package com.api.framework.stepDefinitions;

import com.api.framework.utilities.DBUtility;
import com.api.framework.utilities.commonMethods;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import pojos.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class employeeStepDef {
    DBUtility dbUtility=new DBUtility();
    ResultSet resultSet=null;
    Response response=null;
    @Given("i enter employeeGender value as {string} digits with {string} request")
    public void iEnterEmployeeGenderValueAsDigitsWithRequest(String inputValue, String method) {
        Faker faker = new Faker();
        String token="54cbee8d-3bdc-49a7-81b8-ae6e665a1541";

        if(method.equalsIgnoreCase("GET")){

        }else if(method.equalsIgnoreCase("POST")){
            Employee employee=Employee.builder().
                    employeeId(18).
                    employeeFirstName(faker.name().firstName()).
                    employeeLastName(faker.name().lastName()).
                    employeeGender(inputValue).
                    employeePhone("1234567890").
                    isNewEmployee(true).build();
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

    }
}
