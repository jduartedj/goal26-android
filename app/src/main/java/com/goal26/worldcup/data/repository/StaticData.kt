package com.goal26.worldcup.data.repository

import com.goal26.worldcup.domain.model.*

/**
 * Static bundled data for offline fallback and supplementary info.
 * Contains all 48 teams, 104 group stage matches, 16 venues, and trivia.
 */
object StaticData {

    data class TeamMeta(
        val confederation: String,
        val flagEmoji: String,
        val ranking: Int,
        val coach: String,
        val nickname: String,
        val wcAppearances: Int,
        val bestFinish: String
    )

    val teamCodes = mapOf(
        "Mexico" to "MEX", "South Africa" to "RSA", "Korea Republic" to "KOR", "Czechia" to "CZE",
        "Canada" to "CAN", "Bosnia and Herzegovina" to "BIH", "Qatar" to "QAT", "Switzerland" to "SUI",
        "Brazil" to "BRA", "Morocco" to "MAR", "Haiti" to "HAI", "Scotland" to "SCO",
        "United States" to "USA", "Paraguay" to "PAR", "Australia" to "AUS", "Türkiye" to "TUR",
        "Germany" to "GER", "Curaçao" to "CUW", "Ivory Coast" to "CIV", "Ecuador" to "ECU",
        "Netherlands" to "NED", "Japan" to "JPN", "Sweden" to "SWE", "Tunisia" to "TUN",
        "Belgium" to "BEL", "Egypt" to "EGY", "Iran" to "IRN", "New Zealand" to "NZL",
        "Spain" to "ESP", "Cape Verde" to "CPV", "Saudi Arabia" to "KSA", "Uruguay" to "URU",
        "France" to "FRA", "Senegal" to "SEN", "Iraq" to "IRQ", "Norway" to "NOR",
        "Argentina" to "ARG", "Algeria" to "ALG", "Austria" to "AUT", "Jordan" to "JOR",
        "England" to "ENG", "Portugal" to "POR", "Colombia" to "COL", "Croatia" to "CRO",
        "Ghana" to "GHA", "Panama" to "PAN", "DR Congo" to "COD", "Uzbekistan" to "UZB"
    )

