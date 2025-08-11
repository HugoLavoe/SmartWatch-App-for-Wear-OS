/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.myapplication.presentation
import android.app.DatePickerDialog
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.AppCard
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.example.myapplication.R
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            Navegacion()
        }
    }
}

// Pagina1
// Funciones para obtener la fecha y hora actual en la zona horaria de México
fun getCurrentTime(): String {
    val timeFormat = SimpleDateFormat("HH:mm", Locale("es", "MX"))
    timeFormat.timeZone = TimeZone.getTimeZone("Hugo/Mexico_City")
    return timeFormat.format(Date())
}

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("EEE dd MMM", Locale("es", "MX"))
    dateFormat.timeZone = TimeZone.getTimeZone("Hugo/Mexico_City")
    return dateFormat.format(Date()).replaceFirstChar { it.uppercase() }
}
//PORTADA
@Composable
fun PaginaPortada(navController: NavController) {
    var currentTime by remember { mutableStateOf(getCurrentTime()) }
    var currentDate by remember { mutableStateOf(getCurrentDate()) }

    // LaunchedEffect para actualizar el tiempo cada segundo
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = getCurrentTime()
            currentDate = getCurrentDate()
            delay(1000L) // Espera un segundo
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Color de fondo para evitar transparencias
    ) {
        // Fondo de pantalla
        Image(
            painter = painterResource(id = R.drawable.batman),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), // Ajusta el padding según tu diseño
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Contenido de la página
            Text(
                text = currentDate,
                textAlign = TextAlign.Center,
                color = Color(0xFFCEFF25),
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
            Text(
                text = "Hugo Lavoe",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )
            Text(
                text = currentTime,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 65.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )


            // Botones de navegación en  fila
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { navController.navigate("RutaDos") },
                    modifier = Modifier.size(35.dp), // Modificar tamaño del botón
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF86895D))// Establecer color de fondo verde
                )  {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                        contentDescription = "Siguiente",
                        modifier = Modifier.size(15.dp) // Modificar tamaño del icono
                    )
                }
            }
        }
    }
}
// Fin pagina1

//Inicio pagina2
data class App(val nombre: String, val imageResId: Int, val ruta: String)


@Composable
fun ListaApps(navController: NavController) {
    val apps = listOf(
        App("Run Week", R.drawable.uno, "RutaTres"),
        App("Power Yoga", R.drawable.dos, "RutaCuatro"),
        App("Running", R.drawable.tres, "RutaCinco"),
        App("Max Spd", R.drawable.cuatro, "RutaSeis"),
        App("Calculadora", R.drawable.calculadora, "RutaSiete"),
        App("Cronometro", R.drawable.cronometro, "RutaOcho"),
        App("Reproductor Musc", R.drawable.musica, "RutaNueve"),
        App("Emociones", R.drawable.emociones, "RutaDiez"),
        App("Galeria", R.drawable.galeria, "RutaOnce"),
        App("Notas", R.drawable.notas, "RutaDieciSeis"),
        App("Mensajes", R.drawable.chat, "RutaDoce"),
        App("Configuracion", R.drawable.configuraciones, "RutaDieciSiete"),
        App("HOME", R.drawable.inicio, "RutaUno")
    )

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Permitir desplazamiento vertical
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "APPS",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 20.sp,
            )
        }

        apps.forEach { app ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clickable {
                        if (app.ruta.isNotEmpty()) {
                            navController.navigate(app.ruta)
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = app.imageResId),
                    contentDescription = app.nombre,
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = app.nombre,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

// MENU
@Composable
fun PaginaMenu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ListaApps(navController)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("RutaUno") },
            modifier = Modifier.size(50.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
//Fin pagina2

//Inicio pagina3
@Composable
fun IconoImagen(resourceId: Int, contentDescription: String) {
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color(0xFFCEFF25))
            .padding(5.dp)
    )
}

