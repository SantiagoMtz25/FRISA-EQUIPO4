package com.example.loginpagetest.screens.homepage

// DataProvider.kt
object DataProvider {
    val categories = listOf(
        Category("Salud", listOf(
            Organization("Salud Org 1"),
            Organization("Salud Org 2"),
            Organization("Salud Org 3")
        )),
        Category("Educación", listOf(
            Organization("Educación Org 1"),
            Organization("Educación Org 2"),
            Organization("Educación Org 3")
        )),
        Category("Medio ambiente", listOf(
            Organization("Medio ambiente Org 1"),
            Organization("Medio ambiente Org 2"),
            Organization("Medio ambiente Org 3")
        )),
        Category("Derechos Humanos", listOf(
            Organization("Derechos Humanos Org 1"),
            Organization("Derechos Humanos Org 2"),
            Organization("Derechos Humanos Org 3")
        )),
        Category("Derechos LGBT+", listOf()),
        Category("Deportes", listOf()),
        Category("Violencia de Género", listOf()),
        Category("Discapacidad", listOf())
    )
}
