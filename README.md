# appreview
Spring boot test and proof of concept application.

## Requirements
* Java JDK8
* Gradle

## Usage
Clone, build and run the application:
```
git clone https://github.com/skerkewitz/appreview.git
cd appreview
git checkout develop
gradle bootRun -Pargs="--appStoreAppId=$YOUR_APP_ID$"
```

Replace $YOUR_APP_ID$ with your Apple iOS AppStore ID.
