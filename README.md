### Overview 👇🏻
A short coding kata exercise followed along with TDD design demonstrating production-ready Spring Webflux application.


#### Spring Webflux 🧲
Spring Webflux is used for building reactive Spring applications i.e. non-blocking (asynchronous) in nature. Typically in I/O based (blocking) framework, application waits until task is finished before handing other task (in-queue) which makes the entire application slow and less responsive.

By making the nature non-blocking, application can handle multiple concurrent requests at a time which increases the responsiveness of the entire application making it more scalable at a very high distributed level for handling thousands of multiple concurrent requests at a time. Spring Webflux is used for building asynchronous Spring applications.

#### Reactor Kotlin ⚙️
Reactor Kotlin is an advanced asynchronous programming library designed for Kotlin, built on top of Reactor Core, which is a reactive programming library for the JVM. It enables developers to write non-blocking, event-driven applications that can handle concurrent operations efficiently.

It supports:
- Reactive Streams (Mono)
- Asynchronous operations
- Error Handling
- Backpressure Handling
- Functional Programming

##### Dependencies used 🔧:
- Spring Boot 3.1
- Spring WebFlux
- Reactor Core
- Reactor Kotlin extensions
- JUnit
- Mockito
- MockK
- Spring JPA
- Reactor Test
- Netty
- Gradle
- SpringMockk
- H2 Database
