package com.goal26.worldcup.data.repository

import com.goal26.worldcup.domain.model.Player
import com.goal26.worldcup.domain.model.TeamSquad

/**
 * Remaining team squads for full coverage of notable teams.
 */
object SquadDataMore {

    val squads = mapOf(
        "SUI" to TeamSquad("SUI", listOf(
            Player("Yann Sommer", 1, "GK", "Inter Milan", 37, 90, 0),
            Player("Ricardo Rodríguez", 13, "DF", "Betis", 33, 80, 4),
            Player("Manuel Akanji", 5, "DF", "Manchester City", 30, 65, 3, isCaptain = true),
            Player("Nico Elvedi", 4, "DF", "Borussia M'gladbach", 29, 50, 1),
            Player("Silvan Widmer", 2, "DF", "Mainz", 33, 40, 1),
            Player("Granit Xhaka", 10, "MF", "Bayer Leverkusen", 33, 130, 14),
            Player("Denis Zakaria", 6, "MF", "Monaco", 29, 55, 4),
            Player("Xherdan Shaqiri", 23, "FW", "Basel", 34, 120, 32),
            Player("Breel Embolo", 7, "FW", "Monaco", 29, 60, 15),
            Player("Noah Okafor", 19, "FW", "AC Milan", 25, 25, 7),
            Player("Dan Ndoye", 11, "FW", "Bologna", 25, 15, 3)
        )),
        "AUS" to TeamSquad("AUS", listOf(
            Player("Mathew Ryan", 1, "GK", "Roma", 34, 85, 0),
            Player("Aziz Behich", 16, "DF", "Dundee United", 35, 60, 3),
            Player("Harry Souttar", 19, "DF", "Leicester City", 27, 20, 4),
            Player("Kye Rowles", 3, "DF", "Hearts", 28, 15, 0),
            Player("Nathaniel Atkinson", 2, "DF", "Hearts", 25, 10, 0),
            Player("Aaron Mooy", 13, "MF", "Retired", 35, 60, 8, isCaptain = true),
            Player("Jackson Irvine", 7, "MF", "St. Pauli", 32, 55, 8),
            Player("Keanu Baccus", 23, "MF", "Mansfield Town", 28, 15, 0),
            Player("Mathew Leckie", 7, "FW", "Melbourne City", 35, 80, 14),
            Player("Craig Goodwin", 11, "FW", "Adelaide United", 32, 30, 5),
            Player("Nestory Irankunda", 17, "FW", "Bayern Munich", 19, 10, 2)
        )),
        "TUR" to TeamSquad("TUR", listOf(
            Player("Altay Bayındır", 1, "GK", "Manchester United", 27, 15, 0),
            Player("Mert Müldür", 2, "DF", "Fenerbahçe", 27, 30, 2),
            Player("Merih Demiral", 3, "DF", "Al-Ahli", 28, 45, 4),
            Player("Samet Akaydin", 4, "DF", "Fenerbahçe", 31, 20, 1),
            Player("Ferdi Kadıoğlu", 18, "DF", "Brighton", 25, 25, 1),
            Player("Hakan Çalhanoğlu", 10, "MF", "Inter Milan", 32, 90, 20, isCaptain = true),
            Player("İsmail Yüksek", 8, "MF", "Fenerbahçe", 25, 15, 0),
            Player("Arda Güler", 14, "MF", "Real Madrid", 21, 20, 5),
            Player("Kenan Yıldız", 11, "FW", "Juventus", 21, 15, 3),
            Player("Barış Alper Yılmaz", 7, "FW", "Galatasaray", 25, 15, 2),
            Player("Cenk Tosun", 9, "FW", "Beşiktaş", 35, 65, 20)
        )),
        "QAT" to TeamSquad("QAT", listOf(
            Player("Saad Al-Sheeb", 1, "GK", "Al-Sadd", 36, 85, 0),
            Player("Pedro Miguel", 2, "DF", "Al-Sadd", 36, 70, 5),
            Player("Bassam Al-Rawi", 15, "DF", "Al-Duhail", 29, 55, 2),
            Player("Homam Ahmed", 3, "DF", "Al-Gharafa", 25, 40, 1),
            Player("Hassan Al-Haydos", 10, "MF", "Al-Sadd", 35, 180, 40, isCaptain = true),
            Player("Abdulaziz Hatem", 6, "MF", "Al-Rayyan", 33, 70, 8),
            Player("Karim Boudiaf", 8, "MF", "Al-Duhail", 36, 65, 2),
            Player("Akram Afif", 11, "FW", "Al-Sadd", 27, 75, 30),
            Player("Almoez Ali", 19, "FW", "Al-Duhail", 28, 100, 50),
            Player("Mohammed Muntari", 9, "FW", "Al-Duhail", 33, 50, 15),
            Player("Ahmed Al-Rawi", 7, "FW", "Al-Rayyan", 25, 20, 3)
        )),
        "ECU" to TeamSquad("ECU", listOf(
            Player("Hernán Galíndez", 1, "GK", "Huracán", 37, 30, 0),
            Player("Ángelo Preciado", 2, "DF", "Genk", 28, 30, 1),
            Player("Piero Hincapié", 3, "DF", "Bayer Leverkusen", 23, 40, 2),
            Player("Félix Torres", 4, "DF", "Corinthians", 27, 35, 3),
            Player("Pervis Estupiñán", 7, "DF", "Brighton", 28, 40, 3),
            Player("Moisés Caicedo", 23, "MF", "Chelsea", 24, 40, 5, isCaptain = true),
            Player("Jhegson Méndez", 8, "MF", "Los Angeles FC", 29, 30, 2),
            Player("Jeremy Sarmiento", 17, "FW", "Brighton", 22, 15, 2),
            Player("Enner Valencia", 13, "FW", "Internacional", 36, 85, 40),
            Player("Gonzalo Plata", 19, "FW", "Al-Sadd", 24, 30, 6),
            Player("Kendry Páez", 10, "MF", "Chelsea", 18, 15, 3)
        )),
        "IRN" to TeamSquad("IRN", listOf(
            Player("Alireza Beiranvand", 1, "GK", "Persepolis", 32, 55, 0),
            Player("Sadegh Moharrami", 5, "DF", "Dinamo Zagreb", 28, 25, 1),
            Player("Morteza Pouraliganji", 8, "DF", "Persepolis", 34, 55, 3),
            Player("Ehsan Hajsafi", 3, "DF", "AEK Athens", 36, 130, 7, isCaptain = true),
            Player("Milad Mohammadi", 15, "DF", "AEK Athens", 32, 55, 1),
            Player("Saeid Ezatolahi", 6, "MF", "Vejle", 29, 55, 3),
            Player("Ali Gholizadeh", 7, "FW", "Charleroi", 28, 35, 5),
            Player("Mehdi Taremi", 9, "FW", "Inter Milan", 33, 80, 45),
            Player("Sardar Azmoun", 20, "FW", "Roma", 31, 75, 45),
            Player("Alireza Jahanbakhsh", 11, "FW", "Feyenoord", 33, 70, 15),
            Player("Allahyar Sayyadmanesh", 14, "FW", "Hull City", 23, 15, 2)
        )),
        "NOR" to TeamSquad("NOR", listOf(
            Player("Ørjan Nyland", 1, "GK", "Sevilla", 35, 20, 0),
            Player("Birger Meling", 5, "DF", "Rennes", 29, 40, 1),
            Player("Leo Østigård", 4, "DF", "Napoli", 25, 25, 2),
            Player("Stefan Strandberg", 3, "DF", "Valerenga", 35, 30, 1),
            Player("Marcus Holmgren Pedersen", 2, "DF", "Feyenoord", 26, 20, 1),
            Player("Martin Ødegaard", 10, "MF", "Arsenal", 27, 60, 10, isCaptain = true),
            Player("Sander Berge", 8, "MF", "Fulham", 28, 50, 5),
            Player("Fredrik Aursnes", 6, "MF", "Benfica", 30, 25, 1),
            Player("Erling Haaland", 9, "FW", "Manchester City", 25, 35, 32),
            Player("Alexander Sørloth", 11, "FW", "Atlético Madrid", 30, 40, 12),
            Player("Antonio Nusa", 7, "FW", "RB Leipzig", 21, 10, 2)
        ))
    )
}
