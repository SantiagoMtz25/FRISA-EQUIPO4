package com.example.loginpagetest.model.getall

data class GetAllOrganizationsResponse(
    val salud: List<GetAllOrganizationsResponseItem> = emptyList(),
    val educacion: List<GetAllOrganizationsResponseItem> = emptyList(),
    val medioAmbiente: List<GetAllOrganizationsResponseItem> = emptyList(),
    val derechosHumanos: List<GetAllOrganizationsResponseItem> = emptyList(),
    val asociasionesReligiosas: List<GetAllOrganizationsResponseItem> = emptyList(),
    val transportePublico: List<GetAllOrganizationsResponseItem> = emptyList(),
    val cultura: List<GetAllOrganizationsResponseItem> = emptyList(),
    val serviciosAsistenciales: List<GetAllOrganizationsResponseItem> = emptyList()
)