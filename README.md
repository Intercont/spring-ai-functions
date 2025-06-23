# Spring AI Functions

This repository is a practical implementation of Spring AI with Java, providing functions to retrieve data from external APIs (such as weather and stock APIs) and feed it into OpenAI prompts. The goal is to enable applications to deliver intelligent, up-to-date, real-time data to users by leveraging AI capabilities with live data sources.

## Project Goals

- Integrate Spring AI with Java to build intelligent applications.
- Retrieve and process data from external APIs (on this example, for weather and stock prices).
- Feed external data into OpenAI models for enhanced, context-aware responses.
- Provide a practical foundation for real-time data and AI integration.

## Technologies Used

The project is built with the following core technologies:

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring AI 1.0.0**
- **OpenAI Model Integration via Spring AI**
- **Lombok** (for reducing boilerplate code)
- **Jackson Databind** (for JSON serialization/deserialization)
- **Spring Boot Starter Web** (for building RESTful APIs)
- **Spring Boot Starter Test** (for testing)

All dependencies and versions are managed via Maven. See [`pom.xml`](pom.xml) for details.

## Getting Started

### Prerequisites

- **Java 21** or higher installed
- **Maven** 3.8+ installed.
- An **OpenAI API Key**

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Intercont/spring-ai-functions.git
   cd spring-ai-functions
   ```

2. **Configure your environment:**
   - Set your OpenAI API key in your environment variables or in your IDE if you are running from it:
   ```bash
    export OPENAI_API_KEY=YOUR_OPENAI_API_KEY
    ```
    - Set your Api Ninjas API key in your environment variables or in your IDE if you are running from it:
   ```bash
    export API_NINJAS_KEY=YOUR_API_NINJAS_KEY
    ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

5. **Access APIs:**
   - The application will start on [http://localhost:8080](http://localhost:8080) by default.
   - Explore the exposed endpoints for retrieving real-time data and AI-powered results.

## Contributing

Contributions are welcome! Please open issues or pull requests to help improve the project.

## License

This project is provided for educational and practical purposes. See the repository for license details.

---

**Maintainer:** [Intercont](https://github.com/Intercont)

*Built with [Spring Boot](https://spring.io/projects/spring-boot) and [Spring AI](https://github.com/spring-projects/spring-ai).*

**Subscribe at [igorfragadev.com](https://igorfragadev.com) for more**
