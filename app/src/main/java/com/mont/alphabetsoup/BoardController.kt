package com.mont.alphabetsoup

class BoardController {
    private val soup = arrayOf(
        charArrayOf('T', 'A', 'C', 'D', 'E', 'F'),
        charArrayOf('K', 'D', 'O', 'G', 'D', 'N'),
        charArrayOf('U', 'V', 'W', 'F', 'R', 'E'),
        charArrayOf('E', 'F', 'G', 'I', 'I', 'K'),
        charArrayOf('O', 'P', 'Q', 'S', 'B', 'C'),
        charArrayOf('Y', 'Z', 'A', 'H', 'C', 'I'),
        charArrayOf('E', 'S', 'R', 'O', 'H', 'H'),
        charArrayOf('S', 'T', 'U', 'V', 'W', 'C')
    )
    val greenSoup = arrayOf(
        charArrayOf('x', 'x', 'X', 'X', 'X', 'X'),
        charArrayOf('X', 'X', 'X', 'X', 'X', 'X'),
        charArrayOf('X', 'X', 'X', 'X', 'X', 'X'),
        charArrayOf('X', 'X', 'X', 'X', 'X', 'X'),
        charArrayOf('X', 'X', 'X', 'X', 'X', 'X'),
        charArrayOf('X', 'X', 'X', 'X', 'X', 'X'),
        charArrayOf('X', 'X', 'X', 'X', 'X', 'X'),
        charArrayOf('X', 'X', 'X', 'X', 'X', 'X')
    )
    private val words = mutableMapOf<String, String>(
        "0200" to "CAT",
        "1113" to "DOG",
        "2353" to "FISH",
        "6460" to "HORSE",
        "7515" to "CHICKEN",
        "4414" to "BIRD",
        "0222" to "COW"
    )
    val boardArr: Array<Array<Char>> = Array(soup.size) { Array(soup[0].size) { ' ' } }
    var touchedCounter: Int = 0
    var clicked = ""

    fun addToClicked(button: String) {
        touchedCounter += 1
        if (touchedCounter <= 2) {
            clicked += button;
        }
    }
    fun fillGreenSoup(word: String){
        val words = mutableMapOf<String, String>(
            "CAT" to "020100",
            "DOG" to "111213",
            "FISH" to "23334353",
            "HORSE" to "6463626160",
            "CHICKEN" to "75655545352515",
            "BIRD" to "44342414",
            "COW" to "021222"
        )
        val positions = words[word]?.chunked(2)
        if (positions != null) {
            for (i in positions.indices){
                var s = ""+positions[i].get(0)
                var e = ""+positions[i].get(1)
                greenSoup[s.toInt()][e.toInt()] = '*'
            }
        }
    }
    fun returnCorrect(): Boolean {
        if (words.containsKey(clicked)){
            var word = ""
            when(clicked){
                "0200" -> word = "CAT"
                "1113" -> word = "DOG"
                "2353" -> word = "FISH"
                "6460" -> word = "HORSE"
                "7515" -> word = "CHICKEN"
                "4414" -> word = "BIRD"
                "0222" -> word = "COW"
            }
            fillGreenSoup(word)
            touchedCounter = 0
            return true
        }
        touchedCounter = 0
        clicked = ""
        return false
    }

    fun initBoard() {
        for (i in 0 until 8) {
            for (j in 0 until 6) {
                boardArr[i][j] = soup[i][j].uppercaseChar()
            }
        }
    }
}