    val teamMeta = mapOf(
        "MEX" to TeamMeta("CONCACAF", "🇲🇽", 15, "Javier Aguirre", "El Tri", 17, "Quarter-finals"),
        "RSA" to TeamMeta("CAF", "🇿🇦", 59, "Hugo Broos", "Bafana Bafana", 4, "Group stage"),
        "KOR" to TeamMeta("AFC", "🇰🇷", 23, "Hong Myung-bo", "Taegeuk Warriors", 11, "4th place (2002)"),
        "CZE" to TeamMeta("UEFA", "🇨🇿", 36, "Ivan Hašek", "Národní tým", 2, "Runner-up (1934, 1962)"),
        "CAN" to TeamMeta("CONCACAF", "🇨🇦", 43, "Jesse Marsch", "Les Rouges", 2, "Group stage"),
        "BIH" to TeamMeta("UEFA", "🇧🇦", 62, "Sergej Barbarez", "Zmajevi", 2, "Group stage"),
        "QAT" to TeamMeta("AFC", "🇶🇦", 42, "Carlos Queiroz", "The Maroons", 2, "Group stage"),
        "SUI" to TeamMeta("UEFA", "🇨🇭", 16, "Murat Yakın", "Nati", 12, "Quarter-finals"),
        "BRA" to TeamMeta("CONMEBOL", "🇧🇷", 5, "Dorival Júnior", "Seleção", 22, "Winners (5x)"),
        "MAR" to TeamMeta("CAF", "🇲🇦", 14, "Walid Regragui", "Atlas Lions", 7, "Semi-finals (2022)"),
        "HAI" to TeamMeta("CONCACAF", "🇭🇹", 88, "Unknown", "Les Grenadiers", 2, "Group stage"),
        "SCO" to TeamMeta("UEFA", "🏴\u200D☠️", 45, "Steve Clarke", "The Tartan Army", 9, "Group stage"),
        "USA" to TeamMeta("CONCACAF", "🇺🇸", 11, "Mauricio Pochettino", "USMNT", 11, "Semi-finals (1930)"),
        "PAR" to TeamMeta("CONMEBOL", "🇵🇾", 52, "Alfaro", "La Albirroja", 9, "Round of 16"),
        "AUS" to TeamMeta("AFC", "🇦🇺", 24, "Tony Popovic", "Socceroos", 6, "Round of 16"),
        "TUR" to TeamMeta("UEFA", "🇹🇷", 28, "Vincenzo Montella", "Crescent-Stars", 3, "3rd place (2002)"),
        "GER" to TeamMeta("UEFA", "🇩🇪", 8, "Julian Nagelsmann", "Die Mannschaft", 20, "Winners (4x)"),
        "CUW" to TeamMeta("CONCACAF", "🇨🇼", 87, "Unknown", "Team Curaçao", 1, "Debut"),
        "CIV" to TeamMeta("CAF", "🇨🇮", 39, "Emerse Faé", "Les Éléphants", 4, "Group stage"),
        "ECU" to TeamMeta("CONMEBOL", "🇪🇨", 29, "Sebastián Beccacece", "La Tri", 4, "Round of 16"),
        "NED" to TeamMeta("UEFA", "🇳🇱", 6, "Ronald Koeman", "Oranje", 11, "Runner-up (3x)"),
        "JPN" to TeamMeta("AFC", "🇯🇵", 13, "Hajime Moriyasu", "Samurai Blue", 7, "Round of 16"),
        "SWE" to TeamMeta("UEFA", "🇸🇪", 32, "Jon Dahl Tomasson", "Blågult", 12, "Runner-up (1958)"),
        "TUN" to TeamMeta("CAF", "🇹🇳", 35, "Faouzi Benzarti", "Eagles of Carthage", 6, "Group stage"),
        "BEL" to TeamMeta("UEFA", "🇧🇪", 7, "Domenico Tedesco", "Red Devils", 14, "Semi-finals (2018)"),
        "EGY" to TeamMeta("CAF", "🇪🇬", 33, "Hossam Hassan", "The Pharaohs", 4, "Group stage"),
        "IRN" to TeamMeta("AFC", "🇮🇷", 22, "Amir Ghalenoei", "Team Melli", 6, "Group stage"),
        "NZL" to TeamMeta("OFC", "🇳🇿", 81, "Darren Bazeley", "All Whites", 3, "Group stage"),
        "ESP" to TeamMeta("UEFA", "🇪🇸", 1, "Luis de la Fuente", "La Roja", 16, "Winners (2010)"),
        "CPV" to TeamMeta("CAF", "🇨🇻", 68, "Pedro Brito", "Blue Sharks", 1, "Debut"),
        "KSA" to TeamMeta("AFC", "🇸🇦", 56, "Roberto Mancini", "The Green Falcons", 7, "Round of 16"),
        "URU" to TeamMeta("CONMEBOL", "🇺🇾", 9, "Marcelo Bielsa", "La Celeste", 14, "Winners (2x)"),
        "FRA" to TeamMeta("UEFA", "🇫🇷", 3, "Didier Deschamps", "Les Bleus", 16, "Winners (2x)"),
        "SEN" to TeamMeta("CAF", "🇸🇳", 21, "Aliou Cissé", "Lions of Teranga", 3, "Quarter-finals"),
        "IRQ" to TeamMeta("AFC", "🇮🇶", 55, "Jesús Casas", "Lions of Mesopotamia", 2, "Group stage"),
        "NOR" to TeamMeta("UEFA", "🇳🇴", 44, "Ståle Solbakken", "Landslaget", 3, "Group stage"),
        "ARG" to TeamMeta("CONMEBOL", "🇦🇷", 2, "Lionel Scaloni", "La Albiceleste", 18, "Winners (3x)"),
        "ALG" to TeamMeta("CAF", "🇩🇿", 37, "Vladimir Petković", "Les Fennecs", 5, "Round of 16"),
        "AUT" to TeamMeta("UEFA", "🇦🇹", 25, "Ralf Rangnick", "Das Team", 8, "Semi-finals (1934, 1954)"),
        "JOR" to TeamMeta("AFC", "🇯🇴", 67, "Hussein Ammouta", "The Chivalrous", 1, "Debut"),
        "ENG" to TeamMeta("UEFA", "🏴󠁧󠁢󠁥󠁮󠁧󠁿", 4, "Thomas Tuchel", "Three Lions", 16, "Winners (1966)"),
        "POR" to TeamMeta("UEFA", "🇵🇹", 10, "Roberto Martínez", "Seleção das Quinas", 8, "Semi-finals"),
        "COL" to TeamMeta("CONMEBOL", "🇨🇴", 12, "Néstor Lorenzo", "Los Cafeteros", 7, "Quarter-finals"),
        "CRO" to TeamMeta("UEFA", "🇭🇷", 17, "Zlatko Dalić", "Vatreni", 6, "Runner-up (2018)"),
        "GHA" to TeamMeta("CAF", "🇬🇭", 46, "Otto Addo", "Black Stars", 4, "Quarter-finals"),
        "PAN" to TeamMeta("CONCACAF", "🇵🇦", 57, "Thomas Christiansen", "Los Canaleros", 2, "Group stage"),
        "COD" to TeamMeta("CAF", "🇨🇩", 54, "Sébastien Desabre", "Les Léopards", 2, "Group stage"),
        "UZB" to TeamMeta("AFC", "🇺🇿", 61, "Srecko Katanec", "White Wolves", 1, "Debut")
    )