@Composable
fun IconosRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(ScrollState(0))
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        val iconos = listOf(
            R.drawable.baseline_directions_run_24 to "Icon Run",
            R.drawable.baseline_accessibility_24 to "Icon Estrangement",
            R.drawable.baseline_directions_bike_24 to "Icon Bike"
        )
        repeat(1) {
            iconos.forEach { (resId, desc) ->
                IconoImagen(resourceId = resId, contentDescription = desc)
            }
        }
    }
}
@Composable
fun Boton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Int = 65,
    height: Int = 23,
    cornerRadius: Int = 8,
    fontSize: Int = 14
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.DarkGray,
            contentColor = Color.White
        ),
        modifier = modifier
            .padding(top = 16.dp)
            .size(width.dp, height.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            color = Color.White // Color del texto en blanco
        )
    }
}

//Diseño1
@Composable
fun PaginaDiseno1(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texto añadido
        Text(
            text = "1 run this week",
            textAlign = TextAlign.Center,
            color = Color(0xFFCEFF25),
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp)
        )
        // IconosRow añadido
        IconosRow()

        Boton(
            text = "More",
            onClick = { /* Acción del botón */ },
            modifier = Modifier.padding(top = 16.dp) // Ejemplo de modificador adicional
        )
        Button(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier.size(35.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCEFF25))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(15.dp)
            )
        }
    }
}
//Fin pagina3

//Inicio pagina4
@Composable
fun Boton2(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Int = 130,
    height: Int = 80,
    cornerRadius: Int = 30,
    fontSize: Int = 20
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFCEFF25)

        ),
        modifier = modifier
            .size(width.dp, height.dp) // Ajusta el tamaño del botón según sea necesario
            .padding(top = 8.dp, bottom = 16.dp) // Ajusta el padding superior e inferior del botón
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            color = Color.Black // Color del texto en blanco
        )
    }
}
//Diseño2
@Composable
fun PaginaDiseno2(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Contenido Pagina
        Text(
            text = "Power Yoga",
            textAlign = TextAlign.Center,
            color = Color(0xFFCEFF25),
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp) // Ajusta el padding vertical según sea necesario
        )
        Boton2(
            text = "Start",
            onClick = { /* Acción del botón */ },
            modifier = Modifier.padding(vertical = 8.dp) // Ajusta el padding vertical del botón
        )

        Text(
            text = "Last session 45m",
            textAlign = TextAlign.Center,
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Ajusta el padding vertical según sea necesario
        )

        //Botones de navegacion
        Button(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier.size(35.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCEFF25))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(15.dp)
            )
        }
    }
}
//Fin pagina4

//Inicio pagina5

//Diseño3
@Composable
fun PaginaDiseno3(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_directions_run_24),
            contentDescription = "Hombre Corriendo",
            tint = Color(0xFFCEFF25), // Cambia el color del icono aquí
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = "7:21 / mi",
            textAlign = TextAlign.Center,
            color = Color.LightGray,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp) // Ajusta el padding vertical según sea necesario
        )
        // Texto añadido
        Text(
            text = "Running",
            textAlign = TextAlign.Center,
            color = Color(0xFFCEFF25),
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp) // Ajusta el padding vertical según sea necesario
        )


        Button(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier.size(35.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCEFF25))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(15.dp)
            )
        }
    }
}
//Fin pagina5

//Inicio pagina6
//Diseño4
@Composable
fun PaginaDiseno4(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "12:33",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(vertical = 10.dp) // Ajusta el padding vertical
        )

        // Fila principal que contiene las dos columnas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Primera columna
            Column(
                modifier = Modifier.weight(1f), // Toma el 50% del ancho disponible
                verticalArrangement = Arrangement.spacedBy(4.dp), // Ajusta el espaciado vertical entre los Text
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Max Spd",
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
                Text(
                    text = "46.5",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
                Text(
                    text = "mph",
                    textAlign = TextAlign.Center,
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
            }

            // Segunda columna
            Column(
                modifier = Modifier.weight(1f), // Toma el 50% del ancho disponible
                verticalArrangement = Arrangement.spacedBy(4.dp), // Ajusta el espaciado vertical entre los Text
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Distance",
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
                Text(
                    text = "21.8",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
                Text(
                    text = "mile",
                    textAlign = TextAlign.Center,
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp) // Ajusta el padding vertical
                )
            }
        }

        Button(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier.size(35.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCEFF25))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(15.dp)
            )
        }
    }
}
//Fin pagina6

