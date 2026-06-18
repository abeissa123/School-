package com.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BrandBlue
import com.example.ui.theme.BrandGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(role: String, onLogout: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GarouaSchool", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BrandBlue),
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Déconnexion", tint = Color.White)
                    }
                }
            )
        },
        floatingActionButton = {
            if (role == "Enseignant" || role == "Directeur" || role == "Administrateur") {
                FloatingActionButton(onClick = { /* TODO: Add Actions */ }, containerColor = BrandBlue, contentColor = Color.White) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                UserProfileHeader(role)
            }
            
            item {
                Text("Tableau de bord - $role", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }

            item {
                when (role) {
                    "Élève" -> StudentModules()
                    "Parent" -> ParentModules()
                    "Enseignant" -> TeacherModules()
                    "Directeur" -> DirectorModules()
                    "Administrateur" -> AdminModules()
                    else -> StudentModules()
                }
            }
        }
    }
}

@Composable
fun UserProfileHeader(role: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(BrandGreen.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = "Profile", tint = BrandGreen, modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("Bienvenue,", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            Text(
                text = "Utilisateur ($role)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun StudentModules() {
    DashboardGrid(
        items = listOf(
            DashboardItem("Mes Notes", Icons.Default.Grade, BrandBlue),
            DashboardItem("Emploi du temps", Icons.Default.DateRange, BrandGreen),
            DashboardItem("Absences", Icons.Default.Warning, Color(0xFFFFC107)),
            DashboardItem("Paiements", Icons.Default.Payment, Color(0xFFDC3545)),
            DashboardItem("Bibliothèque", Icons.Default.LibraryBooks, Color(0xFF6f42c1)),
            DashboardItem("Messagerie", Icons.Default.Message, Color(0xFF17a2b8))
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
    ActivityCard(title = "Dernière Note", description = "Mathématiques : 16/20 - Mention Très Bien")
}

@Composable
fun ParentModules() {
    DashboardGrid(
        items = listOf(
            DashboardItem("Suivi des enfants", Icons.Default.FamilyRestroom, BrandBlue),
            DashboardItem("Paiements & Reçus", Icons.Default.AccountBalanceWallet, BrandGreen),
            DashboardItem("Carnet de notes", Icons.Default.FactCheck, Color(0xFF6f42c1)),
            DashboardItem("Rendez-vous", Icons.Default.Event, Color(0xFF17a2b8))
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
    ActivityCard(title = "Scolarité", description = "Le paiement de la tranche 2 est attendu avant le 15.")
}

@Composable
fun TeacherModules() {
    DashboardGrid(
        items = listOf(
            DashboardItem("Mes Classes", Icons.Default.Class, BrandBlue),
            DashboardItem("Saisie des notes", Icons.Default.Edit, BrandGreen),
            DashboardItem("Appel & Présences", Icons.Default.FactCheck, Color(0xFFFFC107)),
            DashboardItem("Devoirs", Icons.Default.Assignment, Color(0xFF6f42c1))
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
    ActivityCard(title = "Prochain Cours", description = "Lycée - Terminale C - 10:00 AM (Mathématiques)")
}

@Composable
fun DirectorModules() {
    DashboardGrid(
        items = listOf(
            DashboardItem("Inscriptions", Icons.Default.HowToReg, BrandBlue),
            DashboardItem("Statistiques", Icons.Default.BarChart, BrandGreen),
            DashboardItem("Personnel", Icons.Default.Groups, Color(0xFF17a2b8)),
            DashboardItem("Emplois du temps", Icons.Default.Schedule, Color(0xFF6f42c1))
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
    ActivityCard(title = "Synthèse", description = "1420 élèves inscrits, 98% des frais de scolarité recouvrés.")
}

@Composable
fun AdminModules() {
    DashboardGrid(
        items = listOf(
            DashboardItem("Écoles", Icons.Default.Business, BrandBlue),
            DashboardItem("Utilisateurs", Icons.Default.AdminPanelSettings, BrandGreen),
            DashboardItem("Abonnements", Icons.Default.CardMembership, Color(0xFFFFC107)),
            DashboardItem("Revenus", Icons.Default.AttachMoney, Color(0xFF28a745)),
            DashboardItem("Logs", Icons.Default.List, Color(0xFF6c757d))
        )
    )
    Spacer(modifier = Modifier.height(16.dp))
    ActivityCard(title = "Système", description = "Instance GarouaSchool. 3 nouveaux campus ajoutés ce mois.")
}

data class DashboardItem(val title: String, val icon: ImageVector, val color: Color)

@Composable
fun DashboardGrid(items: List<DashboardItem>) {
    Column {
        for (i in items.indices step 2) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                ModuleCard(item = items[i], modifier = Modifier.weight(1f))
                if (i + 1 < items.size) {
                    ModuleCard(item = items[i + 1], modifier = Modifier.weight(1f))
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ModuleCard(item: DashboardItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(120.dp)
            .testTag("module_${item.title}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { /* TODO: Navigate to module */ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = item.color,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ActivityCard(title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = BrandBlue)
            Spacer(modifier = Modifier.height(8.dp))
            Text(description, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
    }
}