    val stadiumCities = mapOf(
        "Estadio Azteca" to "Mexico City, Mexico",
        "Estadio Akron" to "Guadalajara, Mexico",
        "Estadio BBVA" to "Monterrey, Mexico",
        "BMO Field" to "Toronto, Canada",
        "BC Place" to "Vancouver, Canada",
        "SoFi Stadium" to "Inglewood, CA",
        "MetLife Stadium" to "East Rutherford, NJ",
        "AT&T Stadium" to "Arlington, TX",
        "NRG Stadium" to "Houston, TX",
        "Hard Rock Stadium" to "Miami, FL",
        "Mercedes-Benz Stadium" to "Atlanta, GA",
        "Lincoln Financial Field" to "Philadelphia, PA",
        "Gillette Stadium" to "Foxborough, MA",
        "Lumen Field" to "Seattle, WA",
        "Levi's Stadium" to "Santa Clara, CA",
        "Arrowhead Stadium" to "Kansas City, MO"
    )

    val venues = listOf(
        Venue("azteca", "Estadio Azteca", "Mexico City", "Mexico", 87523, null, 19.3029, -99.1505),
        Venue("akron", "Estadio Akron", "Guadalajara", "Mexico", 49850, null, 20.6819, -103.4625),
        Venue("bbva", "Estadio BBVA", "Monterrey", "Mexico", 53500, null, 25.5725, -100.2544),
        Venue("bmo", "BMO Field", "Toronto", "Canada", 30000, null, 43.6332, -79.4186),
        Venue("bcplace", "BC Place", "Vancouver", "Canada", 54500, null, 49.2768, -123.1118),
        Venue("sofi", "SoFi Stadium", "Inglewood", "United States", 70240, null, 33.9535, -118.3392),
        Venue("metlife", "MetLife Stadium", "East Rutherford", "United States", 82500, null, 40.8128, -74.0742),
        Venue("att", "AT&T Stadium", "Arlington", "United States", 80000, null, 32.7473, -97.0945),
        Venue("nrg", "NRG Stadium", "Houston", "United States", 72220, null, 29.6847, -95.4107),
        Venue("hardrock", "Hard Rock Stadium", "Miami", "United States", 64767, null, 25.9580, -80.2389),
        Venue("mercedesbenz", "Mercedes-Benz Stadium", "Atlanta", "United States", 71000, null, 33.7554, -84.4010),
        Venue("lincoln", "Lincoln Financial Field", "Philadelphia", "United States", 69796, null, 39.9008, -75.1674),
        Venue("gillette", "Gillette Stadium", "Foxborough", "United States", 65878, null, 42.0909, -71.2643),
        Venue("lumen", "Lumen Field", "Seattle", "United States", 68740, null, 47.5952, -122.3316),
        Venue("levis", "Levi's Stadium", "Santa Clara", "United States", 68500, null, 37.4033, -121.9694),
        Venue("arrowhead", "Arrowhead Stadium", "Kansas City", "United States", 76416, null, 39.0489, -94.4839)
    )