//Inicio pagina7
@Composable
fun Calculadora(navController: NavController){
    var caja by remember { mutableStateOf("0") }
    var resultado by remember { mutableStateOf(0.0) }
    var operador by remember { mutableStateOf("") }
    var dato1 by remember { mutableStateOf(0.0) }

    fun PresionarBoton(button: String) {
        when (button) {
            in "0".."9", "." -> {
                if (caja == "0") caja = button else caja += button
            }
            in listOf("/", "*", "-", "+") -> {
                operador = button
                dato1 = caja.toDouble()
                caja = "0"
            }
            "=" -> {
                resultado = when (operador) {
                    "/" -> dato1 / caja.toDouble()
                    "*" -> dato1 * caja.toDouble()
                    "-" -> dato1 - caja.toDouble()
                    "+" -> dato1 + caja.toDouble()
                    else -> 0.0
                }
                caja = resultado.toString()
            }
            "C" -> {
                resultado = 0.0
                caja = "0"
            }
            "+/-" -> {
                resultado = -1.0 * caja.toDouble()
                caja = resultado.toString()
            }
            "X²" -> {
                resultado = caja.toDouble() * caja.toDouble()
                caja = resultado.toString()
            }
            "√" -> {
                resultado = sqrt(caja.toDouble())
                caja = resultado.toString()
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = { navController.navigate("RutaDos") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Text("<", color = Color.White)
            }
        }
        LazyColumn {
            item { Text(text = caja, style = androidx.wear.compose.material.MaterialTheme.typography.display3) }
            item { Renglones("7", "8", "9", "/", "C") { PresionarBoton(it) } }
            item { Renglones("4", "5", "6", "*", "+/-") { PresionarBoton(it) } }
            item { Renglones("1", "2", "3", "-", "X²") { PresionarBoton(it) } }
            item { Renglones("0", ".", "=", "+", "√") { PresionarBoton(it) } }
        }
    }
}
@Composable
fun Renglones(vararg buttons: String, onClick: (String) -> Unit) {
    Row {
        buttons.forEach { button ->
            Button(
                modifier = Modifier
                    .padding(all = 2.dp)
                    .size(width = 25.dp, height = 25.dp),
                onClick = { onClick(button) },
                colors = ButtonDefaults.buttonColors(backgroundColor = if (button in "0".."9") Color.LightGray else Color(
                    0xFF3F51B5
                )
                )) {
                Text(text = button)
            }
        }
    }
}

//CALCULADORA
@Composable
fun PaginaCalculadora(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Calculadora(navController)

        Button(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier.size(35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(15.dp)
            )
        }
    }
}
//Fin pagina7

//Inicio pagina8
@Composable
fun Cronometro(navController: NavController) {
    var time by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000L)
            time++
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = String.format("%02d:%02d:%02d", time / 3600, (time % 3600) / 60, time % 60),
                style = MaterialTheme.typography.display3,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(
                    onClick = { isRunning = true },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00BCD4)) // Green
                ) {
                    Text("Start", color = Color.White)
                }

                Button(
                    onClick = { isRunning = false },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF98FBF3)) // PaleGreen
                ) {
                    Text("Stop", color = Color.Black)
                }

                Button(
                    onClick = {
                        isRunning = false
                        time = 0L
                    },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF009688)) // SeaGreen
                ) {
                    Text("Reset", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(4.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3F51B5)) // DarkGreen
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
    }
}

// CRONOMETRO
@Composable
fun PaginaCronometro(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Cronometro(navController)

        Button(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier.size(35.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF03A9F4)) // Green
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(15.dp),
                tint = Color.White
            )
        }
    }
}
//Fin pagina8

