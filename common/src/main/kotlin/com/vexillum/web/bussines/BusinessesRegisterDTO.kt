package com.vexillum.web.bussines

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.validation.constraints.NotBlank

@JsonIgnoreProperties(ignoreUnknown = true)
data class BusinessesRegisterDTO(

    @NotBlank
    val name: String,
    val address: String,
    val location: LocationDTO, //get L and L
    val phone: String

)

data class LocationDTO(

    val longitude: Double,
    val latitude: Double
)