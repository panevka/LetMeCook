package com.letmecook.domain.project

enum class PaymentType {
    MONEY,
    GRATITUDE,
    EQUITY,
    SYMBOLIC,
    VOUCHER
}

data class ProjectEntity (val name: String, val description: String, val tags: Array<String>, val paymentType: PaymentType)