//Inicio pagina9
//Musica
@Composable
fun MusicScreen(navController: NavController, previousScreen: String) {
    val context = LocalContext.current
    var currentSongIndex by remember { mutableStateOf(0) }
    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var currentPosition by remember { mutableStateOf(0) }

    val songs = listOf(
        Song(R.drawable.album1, R.raw.song1, "It Takes Two"),
        Song(R.drawable.album2, R.raw.song2, "Fast Love"),
        Song(R.drawable.abum3, R.raw.song3, "Si Pudiera")
    )

    fun playSong() {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, songs[currentSongIndex].audioResId).apply {
            seekTo(currentPosition)
            start()
        }
        isPlaying = true
    }

    fun pauseSong() {
        mediaPlayer?.pause()
        currentPosition = mediaPlayer?.currentPosition ?: 0
        isPlaying = false
    }

    fun nextSong() {
        currentPosition = 0
        currentSongIndex = (currentSongIndex + 1) % songs.size
        playSong()
    }

    fun previousSong() {
        currentPosition = 0
        currentSongIndex = if (currentSongIndex > 0) currentSongIndex - 1 else songs.size - 1
        playSong()
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = songs[currentSongIndex].backgroundResId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = songs[currentSongIndex].title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { previousSong() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_prev),
                        contentDescription = "Previous",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    if (isPlaying) {
                        pauseSong()
                    } else {
                        playSong()
                    }
                }) {
                    Icon(
                        painter = painterResource(id = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play),
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { nextSong() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_next),
                        contentDescription = "Next",
                        tint = Color.White
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            IconButton(onClick = { navController.navigate(previousScreen) }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

        }
    }
}

data class Song(val backgroundResId: Int, val audioResId: Int, val title: String)


//REPRODUCTOR MUSICA
@Composable
fun PaginaMusica(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MusicScreen(navController = navController, previousScreen = "RutaDos")

        Button(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier.size(35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(15.dp)
            )
        }
    }
}

//Fin pagina9



//Inicio pagina10
val listaEmociones = mutableListOf<Pair<String, String>>()

