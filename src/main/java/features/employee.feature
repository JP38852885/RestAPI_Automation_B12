Feature: testing employee api

  #Description: testing functionality of Employee API
  #Story: 78578

  @positive, @Automatable, @regression
  Scenario: Testing employee API - API Chaining - Dependency Injection - Serialization & Deserialization
    Given i enter employeeGender value as "male" digits with "POST" request
    Then i verify Status code is 201
    And i verify response time is below 5 sec
    Given i enter employeeGender value as "24" digits with "GET" request
    And i verify response message as "Employee has been saved successfully!"



