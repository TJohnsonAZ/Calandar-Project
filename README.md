# "Project Calandar" Project
The "Project Calandar" Project is a calendar based activity tracker that allows a user to create an event and track completion or incompletion on a daily basis. The goal is to increase productivity through daily tracking and provide a very visual output of how productive completing a specific task is.

### Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

#### Prerequisites
Android application APK download can be found at:
https://github.com/TJohnsonAZ/Calandar-Project/raw/main/Deployment/CalandarApp_D4_Release.apk

##### Android
Navigate to the APK (downloads is default) and click to install

##### Windows/Mac 
An Android emulator is necessary for running the application on these operating systems. Android Studio IDE can also run it.
https://developer.android.com/studio
https://www.bignox.com

#### Installing
Android Studio IDE is necessary for modifying the application code (https://developer.android.com/studio). Once downloaded and the repository is cloned, open the Calandar App folder within the IDE. Gradle dependencies will have an update option pop up if Gradle has not been used before. Once updated you will be able to work on the Android side of the application.

The backend requires a JAVA IDE (Eclipse/IntelliJ/etc) and Maven for packaging into a jar file. Once in the IDE, import the CalandarServerv1 folder as an existing Maven project. After changes are made and saved, the project can be packaged on the command line by navigating into the CalandarServerv1/v1 folder, and typing "mvn clean install". After Maven runs, the .jar (not original) can be found within the target folder.

### Deployment
Deployment of backend is on a remote server and communicates via HTTP requests sent from the Android app. Android app is currently downloadable for phone use through the Deployment folder under CalandarApp_D4_Release.apk

### Built With
- Android Studio - IDE used for Android Application Creation
- Eclipse - IDE used for Spring Framework Build 
- Gradle - Package Management system used in the APK build
- Maven - Package Management system used in the Spring BOOT build
- Spring BOOT - Framework used to handle HTTP requests sent by app

### Contributing
Please read [CONTRIBUTING.md](https://github.com/TJohnsonAZ/Calandar-Project/blob/main/CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

### Versioning
For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

### Authors
  - Calvin Harper - Android App
  - Zach Kaufman - Android App
  - Kadan Seward - Spring Boot Server
  - Trevor Johnson - Spring Boot Server
  - Adam Larson - Spring Boot Server
  - Logan ODonnell - Android App

### License
This project is licensed under the GNU General Public License - see the [LICENSE.md](LICENSE.md) file for details

### Acknowledgements
   - Zach helped Calvin and Logan get started with android studio. His knowledge was a great push in the right direction
   - Adam helped the group get started with spring boot which cut training time down immensely.
   - Adam provided the server that we used to set up our remote server used by the app.
