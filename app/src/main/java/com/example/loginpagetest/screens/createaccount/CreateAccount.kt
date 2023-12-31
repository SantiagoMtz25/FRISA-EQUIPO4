package com.example.loginpagetest.screens.createaccount

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.example.loginpagetest.model.OrgRegister
import com.example.loginpagetest.model.OrgRegisterResponse
import com.example.loginpagetest.model.UserRegistrationResponse
import com.example.loginpagetest.service.OrgService
import com.example.loginpagetest.service.UserService
import com.example.loginpagetest.viewmodel.AppViewModel
import com.example.loginpagetest.viewmodel.OrgViewModel
import com.example.loginpagetest.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount(navController: NavHostController, appViewModel: AppViewModel) {
    val userViewModel = UserViewModel(UserService.instance)
    val orgViewModel = OrgViewModel(OrgService.instance)
    // val appViewModel: AppViewModel = viewModel()

    var name by remember { mutableStateOf("Org Alta Ejemplo") }
    var lastName by remember { mutableStateOf("Martinez") }
    var email by remember { mutableStateOf("ejemploNuevaOSC@gmail.com") }
    var password by remember { mutableStateOf("1234") }
    var confirmPassword by remember { mutableStateOf("1234") }
    var phoneNumber by remember { mutableStateOf("123456789") }
    var selectedState by remember { mutableStateOf("Nuevo León") }
    var selectedCity by remember { mutableStateOf("Monterrey") }
    var isStateDropdownExpanded by remember { mutableStateOf(false) }
    var isCityDropdownExpanded by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    var showSuccessSnackbar by remember { mutableStateOf(false) }

    var adminName by remember { mutableStateOf("Admin OSC Nombreq") }
    var rfc by remember { mutableStateOf("LOGJ580812RH7") }
    var description by remember { mutableStateOf("Esta organización trata de...") }
    var webpage by remember { mutableStateOf("www.ejemploosc.com.mx") }
    var category by remember { mutableStateOf("Salud") }

    val registrationResult = remember { mutableStateOf(UserRegistrationResponse()) }
    val orgRegistrationResult = remember { mutableStateOf(OrgRegisterResponse()) }

    LaunchedEffect(key1 = userViewModel) {
        userViewModel.registrationResult.collect { result ->
            if (result != null) {
                registrationResult.value = result
                navController.navigate("login")
            }
        }
    }
    LaunchedEffect(key1 = orgViewModel) {
        orgViewModel.orgRegisterResult.collect { result ->
            if (result != null) {
                orgRegistrationResult.value = result
                navController.navigate("login")
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorScheme.background
    ) {
        val coroutineScope = rememberCoroutineScope()
        val scrollState = rememberScrollState()
        val customRed = colorResource(id = R.color.logoRed)
        val customLighterRed = colorResource(id = R.color.almostlogored)
        val customGray = colorResource(id = R.color.logoGray)
        val customPink = colorResource(id = R.color.lightred_pink)
        var isUserAccount by rememberSaveable { mutableStateOf(false) }

        Column {
            CustomTopBar(title = "Crear Cuenta", navController = navController, screen = "login")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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
                buttonSlider { isChecked ->
                    isUserAccount = isChecked
                }
                if (isUserAccount) {
                    CreateAccountTextField(value = name, onValueChange = { name = it }, label = "Nombre",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = "Name Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = lastName, onValueChange = { lastName = it }, label = "Apellido",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = "Last Name Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = email, onValueChange = { email = it }, label = "Correo", keyboardType = KeyboardType.Email,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = password, onValueChange = { password = it }, label = "Contraseña", keyboardType = KeyboardType.Password, visualTransformation = PasswordVisualTransformation(),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = "Confirmar Contraseña", keyboardType = KeyboardType.Password, visualTransformation = PasswordVisualTransformation(),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = "Teléfono", keyboardType = KeyboardType.Phone,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Phone Icon",
                                tint = customLighterRed
                            )
                        })
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp)
                    ) {
                        OutlinedTextField(
                            value = selectedState,
                            onValueChange = { /* Ignored for readOnly */ },
                            label = { Text("Estado") },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused) isStateDropdownExpanded =
                                        true
                                },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = customRed,
                                focusedIndicatorColor = customPink,
                                unfocusedIndicatorColor = customGray,
                                focusedLabelColor = customLighterRed
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Place,
                                    contentDescription = "Place Icon",
                                    tint = customLighterRed
                                )
                            },
                        )
                        DropdownMenu(
                            expanded =  isStateDropdownExpanded,
                            onDismissRequest = { isStateDropdownExpanded = false }
                        ) {
                            mapStates.keys.forEach { state ->
                                DropdownMenuItem(onClick = {
                                    isStateDropdownExpanded = false
                                    selectedState = state
                                    selectedCity = "Select City" // Reset the city selection
                                }) {
                                    Text(text = state)
                                }
                            }
                        }
                    }
                    // For municipalities
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp)
                    ) {
                        OutlinedTextField(
                            value = selectedCity,
                            onValueChange = { /* Ignored for readOnly */ },
                            label = { Text("Ciudad") },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused) isCityDropdownExpanded =
                                        true
                                },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = customRed,
                                focusedIndicatorColor = customPink,
                                unfocusedIndicatorColor = customGray,
                                focusedLabelColor = customLighterRed
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Home Icon",
                                    tint = customLighterRed
                                )
                            },
                        )
                        DropdownMenu(
                            expanded = isCityDropdownExpanded,
                            onDismissRequest = { isCityDropdownExpanded = false }
                        ) {
                            mapStates[selectedState]?.forEach { city ->  // Get cities for the selected state
                                DropdownMenuItem(onClick = {
                                    isCityDropdownExpanded = false
                                    selectedCity = city
                                }) {
                                    Text(text = city)
                                }
                            } ?: DropdownMenuItem(onClick = { }) {
                                Text(text = "No hay ciudades disponibles")
                            }
                        }
                    }
                    LaunchedEffect(showSnackbar, showSuccessSnackbar) {
                        // If snackbar is shown, scroll all the way down
                        if (showSnackbar) {
                            scrollState.animateScrollTo(scrollState.maxValue)
                        }
                        if (showSuccessSnackbar) {
                            scrollState.animateScrollTo(scrollState.maxValue)
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    MaterialTheme(
                        colorScheme = MaterialTheme.colorScheme.copy(primary = customRed, onPrimary = Color.White)
                    ) {
                        Button(onClick = {
                            if (name.isNotEmpty() &&
                                lastName.isNotEmpty() &&
                                email.isNotEmpty() &&
                                password.isNotEmpty() &&
                                confirmPassword.isNotEmpty() &&
                                phoneNumber.isNotEmpty() &&
                                selectedState.isNotEmpty() &&
                                selectedCity.isNotEmpty() &&
                                password == confirmPassword
                            ) {
                                showSuccessSnackbar = true
                                coroutineScope.launch {
                                    delay(1500)
                                }
                                userViewModel.addUser(name, lastName, email, password,
                                    phoneNumber, selectedState, selectedCity)

                            } else {
                                showSnackbar = true
                            }
                        }) {
                            Text("Crear Cuenta", color = Color.White)
                        }

                        if (showSnackbar && !showSuccessSnackbar) {
                            Snackbar(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .background(Color.Red),
                                action = {
                                    TextButton(onClick = { showSnackbar = false }) {
                                        Text(
                                            "Quitar",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            ) {
                                Text("Llene todos los campos y asegurese que las contraseñas coinciden", color = Color.White)
                            }
                        }

                        if (showSuccessSnackbar) {
                            Snackbar(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .background(Color.Green),
                                action = {
                                    TextButton(onClick = { showSuccessSnackbar = false }) {
                                        Text(
                                            "Quitar",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            ) {
                                Text("Gracias! Cuenta creada exitosamente", color = Color.White)
                            }
                        }
                    }
                } else {
                    // add here the osc create account screen
                    CreateAccountTextField(value = name, onValueChange = { name = it }, label = "OSC Nombre",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = "OSC Name Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = adminName, onValueChange = { adminName = it }, label = "Nombre Administrador",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Administrator Name Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = rfc, onValueChange = { rfc = it }, label = "RFC (si dispone)",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "RFC Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = description, onValueChange = { description = it }, label = "Descripción",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Description Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, keyboardType = KeyboardType.Phone ,label = "Teléfono",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Phone Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = email, onValueChange = { email = it }, keyboardType = KeyboardType.Email, label = "Correo",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = webpage, onValueChange = { webpage = it }, label = "Link Página Web",
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Webpage Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = password, onValueChange = { password = it }, label = "Contraseña", keyboardType = KeyboardType.Password, visualTransformation = PasswordVisualTransformation(),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock Icon",
                                tint = customLighterRed
                            )
                        })
                    CreateAccountTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = "Confirmar Contraseña", keyboardType = KeyboardType.Password, visualTransformation = PasswordVisualTransformation(),
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock Icon",
                                tint = customLighterRed
                            )
                        })
                    var expanded by rememberSaveable { mutableStateOf(false) }
                    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

                    val categories = listOf(
                        "Salud", "Educación", "Medio Ambiente", "Derechos humanos",
                        "Asociaciones Religiosas", "Transporte Público", "Cultura", "Servicios Asistenciales"
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp)
                            .clickable(onClick = { expanded = true }) // this makes the whole field clickable
                    ) {
                        OutlinedTextField(
                            leadingIcon = {
                                androidx.compose.material.Icon(
                                    Icons.Default.List,
                                    contentDescription = "Category Icon",
                                    tint = customLighterRed
                                )
                            },
                            enabled = false,
                            modifier = Modifier.fillMaxWidth(),
                            value = categories[selectedIndex],
                            onValueChange = { /* do nothing as we are changing the value using dropdown selections */ },
                            label = { Text("Categoría") },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                // cursorColor = customRed,
                                // focusedIndicatorColor = customPink,
                                unfocusedIndicatorColor = customGray,
                                focusedLabelColor = customLighterRed,
                                disabledTextColor = Color.Black
                            ),
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            categories.forEachIndexed { index, category ->
                                DropdownMenuItem(onClick = {
                                    selectedIndex = index
                                    // category = category
                                    expanded = false
                                }) {
                                    Text(text = category)
                                }
                            }
                        }
                    }
                    // add state and city here
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp)
                    ) {
                        OutlinedTextField(
                            value = selectedState,
                            onValueChange = {  },
                            // readOnly = true,
                            label = { Text("Estado") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused) isStateDropdownExpanded =
                                        true
                                },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = customRed,
                                focusedIndicatorColor = customPink,
                                unfocusedIndicatorColor = customGray,
                                focusedLabelColor = customLighterRed
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Place,
                                    contentDescription = "Place Icon",
                                    tint = customLighterRed
                                )
                            },
                        )
                        DropdownMenu(
                            expanded = isStateDropdownExpanded,
                            onDismissRequest = { isStateDropdownExpanded = false }
                        ) {
                            mapStates.keys.forEach { state ->
                                DropdownMenuItem(onClick = {
                                    isStateDropdownExpanded = false
                                    selectedState = state
                                    selectedCity = "Select City" // Reset the city selection
                                }) {
                                    Text(text = state)
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp)
                    ) {
                        OutlinedTextField(
                            value = selectedCity,
                            onValueChange = { /* Ignored for readOnly */ },
                            label = { Text("Ciudad") },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused) isCityDropdownExpanded =
                                        true
                                },
                            colors = TextFieldDefaults.textFieldColors(
                                cursorColor = customRed,
                                focusedIndicatorColor = customPink,
                                unfocusedIndicatorColor = customGray,
                                focusedLabelColor = customLighterRed
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Home Icon",
                                    tint = customLighterRed
                                )
                            },
                        )
                        DropdownMenu(
                            expanded = isCityDropdownExpanded,
                            onDismissRequest = { isCityDropdownExpanded = false }
                        ) {
                            mapStates[selectedState]?.forEach { city ->  // Get cities for the selected state
                                DropdownMenuItem(onClick = {
                                    isCityDropdownExpanded = false
                                    selectedCity = city
                                }) {
                                    Text(text = city)
                                }
                            } ?: DropdownMenuItem(onClick = { }) {
                                Text(text = "No hay ciudades disponibles")
                            }
                        }
                    }
                    LaunchedEffect(showSnackbar, showSuccessSnackbar) {
                        // If snackbar is shown, scroll all the way down
                        if (showSnackbar) {
                            scrollState.animateScrollTo(scrollState.maxValue)
                        }
                        if (showSuccessSnackbar) {
                            scrollState.animateScrollTo(scrollState.maxValue)
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    MaterialTheme(
                        colorScheme = MaterialTheme.colorScheme.copy(primary = customRed, onPrimary = Color.White)
                    ) {
                        Button(onClick = {
                            if (name.isNotEmpty() &&
                                adminName.isNotEmpty() &&
                                description.isNotEmpty() &&
                                phoneNumber.isNotEmpty() &&
                                selectedState.isNotEmpty() &&
                                selectedCity.isNotEmpty() &&
                                email.isNotEmpty() &&
                                webpage.isNotEmpty() &&
                                category.isNotEmpty() &&
                                password.isNotEmpty() &&
                                confirmPassword.isNotEmpty() &&
                                password == confirmPassword
                            ) {
                                showSuccessSnackbar = true
                                val organization = OrgRegister(
                                    name, adminName, rfc, description,
                                    phoneNumber, selectedState, selectedCity,
                                    email, webpage, category, password
                                )
                                coroutineScope.launch {
                                    delay(1500)
                                }
                                orgViewModel.addOrganization(appViewModel.getToken(), organization)

                            } else {
                                showSnackbar = true
                            }
                        }) {
                            Text("Crear Cuenta", color = Color.White)
                        }

                        if (showSnackbar && !showSuccessSnackbar) {
                            Snackbar(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .background(Color.Red),
                                action = {
                                    TextButton(onClick = { showSnackbar = false }) {
                                        Text(
                                            "Quitar",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            ) {
                                Text("Llene los apartados correctamente", color = Color.White)
                            }
                        }

                        if (showSuccessSnackbar) {
                            Snackbar(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .background(Color.Green),
                                action = {
                                    TextButton(onClick = { showSuccessSnackbar = false }) {
                                        Text(
                                            "Quitar",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            ) {
                                Text("Su solicitud se ha enviado, se le notificara el resultado por correo.", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    icon: @Composable (() -> Unit)? = null
) {
    val customRed = colorResource(id = R.color.logoRed)
    val customLighterRed = colorResource(id = R.color.almostlogored)
    val customGray = colorResource(id = R.color.logoGray)
    val customPink = colorResource(id = R.color.lightred_pink)

    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = customRed,
            focusedIndicatorColor = customPink,
            unfocusedIndicatorColor = customGray,
            focusedLabelColor = customLighterRed
        ),
        leadingIcon = icon
    )
}