@Composable
fun Emociones(navController: NavController) {
    var selectedDate by remember { mutableStateOf("") }
    var showEmotionButtons by remember { mutableStateOf(false) }
    var selectedEmotion by remember { mutableStateOf("") }
    var showSavedMessage by remember { mutableStateOf(false) }
    val context = LocalContext.current

    fun showDatePickerDialog(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            R.style.CustomDatePickerDialog, // Usa el estilo personalizado
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val formattedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                onDateSelected(formattedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.spi),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(5.dp))

            if (selectedEmotion.isNotEmpty()) {
                Text(text = "Fecha: $selectedDate", fontSize = 10.sp, color=Color.Black)
                Spacer(modifier = Modifier.height(3.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Emoción: $selectedEmotion",
                        textAlign = TextAlign.Center,
                        fontSize = 8.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    when (selectedEmotion) {
                        "Enojo" -> {
                            Image(
                                painter = painterResource(id = R.drawable.en),
                                contentDescription = "Enojad@",
                                modifier = Modifier.size(65.dp)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.Red,
                                        shape = RoundedCornerShape(8.dp),
                                    )
                            ) {
                                Text(
                                    text = "El enojo es como un fuego que consume la paz interior.Transforma esa energía en acción positiva y verás cómo cambia tu mundo",
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                )
                            }
                        }

                        "Tristeza" -> {
                            Image(
                                painter = painterResource(id = R.drawable.t),
                                contentDescription = "tristeza",
                                modifier = Modifier.size(65.dp)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFB2EBF2),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            ) {
                                Text(
                                    text = "La tristeza es el eco silencioso del corazón.  Permítete sentirla, pero recuerda que siempre hay un nuevo amanecer lleno de esperanza.",
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                )
                            }
                        }

                        "Alegria" -> {
                            Image(
                                painter = painterResource(id = R.drawable.al),
                                contentDescription = "Alegria",
                                modifier = Modifier.size(65.dp)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.Yellow,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            ) {
                                Text(
                                    text = "La alegría es la melodía del alma que ilumina nuestros días. Cultiva la gratitud y verás cómo florece en cada momento.",
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
            } else {
                Spacer(modifier = Modifier.height(5.dp))
                if (!showEmotionButtons) {
                    Button(
                        onClick = {
                            showDatePickerDialog {
                                selectedDate = it
                                showEmotionButtons = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF98BF64),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.size(width = 150.dp, height = 60.dp)
                    ) {
                        Text("Registrar emoción", fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Button(
                        onClick = { navController.navigate("resenaSemanal") },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF728C69),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.size(width = 180.dp, height = 28.dp)
                    ) {
                        Text("Ver reseña semanal", fontSize = 8.sp)
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Button(
                            onClick = { navController.navigate("RutaDos") },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                        ) {
                            Text("Regresar", color = Color.Black)
                        }
                    }
                }
            }
            if (showEmotionButtons) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            onClick = {
                                selectedEmotion = "Enojo"
                                listaEmociones.add(Pair(selectedDate, selectedEmotion))
                                showEmotionButtons = false
                                showSavedMessage = true
                            },
                            modifier = Modifier.size(50.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.en),
                                contentDescription = "enojo",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Text("Enojo", textAlign = TextAlign.Center, fontSize = 14.sp,  color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            onClick = {
                                selectedEmotion = "Tristeza"
                                listaEmociones.add(Pair(selectedDate, selectedEmotion))
                                showEmotionButtons = false
                                showSavedMessage = true
                            },
                            modifier = Modifier.size(50.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.t),
                                contentDescription = "tristeza",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Text("Tristeza", textAlign = TextAlign.Center, fontSize = 14.sp,  color = Color.Black)
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        IconButton(
                            onClick = {
                                selectedEmotion = "Alegria"
                                listaEmociones.add(Pair(selectedDate, selectedEmotion))
                                showEmotionButtons = false
                                showSavedMessage = true
                            },
                            modifier = Modifier.size(50.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.al),
                                contentDescription = "alegria",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Text("Alegria", textAlign = TextAlign.Center, fontSize = 14.sp,  color = Color.Black)
                    }
                }
            }
            if (showSavedMessage) {
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "Día guardado",
                    color = Color(0xFFB2EBF2),
                    fontSize = 7.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.height(3.dp))
            // Aquí agregamos la condición para el botón de atrás
            if (selectedEmotion.isNotEmpty() || showEmotionButtons || showSavedMessage) {
                Button(
                    onClick = {  navController.navigate("RutaDiez")},
                    modifier = Modifier.size(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = "Atrás",
                        tint = Color(0xFFB2EBF2),
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ResenaSemanal(navController: NavController, listaEmociones: List<Pair<String, String>>) {
    val ultimosSieteDias = listaEmociones.takeLast(7)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.paya),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Reseña Semanal",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(ultimosSieteDias) { (fecha, emocion) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .border(1.dp, Color(0xFF98BF64), RoundedCornerShape(5.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "Fecha: $fecha",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Emoción: $emocion",
                            fontSize = 10.sp,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Button(
                onClick = { navController.navigate("RutaDiez") },
                modifier = Modifier.size(30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF86895D))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                    contentDescription = "Atras",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}
//EMOCIONES
@Composable
fun PaginaEmociones(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Emociones(navController)

        Button(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier.size(35.dp), // Modificar tamaño del botón
            // Establecer color de fondo verde
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(15.dp)
            )
        }
    }
}
//Fin pagina10

//Inicio pagina11
@Composable
fun Galeria(navController: NavController) {
    var selectedImage by remember { mutableStateOf<Int?>(null) }
    var images by remember {mutableStateOf(listOf(R.drawable.fondo1, R.drawable.fondo2, R.drawable.fondo3, R.drawable.fondo4,R.drawable.fondo5,R.drawable.fondo6,R.drawable.fondo7
        ,R.drawable.fondo8))}
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 1.dp)
    ) {
        Button(
            onClick = { navController.navigate("RutaDos") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        ) {
            Text("<", color = Color.White )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = " Galería ",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(9.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),//número de columnas
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(images.size) { index ->
                Image(
                    painter = painterResource(id = images[index]),
                    contentDescription = null,
                    modifier = Modifier
                        .size(77.dp)
                        .clickable { selectedImage = images[index] },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    if (selectedImage != null) {
        Dialog(
            onDismissRequest = { selectedImage = null }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = selectedImage!!),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentScale = ContentScale.Fit
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { selectedImage = null },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                            Text("❌")
                        }
                        Button(onClick = {
                            images = images.filterNot { it == selectedImage }
                            selectedImage = null
                        },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                            Text("🗑️")
                        }
                    }
                }
            }
        }
    }
}
//GALERIA
@Composable
fun PaginaGaleria(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Galeria(navController)

        Button(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier.size(35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(15.dp)
            )
        }
    }
}
//Fin pagina11

//Inicio pagina12
//Mensajes
@Composable
fun CardExample (
    navController: NavController,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
) {
    AppCard(
        modifier = modifier,
        appImage = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_mail_outline_24),
                contentDescription = "triggers open message action",
                modifier = iconModifier,
            )
        },
        appName = { Text("Messages") },
        time = { Text("12m") },
        title = { Text("Mamá") },
        onClick = { navController.navigate("RutaTrece") }
    ) {
        Text("En donde estas ?")
    }
}

