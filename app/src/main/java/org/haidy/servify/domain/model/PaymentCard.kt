package org.haidy.servify.domain.model

import androidx.annotation.DrawableRes
import org.haidy.servify.app.resources.DrawableResources

data class PaymentCard(
    val cardType: PaymentCardType,
    val cardNumber: String,
    val cardHolder: String,
    @DrawableRes val logoDrawable: Int = getLogo(cardType),
    val expiryDate: Date,
    val securityCode: String
)


enum class PaymentCardType {
    VISA,
    MASTER_CARD,
    AMERICAN_EXPRESS,
    MEEZA,
    UNKNOWN
}

data class Date(
    val month: String,
    val year: String
)

private val res = DrawableResources()
fun getLogo(cardType: PaymentCardType): Int {
    return when (cardType) {
        PaymentCardType.VISA -> res.visaLogo
        PaymentCardType.MASTER_CARD -> res.masterCardLogo
        PaymentCardType.AMERICAN_EXPRESS -> res.americanExpressLogo
        PaymentCardType.MEEZA -> res.meezaLogo
        PaymentCardType.UNKNOWN -> 0
    }
}