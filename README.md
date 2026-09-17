# Registration and Login PoE — Part 1

Console-based Java application for user registration and login, built for PRLD5121.

## Features
- Username validation (must contain `_`, max 5 characters)
- Password complexity validation (8+ chars, uppercase, number, special character)
- South African cell number validation (`+27` prefix)
- Registration and login flow via console input
- Unit tested with JUnit 5

## Run
```
mvn test    # run unit tests
mvn exec:java -Dexec.mainClass="Main"   # run the console app
```
