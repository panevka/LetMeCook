package com.letmecook.domain.project

enum class PaymentType {
    MONEY,
    GRATITUDE,
    EQUITY,
    SYMBOLIC,
    VOUCHER
}

enum class TechnicalStack {
    REACT,
    SPRING,
    KOTLIN,
    HASKELL,
    JULIA,
    ADA,
    SCALA,
    OCAML
}

data class ProjectEntity (val name: String, val description: String, val tags: Array<String>, val paymentType: PaymentType, val technicalStack: TechnicalStack)
