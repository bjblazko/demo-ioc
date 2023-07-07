# Demo: Dependency Injection (DI) / Inversion Of Control (IOC)

This demo project uses Kotlin on the JVM, utilising Jakarta CDI using Quarkus.
Here, we demonstrate several techniques on performing depdendency injection (or how to
avoid it). have a look in these packages:

- `withinf`: the classic approach where we use interfaces and have multiple implementations (here: one for production and one for testing)
- `withmock`: also, classic approach where there is nit interface but a single implementation which is mocked in parts during test
- `bystructure`: shows a way often forgotten - avoid classic dependency trees in which a service is controlling a repository - often it is okay to have the service only have business logic and put entities in - leaving it to the controller to delegate retrieving and persisting
- `withfunc`: using higher-order functions passed as arguments, so behaviour can be passed by the caller

Depending on the package, you have to look into the tests or the `~Resource` class
to see the trick.