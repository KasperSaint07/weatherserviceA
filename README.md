🌤️ Weather Notification System
Strategy + Observer + Factory + Singleton Patterns (with WebSocket live updates)

This project implements a Weather Notification System that collects, processes, and broadcasts weather updates using multiple design patterns.
It demonstrates real-time communication, dynamic strategy switching, and reactive updates through WebSockets and Spring Boot.

🧠 Core Idea

The system simulates a weather station that:

Fetches weather data using different strategies (real-time, batch, manual),

Stores the latest weather state,

Notifies all subscribed observers when the weather changes,

Provides REST API for management,

Streams live updates via WebSocket to any connected clients (e.g., ws.html page).

Pattern	Where Implemented	Purpose
Strategy	UpdateStrategy, RealTimeSensorStrategy, ScheduledBatchStrategy, ManualInputStrategy	Defines interchangeable algorithms for fetching weather data
Observer	WeatherStation, WeatherObserver, WebSocketObserver, HistoryObserver, etc.	Notifies all observers (clients, history, UI) when new weather data arrives
Factory	StrategyFactory	Encapsulates strategy creation logic and centralizes type selection
Singleton	WeatherStation	Ensures only one station instance exists in the entire application
(Bonus) Facade	WeatherStationService	Provides a clean, simplified API layer for the controller
(Bonus) Builder	WeatherData with Lombok @Builder	Creates immutable data objects with readable syntax

kz.aitu.weather
│
├── core
│   ├── model
│   │   └── WeatherData.java               # Immutable data model (temperature, humidity, pressure)
│   ├── strategy
│   │   ├── UpdateStrategy.java            # Interface for fetching data
│   │   ├── RealTimeSensorStrategy.java    # Random simulated data (real-time)
│   │   ├── ScheduledBatchStrategy.java    # Time-based auto updates
│   │   └── ManualInputStrategy.java       # Manual user input
│   ├── observer
│   │   ├── WeatherObserver.java           # Observer interface
│   │   ├── WeatherStation.java            # Singleton Subject that notifies observers
│   │   ├── WebSocketObserver.java         # Sends updates to clients via WebSocket
│   │   ├── HistoryObserver.java           # Saves updates to history
│   │   ├── AnalyticsObserver.java         # (Demo) Analyzes temperature trends
│   │   └── DisplayBoardObserver.java      # (Demo) Mimics display board updates
│   ├── factory
│   │   └── StrategyFactory.java           # Creates strategy instances dynamically
│   └── history
│       └── HistoryService.java            # Stores last N records and calculates statistics
│
├── config
│   ├── ws
│   │   ├── WeatherSocketHandler.java      # Low-level WebSocket connection manager
│   │   └── WebSocketConfig.java           # Registers endpoint /ws/weather
│   ├── BatchTicker.java                   # Scheduled auto-updater (every 5 sec for Batch mode)
│   └── BootstrapConfig.java               # Initializes observers and services at startup
│
├── controller
│   └── WeatherController.java             # REST API endpoints (/weather/...)
│
├── service
│   └── WeatherStationService.java         # Facade between Controller and WeatherStation
│
└── WeatherserviceAApplication.java        # Main entry point (@SpringBootApplication)


A minimal WebSocket client located in /static/ws.html:

Connects to /ws/weather,

Displays temperature, humidity, pressure live,

Shows raw JSON data,

Has buttons for switching strategies and manual input,

Automatically reconnects on disconnect.

{
  "temperature": 22.4,
  "humidity": 47.0,
  "pressure": 1006.8
}


| Endpoint                           | Method     | Description                     |            |                            |
| ---------------------------------- | ---------- | ------------------------------- | ---------- | -------------------------- |
| `/weather/current`                 | GET        | Get current weather data        |            |                            |
| `/weather/update`                  | GET / POST | Force manual update             |            |                            |
| `/weather/strategy?type=realtime   | batch      | manual`                         | GET / POST | Change data-fetch strategy |
| `/weather/manual?t=22&h=40&p=1007` | GET / POST | Set manual data                 |            |                            |
| `/weather/history?limit=50`        | GET        | View last N records             |            |                            |
| `/weather/stats`                   | GET        | View average/min/max statistics |            |                            |


http://localhost:8080/ws.html

