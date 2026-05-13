# Class Diagram

```mermaid
classDiagram
    class Main
    class Maze {
        -Room currentRoom
        -Player player
        -GameConfig config
        +move(char) boolean
        +interactWithCurrentRoom() String
        +lootCurrentRoom() String
        +exitCurrentRoom() String
    }

    class Room {
        <<abstract>>
        -String name
        +getDescription() String
        +getAdjoiningRoom(char) Room
    }

    class PuzzleRoom
    class TreasureRoom
    class SecretRoom
    class ExitRoom
    class HiddenExitRoom
    class DownSecretRoom

    class CommandStrategy {
        <<interface>>
        +execute(Maze) String
    }
    class MoveCommandStrategy
    class InteractCommandStrategy
    class LootCommandStrategy
    class ExitCommandStrategy
    class InventoryCommandStrategy
    class QuitCommandStrategy
    class CommandParser

    class GameConfig {
        <<Singleton>>
        -GameConfig INSTANCE
        +getInstance() GameConfig
    }

    class RoomCreator {
        <<abstract Factory Method creator>>
        +create(RoomType) Room
        #createRoom(RoomType) Room
    }
    class MazeRoomCreator
    class RoomType {
        <<enumeration>>
    }

    class ScoreObserver {
        <<interface Observer>>
        +scoreChanged(int, int) void
    }
    class ScoreAuditLog
    class Player {
        -int score
        -List~String~ inventory
        -List~ScoreObserver~ scoreObservers
        +addToScore(int) void
    }

    Main --> Maze
    Main --> CommandParser
    CommandParser --> CommandStrategy
    CommandStrategy <|.. MoveCommandStrategy
    CommandStrategy <|.. InteractCommandStrategy
    CommandStrategy <|.. LootCommandStrategy
    CommandStrategy <|.. ExitCommandStrategy
    CommandStrategy <|.. InventoryCommandStrategy
    CommandStrategy <|.. QuitCommandStrategy

    Room <|-- PuzzleRoom
    Room <|-- TreasureRoom
    Room <|-- SecretRoom
    Room <|-- ExitRoom
    ExitRoom <|-- HiddenExitRoom
    Room <|-- DownSecretRoom

    RoomCreator <|-- MazeRoomCreator
    Maze --> RoomCreator
    MazeRoomCreator --> RoomType
    Maze --> GameConfig

    Player --> ScoreObserver
    ScoreObserver <|.. ScoreAuditLog
    Maze --> ScoreAuditLog
```

Implemented patterns:

- Strategy: `CommandStrategy` and its command-specific implementations.
- Singleton: `GameConfig`.
- Factory Method: `RoomCreator#createRoom`, implemented by `MazeRoomCreator`.
- Additional pattern: Observer through `ScoreObserver` and `ScoreAuditLog`.
