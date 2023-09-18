package com.example.loginpagetest.screens.createaccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.loginpagetest.R
import com.example.loginpagetest.navigation.CustomTopBar
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CreateAccount(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val scrollState = rememberScrollState()
        Column {
            CustomTopBar(title = "Create User Account", navController = navController, screen = "login")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val focusManager = LocalFocusManager.current
                var isDropdownExpanded by rememberSaveable { mutableStateOf(false) }
                var name by rememberSaveable { mutableStateOf("") }
                var lastName by rememberSaveable { mutableStateOf("") }
                var email by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }
                var confirmPassword by rememberSaveable { mutableStateOf("") }
                var phoneNumber by rememberSaveable { mutableStateOf("") }
                var state by rememberSaveable { mutableStateOf("") }
                var city by rememberSaveable { mutableStateOf("") }

                var selectedState by rememberSaveable { mutableStateOf("") }
                var isCityDropdownExpanded by rememberSaveable { mutableStateOf(false) }
                var isMunicipalityDropdownExpanded by rememberSaveable { mutableStateOf(false) }

                var filteredStates by rememberSaveable { mutableStateOf(listOf<String>()) }
                var filteredMunicipalities by rememberSaveable { mutableStateOf(listOf<String>()) }

                val chihuahuaMunicipalities = listOf(
                    "Ahumada", "Aldama", "Allende", "Aquiles Serdán", "Ascensión",
                    "Bachíniva", "Balleza", "Batopilas", "Bocoyna", "Buenaventura",
                    "Camargo", "Carichí", "Casas Grandes", "Coronado", "Coyame del Sotol",
                    "Cuauhtémoc", "Cusihuiriachi", "Chihuahua", "Chínipas", "Delicias",
                    "Dr. Belisario Domínguez", "Galeana", "Gómez Farías", "Gran Morelos",
                    "Guachochi", "Guadalupe", "Guadalupe y Calvo", "Guazapares", "Guerrero",
                    "Hidalgo del Parral", "Huejotitán", "Ignacio Zaragoza", "Janos", "Jiménez",
                    "Juárez", "Julimes", "López", "Madera", "Maguarichi", "Manuel Benavides",
                    "Matachí", "Matamoros", "Meoqui", "Morelos", "Moris", "Namiquipa",
                    "Nonoava", "Nuevo Casas Grandes", "Ocampo", "Ojinaga", "Praxedis G. Guerrero",
                    "Riva Palacio", "Rosales", "Rosario", "San Francisco de Borja", "San Francisco de Conchos",
                    "San Francisco del Oro", "Santa Bárbara", "Santa Isabel", "Satevó", "Saucillo", "Temósachi",
                    "Urique", "Uruachi", "Valle de Zaragoza"
                )
                val coahuilaMunicipalities = listOf(
                    "Abasolo", "Acuña", "Allende", "Arteaga", "Candela", "Castaños",
                    "Cuatro Ciénegas", "Escobedo", "Francisco I. Madero", "Frontera",
                    "General Cepeda", "Guerrero", "Hidalgo", "Jiménez", "Juárez",
                    "Lamadrid", "Matamoros", "Melchor Múzquiz", "Monclova", "Morelos",
                    "Múzquiz", "Nadadores", "Nava", "Ocampo", "Parras", "Piedras Negras",
                    "Progreso", "Ramos Arizpe", "Sabinas", "Sacramento", "Saltillo",
                    "San Buenaventura", "San Juan de Sabinas", "San Pedro", "Sierra Mojada",
                    "Torreón", "Viesca", "Villa Unión", "Zaragoza"
                )
                val colimaMunicipalities = listOf(
                    "Armería", "Colima", "Comala", "Coquimatlán", "Cuauhtémoc",
                    "Ixtlahuacán", "Manzanillo", "Minatitlán", "Tecomán", "Villa de Álvarez"
                )
                val durangoMunicipalities = listOf(
                    "Canatlán", "Canelas", "Coneto de Comonfort", "Cuencamé", "Durango",
                    "El Oro", "Gómez Palacio", "Guadalupe Victoria", "Guanaceví", "Hidalgo",
                    "Indé", "Lerdo", "Mapimí", "Mezquital", "Nazas", "Nombre de Dios",
                    "Nuevo Ideal", "Ocampo", "Otáez", "Pánuco de Coronado", "Peñón Blanco",
                    "Poanas", "Pueblo Nuevo", "Rodeo", "San Bernardo", "San Dimas", "San Juan del Río",
                    "San Juan de Guadalupe", "San Luis del Cordero", "San Pedro del Gallo", "Santa Clara",
                    "Santiago Papasquiaro", "Súchil", "Tamazula", "Tepehuanes", "Tlahualilo", "Topia",
                    "Vicente Guerrero"
                )
                val guanajuatoMunicipalities = listOf(
                    "Abasolo", "Acámbaro", "San Miguel de Allende", "Apaseo el Alto",
                    "Apaseo el Grande", "Atarjea", "Celaya", "Manuel Doblado", "Comonfort",
                    "Coroneo", "Cortazar", "Cuerámaro", "Doctor Mora", "Dolores Hidalgo",
                    "Guanajuato", "Huanímaro", "Irapuato", "Jaral del Progreso", "Jerécuaro",
                    "León", "Moroleón", "Ocampo", "Pénjamo", "Pueblo Nuevo", "Purísima del Rincón",
                    "Romita", "Salamanca", "Salvatierra", "San Diego de la Unión", "San Felipe",
                    "San Francisco del Rincón", "San José Iturbide", "San Luis de la Paz",
                    "Santa Catarina", "Santa Cruz de Juventino Rosas", "Santiago Maravatío",
                    "Silao", "Tarandacuao", "Tarimoro", "Tierra Blanca", "Uriangato",
                    "Valle de Santiago", "Victoria", "Villagrán", "Xichú", "Yuriria"
                )
                val guerreroMunicipalities = listOf(
                    "Acapulco de Juárez", "Ahuacuotzingo", "Ajuchitlán del Progreso", "Alcozauca de Guerrero",
                    "Alpoyeca", "Apaxtla", "Arcelia", "Atenango del Río", "Atlamajalcingo del Monte", "Atlixtac",
                    "Atoyac de Álvarez", "Ayutla de los Libres", "Azoyú", "Benito Juárez", "Buenavista de Cuéllar",
                    "Coahuayutla de José María Izazaga", "Cocula", "Copala", "Copalillo", "Copanatoyac",
                    "Coyuca de Benítez", "Coyuca de Catalán", "Cuajinicuilapa", "Cualác", "Cuautepec",
                    "Cuetzala del Progreso", "Cutzamala de Pinzón", "Chilapa de Álvarez", "Chilpancingo de los Bravo",
                    "Florencio Villarreal", "General Canuto A. Neri", "General Heliodoro Castillo", "Huamuxtitlán",
                    "Huitzuco de los Figueroa", "Iguala de la Independencia", "Igualapa", "Ixcateopan de Cuauhtémoc",
                    "Zihuatanejo de Azueta", "Juan R. Escudero", "Leonardo Bravo", "Malinaltepec",
                    "Mártir de Cuilapan", "Metlatónoc", "Mochitlán", "Olinalá", "Ometepec", "Pedro Ascencio Alquisiras",
                    "Petatlán", "Pilcaya", "Pungarabato", "Quechultenango", "San Luis Acatlán", "San Marcos",
                    "San Miguel Totolapan", "Taxco de Alarcón", "Tecoanapa", "Técpan de Galeana",
                    "Teloloapan", "Tepecoacuilco de Trujano", "Tetipac", "Tixtla de Guerrero", "Tlacoachistlahuaca",
                    "Tlacoapa", "Tlalchapa", "Tlalixtaquilla de Maldonado", "Tlapa de Comonfort", "Tlapehuala",
                    "La Unión de Isidoro Montes de Oca", "Xalpatláhuac", "Xochihuehuetlán", "Xochistlahuaca",
                    "Zapotitlán Tablas", "Zirándaro", "Zitlala", "Eduardo Neri", "Acatepec", "Marquelia",
                    "José Joaquín de Herrera", "Juchitán", "Iliatenco"
                )
                val hidalgoMunicipalities = listOf(
                    "Acatlán", "Acaxochitlán", "Actopan", "Agua Blanca de Iturbide", "Ajacuba", "Alfajayucan",
                    "Almoloya", "Apan", "Atitalaquia", "Atlapexco", "Atotonilco de Tula", "Atotonilco El Grande",
                    "Calnali", "Cardonal", "Chapantongo", "Chapulhuacán", "Chilcuautla", "Cuautepec de Hinojosa",
                    "El Arenal", "Eloxochitlán", "Emiliano Zapata", "Epazoyucan", "Francisco I. Madero", "Huasca de Ocampo",
                    "Huautla", "Huazalingo", "Huehuetla", "Huejutla de Reyes", "Huichapan", "Ixmiquilpan",
                    "Jacala de Ledezma", "Jaltocán", "Juárez Hidalgo", "Lolotla", "Metepec", "San Agustín Metzquititlán",
                    "Metztitlán", "Mineral de la Reforma", "Mineral del Chico", "Mineral del Monte", "La Misión",
                    "Mixquiahuala de Juárez", "Molango de Escamilla", "Nicolás Flores", "Nopala de Villagrán",
                    "Omitlán de Juárez", "San Felipe Orizatlán", "Pacula", "Pachuca de Soto", "Pisaflores",
                    "Progreso de Obregón", "Mineral de la Reforma", "San Agustín Tlaxiaca", "San Bartolo Tutotepec",
                    "San Salvador", "Santiago de Anaya", "Santiago Tulantepec de Lugo Guerrero", "Singuilucan",
                    "Tasquillo", "Tecozautla", "Tenango de Doria", "Tepeapulco", "Tepehuacán de Guerrero", "Tepeji del Río de Ocampo",
                    "Tepetitlán", "Tetepango", "Villa de Tezontepec", "Tezontepec de Aldama", "Tianguistengo", "Tizayuca",
                    "Tlahuelilpan", "Tlahuiltepa", "Tlanalapa", "Tlanchinol", "Tlaxcoapan", "Tolcayuca", "Tula de Allende",
                    "Tulancingo de Bravo", "Xochiatipan", "Xochicoatlán", "Yahualica", "Zacualtipán de Ángeles",
                    "Zapotlán de Juárez", "Zempoala", "Zimapán"
                )
                val jaliscoMunicipalities = listOf(
                    "Acatic", "Acatlán de Juárez", "Ahualulco de Mercado", "Amacueca", "Amatitán", "Ameca",
                    "San Juanito de Escobedo", "Arandas", "El Arenal", "Atemajac de Brizuela", "Atengo", "Atenguillo",
                    "Atotonilco el Alto", "Atoyac", "Autlán de Navarro", "Ayotlán", "Ayutla", "La Barca", "Bolaños",
                    "Cabo Corrientes", "Casimiro Castillo", "Cihuatlán", "Cocula", "Colotlán", "Concepción de Buenos Aires",
                    "Cuautitlán de García Barragán", "Cuautla", "Cuquío", "Chapala", "Chimaltitán", "Chiquilistlán",
                    "Degollado", "Ejutla", "Encarnación de Díaz", "Etzatlán", "El Grullo", "Guachinango", "Guadalajara",
                    "Hostotipaquillo", "Huejúcar", "Huejuquilla el Alto", "La Huerta", "Ixtlahuacán de los Membrillos",
                    "Ixtlahuacán del Río", "Jalostotitlán", "Jamay", "Jesús María", "Jilotlán de los Dolores",
                    "Jocotepec", "Juanacatlán", "Juchitlán", "Lagos de Moreno", "El Limón", "Magdalena", "Santa María del Oro",
                    "La Manzanilla de la Paz", "Mascota", "Mazamitla", "Mexticacán", "Mezquitic", "Mixtlán", "Ocotlán",
                    "Ojuelos de Jalisco", "Pihuamo", "Poncitlán", "Puerto Vallarta", "Villa Purificación", "Quitupan",
                    "El Salto", "San Cristóbal de la Barranca", "San Diego de Alejandría", "San Juan de los Lagos",
                    "San Julián", "San Marcos", "San Martín de Bolaños", "San Martín Hidalgo", "San Miguel el Alto",
                    "Gómez Farías", "San Sebastián del Oeste", "Santa María de los Ángeles", "Sayula", "Tala", "Talpa de Allende",
                    "Tamazula de Gordiano", "Tapalpa", "Tecalitlán", "Tecolotlán", "Techaluta de Montenegro",
                    "Tenamaxtlán", "Teocaltiche", "Teocuitatlán de Corona", "Tepatitlán de Morelos", "Tequila",
                    "Teuchitlán", "Tizapán el Alto", "Tlajomulco de Zúñiga", "San Pedro Tlaquepaque", "Tolimán", "Tomatlán",
                    "Tonalá", "Tonaya", "Tonila", "Totatiche", "Tototlán", "Tuxcacuesco", "Tuxcueca", "Tuxpan",
                    "Unión de San Antonio", "Unión de Tula", "Valle de Guadalupe", "Valle de Juárez", "San Gabriel",
                    "Villa Corona", "Villa Guerrero", "Villa Hidalgo", "Cañadas de Obregón", "Yahualica de González Gallo",
                    "Zacoalco de Torres", "Zapopan", "Zapotiltic", "Zapotitlán de Vadillo", "Zapotlán del Rey",
                    "Zapotlanejo", "San Ignacio Cerro Gordo"
                )
                val ciudadDeMexicoMunicipalities = listOf(
                    "Álvaro Obregón", "Azcapotzalco", "Benito Juárez", "Coyoacán", "Cuajimalpa de Morelos",
                    "Cuauhtémoc", "Gustavo A. Madero", "Iztacalco", "Iztapalapa", "La Magdalena Contreras",
                    "Miguel Hidalgo", "Milpa Alta", "Tláhuac", "Tlalpan", "Venustiano Carranza", "Xochimilco"
                )
                val michoacanMunicipalities = listOf(
                    "Acuitzio", "Aguililla", "Álvaro Obregón", "Angamacutiro", "Angangueo", "Apatzingán",
                    "Aporo", "Aquila", "Ario", "Arteaga", "Briseñas", "Buenavista", "Carácuaro", "Coahuayana",
                    "Coalcomán de Vázquez Pallares", "Coeneo", "Contepec", "Copándaro", "Cotija", "Cuitzeo",
                    "Charapan", "Charo", "Chavinda", "Cherán", "Chilchota", "Chinicuila", "Chucándiro", "Churintzio",
                    "Churumuco", "Ecuandureo", "Epitacio Huerta", "Erongarícuaro", "Gabriel Zamora", "Hidalgo",
                    "La Huacana", "Huandacareo", "Huaniqueo", "Huetamo", "Huiramba", "Indaparapeo", "Irimbo",
                    "Ixtlán", "Jacona", "Jiménez", "Jiquilpan", "Juárez", "Jungapeo", "Lagunillas", "Madero",
                    "Maravatío", "Marcos Castellanos", "Lázaro Cárdenas", "Morelia", "Morelos", "Múgica",
                    "Nahuatzen", "Nocupétaro", "Nuevo Parangaricutiro", "Nuevo Urecho", "Numarán", "Ocampo",
                    "Pajacuarán", "Panindícuaro", "Parácuaro", "Paracho", "Pátzcuaro", "Penjamillo", "Peribán",
                    "La Piedad", "Purépero", "Puruándiro", "Queréndaro", "Quiroga", "Cojumatlán de Régules",
                    "Los Reyes", "Sahuayo", "San Lucas", "Santa Ana Maya", "Salvador Escalante", "Senguio",
                    "Susupuato", "Tacámbaro", "Tancítaro", "Tangamandapio", "Tangancícuaro", "Tanhuato",
                    "Taretan", "Tarímbaro", "Tepalcatepec", "Tingambato", "Tingüindín", "Tiquicheo de Nicolás Romero",
                    "Tlalpujahua", "Tlazazalca", "Tocumbo", "Tumbiscatío", "Turicato", "Tuxpan", "Tuzantla", "Tzintzuntzan",
                    "Tzitzio", "Uruapan", "Venustiano Carranza", "Villamar", "Vista Hermosa", "Yurécuaro", "Zacapu",
                    "Zamora", "Zináparo", "Zinapécuaro", "Ziracuaretiro", "Zitácuaro", "José Sixto Verduzco"
                )
                val morelosMunicipalities = listOf(
                    "Amacuzac", "Atlatlahucan", "Axochiapan", "Ayala", "Coatlán del Río", "Cuautla", "Cuernavaca",
                    "Emiliano Zapata", "Huitzilac", "Jantetelco", "Jiutepec", "Jojutla", "Jonacatepec de Leandro Valle",
                    "Mazatepec", "Miacatlán", "Ocuituco", "Puente de Ixtla", "Temixco", "Temoac", "Tepalcingo",
                    "Tepoztlán", "Tetecala", "Tetela del Volcán", "Tlalnepantla", "Tlaltizapán de Zapata", "Tlaquiltenango",
                    "Tlayacapan", "Totolapan", "Xochitepec", "Yautepec de Zaragoza", "Yecapixtla", "Zacatepec",
                    "Zacualpan de Amilpas"
                )

                val nayaritMunicipalities = listOf(
                    "Acaponeta", "Ahuacatlán", "Amatlán de Cañas", "Bahía de Banderas", "Compostela", "Del Nayar",
                    "Huajicori", "Ixtlán del Río", "Jala", "La Yesca", "Rosamorada", "Ruíz", "San Blas", "San Pedro Lagunillas",
                    "Santa María del Oro", "Santiago Ixcuintla", "Tecuala", "Tepic", "Tuxpan", "Xalisco"
                )

                val nuevoLeonMunicipalities = listOf(
                    "Abasolo", "Agualeguas", "Allende", "Anáhuac", "Apodaca", "Aramberri", "Bustamante", "Cadereyta Jiménez",
                    "Cerralvo", "China", "Ciénega de Flores", "Doctor Arroyo", "Doctor Coss", "Doctor González",
                    "El Carmen", "Galeana", "García", "San Pedro Garza García", "General Bravo", "General Escobedo",
                    "General Terán", "General Treviño", "General Zaragoza", "Guadalupe", "Hidalgo", "Higueras",
                    "Hualahuises", "Iturbide", "Juárez", "Lampazos de Naranjo", "Linares", "Los Aldamas", "Los Herreras",
                    "Los Ramones", "Marín", "Melchor Ocampo", "Mier y Noriega", "Mina", "Montemorelos", "Monterrey",
                    "Parás", "Pesquería", "Rayones", "Sabinas Hidalgo", "Salinas Victoria", "San Nicolás de los Garza",
                    "Hidalgo", "Santa Catarina", "Santiago", "Vallecillo", "Villaldama"
                )
                val oaxacaMunicipalities = listOf(
                    "Abejones", "Acatlán de Pérez Figueroa", "Ánimas Trujano", "Asunción Cacalotepec", "Asunción Cuyotepeji",
                    "Asunción Ixtaltepec", "Asunción Nochixtlán", "Asunción Ocotlán", "Asunción Tlacolulita", "Ayoquezco de Aldama",
                    "Ayotzintepec", "Calihualá", "Candelaria Loxicha", "Capulálpam de Méndez", "Chahuites", "Chalcatongo de Hidalgo",
                    "Chiquihuitlán de Benito Juárez", "Ciénega de Zimatlán", "Ciudad Ixtepec", "Coatecas Altas", "Coicoyán de las Flores",
                    "Concepción Buenavista", "Concepción Pápalo", "Cosolapa", "Cosoltepec", "Cuilápam de Guerrero", "Cuyamecalco Villa de Zaragoza",
                    // ... (and many more)
                )
                val pueblaMunicipalities = listOf(
                    "Acajete", "Acateno", "Acatlán", "Acatzingo", "Acteopan", "Ahuacatlán", "Ahuatlán", "Ahuazotepec",
                    "Ahuehuetitla", "Ajalpan", "Albino Zertuche", "Aljojuca", "Altepexi", "Amixtlán", "Amozoc",
                    "Aquixtla", "Atempan", "Atexcal", "Atlequizayan", "Atlixco", "Atoyatempan", "Atzala",
                    "Atzitzihuacán", "Atzitzintla", "Axutla", "Ayotoxco de Guerrero", "Calpan", "Caltepec", "Camocuautla",
                    // ... (and many more)
                )
                val queretaroMunicipalities = listOf(
                    "Amealco de Bonfil", "Arroyo Seco", "Cadereyta de Montes", "Colón",
                    "Corregidora", "Ezequiel Montes", "Huimilpan", "Jalpan de Serra",
                    "Landa de Matamoros", "El Marqués", "Pedro Escobedo", "Peñamiller",
                    "Pinal de Amoles", "Querétaro", "San Joaquín", "San Juan del Río",
                    "Tequisquiapan", "Tolimán"
                )
                val quintanaRooMunicipalities = listOf(
                    "Bacalar", "Benito Juárez", "Cozumel", "Felipe Carrillo Puerto",
                    "Isla Mujeres", "José María Morelos", "Lázaro Cárdenas", "Othón P. Blanco",
                    "Puerto Morelos", "Solidaridad", "Tulum"
                )
                val sanLuisPotosiMunicipalities = listOf(
                    "Ahualulco", "Alaquines", "Aquismón", "Armadillo de los Infante",
                    "Axtla de Terrazas", "Cárdenas", "Catorce", "Cedral", "Cerritos",
                    "Cerro de San Pedro", "Charcas", "Ciudad del Maíz", "Ciudad Fernández",
                    "Ciudad Valles", "Coxcatlán", "Ebano", "El Naranjo", "Guadalcázar",
                    "Huehuetlán", "Lagunillas", "Matehuala", "Matlapa", "Mexquitic de Carmona",
                    "Moctezuma", "Rayón", "Rioverde", "Salinas", "San Antonio", "San Ciro de Acosta",
                    "San Luis Potosí", "San Martín Chalchicuautla", "San Nicolás Tolentino", "San Vicente Tancuayalab",
                    "Santa Catarina", "Santa María del Río", "Santo Domingo", "Soledad de Graciano Sánchez",
                    "Tamasopo", "Tamazunchale", "Tampacán", "Tampamolón Corona", "Tamuín", "Tancanhuitz",
                    "Tanlajás", "Tanquián de Escobedo", "Tierra Nueva", "Vanegas", "Venado", "Villa de Arista",
                    "Villa de Arriaga", "Villa de Guadalupe", "Villa de la Paz", "Villa de Ramos", "Villa de Reyes",
                    "Villa Hidalgo", "Villa Juárez", "Xilitla", "Zaragoza"
                )
                val sinaloaMunicipalities = listOf(
                    "Ahome", "Angostura", "Badiraguato", "Concordia", "Cosalá", "Culiacán",
                    "Choix", "El Fuerte", "Elota", "Escuinapa", "Guasave", "Mazatlán",
                    "Mocorito", "Navolato", "Rosario", "Salvador Alvarado", "San Ignacio",
                    "Sinaloa"
                )
                val sonoraMunicipalities = listOf(
                    "Aconchi", "Agua Prieta", "Alamos", "Altar", "Arivechi", "Arizpe",
                    "Atil", "Bacadéhuachi", "Bacanora", "Bacerac", "Bacoachi", "Bácum",
                    "Banámichi", "Baviácora", "Bavispe", "Benito Juárez", "Benjamín Hill",
                    "Caborca", "Cajeme", "Cananea", "Carbó", "La Colorada", "Cucurpe",
                    "Cumpas", "Divisaderos", "Empalme", "Etchojoa", "Fronteras", "General Plutarco Elías Calles",
                    "Granados", "Guaymas", "Hermosillo", "Huachinera", "Huásabas", "Huatabampo",
                    "Huépac", "Imuris", "Magdalena", "Mazatán", "Moctezuma", "Naco", "Nácori Chico",
                    "Nacozari de García", "Navojoa", "Nogales", "Onavas", "Opodepe", "Oquitoa",
                    "Pitiquito", "Puerto Peñasco", "Quiriego", "Rayón", "Rosario", "Sahuaripa",
                    "San Felipe de Jesús", "San Javier", "San Luis Río Colorado", "San Miguel de Horcasitas",
                    "San Pedro de la Cueva", "Santa Ana", "Santa Cruz", "Sáric", "Soyopa", "Suaqui Grande",
                    "Tepache", "Trincheras", "Tubutama", "Ures", "Villa Hidalgo", "Villa Pesqueira",
                    "Yécora"
                )
                val tabascoMunicipalities = listOf(
                    "Balancán", "Cárdenas", "Centla", "Centro", "Comalcalco", "Cunduacán",
                    "Emiliano Zapata", "Huimanguillo", "Jalapa", "Jalpa de Méndez", "Jonuta",
                    "Macuspana", "Nacajuca", "Paraíso", "Tacotalpa", "Teapa", "Tenosique"
                )
                val tamaulipasMunicipalities = listOf(
                    "Abasolo", "Aldama", "Altamira", "Antiguo Morelos", "Burgos", "Bustamante",
                    "Camargo", "Casas", "Ciudad Madero", "Ciudad Mante", "Ciudad Victoria",
                    "Cruillas", "El Mante", "Gómez Farías", "González", "Guémez", "Guerrero",
                    "Gustavo Díaz Ordaz", "Hidalgo", "Jaumave", "Jiménez", "Llera", "Mainero",
                    "Matamoros", "Méndez", "Mier", "Miguel Alemán", "Miquihuana", "Nuevo Laredo",
                    "Nuevo Morelos", "Ocampo", "Padilla", "Palmillas", "Reynosa", "Río Bravo",
                    "San Carlos", "San Fernando", "San Nicolás", "Soto la Marina", "Tampico",
                    "Tula", "Valle Hermoso", "Victoria", "Villagrán", "Xicoténcatl"
                )
                val tlaxcalaMunicipalities = listOf(
                    "Amaxac de Guerrero", "Apetatitlán de Antonio Carvajal", "Atlangatepec",
                    "Atltzayanca", "Apizaco", "Calpulalpan", "El Carmen Tequexquitla", "Cuapiaxtla",
                    "Chiautempan", "Contla de Juan Cuamatzi", "Cuaxomulco", "Españita",
                    "Huamantla", "Hueyotlipan", "Ixtacuixtla de Mariano Matamoros", "Ixtenco",
                    "La Magdalena Tlaltelulco", "Lázaro Cárdenas", "Munoz de Domingo Arenas",
                    "Nanacamilpa de Mariano Arista", "Nativitas", "Panotla", "Sanctórum de Lázaro Cárdenas",
                    "San Damián Texóloc", "San Francisco Tetlanohcan", "San Jerónimo Zacualpan",
                    "San José Teacalco", "San Juan Huactzinco", "San Lorenzo Axocomanitla", "San Lucas Tecopilco",
                    "Santa Ana Nopalucan", "Santa Apolonia Teacalco", "Santa Catarina Ayometla",
                    "Santa Cruz Tlaxcala", "Santa Isabel Xiloxoxtla", "Tenancingo", "Teolocholco",
                    "Tepetitla de Lardizábal", "Tepeyanco", "Terrenate", "Tetla de la Solidaridad",
                    "Tetlatlahuca", "Tlaxcala", "Tlaxco", "Tocatlán", "Totolac", "Tzompantepec",
                    "Xaloztoc", "Xaltocan", "Xicohtzinco", "Yauhquemehcan", "Zacatelco", "Ziltlaltépec de Trinidad Sánchez Santos"
                )
                val veracruzMunicipalities = listOf(
                    "Acajete", "Acatlán", "Acayucan", "Actopan", "Acula", "Acultzingo",
                    // Many more municipalities exist; this is a truncated list.
                )
                val yucatanMunicipalities = listOf(
                    "Abalá", "Acanceh", "Akil", "Baca", "Bokobá", "Buctzotz",
                    // Many more municipalities exist; this is a truncated list.
                )
                val zacatecasMunicipalities = listOf(
                    "Apozol", "Apulco", "Atolinga", "Benito Juárez", "Calera", "Cañitas de Felipe Pescador",
                    // Many more municipalities exist; this is a truncated list.
                )
                val estadoDeMexicoMunicipalities = listOf(
                    "Acambay de Ruíz Castañeda", "Acolman", "Aculco", "Almoloya de Alquisiras", "Almoloya de Juárez", "Almoloya del Río",
                    // Many more municipalities exist; this is a truncated list.
                )
                val mapStates = mapOf(
                    "Aguascalientes" to listOf(
                        "Aguascalientes", "Asientos", "Calvillo", "Cosío", "Jesús María", "Pabellón de Arteaga",
                        "Rincón de Romos", "San José de Gracia", "Tepezalá", "El Llano", "San Francisco de los Romo"
                    ),
                    "Baja California" to listOf(
                        "Ensenada", "Mexicali", "Playas de Rosarito", "Tecate", "Tijuana"
                    ),
                    "Baja California Sur" to listOf(
                        "Comondú", "La Paz", "Loreto", "Los Cabos", "Mulegé"
                    ),
                    "Campeche" to listOf(
                        "Calkiní", "Campeche", "Carmen", "Champotón", "Dzitbalché", "Escárcega",
                        "Hecelchakán", "Hopelchén", "Palizada", "Tenabo", "Candelaria"
                    ),
                    "Chiapas" to listOf(
                        "Acacoyagua", "Acala", "Acapetahua", "Aldama", "Altamirano", "Amatán",
                        "Amatenango de la Frontera", "Amatenango del Valle", "Angel Albino Corzo",
                        "Arriaga", "Bejucal de Ocampo", "Bella Vista", "Benemérito de las Américas",
                        "Berriozábal", "Bochil", "Cacahoatán", "Catazajá", "Cintalapa", "Coapilla",
                        "Comitán de Domínguez", "Copainalá", "El Bosque", "El Porvenir", "Escuintla",
                        "Francisco León", "Frontera Comalapa", "Frontera Hidalgo", "Ixtacomitán",
                        "Ixtapa", "Ixtapangajoya", "Jiquipilas", "Jitotol", "Juárez", "La Concordia",
                        "La Grandeza", "La Independencia", "La Libertad", "La Trinitaria", "Larráinzar",
                        "Las Margaritas", "Las Rosas", "Mapastepec", "Mazapa de Madero", "Mazatán",
                        "Motozintla", "Ocosingo", "Ocotepec", "Ocozocoautla de Espinosa", "Ostuacán",
                        "Osumacinta", "Oxchuc", "Palenque", "Pantelhó", "Pantepec", "Pichucalco",
                        "Pijijiapan", "Pueblo Nuevo Solistahuacán", "Rayón", "Reforma", "Sabanilla",
                        "Salto de Agua", "San Andrés Duraznal", "San Cristóbal de las Casas", "San Fernando",
                        "San Juan Cancuc", "San Lucas", "Santiago el Pinar", "Siltepec", "Simojovel",
                        "Sitalá", "Socoltenango", "Solosuchiapa", "Soyaló", "Suchiapa", "Suchiate",
                        "Sunuapa", "Tapachula", "Tapalapa", "Tapilula", "Tecpatán", "Tenejapa",
                        "Teopisca", "Tila", "Tonalá", "Totolapa", "Tumbalá", "Tuxtla Chico",
                        "Tuxtla Gutiérrez", "Tuzantán", "Tzimol", "Unión Juárez", "Venustiano Carranza",
                        "Villa Comaltitlán", "Villa Corzo", "Villaflores", "Yajalón", "Zinacantán"
                    ),
                    "Chihuahua" to chihuahuaMunicipalities,
                    "Coahuila" to coahuilaMunicipalities,
                    "Colima" to colimaMunicipalities,
                    "Durango" to durangoMunicipalities,
                    "Guanajuato" to guanajuatoMunicipalities,
                    "Guerrero" to guerreroMunicipalities,
                    "Hidalgo" to hidalgoMunicipalities,
                    "Jalisco" to jaliscoMunicipalities,
                    "Ciudad de México" to ciudadDeMexicoMunicipalities,
                    "Michoacán" to michoacanMunicipalities,
                    "Morelos" to morelosMunicipalities,
                    "Nayarit" to nayaritMunicipalities,
                    "Nuevo León" to nuevoLeonMunicipalities,
                    "Oaxaca" to oaxacaMunicipalities,
                    "Puebla" to pueblaMunicipalities,
                    "Querétaro" to queretaroMunicipalities,
                    "Quintana Roo" to quintanaRooMunicipalities,
                    "San Luis Potosí" to sanLuisPotosiMunicipalities,
                    "Sinaloa" to sinaloaMunicipalities,
                    "Sonora" to sonoraMunicipalities,
                    "Tabasco" to tabascoMunicipalities,
                    "Tamaulipas" to tamaulipasMunicipalities,
                    "Tlaxcala" to tlaxcalaMunicipalities,
                    "Veracruz" to veracruzMunicipalities,
                    "Yucatán" to yucatanMunicipalities,
                    "Zacatecas" to zacatecasMunicipalities,
                    "Estado de México" to estadoDeMexicoMunicipalities
                )
                val image: Painter = painterResource(id = R.drawable.frisa)
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp, 100.dp)
                        .align(Alignment.CenterHorizontally)
                )

                CreateAccountTextField(value = name, onValueChange = { name = it }, label = "Name")
                CreateAccountTextField(value = lastName, onValueChange = { lastName = it }, label = "Last Name")
                CreateAccountTextField(value = email, onValueChange = { email = it }, label = "Email", keyboardType = KeyboardType.Email)
                CreateAccountTextField(value = password, onValueChange = { password = it }, label = "Password", keyboardType = KeyboardType.Password)
                CreateAccountTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = "Confirm Password", keyboardType = KeyboardType.Password)
                CreateAccountTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = "Phone Number", keyboardType = KeyboardType.Phone)
                // CreateAccountTextField(value = state, onValueChange = { state = it }, label = "State"),
                // For States dropdown
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp)
                ) {
                    OutlinedTextField(
                        value = state,
                        readOnly = true,
                        onValueChange = { },
                        label = { Text("State") },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                // Hide the keyboard
                                focusManager.clearFocus()
                            }
                        ),
                        trailingIcon = {
                            // Dropdown Arrow
                            Text(
                                text = "▼",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable { isDropdownExpanded = true }
                            )
                        },
                        modifier = Modifier
                            .clickable { isDropdownExpanded = true }
                            .fillMaxWidth()
                    )

                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        mapStates.keys.forEach { dropdownState ->
                            DropdownMenuItem(onClick = {
                                state = dropdownState
                                selectedState = dropdownState
                                isDropdownExpanded = false
                            }) {
                                Text(text = dropdownState)
                            }
                        }
                    }
                }
                // Municipality dropdown
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp)
                ) {
                    OutlinedTextField(
                        value = city,
                        onValueChange = { },
                        label = { Text("Municipality") },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                // Hide the keyboard
                                focusManager.clearFocus()
                            }
                        ),
                        trailingIcon = {
                            // Dropdown Arrow
                            Text(
                                text = "▼",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable { isDropdownExpanded = true }
                            )
                        },
                        modifier = Modifier
                            .clickable { isDropdownExpanded = true }
                            .fillMaxWidth()
                    )

                    DropdownMenu(
                        expanded = isCityDropdownExpanded,
                        onDismissRequest = { isCityDropdownExpanded = false }
                    ) {
                        mapStates[selectedState]?.forEach { municipality ->
                            DropdownMenuItem(onClick = {
                                city = municipality
                                isCityDropdownExpanded = false
                            }) {
                                Text(text = municipality)
                            }
                        }
                    }
                }

                // CreateAccountTextField(value = city, onValueChange = { city = it }, label = "Municipality")
                Button(onClick = {
                    // println("Create Account button clicked")
                    navController.navigate("login")
                }) {
                    Text("Create Account")
                }
            }
        }
    }
}

@Composable
fun CreateAccountTextField(value: String, onValueChange: (String) -> Unit, label: String, keyboardType: KeyboardType = KeyboardType.Text) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    )
}

