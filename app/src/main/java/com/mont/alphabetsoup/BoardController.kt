package com.mont.alphabetsoup

import java.util.*

class BoardController {
    private val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    val greenSoup = arrayOf(
        charArrayOf('X', 'X', 'X', 'X', 'X', 'X'),
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
        "7535" to "KOALA",
        "4414" to "BIRD",
        "3151" to "COW"
    )
    val boardArr: Array<Array<Char>> = Array(8) { Array(6) { ' ' } }
    var touchedCounter: Int = 0
    var clicked = ""

    fun addToClicked(button: String) {
        touchedCounter += 1
        if (touchedCounter <= 2) {
            clicked += button;
        }
    }
    private fun fillGreenSoup(word: String){
        val words = mutableMapOf<String, String>(
            "CAT" to "020100",
            "DOG" to "111213",
            "FISH" to "23334353",
            "HORSE" to "6463626160",
            "KOALA" to "7565554535",
            "BIRD" to "44342414",
            "COW" to "314151"
        )
        val positions = words[word]?.chunked(2)
        if (positions != null) {
            for (i in positions.indices){
                val s = ""+ positions[i][0]
                val e = ""+ positions[i][1]
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
                "7535" -> word = "KOALA"
                "4414" -> word = "BIRD"
                "3151" -> word = "COW"
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
        val threeLetterWords = arrayOf("CAT", "DOG", "COW", "APE", "BAT", "ELK", "FOX", "OWL", "PIG", "RAT")
        val fourLetterWords = arrayOf("FISH", "BOAR", "GOAT", "HARE", "ORCA", "LYNX", "LION", "PUMA", "WOLF", "MULE")
        val fiveLetterWords = arrayOf("BISON","CAMEL","HORSE","KOALA","GOOSE","EAGLE","GECKO","MOUSE","OTTER","SQUID")

        threeLetterWords.shuffle()
        fourLetterWords.shuffle()
        fiveLetterWords.shuffle()

        val queueThree: Queue<String> = LinkedList<String>()
        val queueFour: Queue<String> = LinkedList<String>()
        val queueFive: Queue<String> = LinkedList<String>()
        queueThree.addAll(threeLetterWords)
        queueFour.addAll(fourLetterWords)
        queueFive.addAll(fiveLetterWords)

        val words = mutableMapOf<String, String>(
            queueThree.remove() to "020100",
            queueThree.remove() to "111213",
            queueFour.remove() to "23334353",
            queueFive.remove() to "6463626160",
            queueFive.remove() to "7565554535",
            queueFour.remove() to "44342414",
            queueThree.remove() to "314151"
        )

        for (i in 0 until 8) {
            for (j in 0 until 6) {
                boardArr[i][j] = alphabet.random()
            }
        }

        for (key in words.keys.iterator()) {
            val positions = words[key]?.chunked(2)
            if (positions != null) {
                for (i in positions.indices) {
                    val s = "" + positions[i][0]
                    val e = "" + positions[i][1]
                    boardArr[s.toInt()][e.toInt()] = key[i]
                }
            }
        }
    }
}