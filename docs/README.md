# SidewayQR
SidewayQR is a minimalist QR attendance tracking application. Built using:

- Android Studio Koala (v2024.1.1)
- Jetpack Compose (with Kotlin v1.9)
- Material 3
- Material 3 Icons
- Quickie (QR Code Scanning Library)
- Retrofit

## About
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

## Screenshots & Demonstrations

## Acknowledgement

