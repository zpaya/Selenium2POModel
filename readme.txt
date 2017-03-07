# Configure project properties as below
path: /dubizzle/src/test/com/dubizzle/config/properties/project.properties
testData path: /testdata
testcases = /dubizzle/src/test/com/dubizzle/aut/testcase

## WEB ##
browser (Firefox, Chrome)
URL (Web Application under test)
URI (Webservice API base URL)
#Run testng.xml Suite using maven
>mvn test

#Run individual testng.xml and testng-api.xml suite from eclipse.

## ANDROID ##
#Android-SDK should be installed in your system and Environment variable should be set.
Specify android mobile device configuration setting 
deviceName (adb devices) 
platformVersion (ANDROID OS version)

#Run Test Case:
'/dubizzle/src/test/com/dubizzle/aut/testcase/AndroidTest.java' as TestNg Test

