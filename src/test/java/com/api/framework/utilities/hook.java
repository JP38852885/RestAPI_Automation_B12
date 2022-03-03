package com.api.framework.utilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.*;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class hook {

    @Before
    public void beforeScenario(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri(commonMethods.readProperties("config/Config.properties").getProperty("baseURI")).
                setBasePath(commonMethods.readProperties("config/Config.properties").getProperty("basePath")).
                setContentType("application/json;charset=utf-8").addHeader("Authorization", "Bearer"+"4b3f5a3e-2fba-4a78-bf49-68fc136073c0").setPort(8083).log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

    }

    @After
    public void afterScenario(){
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();

    }
}