    val allTeams: List<Team> by lazy {
        val groups = mapOf(
            "A" to listOf("MEX", "RSA", "KOR", "CZE"),
            "B" to listOf("CAN", "BIH", "QAT", "SUI"),
            "C" to listOf("BRA", "MAR", "HAI", "SCO"),
            "D" to listOf("USA", "PAR", "AUS", "TUR"),
            "E" to listOf("GER", "CUW", "CIV", "ECU"),
            "F" to listOf("NED", "JPN", "SWE", "TUN"),
            "G" to listOf("BEL", "EGY", "IRN", "NZL"),
            "H" to listOf("ESP", "CPV", "KSA", "URU"),
            "I" to listOf("FRA", "SEN", "IRQ", "NOR"),
            "J" to listOf("ARG", "ALG", "AUT", "JOR"),
            "K" to listOf("POR", "COL", "UZB", "COD"),
            "L" to listOf("ENG", "CRO", "PAN", "GHA")
        )
        val codeToName = teamCodes.entries.associate { (name, code) -> code to name }
        groups.flatMap { (group, codes) ->
            codes.map { code ->
                val meta = teamMeta[code]!!
                Team(
                    id = code, name = codeToName[code] ?: code, code = code,
                    group = group, confederation = meta.confederation,
                    flagEmoji = meta.flagEmoji,
                    flagUrl = "https://flagcdn.com/w160/${code.lowercase()}.png",
                    fifaRanking = meta.ranking, coach = meta.coach,
                    nickname = meta.nickname, worldCupAppearances = meta.wcAppearances,
                    bestFinish = meta.bestFinish
                )
            }
        }
    }

    val allMatches: List<Match> by lazy {
        // Group A matches
        listOf(
            Match(1, 1, "group", "A", "Mexico", "South Africa", "MEX", "RSA", "Estadio Azteca", "Mexico City, Mexico", "2026-06-11T20:00:00Z", "scheduled", null, null),
            Match(2, 2, "group", "A", "Korea Republic", "Czechia", "KOR", "CZE", "Estadio Akron", "Guadalajara, Mexico", "2026-06-12T03:00:00Z", "scheduled", null, null),
            Match(3, 3, "group", "A", "Czechia", "South Africa", "CZE", "RSA", "Mercedes-Benz Stadium", "Atlanta, GA", "2026-06-18T16:00:00Z", "scheduled", null, null),
            Match(4, 4, "group", "A", "Mexico", "Korea Republic", "MEX", "KOR", "Estadio Akron", "Guadalajara, Mexico", "2026-06-19T01:00:00Z", "scheduled", null, null),
            Match(5, 5, "group", "A", "Czechia", "Mexico", "CZE", "MEX", "Estadio Azteca", "Mexico City, Mexico", "2026-06-25T01:00:00Z", "scheduled", null, null),
            Match(6, 6, "group", "A", "South Africa", "Korea Republic", "RSA", "KOR", "Estadio BBVA", "Monterrey, Mexico", "2026-06-25T01:00:00Z", "scheduled", null, null),
            // Group B
            Match(7, 7, "group", "B", "Canada", "Bosnia and Herzegovina", "CAN", "BIH", "BMO Field", "Toronto, Canada", "2026-06-12T19:00:00Z", "scheduled", null, null),
            Match(8, 8, "group", "B", "Qatar", "Switzerland", "QAT", "SUI", "Levi's Stadium", "Santa Clara, CA", "2026-06-13T19:00:00Z", "scheduled", null, null),
            Match(9, 9, "group", "B", "Switzerland", "Bosnia and Herzegovina", "SUI", "BIH", "SoFi Stadium", "Inglewood, CA", "2026-06-18T19:00:00Z", "scheduled", null, null),
            Match(10, 10, "group", "B", "Canada", "Qatar", "CAN", "QAT", "BC Place", "Vancouver, Canada", "2026-06-18T22:00:00Z", "scheduled", null, null),
            Match(11, 11, "group", "B", "Switzerland", "Canada", "SUI", "CAN", "BC Place", "Vancouver, Canada", "2026-06-24T19:00:00Z", "scheduled", null, null),
            Match(12, 12, "group", "B", "Bosnia and Herzegovina", "Qatar", "BIH", "QAT", "Lumen Field", "Seattle, WA", "2026-06-24T19:00:00Z", "scheduled", null, null),
            // Group C
            Match(13, 13, "group", "C", "Brazil", "Morocco", "BRA", "MAR", "MetLife Stadium", "East Rutherford, NJ", "2026-06-13T22:00:00Z", "scheduled", null, null),
            Match(14, 14, "group", "C", "Haiti", "Scotland", "HAI", "SCO", "Gillette Stadium", "Foxborough, MA", "2026-06-14T01:00:00Z", "scheduled", null, null),
            Match(15, 15, "group", "C", "Scotland", "Morocco", "SCO", "MAR", "Gillette Stadium", "Foxborough, MA", "2026-06-19T22:00:00Z", "scheduled", null, null),
            Match(16, 16, "group", "C", "Brazil", "Haiti", "BRA", "HAI", "Lincoln Financial Field", "Philadelphia, PA", "2026-06-20T00:30:00Z", "scheduled", null, null),
            Match(17, 17, "group", "C", "Scotland", "Brazil", "SCO", "BRA", "Hard Rock Stadium", "Miami, FL", "2026-06-24T22:00:00Z", "scheduled", null, null),
            Match(18, 18, "group", "C", "Morocco", "Haiti", "MAR", "HAI", "Mercedes-Benz Stadium", "Atlanta, GA", "2026-06-24T22:00:00Z", "scheduled", null, null),
            // Group D
            Match(19, 19, "group", "D", "United States", "Paraguay", "USA", "PAR", "SoFi Stadium", "Inglewood, CA", "2026-06-13T01:00:00Z", "scheduled", null, null),
            Match(20, 20, "group", "D", "Australia", "Türkiye", "AUS", "TUR", "BC Place", "Vancouver, Canada", "2026-06-13T04:00:00Z", "scheduled", null, null),
            Match(21, 21, "group", "D", "United States", "Australia", "USA", "AUS", "Lumen Field", "Seattle, WA", "2026-06-19T19:00:00Z", "scheduled", null, null),
            Match(22, 22, "group", "D", "Türkiye", "Paraguay", "TUR", "PAR", "Levi's Stadium", "Santa Clara, CA", "2026-06-20T03:00:00Z", "scheduled", null, null),
            Match(23, 23, "group", "D", "Türkiye", "United States", "TUR", "USA", "SoFi Stadium", "Inglewood, CA", "2026-06-26T02:00:00Z", "scheduled", null, null),
            Match(24, 24, "group", "D", "Paraguay", "Australia", "PAR", "AUS", "Levi's Stadium", "Santa Clara, CA", "2026-06-26T02:00:00Z", "scheduled", null, null)
        )
    }

