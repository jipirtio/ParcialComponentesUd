package com.ud.Viajes.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ud.Viajes.Logic.GestorViajes
import com.ud.Viajes.Logic.Viaje

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaViajesVista(controladorNavegacion: NavController) {
    val context = LocalContext.current
    val gestorViajes = GestorViajes(context)
    val viajes = gestorViajes.obtenerViajes()
    var textoBusqueda by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mis viajes",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        },
        floatingActionButton = {
            Button(onClick = { controladorNavegacion.navigate("nuevoViaje/0") }) {
                Text(text = "Agregar")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                label = { Text("Buscar por destino") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            val viajesFiltrados = viajes.filter {
                it.destino.contains(textoBusqueda, ignoreCase = true)
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(viajesFiltrados) { viaje ->
                    ViajeItem(viaje, controladorNavegacion)
                }
            }
        }
    }
}

@Composable
fun ViajeItem(viaje: Viaje, controladorNavegacion: NavController) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { controladorNavegacion.navigate("detallesViaje/${viaje.idViaje}") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = viaje.destino,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Fecha: ${viaje.fechaInicio} - ${viaje.fechaFin}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}