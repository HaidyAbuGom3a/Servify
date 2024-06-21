package org.haidy.servify.data.repository.fake

import org.haidy.servify.domain.model.Location
import org.haidy.servify.domain.model.Service
import org.haidy.servify.domain.model.Specialist
import org.haidy.servify.domain.repository.ISpecialistRepository

class FakeSpecialistsRepository : ISpecialistRepository {
    override suspend fun getBestSpecialists(): List<Specialist> {
        return specialists
    }

    override suspend fun getFilteredSpecialists(
        serviceName: String?,
        name: String?,
        rating: String?
    ): List<Specialist> {
        return specialists.filter {
            it.name.contains(name.toString()) || (it.rating <= (rating?.toDouble()
                ?: 0.0)) || it.service.name.contains(serviceName.toString())
        }
    }

    override suspend fun getNearestSpecialists(): List<Specialist> {
        return specialists.filter { it.location.governorate == "New Damietta" }
    }

    override suspend fun getSpecialist(id: String): Specialist {
        return specialists.first { it.id == id }
    }

    override suspend fun getServiceSpecialists(serviceName: String): List<Specialist> {
        TODO("Not yet implemented")
    }
}


private val specialists = listOf(
    Specialist(
        name = "Ayman",
        location = Location(
            latitude = 30.020473,
            longitude = 31.228638,
            country = "Egypt",
            governorate = "Cairo"
        ),
        id = 1.toString(),
        rating = 5.0,
        service = Service(
            id = 1.toString(),
            discount = 0.10,
            imageUrl = "https://hiring-assets.careerbuilder.com/media/attachments/careerbuilder-original-3580.jpg?1511294086",
            description = "This is electricity work",
            name = "Electricity",
            status = true
        ),
        imageUrl = "https://media.istockphoto.com/id/1353480176/photo/a-male-electrician-works-in-a-switchboard-with-an-electrical-connecting-cable.jpg?s=612x612&w=0&k=20&c=E5kmvTyFt4uTDWqOv6n4dl8fF7d2_AZ4PMGh9CmzarI="
    ),
    Specialist(
        name = "Nader",
        location = Location(
            latitude = 31.418125,
            longitude = 31.814249,
            country = "Egypt",
            governorate = "Damietta"
        ),
        id = 2.toString(),
        rating = 5.0,
        service = Service(
            id = 2.toString(),
            discount = 0.10,
            imageUrl = "https://www.neit.edu/wp-content/uploads/2023/12/image-2.png",
            description = "This is air conditioning work",
            name = "Air Conditioning",
            status = true
        ),
        imageUrl = "https://www.shutterstock.com/image-photo/technician-service-cleaning-air-conditioner-600nw-1498805081.jpg"
    ),
    Specialist(
        name = "Ahmed",
        location = Location(
            latitude = 31.427038,
            longitude = 31.654400,
            country = "Egypt",
            governorate = "New Damietta"
        ),
        id = 3.toString(),
        rating = 4.5,
        service = Service(
            id = 3.toString(),
            discount = 0.10,
            imageUrl = "https://www.shutterstock.com/image-photo/portrait-confident-male-carpenter-standing-600nw-2268295179.jpg",
            description = "This is carpentry work",
            name = "Carpentry",
            status = true
        ),
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWNSfQ9MuufS49C0Y09JPKoZVpNFlT6OVhUmXfWwRtuMrjYlysrmrzZxPxB7RXTn6HhIY&usqp=CAU"
    ),
    Specialist(
        name = "Salah",
        location = Location(
            latitude = 31.430430,
            longitude = 31.649057,
            country = "Egypt",
            governorate = "New Damietta"
        ),
        id = 4.toString(),
        rating = 4.5,
        service = Service(
            id = 3.toString(),
            discount = 0.10,
            imageUrl = "https://www.shutterstock.com/image-photo/portrait-confident-male-carpenter-standing-600nw-2268295179.jpg",
            description = "This is carpentry work",
            name = "Carpentry",
            status = true
        ),
        imageUrl = "https://www.shutterstock.com/image-photo/profession-carpentry-woodwork-people-concept-600nw-559842814.jpg"
    ),
    Specialist(
        name = "Waleed",
        location = Location(
            latitude = 31.425991,
            longitude = 31.654069,
            country = "Egypt",
            governorate = "New Damietta"
        ),
        id = 5.toString(),
        rating = 5.0,
        service = Service(
            id = 2.toString(),
            discount = 0.0,
            imageUrl = "https://www.neit.edu/wp-content/uploads/2023/12/image-2.png",
            description = "This is air conditioning work",
            name = "Air Conditioning",
            status = true
        ),
        imageUrl = "https://www.shutterstock.com/image-photo/technician-service-cleaning-air-conditioner-600nw-1498805081.jpg"
    )
)