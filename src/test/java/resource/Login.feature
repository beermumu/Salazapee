# new feature
# Tags: optional
    
Feature: Login to admin page

    Background: start up the application
        Giveen the application is running

    
    Scenario: When Username entered
        When I enter "B" as Username
        And enter "B" as Password
        And enter I press login button
        Then I Should see adminpage

    Scenario: When Admin entered
        When I enter "A" as Username
        And enter "A" as Password
        And I press Login button
        Then I should see product list