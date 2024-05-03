package org.haidy.servify.data.repository.fake

import org.haidy.servify.domain.model.Service
import org.haidy.servify.domain.repository.IServiceRepository
import javax.inject.Inject

class FakeServicesRepository @Inject constructor() : IServiceRepository {
    override suspend fun getAllServices(): List<Service> {
        return services
    }

    override suspend fun getAllOffers(): List<String> {
        return offers
    }

    private val offers = listOf(
        "https://i.imgur.com/uuIanTc.png",
        "https://www.homefix.bh/wp-content/uploads/2024/02/Carpentry.png",
        "https://png.pngtree.com/thumb_back/fh260/background/20230407/pngtree-plumber-installing-pipes-mhoaraufrederic151110-plumbing-photo-image_2311512.jpg"
    )

    private val services = listOf(
        Service(
            "Carpentry",
            "https://imgur.com/Us0D6oB.png",
            true,
            "",
            "",
            0.0
        ),
        Service(
            "Plumbing",
            "https://imgur.com/bW80VFJ.png",
            true,
            "",
            "",
            0.0
        ),
        Service(
            "Electricity",
            "https://imgur.com/UngOPii.png",
            true,
            "",
            "",
            0.0
        ),
        Service(
            "Conditioning",
            "https://imgur.com/NYGGtTW.png",
            true,
            "",
            "",
            0.0
        ),
        Service(
            "Carpentry",
            "https://imgur.com/Us0D6oB.png",
            true,
            "",
            "",
            0.0
        ),
        Service(
            "Plumbing",
            "https://imgur.com/bW80VFJ.png",
            true,
            "",
            "",
            0.0
        ),
    )

}