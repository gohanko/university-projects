# SidewayQR
SidewayQR is a minimalist QR attendance tracking application. Built using:

- Android Studio Koala (v2024.1.1)
- Jetpack Compose (with Kotlin v1.9)
- Material 3
- Material 3 Icons
- Quickie (QR Code Scanning Library)
- Retrofit

The application is a frontend for the API provided by [sidewayqr-api](https://github.com/gohanko/sidewayqr-api). Basically, the API software provides the functions of a QR attendance application but this mobile application provides an Android frontend to access the API.

It is built for a group assignment for UCCD3223 Mobile Applications Development at University Tunku Abdul Rahman.

## Features
- Basic Authorisation (Login/Logout/Register/Change Password)
- View events that user either created, or joined.
- User can create/read/update/delete events.
- User can join and leave an event (join means you're interested in going).
- User can scan a QR code to attend an event (attend means you're present).

For the backend implementation, please have a look at [sidewayqr-api](https://github.com/gohanko/sidewayqr-api).

## Getting Started
This section describes how to get started with building the application on our local machine.

### Prerequisites
Some software we should have installed before getting started:
Git (any version compatible with GitHub)
Android Studio Koala (v2024.1.1)
Older versions of Android Studio will have issues importing, building, and running the application due to different versions of Android Gradle Plugin (AGP). If one runs into this issue, a clean install, including deleting all the SDK files should be able to solve the issues.

### Installation
To get started and running:

- Clone this repository and import into Android Studio
  - Simply do: ```git clone git@github.com:gohanko/SidewayQR.git`````
  - Import into Android Studio
- Run it as per any other android projects.

There we go! You should be able to build and run the application locally.

### Folder Structure
Here is the how the source code is structured:
- **data/** - Contains code that holds user data during run time.
  - **api/** - Contains data classes related to API request and response.
  - **datastore/** - Contains code to access the SharedPreference DataStore.
  - **model/** - Contains code that holds data received from the API.
- **navigation/** - Contains Navigation Graphs for Android navigation.
- **network/** - Contains Retrofit networking code.
  - **interceptor/** - Contains interceptor for OkHTTP3. Mostly for retrieving and storing auth tokens, as well as apply the auth tokens when making calls (for authorized only endpoints).
- **ui/** - Contains code to UI components and different screens.
  - **composables/** - All reusable components live here. Business logic should be minimal.
  - **screens/** - Different screens (pages) of the application live here. Contains business logic related to the UI.
  - **theme/** - Has theme, theme manager, colors and font files.
- **utility/** - For business logic that is not UI related, and also random things.
- **viewmodel/** - Has API related viewmodels that hold data after calling the API.

## Screenshots & Demonstrations

### Screenshots
![alt text](images/Screenshot_20240903_175041.png)

### Video Demonstration
[![Watch the video](videos/demo.mp4)](videos/demo.mp4)

## Acknowledgement

