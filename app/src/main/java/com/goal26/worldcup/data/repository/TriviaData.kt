package com.goal26.worldcup.data.repository

import com.goal26.worldcup.domain.model.TriviaQuestion

/**
 * Extended trivia question bank — 60 questions across multiple categories.
 */
object TriviaData {

    val allQuestions = listOf(
        // === HISTORY ===
        TriviaQuestion(1, "Which country has won the most FIFA World Cups?", listOf("Germany", "Brazil", "Argentina", "Italy"), 1, "History", "Easy", "Brazil has won 5 World Cups (1958, 1962, 1970, 1994, 2002)."),
        TriviaQuestion(2, "Who won the first ever World Cup in 1930?", listOf("Brazil", "Italy", "Uruguay", "Argentina"), 2, "History", "Easy", "Uruguay hosted and won the inaugural FIFA World Cup in 1930."),
        TriviaQuestion(3, "Who won the 2022 World Cup in Qatar?", listOf("France", "Brazil", "Argentina", "Croatia"), 2, "History", "Easy", "Argentina beat France in a penalty shootout in the 2022 final."),
        TriviaQuestion(4, "Which team won the 2018 World Cup?", listOf("Croatia", "France", "Belgium", "England"), 1, "History", "Easy", "France beat Croatia 4-2 in the final in Moscow."),
        TriviaQuestion(5, "Which African team reached the 2022 semi-finals?", listOf("Senegal", "Ghana", "Morocco", "Cameroon"), 2, "History", "Medium", "Morocco became the first African team to reach the World Cup semi-finals in 2022."),
        TriviaQuestion(6, "Who won the 2010 World Cup in South Africa?", listOf("Netherlands", "Spain", "Germany", "Brazil"), 1, "History", "Easy", "Spain won their first World Cup, beating Netherlands 1-0 with Iniesta's goal."),
        TriviaQuestion(7, "Which country hosted the 2006 World Cup?", listOf("France", "Japan", "Germany", "South Africa"), 2, "History", "Easy", "Germany hosted the 2006 World Cup, won by Italy."),
        TriviaQuestion(8, "In which year did England win the World Cup?", listOf("1962", "1966", "1970", "1974"), 1, "History", "Easy", "England won the World Cup at home in 1966, beating West Germany 4-2."),
        TriviaQuestion(9, "Which was the first Asian country to reach the World Cup semi-finals?", listOf("Japan", "South Korea", "Iran", "Saudi Arabia"), 1, "History", "Medium", "South Korea reached the semi-finals in 2002 as co-hosts."),
        TriviaQuestion(10, "Which country won the World Cup in 1998?", listOf("Brazil", "Italy", "France", "Argentina"), 2, "History", "Easy", "France won at home, beating Brazil 3-0 in the final."),

        // === RECORDS ===
        TriviaQuestion(11, "Who scored the most goals in World Cup history?", listOf("Pelé", "Miroslav Klose", "Ronaldo", "Gerd Müller"), 1, "Records", "Medium", "Miroslav Klose holds the record with 16 goals across 4 World Cups."),
        TriviaQuestion(12, "Which player scored a hat-trick in a World Cup final?", listOf("Pelé", "Geoff Hurst", "Zinedine Zidane", "Kylian Mbappé"), 1, "Records", "Hard", "Geoff Hurst scored a hat-trick in the 1966 final. Mbappé also scored 3 in 2022."),
        TriviaQuestion(13, "What is the youngest age a player has scored in a World Cup?", listOf("16", "17", "18", "19"), 1, "Records", "Hard", "Pelé scored at 17 years old in the 1958 World Cup."),
        TriviaQuestion(14, "Which team has the most World Cup appearances without winning?", listOf("Netherlands", "Portugal", "Mexico", "Belgium"), 2, "Records", "Hard", "Mexico has appeared in 17 World Cups without winning the tournament."),
        TriviaQuestion(15, "Which is the only team to play in every World Cup?", listOf("Germany", "Italy", "Argentina", "Brazil"), 3, "Records", "Medium", "Brazil is the only team to have participated in every World Cup since 1930."),
        TriviaQuestion(16, "Who has appeared in the most World Cup tournaments as a player?", listOf("Lionel Messi", "Cristiano Ronaldo", "Lothar Matthäus", "Gianluigi Buffon"), 2, "Records", "Hard", "Lothar Matthäus appeared in 5 World Cups (1982-1998). Antonio Carbajal also did."),
        TriviaQuestion(17, "What is the fastest goal ever scored in a World Cup match?", listOf("5 seconds", "11 seconds", "16 seconds", "23 seconds"), 1, "Records", "Hard", "Hakan Şükür scored after 11 seconds in the 2002 third-place match (Turkey vs South Korea)."),
        TriviaQuestion(18, "Which player has the most World Cup assists?", listOf("Diego Maradona", "Pelé", "Lionel Messi", "Thomas Müller"), 2, "Records", "Hard", "Lionel Messi holds the record for most World Cup assists."),
        TriviaQuestion(19, "What is the biggest margin of victory in a World Cup match?", listOf("7-1", "9-0", "10-1", "12-0"), 2, "Records", "Hard", "Hungary beat El Salvador 10-1 in 1982. Australia beat American Samoa 31-0 in qualifying."),
        TriviaQuestion(20, "Who scored the 'Goal of the Century'?", listOf("Pelé", "Zinedine Zidane", "Diego Maradona", "Lionel Messi"), 2, "Records", "Medium", "Maradona's second goal against England in the 1986 quarter-final is the 'Goal of the Century'."),

        // === 2026 TOURNAMENT ===
        TriviaQuestion(21, "How many teams are in the 2026 World Cup?", listOf("32", "36", "48", "64"), 2, "2026", "Easy", "The 2026 World Cup expanded to 48 teams for the first time."),
        TriviaQuestion(22, "Which 3 countries are hosting the 2026 World Cup?", listOf("USA, Canada, Mexico", "USA, Brazil, Argentina", "Spain, Portugal, Morocco", "England, Scotland, Ireland"), 0, "2026", "Easy", "The tournament is co-hosted by the United States, Canada, and Mexico."),
        TriviaQuestion(23, "How many groups are in the 2026 World Cup?", listOf("8", "10", "12", "16"), 2, "2026", "Medium", "There are 12 groups of 4 teams each."),
        TriviaQuestion(24, "How many matches will be played in the 2026 World Cup?", listOf("64", "80", "104", "128"), 2, "2026", "Medium", "104 matches will be played across the tournament."),
        TriviaQuestion(25, "How many host cities are there in the 2026 World Cup?", listOf("11", "14", "16", "20"), 2, "2026", "Medium", "There are 16 host cities across USA (11), Canada (2), and Mexico (3)."),
        TriviaQuestion(26, "Which team is the defending champion entering 2026?", listOf("France", "Brazil", "Germany", "Argentina"), 3, "2026", "Easy", "Argentina won the 2022 World Cup and enters as defending champions."),
        TriviaQuestion(27, "What is the opening match of the 2026 World Cup?", listOf("USA vs Paraguay", "Brazil vs Morocco", "Mexico vs South Africa", "Canada vs Bosnia"), 2, "2026", "Medium", "The opening match is Mexico vs South Africa at Estadio Azteca on June 11."),
        TriviaQuestion(28, "Which stadium will host the 2026 World Cup Final?", listOf("SoFi Stadium", "MetLife Stadium", "Estadio Azteca", "AT&T Stadium"), 1, "2026", "Medium", "MetLife Stadium in East Rutherford, NJ will host the final on July 19."),
        TriviaQuestion(29, "How many teams advance from the group stage in 2026?", listOf("24", "28", "32", "36"), 2, "2026", "Medium", "32 teams advance: top 2 from each group plus 8 best third-place teams."),
        TriviaQuestion(30, "When does the 2026 World Cup run?", listOf("May 15 - Jun 30", "Jun 11 - Jul 19", "Jul 1 - Aug 15", "Jun 1 - Jul 1"), 1, "2026", "Easy", "The tournament runs from June 11 to July 19, 2026."),

        // === 2026 GROUPS ===
        TriviaQuestion(31, "Which group is Argentina in for 2026?", listOf("Group D", "Group H", "Group J", "Group A"), 2, "2026", "Medium", "Argentina is in Group J with Algeria, Austria, and Jordan."),
        TriviaQuestion(32, "Who does the USA play first in 2026?", listOf("Mexico", "Australia", "Paraguay", "Türkiye"), 2, "2026", "Easy", "The USA plays Paraguay on June 12 at SoFi Stadium."),
        TriviaQuestion(33, "Which group is Brazil in for 2026?", listOf("Group A", "Group C", "Group E", "Group I"), 1, "2026", "Medium", "Brazil is in Group C with Morocco, Haiti, and Scotland."),
        TriviaQuestion(34, "Which group is Spain in for 2026?", listOf("Group F", "Group H", "Group K", "Group B"), 1, "2026", "Medium", "Spain is in Group H with Cape Verde, Saudi Arabia, and Uruguay."),
        TriviaQuestion(35, "Which teams are in Group L?", listOf("Portugal, Colombia, Uzbekistan, DR Congo", "England, Croatia, Panama, Ghana", "France, Senegal, Iraq, Norway", "Belgium, Egypt, Iran, New Zealand"), 1, "2026", "Hard", "Group L consists of England, Croatia, Panama, and Ghana."),

        // === VENUES ===
        TriviaQuestion(36, "What is the capacity of Estadio Azteca?", listOf("65,000", "72,000", "80,000", "87,523"), 3, "Venues", "Hard", "Estadio Azteca in Mexico City has a capacity of 87,523."),
        TriviaQuestion(37, "Which stadium hosts the opening match?", listOf("MetLife Stadium", "Estadio Azteca", "SoFi Stadium", "BMO Field"), 1, "Venues", "Medium", "Estadio Azteca in Mexico City hosts the opening match."),
        TriviaQuestion(38, "How many venues are in the USA for 2026?", listOf("8", "10", "11", "13"), 2, "Venues", "Medium", "11 venues in the USA, 3 in Mexico, and 2 in Canada."),
        TriviaQuestion(39, "Which Canadian city has a World Cup venue?", listOf("Montreal & Ottawa", "Toronto & Vancouver", "Calgary & Edmonton", "Toronto & Montreal"), 1, "Venues", "Medium", "BMO Field in Toronto and BC Place in Vancouver host matches."),
        TriviaQuestion(40, "MetLife Stadium is located in which US state?", listOf("New York", "New Jersey", "Connecticut", "Pennsylvania"), 1, "Venues", "Medium", "MetLife Stadium is in East Rutherford, New Jersey."),

        // === PLAYERS ===
        TriviaQuestion(41, "Who is the all-time top scorer for Argentina?", listOf("Diego Maradona", "Gabriel Batistuta", "Lionel Messi", "Sergio Agüero"), 2, "Players", "Medium", "Lionel Messi is Argentina's all-time top scorer with 100+ international goals."),
        TriviaQuestion(42, "Who captains England?", listOf("Jude Bellingham", "Harry Kane", "Bukayo Saka", "Declan Rice"), 0, "Players", "Medium", "Jude Bellingham has worn the captain's armband for England."),
        TriviaQuestion(43, "Who is France's all-time top scorer?", listOf("Thierry Henry", "Michel Platini", "Kylian Mbappé", "Olivier Giroud"), 3, "Players", "Hard", "Olivier Giroud surpassed Henry's record to become France's all-time top scorer."),
        TriviaQuestion(44, "Which Portuguese player has scored in 5 consecutive World Cups?", listOf("Cristiano Ronaldo", "Luís Figo", "Eusébio", "No one has"), 0, "Players", "Hard", "Cristiano Ronaldo has scored in every World Cup he's played in (2006-2022)."),
        TriviaQuestion(45, "Who is Brazil's youngest ever World Cup captain?", listOf("Neymar", "Ronaldo", "Pelé", "Thiago Silva"), 0, "Players", "Hard", "Neymar was one of Brazil's youngest captains at a World Cup."),

        // === RULES & FORMAT ===
        TriviaQuestion(46, "How many substitutes can each team use per match in 2026?", listOf("3", "4", "5", "6"), 2, "Rules", "Medium", "FIFA allows 5 substitutions per team per match since 2022."),
        TriviaQuestion(47, "What technology is used for offside decisions?", listOf("Hawk-Eye", "VAR only", "Semi-automated offside", "Goal-line technology"), 2, "Rules", "Medium", "Semi-automated offside technology (SAOT) uses AI and tracking cameras."),
        TriviaQuestion(48, "How many players are in a World Cup squad?", listOf("23", "25", "26", "28"), 2, "Rules", "Medium", "FIFA expanded squads to 26 players starting from the 2022 World Cup."),
        TriviaQuestion(49, "What happens if a group stage match ends in a draw?", listOf("Extra time", "Penalties", "It stays a draw", "Golden goal"), 2, "Rules", "Easy", "Group stage matches can end in a draw. Only knockout matches go to extra time/penalties."),
        TriviaQuestion(50, "What color card results in a match ban?", listOf("Yellow", "Red", "Two yellows", "Orange"), 2, "Rules", "Easy", "A red card results in an immediate match ban plus at least one additional match suspension."),

        // === FUN FACTS ===
        TriviaQuestion(51, "What is the official match ball of the 2026 World Cup made by?", listOf("Nike", "Puma", "Adidas", "Umbro"), 2, "Fun Facts", "Easy", "Adidas has supplied the official World Cup match ball since 1970."),
        TriviaQuestion(52, "How many FIFA World Cups have been held?", listOf("20", "21", "22", "23"), 3, "Fun Facts", "Medium", "The 2026 World Cup is the 23rd edition of the tournament."),
        TriviaQuestion(53, "The 2026 World Cup is the first to be hosted by how many countries?", listOf("1", "2", "3", "4"), 2, "Fun Facts", "Easy", "It's the first World Cup to be hosted by 3 countries simultaneously."),
        TriviaQuestion(54, "What was the name of the 2022 World Cup mascot?", listOf("Fuleco", "La'eeb", "Zakumi", "Footix"), 1, "Fun Facts", "Medium", "La'eeb was the official mascot of the 2022 FIFA World Cup in Qatar."),
        TriviaQuestion(55, "Which World Cup had the 'Hand of God' goal?", listOf("1982", "1986", "1990", "1994"), 1, "Fun Facts", "Medium", "Maradona's 'Hand of God' goal was in the 1986 quarter-final vs England."),
        TriviaQuestion(56, "Which country has hosted the most World Cups?", listOf("Italy", "Brazil", "Mexico", "France"), 2, "Fun Facts", "Medium", "Mexico is hosting for the 3rd time (1970, 1986, 2026)."),
        TriviaQuestion(57, "What is the FIFA World Cup trophy made of?", listOf("Gold", "Silver", "18-carat gold", "Bronze"), 2, "Fun Facts", "Hard", "The FIFA World Cup Trophy is made of 18-carat gold and weighs 6.175 kg."),
        TriviaQuestion(58, "How long is a standard World Cup match?", listOf("80 minutes", "90 minutes", "100 minutes", "120 minutes"), 1, "Fun Facts", "Easy", "A standard match is 90 minutes (two 45-minute halves) plus stoppage time."),
        TriviaQuestion(59, "What animal correctly predicted 2010 World Cup results?", listOf("Paul the Octopus", "Mani the Parakeet", "Nelly the Elephant", "Achilles the Cat"), 0, "Fun Facts", "Medium", "Paul the Octopus correctly predicted 8 out of 8 matches in the 2010 World Cup."),
        TriviaQuestion(60, "Which is the smallest country ever to qualify for a World Cup?", listOf("Iceland", "Trinidad & Tobago", "Curaçao", "Cape Verde"), 2, "Fun Facts", "Hard", "Curaçao (population ~150k) qualifies for 2026, one of the smallest nations ever.")
    )
}
