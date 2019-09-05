# Getting Started


##### Basic Explained

| architecture      | description                   |
|---                | ---                           |
| Model             | Response / Entity             |
| View              | Activity / Fragment (UI)      |
| ViewModel         | Business Logic                |

##### App Structure Explained
     Model            
     
     1. FlightDetailData- Holds the response data 
     2. Flight Response- Gets the flight api response and converts the json object to java objects using Gson of retrofit and sets in FlightDetailData
     
   
    View           
     
     1. MainActivity- Launcher activity uses splash screen for time 2 secs and then calls FlightActivity
     2. FlightActivity- Main activity of project responsible for interaction between viewmodel.Using Flight flightview model to set data.
     3. FlightAdapter- Adapter class sets the content using binding by ItemFlightViewModel
     
     View Model
     
     1. FlightViewModel- Business logic for getting data from api, sending it to activity which in turns sends it to adpater by binding it with ItemFlightViewModel
     2. ItemFlightViewModel- Business logic to update the Ui of adapter.
     
#### Steps to Install app
1. Copy apk from apk of folder of project and paste in your local
2. Run command on terminal adb install apk-file path
     


