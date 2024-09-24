package com.ud.Viajes.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ud.Viajes.Logic.GestorViajes
import com.ud.Viajes.Logic.Viaje
import com.ud.Viajes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevoViajeVista(controladorNavegacion: NavController, idViaje: Int) {
    val context = LocalContext.current
    val gestorViajes = remember { GestorViajes(context) }
    var destino by remember { mutableStateOf("") }
    var presupuesto by remember { mutableStateOf("") }
    var fechaInicio by remember { mutableStateOf("") }
    var fechaFin by remember { mutableStateOf("") }
    var actividades by remember { mutableStateOf("") }
    var lugares by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            id = if (idViaje == 0) R.string.titulo_nuevo_viaje else R.string.titulo_editar_viaje,
                            idViaje
                        ),
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            OutlinedTextField(
                value = destino,
                onValueChange = { destino = it },
                label = { Text(stringResource(R.string.etiqueta_destino)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                value = presupuesto,
                onValueChange = { presupuesto = it },
                label = { Text(stringResource(R.string.etiqueta_presupuesto)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                value = fechaInicio,
                onValueChange = { fechaInicio = it },
                label = { Text(stringResource(R.string.etiqueta_fecha_inicio)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                value = fechaFin,
                onValueChange = { fechaFin = it },
                label = { Text(stringResource(R.string.etiqueta_fecha_fin)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                value = actividades,
                onValueChange = { actividades = it },
                label = { Text(stringResource(R.string.etiqueta_actividades)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            OutlinedTextField(
                value = lugares,
                onValueChange = { lugares = it },
                label = { Text(stringResource(R.string.etiqueta_lugares)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val nuevoViaje = Viaje(
                    idViaje = if (idViaje == 0) gestorViajes.obtenerViajes().size + 1 else idViaje,
                    destino = destino,
                    presupuesto = presupuesto.toDoubleOrNull() ?: 0.0,
                    fechaInicio = fechaInicio,
                    fechaFin = fechaFin,
                    actividades = actividades,
                    lugares = lugares
                )
                if (idViaje == 0) {
                    gestorViajes.agregarViaje(nuevoViaje)
                } else {
                    gestorViajes.modificarViaje(nuevoViaje)
                }
                controladorNavegacion.navigate("listaViajes")
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ) {
                Text(text = stringResource(id = if (idViaje == 0) R.string.boton_agregar else R.string.boton_modificar))
            }
        }
    }
}