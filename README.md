# Loan Type

[Backend challenge from Creditas](https://github.com/Creditas/challenge/tree/master/backend/code-challenges/kotlin) to check suitable loans for a person based on its characteristics.

## Motivation

The main purpose for working on this challenge was to improve my development skills (clean code, design patterns, TDD, DDD etc.), because I've been into too much theory, and I almost don't put that knowledge into practice.

So, I've implemented the requirements of the challenge and also complemented with integration* and mutation tests, as well as linting*, code quality* and coverage* validations. All of these validations (*) run in a [CI/CD pipeline](https://github.com/DanielBrito/creditas-challenge-suitable-loans/actions) that fails when some of the requirements isn't achieved, blocking the merge into the main branch.

To manage the implementation of this application, I've created a [GitHub Project](https://github.com/users/DanielBrito/projects/5/views/1) to add tasks, and work on them by following a Kanban approach.

## Tech Stack

Besides using **Kotlin** along with **Spring Framework Web**, I also included some other important tools:

- [Docker](https://www.docker.com/): To containerize the application
- [Pitest](https://pitest.org/): For mutation testing
- [Detekt](https://detekt.dev/): For linting validation
- [Mockk](https://mockk.io/): For test doubles
- [AssertJ](https://assertj.github.io/doc/): For test assertions
- [JaCoCo](https://www.eclemma.org/index.html): For test coverage
- [SonarQube](https://www.sonarsource.com/products/sonarcloud/): For quality assurance

## Running Locally

Inside the `loan` folder execute the following commands:

- Build the project and generate the `jar` file:
```shell
./gradlew clean build
```

- Build the Docker image:
```shell
docker build -t loan .
```

- Run the Docker container
```shell
docker run -p 8080:8080 loan
````

- Making a request:
```shell
curl --request POST \
  --url http://localhost:8080/suitable-loans \
  --header 'Content-Type: application/json' \
  --data '{
  "customer": {
    "name": "Daniel",
    "cpf": "123.456.789-10",
    "age": 30,
    "location": "SP",
    "income": 4000.0
  }
}'
```

- Stopping the application:
```shell
# To get the container id or name
docker ps
```

```shell
# To stop the container
docker stop <id or name>
```

### Validations

#### Linter

```shell
./gradlew detekt
```

#### Unit Testing

```shell
./gradlew test
```

#### Mutation Testing

This project was an excellent opportunity to explore how mutant tests work. It was challenging to kill all of them, but I did my best for the moment.

```shell
./gradlew pitest
```

#### Integration Testing

```shell
./gradlew integrationTest
```

#### Coverage

```shell
./gradlew jacocoTestReport
```
