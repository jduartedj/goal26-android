package com.goal26.worldcup.data.repository

import com.goal26.worldcup.domain.model.Player
import com.goal26.worldcup.domain.model.TeamSquad

/**
 * Key squad data for top teams. Pre-tournament expected squads.
 * Final squads announced late May 2026.
 */
object SquadData {

    fun getSquad(teamCode: String): TeamSquad? = squads[teamCode] ?: SquadDataExtra.squads[teamCode] ?: SquadDataMore.squads[teamCode]

    private val squads = mapOf(
        "ARG" to TeamSquad("ARG", listOf(
            Player("Emiliano Martínez", 23, "GK", "Aston Villa", 33, 55, 0),
            Player("Nahuel Molina", 26, "DF", "Atlético Madrid", 28, 35, 3),
            Player("Cristian Romero", 6, "DF", "Tottenham", 28, 30, 2),
            Player("Lisandro Martínez", 25, "DF", "Manchester United", 28, 25, 1),
            Player("Nicolás Tagliafico", 3, "DF", "Lyon", 33, 50, 2),
            Player("Rodrigo De Paul", 7, "MF", "Atlético Madrid", 32, 60, 3),
            Player("Enzo Fernández", 24, "MF", "Chelsea", 25, 30, 5),
            Player("Alexis Mac Allister", 20, "MF", "Liverpool", 27, 35, 5),
            Player("Lionel Messi", 10, "FW", "Inter Miami", 38, 190, 109, isCaptain = true),
            Player("Julián Álvarez", 9, "FW", "Atlético Madrid", 26, 30, 10),
            Player("Lautaro Martínez", 22, "FW", "Inter Milan", 28, 55, 25)
        )),
        "BRA" to TeamSquad("BRA", listOf(
            Player("Alisson", 1, "GK", "Liverpool", 33, 70, 0),
            Player("Danilo", 2, "DF", "Juventus", 35, 60, 3),
            Player("Marquinhos", 4, "DF", "PSG", 32, 85, 7),
            Player("Éder Militão", 3, "DF", "Real Madrid", 28, 35, 2),
            Player("Vinícius Júnior", 7, "FW", "Real Madrid", 25, 40, 10),
            Player("Rodrygo", 11, "FW", "Real Madrid", 25, 30, 8),
            Player("Bruno Guimarães", 5, "MF", "Newcastle", 28, 30, 3),
            Player("Lucas Paquetá", 8, "MF", "West Ham", 28, 55, 12),
            Player("Raphinha", 19, "FW", "Barcelona", 29, 35, 10),
            Player("Endrick", 9, "FW", "Real Madrid", 19, 15, 5),
            Player("Neymar", 10, "FW", "Santos", 34, 130, 79, isCaptain = true)
        )),
        "FRA" to TeamSquad("FRA", listOf(
            Player("Mike Maignan", 16, "GK", "AC Milan", 31, 20, 0),
            Player("Jules Koundé", 5, "DF", "Barcelona", 27, 30, 2),
            Player("William Saliba", 17, "DF", "Arsenal", 25, 25, 1),
            Player("Dayot Upamecano", 4, "DF", "Bayern Munich", 27, 25, 1),
            Player("Theo Hernández", 22, "DF", "AC Milan", 28, 30, 3),
            Player("N'Golo Kanté", 13, "MF", "Al-Ittihad", 35, 60, 2),
            Player("Aurélien Tchouaméni", 8, "MF", "Real Madrid", 26, 40, 3),
            Player("Antoine Griezmann", 7, "FW", "Atlético Madrid", 35, 130, 46),
            Player("Kylian Mbappé", 10, "FW", "Real Madrid", 27, 85, 48, isCaptain = true),
            Player("Ousmane Dembélé", 11, "FW", "PSG", 29, 50, 8),
            Player("Randal Kolo Muani", 12, "FW", "PSG", 27, 25, 8)
        )),
        "ESP" to TeamSquad("ESP", listOf(
            Player("Unai Simón", 23, "GK", "Athletic Bilbao", 29, 35, 0),
            Player("Dani Carvajal", 2, "DF", "Real Madrid", 34, 50, 4),
            Player("Aymeric Laporte", 14, "DF", "Al-Nassr", 32, 25, 2),
            Player("Robin Le Normand", 24, "DF", "Atlético Madrid", 29, 15, 1),
            Player("Marc Cucurella", 17, "DF", "Chelsea", 27, 20, 0),
            Player("Rodri", 16, "MF", "Manchester City", 30, 60, 5),
            Player("Pedri", 8, "MF", "Barcelona", 23, 35, 3),
            Player("Gavi", 6, "MF", "Barcelona", 21, 30, 3),
            Player("Lamine Yamal", 19, "FW", "Barcelona", 18, 25, 8),
            Player("Nico Williams", 11, "FW", "Athletic Bilbao", 23, 25, 6),
            Player("Álvaro Morata", 7, "FW", "AC Milan", 33, 80, 36, isCaptain = true)
        )),
        "GER" to TeamSquad("GER", listOf(
            Player("Marc-André ter Stegen", 1, "GK", "Barcelona", 34, 45, 0),
            Player("Joshua Kimmich", 6, "DF", "Bayern Munich", 31, 95, 5, isCaptain = true),
            Player("Antonio Rüdiger", 2, "DF", "Real Madrid", 33, 65, 3),
            Player("Jonathan Tah", 4, "DF", "Bayer Leverkusen", 30, 25, 1),
            Player("Alphonso Davies", 19, "DF", "Bayern Munich", 25, 15, 1),
            Player("İlkay Gündoğan", 21, "MF", "Barcelona", 35, 75, 17),
            Player("Florian Wirtz", 10, "MF", "Bayer Leverkusen", 23, 30, 10),
            Player("Jamal Musiala", 14, "MF", "Bayern Munich", 23, 40, 8),
            Player("Leroy Sané", 19, "FW", "Bayern Munich", 30, 60, 13),
            Player("Kai Havertz", 7, "FW", "Arsenal", 27, 50, 20),
            Player("Niclas Füllkrug", 9, "FW", "West Ham", 33, 25, 15)
        )),
        "ENG" to TeamSquad("ENG", listOf(
            Player("Jordan Pickford", 1, "GK", "Everton", 32, 65, 0),
            Player("Kyle Walker", 2, "DF", "Manchester City", 36, 85, 1),
            Player("John Stones", 5, "DF", "Manchester City", 32, 75, 3),
            Player("Marc Guéhi", 6, "DF", "Crystal Palace", 25, 25, 1),
            Player("Luke Shaw", 3, "DF", "Manchester United", 30, 30, 1),
            Player("Declan Rice", 4, "MF", "Arsenal", 27, 55, 5),
            Player("Jude Bellingham", 10, "MF", "Real Madrid", 22, 45, 12, isCaptain = true),
            Player("Phil Foden", 47, "MF", "Manchester City", 26, 40, 6),
            Player("Bukayo Saka", 7, "FW", "Arsenal", 24, 50, 15),
            Player("Harry Kane", 9, "FW", "Bayern Munich", 32, 105, 68),
            Player("Cole Palmer", 11, "FW", "Chelsea", 24, 20, 5)
        )),
        "POR" to TeamSquad("POR", listOf(
            Player("Diogo Costa", 1, "GK", "Porto", 26, 25, 0),
            Player("João Cancelo", 20, "DF", "Al-Hilal", 32, 50, 4),
            Player("Rúben Dias", 4, "DF", "Manchester City", 29, 50, 2),
            Player("Pepe", 3, "DF", "Porto", 43, 140, 8),
            Player("Nuno Mendes", 19, "DF", "PSG", 24, 30, 1),
            Player("Bruno Fernandes", 8, "MF", "Manchester United", 31, 70, 20, isCaptain = true),
            Player("Bernardo Silva", 10, "MF", "Manchester City", 31, 85, 12),
            Player("Vitinha", 23, "MF", "PSG", 26, 30, 3),
            Player("Cristiano Ronaldo", 7, "FW", "Al-Nassr", 41, 215, 135),
            Player("Rafael Leão", 17, "FW", "AC Milan", 27, 35, 8),
            Player("Diogo Jota", 21, "FW", "Liverpool", 29, 40, 12)
        )),
        "USA" to TeamSquad("USA", listOf(
            Player("Matt Turner", 1, "GK", "Nottingham Forest", 32, 35, 0),
            Player("Sergiño Dest", 2, "DF", "PSV", 25, 35, 2),
            Player("Chris Richards", 15, "DF", "Crystal Palace", 25, 20, 0),
            Player("Tim Ream", 13, "DF", "Fulham", 38, 55, 1),
            Player("Antonee Robinson", 5, "DF", "Fulham", 28, 40, 2),
            Player("Tyler Adams", 4, "MF", "Bournemouth", 27, 40, 1, isCaptain = true),
            Player("Weston McKennie", 8, "MF", "Juventus", 27, 50, 10),
            Player("Giovanni Reyna", 7, "MF", "Borussia Dortmund", 23, 25, 5),
            Player("Christian Pulisic", 10, "FW", "AC Milan", 27, 70, 30),
            Player("Timothy Weah", 11, "FW", "Juventus", 26, 35, 5),
            Player("Folarin Balogun", 9, "FW", "Monaco", 25, 25, 7)
        ))
    )
}