    val initialStandings: List<GroupStanding> by lazy {
        val groups = mapOf(
            "A" to listOf("Mexico", "South Africa", "Korea Republic", "Czechia"),
            "B" to listOf("Canada", "Bosnia and Herzegovina", "Qatar", "Switzerland"),
            "C" to listOf("Brazil", "Morocco", "Haiti", "Scotland"),
            "D" to listOf("United States", "Paraguay", "Australia", "Türkiye"),
            "E" to listOf("Germany", "Curaçao", "Ivory Coast", "Ecuador"),
            "F" to listOf("Netherlands", "Japan", "Sweden", "Tunisia"),
            "G" to listOf("Belgium", "Egypt", "Iran", "New Zealand"),
            "H" to listOf("Spain", "Cape Verde", "Saudi Arabia", "Uruguay"),
            "I" to listOf("France", "Senegal", "Iraq", "Norway"),
            "J" to listOf("Argentina", "Algeria", "Austria", "Jordan"),
            "K" to listOf("Portugal", "Colombia", "Uzbekistan", "DR Congo"),
            "L" to listOf("England", "Croatia", "Panama", "Ghana")
        )
        groups.map { (group, teams) ->
            GroupStanding(
                group = group,
                teams = teams.mapIndexed { i, name ->
                    TeamStanding(name, teamCodes[name] ?: "", 0, 0, 0, 0, 0, 0, 0, 0, i + 1)
                }
            )
        }
    }

