Feature: CucumberJava

  Scenario: Verify that search for a lesson on nagwa website then opening the lesson worksheet to count the number of questions in this worksheet

    Given I have open the browser

    And I open Nagwa website

    And I Choose Website Language

    And I Click on search icon

    And I Enter "lesson_name" on search field

    And I Click on Search to Search for "lesson_name"

    When Verify All Lessons Contain "lesson_Name"

    And All Lessons that display after search successfully

    And I click on 2nd Lesson

    And I Click on Preview All Questions

    Then All Questions Appear

    And Count Questions Number