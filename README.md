# Investment Portfolio Tracker

Backend for a simple investment portfolio tracker application, with basic RestfulAPI endpoints and PostgreSQL database.

## Get Started

To start the application, make sure you have Docker desktop running and postgreSQL installed. Run the following command to get the database up and running:

```docker compose up```

Once the docker container is running, get the Springboot application running by executing:

```mvn spring-boot:run```

## Future Work

- Add unit tests
- Set up scheduled updates from external API to fetch stock prices
- Cache stock data response from external API
- Add role-based Spring security (ADMIN, USER)
