# RetailStoreDiscountSystem
Retail Discount System

##  Purpose
To calculate discount on items
1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.

##  Build Process
* Get the clone of the code from the url ="https://github.com/VJCOGNIZANT/RetailStoreDiscountSystem".
* Open Command Promt from your system and go to the project resource.
* Use "mvn clean install -U" cammand to build your application.

## Test
* DiscountCalculatorImplTest class covers all the possible scenerios.

## COde Coverage
* Install eclema to your IDE.
* Right clic k and choose coverage as JUnit to see the code coverage.

## Sonar Report Generation
* Install sonar cube locally, run the instance and open "http://localhost:9000".
* Installl sonarLint from marketplace and restart IDE.
* Open your project in command prompt and run "mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dmaven.test.failure.ignore=true" command to run sonar on the code.
*Bind the project to Sonar cube using Sonar List and Run Sonar Lint Analyze on the project to get sonar report.

