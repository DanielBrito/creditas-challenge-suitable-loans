# Loan Type

[Backend challenge from Creditas](https://github.com/Creditas/challenge/tree/master/backend/code-challenges/kotlin) to check suitable loans for a person based on its characteristics.

## Motivation

The main purpose for working on this challenge is to improve my development skills (clean code, design patterns, TDD, DDD etc.), because I've been into too much theory, and I almost don't put that knowledge into practice.

I've implemented the requirements of the challenge and also complemented with integration and mutation tests, as well as linting, code quality and coverage validations. All of these validations run in a [CI/CD pipeline](https://github.com/DanielBrito/creditas-challenge-suitable-loans/actions) that fails when some of the requirements isn't achieved.

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

- Building with Docker

TODO (commands)

- Running the application

TODO (commands)

- Making a request

TODO (curl)

### Validations

#### Unit Testing

TODO (screenshot)

#### Mutation Testing

TODO (explanation and screenshot)

#### Integration Testing

TODO (screenshoot)

#### Quality Assurance

TODO (screenshot)
