# web-token-generation-validation

## Instructions

Please, develop a web-based application that allows you to generate tokens and validate them.

Token has a format of XXXX-XXXX-XXXX-XXXX, and consists of the 0-9 digits.

User is able to choose which digits are available for token generation in the UI, e.g. available digits are: 2,4,7,9,0 -
it means that token can consist only of them, e.g. 2249-4472-0279-9420

User is able to initiate token creation via UI, in this case available digits are being passed to the GeneratorService
and created token is displayed on UI. Generator creates a token randomly based on available digits provided.

User is able to validate the created token via UI, in this case the token is being passed to ValidatorService and the
result of validation is displayed in UI. Validator uses Lunh algorithm to check if token is valid or
not: https://en.wikipedia.org/wiki/Luhn_algorithm

Tech requirements:

FrontEnd - is React application

GeneratorService, ValidatorService - are SpringBoot applications

Communication is via REST API

Code should be placed into public repo (e.g. GitHub) and link needs to be provided.

Repository structure:

/frontend/..

/generator/..

/validator/..

start.sh

start.bat

Running start.sh (or start.bat for MS Windows) script should bring up all services and make FE available on localhost:
8080

## Assumptions and Info

### General

* Each spring service is separate
* The generator service has 100% test coverage(with an integration test as well), the validator has only unit testing
  for util only given it's the same
  logic

### Generator

* Token digits are optional, default is to use 0-9
* Token digits must be single digits, otherwise bad request is returned

### Validator

* Returns bad request in case token is invalid, otherwise 'true' or 'false' strings

### Frontend

* Uses nextjs with react
* Uses CORS to be able to communicate to the generator and validator services
* Has basic UI using material UI
* Tested using chrome
* Added unittesting that uses react library and user events to validate basic scenarios
* Images for ui state

