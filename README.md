GenAI Chatbot - Spring Boot Project

A simple, full-stack Generative AI chatbot using Spring Boot, H2 in-memory database, OpenAI API (via Azure endpoint), and Thymeleaf for UI.

Features

Ask natural language questions via web UI or REST API

Responses powered by OpenAI GPT-3.5

Questions & answers stored in H2 database

Clean HTML UI using Thymeleaf

View all asked questions through a REST endpoint

Technologies Used

Java 17+ / Spring Boot

Thymeleaf (UI rendering)

OpenAI GPT via Azure endpoint

H2 Database (in-memory)

REST API (for integration)

API Endpoints

Ask a question:

curl --location --request POST 'http://localhost:8080/api/ask' \
--header 'Content-Type: application/json' \
--data-raw '{"question": "What is Vector Database in Redis process?"}'

View all asked questions:

curl --location --request GET 'http://localhost:8080/api/questions'

Open the chatbot UI:

http://localhost:8080

Project Setup Instructions

1. Clone the repository

git clone git@github.com:abhichauhan800/genai-chatbot-spring.git
cd genai-chatbot-spring

2. Add API keys to src/main/resources/application.properties

open.ai.gpt.url=https://jsdev.openai.azure.com
open.ai.api.key=3f180e7ab019487e898ba7177dbde3ee
open.ai.api.version=2023-05-15
open.ai.model=gpt-3.5-turbo-1106
open.ai.model.deployment=GPT35T-0301

Replace the API key with your own if required.

3. Run the application

./gradlew bootRun

Or if using an IDE like IntelliJ:

Run the ChatBotApplication.java class directly