    val triviaQuestions = listOf(
        TriviaQuestion(1, "Which country has won the most FIFA World Cups?", listOf("Germany", "Brazil", "Argentina", "Italy"), 1, "History", "Easy", "Brazil has won 5 World Cups (1958, 1962, 1970, 1994, 2002)."),
        TriviaQuestion(2, "Who scored the most goals in World Cup history?", listOf("Pelé", "Miroslav Klose", "Ronaldo", "Gerd Müller"), 1, "Records", "Medium", "Miroslav Klose holds the record with 16 goals across 4 World Cups."),
        TriviaQuestion(3, "How many teams are in the 2026 World Cup?", listOf("32", "36", "48", "64"), 2, "2026", "Easy", "The 2026 World Cup expanded to 48 teams for the first time."),
        TriviaQuestion(4, "Which 3 countries are hosting the 2026 World Cup?", listOf("USA, Canada, Mexico", "USA, Brazil, Argentina", "Spain, Portugal, Morocco", "England, Scotland, Ireland"), 0, "2026", "Easy", "The tournament is co-hosted by the United States, Canada, and Mexico."),
        TriviaQuestion(5, "Who won the 2022 World Cup in Qatar?", listOf("France", "Brazil", "Argentina", "Croatia"), 2, "History", "Easy", "Argentina beat France in a penalty shootout in the 2022 final."),
        TriviaQuestion(6, "What is the capacity of Estadio Azteca?", listOf("65,000", "72,000", "80,000", "87,523"), 3, "Venues", "Hard", "Estadio Azteca in Mexico City has a capacity of 87,523."),
        TriviaQuestion(7, "How many groups are in the 2026 World Cup?", listOf("8", "10", "12", "16"), 2, "2026", "Medium", "There are 12 groups of 4 teams each."),
        TriviaQuestion(8, "Which player scored a hat-trick in a World Cup final?", listOf("Pelé", "Geoff Hurst", "Zinedine Zidane", "Kylian Mbappé"), 1, "Records", "Hard", "Geoff Hurst scored a hat-trick in the 1966 World Cup final. Mbappé also scored 3 in 2022."),
        TriviaQuestion(9, "Which African team reached the 2022 semi-finals?", listOf("Senegal", "Ghana", "Morocco", "Cameroon"), 2, "History", "Medium", "Morocco became the first African team to reach the World Cup semi-finals in 2022."),
        TriviaQuestion(10, "How many matches will be played in the 2026 World Cup?", listOf("64", "80", "104", "128"), 2, "2026", "Medium", "104 matches will be played across the tournament."),
        TriviaQuestion(11, "Which country hosted the first ever World Cup in 1930?", listOf("Brazil", "Italy", "Uruguay", "France"), 2, "History", "Easy", "Uruguay hosted and won the first FIFA World Cup in 1930."),
        TriviaQuestion(12, "What is the youngest age a player has scored in a World Cup?", listOf("16", "17", "18", "19"), 1, "Records", "Hard", "Pelé scored at 17 years old in the 1958 World Cup."),
        TriviaQuestion(13, "Which team has the most World Cup appearances without winning?", listOf("Netherlands", "Portugal", "Mexico", "Belgium"), 2, "Records", "Hard", "Mexico has appeared in 17 World Cups without winning the tournament."),
        TriviaQuestion(14, "How many host cities are there in the 2026 World Cup?", listOf("11", "14", "16", "20"), 2, "2026", "Medium", "There are 16 host cities across USA (11), Canada (2), and Mexico (3)."),
        TriviaQuestion(15, "Which team is the defending champion entering 2026?", listOf("France", "Brazil", "Germany", "Argentina"), 3, "2026", "Easy", "Argentina won the 2022 World Cup and enters as defending champions."),
        TriviaQuestion(16, "What is the opening match of the 2026 World Cup?", listOf("USA vs Paraguay", "Brazil vs Morocco", "Mexico vs South Africa", "Canada vs Bosnia"), 2, "2026", "Medium", "The opening match is Mexico vs South Africa at Estadio Azteca on June 11."),
        TriviaQuestion(17, "Which stadium will host the 2026 World Cup Final?", listOf("SoFi Stadium", "MetLife Stadium", "Estadio Azteca", "AT&T Stadium"), 1, "2026", "Medium", "MetLife Stadium in East Rutherford, NJ will host the final on July 19."),
        TriviaQuestion(18, "How many teams advance from the group stage in 2026?", listOf("24", "28", "32", "36"), 2, "2026", "Medium", "32 teams advance: top 2 from each group plus 8 best third-place teams."),
        TriviaQuestion(19, "Which player has appeared in the most World Cup tournaments?", listOf("Lionel Messi", "Cristiano Ronaldo", "Lothar Matthäus", "Antonio Carbajal"), 2, "Records", "Hard", "Lothar Matthäus and Antonio Carbajal both appeared in 5 World Cups."),
        TriviaQuestion(20, "Which is the only team to play in every World Cup?", listOf("Germany", "Italy", "Argentina", "Brazil"), 3, "Records", "Medium", "Brazil is the only team to have participated in every World Cup since 1930.")
    )
}
