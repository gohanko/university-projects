# sidewayqr-api
![example workflow](https://github.com/gohanko/sidewayqr-api/actions/workflows/test.yml/badge.svg)

Basic demonstration REST API for QR attendance tracking mobile application. Built using Express.js.

## Getting Started


## Folder Structure
The application is structured as the following:

- **configs/** - Stores application configs, including loading from .env files.
- **controllers/** - Code that implements business logic.
- **fixtures/** - Stores json files used to artificially populate the database.
- **middlewares/** - Code to extend the functionality of Express (e.g must authorize to - view, input validation)
- **models/** - SQL models.
- **routes/** - Files to define URLs and connect them to a controller.
- **services/** - Code to initiate certain external services such as an ORM instance to access a database.
- **tests/** - Tests to make sure the entire project is working as expected.

## Features
The REST API implements the following features:

- Basic Authentication
    - Register
    - Login
    - Logout
    - Change Password
- Authorization (through JSON Web Tokens)
- Event
    - Create
    - Read
    - Update
    - Delete
- Event QR (generate and serves a QR code)
- Event Participation
    - Join (to notify you’re interested in joining)
    - Leave
    - Attend (actually signifies that you scanned the QR code and is physically there)