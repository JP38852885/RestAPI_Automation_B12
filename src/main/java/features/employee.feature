Feature: testing employee api

  #Description: testing functionality of Employee API
  #Story: 78578

  @positive, @Automatable, @regression
  Scenario: Testing employee API - employeeAdd request - EmployeeGender field value with 4 digits
    Given i enter employeeGender value as "male" digits with "POST" request
    Then i verify Status code is 201
    And i verify response time is below 5 sec
    And i verify response message as "Employee has been saved successfully!"

  @positive, @Automatable, @regression
  Scenario: Testing employee API - employeeAdd request - EmployeeGender field value with 6 digits
    Given i enter employeeGender value as 6 digits with "PUT" request
    Then i verify Status code is 200
    And i verify response time is below 5 sec
    And i verify response with DB with "select * from employee order by 'emp_id' Desc;"

