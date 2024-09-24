package com.ud.Viajes.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ud.Viajes.Logic.GestorViajes
import com.ud.Viajes.R
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallesViajeVista(controladorNavegacion: NavController, idViaje: Int) {
    val context = LocalContext.current
    val gestorViajes = GestorViajes(context)
    val viaje = gestorViajes.buscarViaje(idViaje)
    val formatoMoneda = NumberFormat.getCurrencyInstance(Locale.getDefault())
    var mostrarDialogo by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.titulo_detalles_viaje),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)) {
            viaje?.let {
                Text(
                    text = stringResource(R.string.etiqueta_numero_viaje, it.idViaje),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(R.string.etiqueta_destino, it.destino),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(R.string.etiqueta_fecha_inicio, it.fechaInicio),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(R.string.etiqueta_fecha_fin, it.fechaFin),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(
                        R.string.etiqueta_presupuesto,
                        formatoMoneda.format(it.presupuesto)
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(R.string.etiqueta_actividades, it.actividades),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(R.string.etiqueta_lugares, it.lugares),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OutlinedButton(onClick = {
                        controladorNavegacion.navigate("nuevoViaje/${viaje.idViaje}")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Editar",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Editar")
                    }
                    OutlinedButton(onClick = { mostrarDialogo = true }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Eliminar",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Eliminar")
                    }
                }

                AnimatedVisibility(visible = mostrarDialogo) {
                    AlertDialog(
                        onDismissRequest = { mostrarDialogo = false },
                        title = { Text("Confirmar eliminación") },
                        text = { Text("¿Estás seguro de que quieres eliminar este viaje?") },
                        confirmButton = {
                            Button(onClick = {
                                viaje.let {
                                    gestorViajes.eliminarViaje(it)
                                    controladorNavegacion.navigate("listaViajes")
                                }
                                mostrarDialogo = false
                            }) {
                                Text("Eliminar")
                            }
                        },
                        dismissButton = {
                            Button(onClick = { mostrarDialogo = false }) {
                                Text("Cancelar")
                            }
                        }
                    )
                }
            }
        }
    }
}