package com.letmecook.domain.project

import kotlin.time.ExperimentalTime

object Mocks {

    @OptIn(ExperimentalTime::class)
    val projectList = listOf(
        ProjectDto(
            1,
            "Weather App",
            "A web application that provides detailed, real-time weather information including temperature, humidity, wind speed, and forecasts for multiple locations. Users can save favorite cities and receive notifications for severe weather alerts.",
            PaymentType.MONEY,
            listOf(TechnicalStack.REACT, TechnicalStack.KOTLIN),
            "2025-11-22T18:52:35.330495Z",
            author_id = 1,
            author_username = "John Smith"
        ),
        ProjectDto(
            2,
            "Task Manager",
            "A productivity application that helps users manage daily tasks, set reminders, create recurring events, and track progress over time. Includes features like prioritization, tagging, and collaboration with team members.",
            PaymentType.EQUITY,
            listOf(TechnicalStack.SPRING, TechnicalStack.KOTLIN),
            "2025-11-22T18:52:35.330495Z",
            author_id = 1,
            author_username = "John Smith"
        ),
        ProjectDto(
            3,
            "Finance Tracker",
            "An application to help users track income, expenses, and investments. Generates visual reports, monthly summaries, and insights to improve financial habits. Supports importing bank statements and syncing with external services.",
            PaymentType.GRATITUDE,
            listOf(TechnicalStack.SCALA, TechnicalStack.JULIA),
            "2025-11-22T18:52:35.330495Z",
            author_id = 1,
            author_username = "John Smith"
        ),
        ProjectDto(
            4,
            "Portfolio Site",
            "A personal portfolio website to showcase projects, blog posts, and professional achievements. Includes sections for resume, contact form, and a gallery for multimedia projects. Responsive design for both desktop and mobile devices.",
            PaymentType.SYMBOLIC,
            listOf(TechnicalStack.REACT, TechnicalStack.OCAML),
            "2025-11-22T18:52:35.330495Z",
            author_id = 1,
            author_username = "John Smith"
        ),
        ProjectDto(
            5,
            "Chatbot",
            "An AI-powered chatbot designed to provide customer support and answer frequently asked questions. Integrates with messaging platforms and includes natural language processing for improved user interactions.",
            PaymentType.MONEY,
            listOf(TechnicalStack.KOTLIN, TechnicalStack.HASKELL),
            "2025-11-22T18:52:35.330495Z",
            author_id = 1,
            author_username = "John Smith"
        ),
    )
    fun getAllProjects(): List<ProjectDto> {
        return projectList
    }

}