//MENSAJES
@Composable
fun PaginaMensajes(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Contenido Pagina
        CardExample(
            navController = navController,
            modifier = Modifier.padding(16.dp),
            iconModifier = Modifier.size(24.dp)
        )

        Button(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier.size(35.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF728C69))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                modifier = Modifier.size(15.dp)

            )
        }
    }
}

@Composable
fun imagen(){
    Image(
        painter = painterResource(id = R.drawable.whastapp),
        contentDescription = "Icono whats",
        modifier = Modifier.size(50.dp)
    )
}

// Creamos una data class para representar los elementos de la lista de botones
data class Botones(val id: Int, val rotulo: String)

// Modifica la función ListaBotones para incluir el navController
@Composable
fun ListaBotones(navController: NavController) {
    // Inicializamos una lista con elementos
    val listaB = remember { mutableStateListOf(
        Botones(id = 0, rotulo = "✉\uFE0F\u200B"),
    ) }

    // Creamos un estado para almacenar el botón seleccionado
    val botonSeleccionado = remember { mutableStateOf<Botones?>(null) }
    val isEditing = remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    // Definimos el color personalizado
    val customColor = Color(0xFF728C69)

    // Creamos un LazyColumn para mostrar la lista de botones
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp)
    ) {
        item {
            Text(
                text = "Mamá",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Llego en media",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
        items(listaB) { button ->
            // Cada elemento de la lista es un botón
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                if (isEditing.value && botonSeleccionado.value == button) {
                    TextField(
                        value = textState.value,
                        onValueChange = { newText ->
                            textState.value = newText
                        },
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = {
                            // Guardar el texto editado en el botón y salir del modo de edición
                            botonSeleccionado.value?.let {
                                val index = listaB.indexOf(it)
                                listaB[index] = it.copy(rotulo = textState.value.text)
                            }
                            isEditing.value = false
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Guardar"
                        )
                    }
                } else {
                    Button(
                        onClick = {
                            botonSeleccionado.value = button
                            textState.value = TextFieldValue(button.rotulo)
                            isEditing.value = true
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (botonSeleccionado.value == button)
                                Color.Blue else Color.Gray
                        )
                    ) {
                        Text(text = button.rotulo)
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("Rutaquince")
                    },
                    containerColor = customColor,
                    contentColor = Color.White,
                ) {
                    Icon(Icons.Default.ThumbUp, contentDescription = "Navegar a Rutaquince")
                }
            }
        }
    }
}
@Composable
fun PaginaRespuestas(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imagen()
        ListaBotones(navController)

    }
}


@Composable
fun texto4() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color(0xFF98BF64), shape = RoundedCornerShape(45.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "ENVIADO",
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        )
    }
}

@Composable
fun PaginaCortina(navController: NavController) {
    // Columna para contenido de Pagina4
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        texto4()

        // Añadimos un espacio para separar el texto del botón
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de regresar
        IconButton(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier
                .size(48.dp)
                .background(Color.White, CircleShape) // Fondo blanco y forma circular
                .padding(8.dp), // Espaciado interno
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                tint = Color.Black // Color del icono
            )
        }
    }
}
//Fin pagina12


