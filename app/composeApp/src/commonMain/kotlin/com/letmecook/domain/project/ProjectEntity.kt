package com.letmecook.domain.project

internal enum class PaymentType {
    MONEY,
    GRATITUDE,
    EQUITY,
    SYMBOLIC,
    VOUCHER,
}

internal enum class TechnicalStack {
    REACT,
    SPRING,
    KOTLIN,
    HASKELL,
    JULIA,
    ADA,
    SCALA,
    OCAML,
}

internal data class ProjectEntity(
    val name: String,
    val description: String,
    val tags: Array<String>,
    val paymentType: PaymentType,
    val technicalStack: TechnicalStack,
)
