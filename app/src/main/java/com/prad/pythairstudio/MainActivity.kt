package com.prad.pythairstudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PythAirApp()
            }
        }
    }
}

@Composable
fun PythAirApp() {
    var tab by remember { mutableStateOf(0) }
    var code by remember {
        mutableStateOf(
            "screen Main {\n" +
                "    title: \"PythAIR\"\n" +
                "    text: \"Hello from PythAIR APK\"\n" +
                "}"
        )
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF10141F), Color(0xFF1A2233))
                    )
                )
        ) {
            Header()
            Divider(color = Color.White.copy(alpha = 0.12f))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(14.dp)
            ) {
                when (tab) {
                    0 -> EditorScreen(code = code, onCodeChange = { code = it })
                    1 -> BrowserScreen()
                    2 -> FilesScreen()
                    3 -> ConsoleScreen(code)
                    4 -> SettingsScreen()
                }
            }
            NavigationBar {
                NavigationBarItem(
                    selected = tab == 0,
                    onClick = { tab = 0 },
                    icon = { Icon(Icons.Default.PlayArrow, contentDescription = null) },
                    label = { Text("Editor") }
                )
                NavigationBarItem(
                    selected = tab == 1,
                    onClick = { tab = 1 },
                    icon = { Icon(Icons.Default.Language, contentDescription = null) },
                    label = { Text("Browser") }
                )
                NavigationBarItem(
                    selected = tab == 2,
                    onClick = { tab = 2 },
                    icon = { Icon(Icons.Default.Folder, contentDescription = null) },
                    label = { Text("Files") }
                )
                NavigationBarItem(
                    selected = tab == 3,
                    onClick = { tab = 3 },
                    icon = { Icon(Icons.Default.Terminal, contentDescription = null) },
                    label = { Text("Console") }
                )
                NavigationBarItem(
                    selected = tab == 4,
                    onClick = { tab = 4 },
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("Settings") }
                )
            }
        }
    }
}

@Composable
fun Header() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "PythAIR Studio",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Minimal Android APK starter",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFB8C4D9)
        )
    }
}

@Composable
fun EditorScreen(code: String, onCodeChange: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1F2A40)),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text("PythAIR Editor", color = Color.White, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = code,
                    onValueChange = onCodeChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Monospace),
                    label = { Text("Code") }
                )
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(onClick = {}) { Text("Run") }
                    Button(onClick = {}) { Text("Save") }
                }
            }
        }
    }
}

@Composable
fun BrowserScreen() {
    InfoCard(
        title = "Browser",
        body = "This is the browser placeholder. Later this can switch backend between Android WebView and other engines."
    )
}

@Composable
fun FilesScreen() {
    InfoCard(
        title = "Files",
        body = "Project files screen placeholder for PythAIR projects, scripts, assets, and templates."
    )
}

@Composable
fun ConsoleScreen(code: String) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF121826)),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Console", color = Color.White, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(10.dp))
            Text("Ready to run PythAIR.", color = Color(0xFF84F4A0))
            Spacer(Modifier.height(8.dp))
            Text("Current code preview:", color = Color(0xFFB8C4D9))
            Spacer(Modifier.height(8.dp))
            Text(code, color = Color.White, fontFamily = FontFamily.Monospace)
        }
    }
}

@Composable
fun SettingsScreen() {
    InfoCard(
        title = "Settings",
        body = "Settings placeholder for theme, runtime, browser backend, and future Python/PythAIR engine options."
    )
}

@Composable
fun InfoCard(title: String, body: String) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F2A40)),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, color = Color.White, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(12.dp))
            Text(body, color = Color(0xFFD6E0F0))
            Spacer(Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .width(12.dp)
                        .height(12.dp)
                        .background(Color(0xFF6EA8FF), RoundedCornerShape(99.dp))
                )
                Spacer(Modifier.width(8.dp))
                Text("PythAIR UI active", color = Color.White)
            }
        }
    }
}