//Inicio Pagina13
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notas(navController: NavController) {
    var valor by remember { mutableStateOf("") }
    var todoList by remember { mutableStateOf(listOf<Pair<String, Boolean>>()) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.azul1),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(17.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Button(
                        onClick = { navController.navigate("RutaDos") },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                    ) {
                        Text("<", color = Color.White)
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedTextField(
                        value = valor,
                        onValueChange = { valor = it },
                        label = { Text(text = "Nuevo" , color = Color.White) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(focusedLabelColor = Color.White),
                        modifier = Modifier.weight(1f)
                    )

                    Button(
                        onClick = {
                            if (valor.isNotEmpty()) {
                                todoList = todoList + (valor to false)
                                valor = ""
                            }
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .padding(top = 15.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = "Agregar",
                            tint = Color.White,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }

            items(todoList) { task ->
                var isChecked by remember { mutableStateOf(task.second) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { checked ->
                            isChecked = checked
                            todoList = todoList.map {
                                if (it.first == task.first) {
                                    task.first to checked
                                } else {
                                    it
                                }
                            }
                        },
                        colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
                    )
                    Text(
                        text = task.first,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}
//Notas
@Composable
fun PaginaNotas(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Llamamos la funcion
        Notas(navController)


    }
}

//Fin pagina13


@Composable
fun PaginaConfig(navController: NavController) {
    // Estado para controlar si se muestra la información detallada
    var mostrarInfoDetallada by remember { mutableStateOf(false) }

    // Columna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Smartwatch",
            textAlign = TextAlign.Center,

            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Contenedor clicable para la imagen y detalles
        Box(
            modifier = Modifier
                .clickable {
                    mostrarInfoDetallada = !mostrarInfoDetallada
                }
                .padding(bottom = 16.dp)
        ) {
            // Imagen del producto
            Image(
                painter = painterResource(id = R.drawable.smart),
                contentDescription = "Smarwatch",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center)
            )

            // Mostrar información detallada si está activada
            if (mostrarInfoDetallada) {
                // Columna para el contenido detallado
                Column(
                    modifier = Modifier
                        .background(Color.Black, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Pulsera inteligente con pantalla AMOLED de 1.47 pulgadas, monitoreo de frecuencia cardíaca, SpO2, acelerómetro, giroscopio y sensor de luz.",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Espaciador flexible
        Spacer(modifier = Modifier.weight(1f))

        // Botón de regresar
        IconButton(
            onClick = { navController.navigate("RutaDos") },
            modifier = Modifier
                .size(48.dp)
                .background(Color.White, CircleShape)
                .padding(8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Regresar",
                tint = Color.Black
            )
        }
    }
}


// Navegacion
@Composable
fun Navegacion() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "RutaUno") {
        composable("RutaUno") { PaginaPortada(navController) }
        composable("RutaDos") { PaginaMenu(navController) }
        composable("RutaTres") { PaginaDiseno1(navController) }
        composable("RutaCuatro") { PaginaDiseno2(navController) }
        composable("RutaCinco") { PaginaDiseno3(navController) }
        composable("RutaSeis") { PaginaDiseno4(navController) }
        composable("RutaSiete") { PaginaCalculadora(navController) }
        composable("RutaOcho") { PaginaCronometro(navController) }
        composable("RutaNueve") { PaginaMusica(navController) }
        composable("RutaDiez") { PaginaEmociones(navController) }
        composable("resenasemanal") { ResenaSemanal(navController, listaEmociones) }
        composable("RutaOnce") { PaginaGaleria(navController) }
        composable("RutaDoce") { PaginaMensajes(navController) }
        composable("RutaTrece") { PaginaRespuestas(navController) }
        composable("RutaQuince") { PaginaCortina(navController) }
        composable("RutaMusica") { MusicScreen(navController, "RutaNueve") }
        composable("RutaDieciSeis") { PaginaNotas(navController) }
        composable("RutaDieciSiete") { PaginaConfig(navController) }



    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Navegacion()
}
