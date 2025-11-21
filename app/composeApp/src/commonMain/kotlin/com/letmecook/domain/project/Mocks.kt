package com.letmecook.domain.project
object Mocks {

    val projectList = listOf(
        ProjectDto(
            1,
            "Weather App",
            "A web application that provides detailed, real-time weather information including temperature, humidity, wind speed, and forecasts for multiple locations. Users can save favorite cities and receive notifications for severe weather alerts.",
            PaymentType.MONEY,
            listOf(TechnicalStack.REACT, TechnicalStack.KOTLIN)
        ),
        ProjectDto(
            2,
            "Task Manager",
            "A productivity application that helps users manage daily tasks, set reminders, create recurring events, and track progress over time. Includes features like prioritization, tagging, and collaboration with team members.",
            PaymentType.EQUITY,
            listOf(TechnicalStack.SPRING, TechnicalStack.KOTLIN)
        ),
        ProjectDto(
            3,
            "Finance Tracker",
            "An application to help users track income, expenses, and investments. Generates visual reports, monthly summaries, and insights to improve financial habits. Supports importing bank statements and syncing with external services.",
            PaymentType.GRATITUDE,
            listOf(TechnicalStack.SCALA, TechnicalStack.JULIA)
        ),
        ProjectDto(
            4,
            "Portfolio Site",
            "A personal portfolio website to showcase projects, blog posts, and professional achievements. Includes sections for resume, contact form, and a gallery for multimedia projects. Responsive design for both desktop and mobile devices.",
            PaymentType.SYMBOLIC,
            listOf(TechnicalStack.REACT, TechnicalStack.OCAML)
        ),
        ProjectDto(
            5,
            "Chatbot",
            "An AI-powered chatbot designed to provide customer support and answer frequently asked questions. Integrates with messaging platforms and includes natural language processing for improved user interactions.",
            PaymentType.MONEY,
            listOf(TechnicalStack.KOTLIN, TechnicalStack.HASKELL)
        ),
        ProjectDto(
            6,
            "IoT Dashboard",
            "A real-time dashboard for monitoring and managing IoT devices. Displays sensor data, device status, and alerts. Includes analytics and historical data visualization for better decision-making and device management.",
            PaymentType.VOUCHER,
            listOf(TechnicalStack.SPRING, TechnicalStack.SCALA)
        ),
        ProjectDto(
            7,
            "Game Engine",
            "A lightweight 2D game engine designed for rapid development of platformer and puzzle games. Supports physics simulation, sprite animations, audio management, and basic AI components for non-player characters.",
            PaymentType.EQUITY,
            listOf(TechnicalStack.ADA, TechnicalStack.JULIA)
        ),
        ProjectDto(
            8,
            "Data Visualizer",
            "An interactive data visualization tool that converts large datasets into intuitive charts, graphs, and dashboards. Allows filtering, sorting, and exporting of visualizations to various formats for reporting purposes.",
            PaymentType.MONEY,
            listOf(TechnicalStack.REACT, TechnicalStack.SCALA)
        ),
        ProjectDto(
            9,
            "Compiler Tool",
            "A compiler project for a custom programming language, handling lexical analysis, parsing, and code generation. Includes debugging tools and optimization passes to improve the efficiency of compiled code.",
            PaymentType.GRATITUDE,
            listOf(TechnicalStack.OCAML, TechnicalStack.ADA)
        ),
        ProjectDto(
            10,
            "Social Media Clone",
            "A small-scale social media platform that allows users to create profiles, post content, follow others, and interact through comments and likes. Includes basic moderation tools and notifications for user engagement.",
            PaymentType.MONEY,
            listOf(TechnicalStack.REACT, TechnicalStack.SPRING)
        )
    )
    fun getAllProjects(): List<ProjectDto> {
        return projectList
    }

}

