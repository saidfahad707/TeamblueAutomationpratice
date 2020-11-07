@teamblue 
Feature: Validate login function and verify pricing with shipping cost at checkout in my Store 

Scenario: User abul to validate login funciton and verify pricing cost at different page
	Given user open Webbrowser and navigate to My Stor login page 
	Then user Click on the Sign-in button, 
	And user enter valid username and password
	And user submit Sign-in button
	And user You will get a home page; on the home page verify page title 
	Then user Click on the upper left corner (Dresses) link, below displayed showing 5 items; 
	And user Print all the price values in sorted order (descending order) on the console 
	And user Select the second dress on that list; remember the price (the list may change so it is dynamic) 
	When user On the next page click on Proceed to checkout 
	Then user On the next page verify there is a Total price with shipping, close the window and logout 	