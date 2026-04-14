# Project: FlashyCard

FlashyCard is a CLI-based flashcard application designed for power users and fast typists to facilitate efficient content memorization.

## Summary of Contributions

### Technical Enhancements

**Architectural & OOP Design**

- Designed the application's Object-Oriented structure, enforcing a strict separation of concerns between the Model (data), UI (interaction), Parser (logic), and Storage (persistence).

- Developed the project’s initial skeleton code, defining abstract classes, interfaces, and custom exceptions to allow the team to begin parallel development immediately by providing a stable foundation.

**Parsing Framework**

- Designed and implemented a regex-based parsing system to process user input.

- Developed the `CommandParser` logic and specific implementations for the `add`, `view`, and `delete` parsers, ensuring malformed input is handled gracefully through custom exception handling.

**In-Memory Storage**

- Implemented the `KnowledgeBase` class to provide an abstraction for managing flashcard data structures.

**Core Logic (CRUD)**

- Built the underlying logic for executing core commands, specifically the addition, viewing, and deletion of cards within the system.

### Documentation Contributions

**User Guide (UG)**

- Authored the initial draft of the User Guide, providing clear documentation for all basic CLI commands.

**Developer Guide (DG)**

- Contributed to the Architecture section, detailing how the `FlashyCard` main class coordinates interactions between the various system components.

### Team-Based Tasks

- **Integration**: Completed the core OOP design and skeleton code early in the lifecycle to minimize bottlenecks and provide a clear roadmap for the team.

- **Maintenance & Standards**: Adhered to coding standards and style guidelines across the repository and updated the Gradle build configuration to maintain environment consistency.

- **Bug Triage & Resolution**: Helped and managed the triaging and fixing of reported bugs.
