package com.goal26.worldcup.data.repository

import com.goal26.worldcup.domain.model.Player
import com.goal26.worldcup.domain.model.TeamSquad

/**
 * Extended squad data for remaining key teams.
 */
object SquadDataExtra {

    val squads = mapOf(
        "NED" to TeamSquad("NED", listOf(
            Player("Bart Verbruggen", 1, "GK", "Brighton", 23, 20, 0),
            Player("Denzel Dumfries", 22, "DF", "Inter Milan", 30, 55, 8),
            Player("Virgil van Dijk", 4, "DF", "Liverpool", 34, 75, 8, isCaptain = true),
            Player("Nathan Aké", 5, "DF", "Manchester City", 31, 45, 3),
            Player("Jorrel Hato", 3, "DF", "Ajax", 19, 10, 1),
            Player("Frenkie de Jong", 21, "MF", "Barcelona", 29, 55, 3),
            Player("Ryan Gravenberch", 6, "MF", "Liverpool", 24, 25, 2),
            Player("Xavi Simons", 10, "MF", "RB Leipzig", 23, 25, 5),
            Player("Cody Gakpo", 11, "FW", "Liverpool", 25, 40, 15),
            Player("Memphis Depay", 9, "FW", "Corinthians", 32, 95, 46),
            Player("Brian Brobbey", 7, "FW", "Ajax", 24, 15, 4)
        )),
        "JPN" to TeamSquad("JPN", listOf(
            Player("Zion Suzuki", 1, "GK", "Parma", 22, 10, 0),
            Player("Takehiro Tomiyasu", 2, "DF", "Arsenal", 27, 40, 1),
            Player("Ko Itakura", 22, "DF", "Borussia M'gladbach", 29, 30, 3),
            Player("Shogo Taniguchi", 5, "DF", "Kawasaki Frontale", 34, 20, 2),
            Player("Wataru Endo", 6, "MF", "Liverpool", 33, 60, 3, isCaptain = true),
            Player("Hidemasa Morita", 8, "MF", "Sporting CP", 29, 35, 3),
            Player("Takefusa Kubo", 11, "FW", "Real Sociedad", 25, 35, 5),
            Player("Kaoru Mitoma", 9, "FW", "Brighton", 29, 30, 8),
            Player("Daichi Kamada", 10, "MF", "Crystal Palace", 30, 35, 7),
            Player("Kyogo Furuhashi", 13, "FW", "Celtic", 31, 20, 5),
            Player("Ayase Ueda", 18, "FW", "Feyenoord", 27, 25, 10)
        )),
        "MEX" to TeamSquad("MEX", listOf(
            Player("Guillermo Ochoa", 13, "GK", "Salernitana", 41, 140, 0),
            Player("Jorge Sánchez", 4, "DF", "Cruz Azul", 28, 35, 1),
            Player("César Montes", 3, "DF", "Almería", 29, 45, 3),
            Player("Johan Vásquez", 5, "DF", "Genoa", 26, 30, 2),
            Player("Jesús Gallardo", 23, "DF", "Monterrey", 31, 70, 2),
            Player("Edson Álvarez", 4, "MF", "West Ham", 28, 65, 5, isCaptain = true),
            Player("Luis Romo", 7, "MF", "Cruz Azul", 30, 30, 4),
            Player("Diego Lainez", 10, "MF", "Tigres", 26, 20, 2),
            Player("Hirving Lozano", 22, "FW", "PSV", 30, 70, 18),
            Player("Raúl Jiménez", 9, "FW", "Fulham", 35, 105, 35),
            Player("Santiago Giménez", 19, "FW", "Feyenoord", 25, 25, 12)
        )),
        "MAR" to TeamSquad("MAR", listOf(
            Player("Yassine Bounou", 1, "GK", "Al-Hilal", 33, 60, 0),
            Player("Achraf Hakimi", 2, "DF", "PSG", 27, 75, 10, isCaptain = true),
            Player("Nayef Aguerd", 5, "DF", "West Ham", 30, 40, 3),
            Player("Noussair Mazraoui", 3, "DF", "Manchester United", 28, 35, 2),
            Player("Sofyan Amrabat", 4, "MF", "Fenerbahçe", 29, 60, 1),
            Player("Azzedine Ounahi", 8, "MF", "Marseille", 26, 30, 3),
            Player("Hakim Ziyech", 7, "FW", "Galatasaray", 33, 50, 20),
            Player("Youssef En-Nesyri", 9, "FW", "Roma", 29, 65, 25),
            Player("Sofiane Boufal", 11, "FW", "Al-Rayyan", 31, 30, 5),
            Player("Ibrahim Díaz", 10, "FW", "Real Madrid", 25, 10, 2),
            Player("Bilal El Khannouss", 14, "MF", "Leicester City", 20, 15, 2)
        )),
        "URU" to TeamSquad("URU", listOf(
            Player("Sergio Rochet", 1, "GK", "Inter Miami", 33, 20, 0),
            Player("Ronald Araújo", 4, "DF", "Barcelona", 27, 35, 2),
            Player("José María Giménez", 3, "DF", "Atlético Madrid", 31, 85, 4),
            Player("Nahitan Nández", 8, "MF", "Al-Ain", 29, 55, 4),
            Player("Federico Valverde", 15, "MF", "Real Madrid", 27, 60, 8, isCaptain = true),
            Player("Rodrigo Bentancur", 6, "MF", "Tottenham", 29, 55, 2),
            Player("Facundo Pellistri", 11, "FW", "Manchester United", 24, 25, 3),
            Player("Darwin Núñez", 9, "FW", "Liverpool", 27, 40, 15),
            Player("Luis Suárez", 7, "FW", "Inter Miami", 39, 140, 68),
            Player("Maximiliano Araújo", 22, "FW", "Sporting CP", 24, 10, 2),
            Player("Nicolás De La Cruz", 10, "MF", "Flamengo", 27, 30, 4)
        )),
        "COL" to TeamSquad("COL", listOf(
            Player("David Ospina", 1, "GK", "Atlético Nacional", 37, 75, 0),
            Player("Daniel Muñoz", 2, "DF", "Crystal Palace", 28, 30, 3),
            Player("Yerry Mina", 13, "DF", "Cagliari", 31, 45, 8),
            Player("Davinson Sánchez", 23, "DF", "Galatasaray", 30, 50, 2),
            Player("Johan Mojica", 17, "DF", "Mallorca", 32, 30, 1),
            Player("Richard Ríos", 6, "MF", "Palmeiras", 24, 20, 2),
            Player("Jefferson Lerma", 16, "MF", "Crystal Palace", 30, 55, 3),
            Player("James Rodríguez", 10, "MF", "León", 34, 110, 28, isCaptain = true),
            Player("Luis Díaz", 7, "FW", "Liverpool", 29, 45, 10),
            Player("Jhon Arias", 11, "FW", "Fluminense", 27, 20, 5),
            Player("Rafael Santos Borré", 9, "FW", "Internacional", 30, 25, 5)
        )),
        "CRO" to TeamSquad("CRO", listOf(
            Player("Dominik Livaković", 1, "GK", "Fenerbahçe", 29, 60, 0),
            Player("Josip Stanišić", 2, "DF", "Bayer Leverkusen", 24, 20, 1),
            Player("Joško Gvardiol", 20, "DF", "Manchester City", 24, 40, 3),
            Player("Duje Ćaleta-Car", 5, "DF", "Lyon", 30, 35, 2),
            Player("Luka Modrić", 10, "MF", "Real Madrid", 40, 180, 25, isCaptain = true),
            Player("Mateo Kovačić", 8, "MF", "Manchester City", 32, 110, 5),
            Player("Marcelo Brozović", 11, "MF", "Al-Nassr", 33, 100, 8),
            Player("Lovro Majer", 7, "MF", "Wolfsburg", 27, 25, 4),
            Player("Andrej Kramarić", 9, "FW", "Hoffenheim", 33, 90, 28),
            Player("Igor Matanović", 19, "FW", "Eintracht Frankfurt", 22, 10, 3),
            Player("Martin Baturina", 14, "MF", "Dinamo Zagreb", 21, 15, 2)
        )),
        "BEL" to TeamSquad("BEL", listOf(
            Player("Koen Casteels", 1, "GK", "Al-Qadsiah", 32, 20, 0),
            Player("Timothy Castagne", 21, "DF", "Fulham", 30, 45, 3),
            Player("Wout Faes", 4, "DF", "Leicester City", 26, 25, 1),
            Player("Jan Vertonghen", 5, "DF", "Anderlecht", 39, 155, 10),
            Player("Arthur Theate", 3, "DF", "Rennes", 24, 20, 1),
            Player("Kevin De Bruyne", 7, "MF", "Manchester City", 35, 105, 28, isCaptain = true),
            Player("Youri Tielemans", 8, "MF", "Aston Villa", 29, 65, 8),
            Player("Orel Mangala", 6, "MF", "Lyon", 26, 15, 0),
            Player("Jérémy Doku", 11, "FW", "Manchester City", 24, 30, 4),
            Player("Loïs Openda", 9, "FW", "RB Leipzig", 26, 30, 10),
            Player("Leandro Trossard", 10, "FW", "Arsenal", 31, 45, 10)
        )),
        "KOR" to TeamSquad("KOR", listOf(
            Player("Kim Seung-gyu", 1, "GK", "Al-Shabab", 36, 65, 0),
            Player("Kim Min-jae", 3, "DF", "Bayern Munich", 29, 50, 4),
            Player("Kim Jin-su", 6, "DF", "Jeonbuk", 34, 70, 5),
            Player("Cho Yu-min", 4, "DF", "Wolverhampton", 25, 15, 0),
            Player("Lee Kang-in", 10, "MF", "PSG", 25, 55, 12, isCaptain = true),
            Player("Hwang In-beom", 8, "MF", "Red Star Belgrade", 29, 55, 7),
            Player("Paik Seung-ho", 16, "MF", "Jeonbuk", 29, 20, 1),
            Player("Son Heung-min", 7, "FW", "Tottenham", 33, 130, 50),
            Player("Hwang Hee-chan", 11, "FW", "Wolverhampton", 29, 50, 12),
            Player("Oh Hyeon-gyu", 9, "FW", "Celtic", 23, 15, 5),
            Player("Cho Gue-sung", 18, "FW", "Midtjylland", 28, 25, 8)
        )),
        "SEN" to TeamSquad("SEN", listOf(
            Player("Edouard Mendy", 16, "GK", "Al-Ahli", 34, 40, 0),
            Player("Kalidou Koulibaly", 3, "DF", "Al-Hilal", 35, 75, 3, isCaptain = true),
            Player("Abdou Diallo", 22, "DF", "Al-Arabi", 30, 35, 1),
            Player("Formose Mendy", 2, "DF", "Lens", 24, 10, 0),
            Player("Nampalys Mendy", 6, "MF", "Leicester City", 33, 30, 0),
            Player("Pape Gueye", 5, "MF", "Villarreal", 27, 20, 1),
            Player("Idrissa Gana Gueye", 8, "MF", "Everton", 36, 100, 5),
            Player("Ismaïla Sarr", 11, "FW", "Crystal Palace", 28, 55, 12),
            Player("Sadio Mané", 10, "FW", "Al-Nassr", 34, 100, 40),
            Player("Nicolas Jackson", 9, "FW", "Chelsea", 25, 20, 5),
            Player("Iliman Ndiaye", 7, "FW", "Everton", 26, 20, 4)
        )),
        "EGY" to TeamSquad("EGY", listOf(
            Player("Mohamed El-Shenawy", 1, "GK", "Al Ahly", 36, 35, 0),
            Player("Ahmed Fatouh", 7, "DF", "Zamalek", 32, 40, 2),
            Player("Mahmoud Hamdy", 6, "DF", "Al Ahly", 29, 25, 1),
            Player("Omar Gaber", 3, "DF", "Pyramids", 34, 45, 2),
            Player("Emam Ashour", 5, "MF", "Al Ahly", 27, 20, 2),
            Player("Nabil Dunga", 8, "MF", "Al Ain", 24, 15, 1),
            Player("Mohamed Salah", 10, "FW", "Liverpool", 34, 100, 58, isCaptain = true),
            Player("Mostafa Mohamed", 9, "FW", "Nantes", 28, 40, 12),
            Player("Trezeguet", 14, "FW", "Trabzonspor", 31, 55, 10),
            Player("Omar Marmoush", 11, "FW", "Manchester City", 27, 25, 8),
            Player("Ibrahim Adel", 22, "FW", "Al Ahly", 23, 15, 4)
        )),
        "ALG" to TeamSquad("ALG", listOf(
            Player("Raïs M'Bolhi", 1, "GK", "Al-Ettifaq", 38, 90, 0),
            Player("Aïssa Mandi", 6, "DF", "Villarreal", 33, 80, 5),
            Player("Ramy Bensebaini", 5, "DF", "Borussia Dortmund", 31, 50, 3),
            Player("Youcef Atal", 2, "DF", "Nice", 30, 25, 3),
            Player("Ismaël Bennacer", 8, "MF", "AC Milan", 28, 50, 3),
            Player("Houssem Aouar", 10, "MF", "Al-Ittihad", 28, 20, 3),
            Player("Amine Gouiri", 7, "FW", "Rennes", 26, 25, 5),
            Player("Said Benrahma", 22, "FW", "Lyon", 30, 30, 7),
            Player("Islam Slimani", 13, "FW", "USM Alger", 38, 95, 45, isCaptain = true),
            Player("Baghdad Bounedjah", 9, "FW", "Al-Sadd", 33, 55, 30),
            Player("Mohamed Amoura", 11, "FW", "Wolfsburg", 24, 15, 5)
        ))
    )
}
