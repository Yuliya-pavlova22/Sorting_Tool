package sorting
import java.io.File
import java.util.Scanner


fun main(args: Array<String>) {
    val allowedDataTypes = listOf("long", "word", "line")
    val allowedSortingTypes = listOf("natural", "byCount")

    var dataType = "word"
    var sortingType = "natural"
    var inpitFile = File("")
    var outputFile = File("out.txt")
    try {
        for (arg in args) {
            if (arg.matches("-.+".toRegex())) {
                when (arg) {
                    "-dataType" -> { dataType = args.find { it in allowedDataTypes }
                        ?: throw Exception("No data type defined!")
                    }
                    "-sortingType" -> { sortingType = args.find { it in allowedSortingTypes }
                        ?: throw Exception("No sorting type defined!")
                    }
                    "-inputFile" -> { inpitFile = File(args[args.indexOf("-inputFile") + 1]) }
                    "-outputFile" -> { outputFile = File(args[args.indexOf("-outputFile") + 1]) }
                    else -> println("\"$arg\" is not a valid parameter. It will be skipped.")
                }
            }
        }
    }  catch (e: Exception) {
    println(e.message)
}
    if (inpitFile.exists())println(inpitFile.readText())
    if (!outputFile.exists()) outputFile.createNewFile()

    when (dataType) {
        "long" -> {
            val numList = formNumList()
                when(sortingType){
                    "natural" -> {sortIntegers(numList)}
                    "byCount" -> {sortIntByCount(numList)}
                }
        }
        "line" -> {
            val lineList = formLine()
            when(sortingType){
                "natural" -> {sortLine(lineList)}
                "byCount" -> {sortLineByCount(lineList)}
            }
        }

        "word" -> {
            val wordList = formWorldList()
            when(sortingType){
                "natural" -> {sortWord(wordList)}
                "byCount" -> {sortWordByCount(wordList)}
            }
        }

    }
}

fun sortLineByCount(lineList: MutableList<String>) {
    println("Total numbers: ${lineList.size}")
    var lineMutList = mutableMapOf<String, Int>()
    lineList.sorted().forEach {
        var line = it
        var count = lineList.count { it == line}
        lineMutList.put(it, count)
    }
    val sortedMap = lineMutList.toList().sortedBy { (k, v) -> v }.toMap()

    sortedMap.forEach{
        var percent = 100 * it.value / lineList.size
        println("${it.key}: ${it.value} time(s), $percent%")
    }
}

fun sortLine(lineList: MutableList<String>) {
    println("Total numbers: ${lineList.size}.\n" +
            "Sorted data: ${lineList.sorted().joinToString(separator = "\n")}")
}

fun sortWordByCount(wordList: MutableList<String>) {
    println("Total numbers: ${wordList.size}")
    var wordMutList = mutableMapOf<String, Int>()
    wordList.sorted().forEach {
        var word = it
        var count = wordList.count { it == word}
        wordMutList.put(it, count)
    }
    val sortedMap = wordMutList.toList().sortedBy { (k, v) -> v }.toMap()

    sortedMap.forEach{
        var percent = 100 * it.value / wordList.size
        println("${it.key}: ${it.value} time(s), $percent%")
    }

}

fun sortWord(wordList: MutableList<String>) {
    println("Total numbers: ${wordList.size}.\n" +
            "Sorted data: ${wordList.sorted().joinToString(separator = " ")}")
}

fun sortIntByCount(numList: MutableList<Int>) {
    println("Total numbers: ${numList.size}")
    var numMutList = mutableMapOf<Int, Int>()
    numList.sorted().forEach {
        var num = it
        var count = numList.count { it == num}
        numMutList.put(it, count)
    }
    val sortedMap = numMutList.toList().sortedBy { (k, v) -> v }.toMap()

    sortedMap.forEach{
        var percent = 100 * it.value / numList.size
        println("${it.key}: ${it.value} time(s), $percent%")
    }

}

fun formNumList(): MutableList<Int>{
    val numList = mutableListOf<Int>()
    val scanner = Scanner(System.`in`)
    while (scanner.hasNext()) {
        val next = scanner.next()
        try {
            numList.add(next.toInt())
        } catch (e: Exception) {
            println("\"$next\" is not a long. It will be skipped.")
        }

    }
    return  numList
}

fun sortIntegers(numList: MutableList<Int>){
    println("Total numbers: ${numList.size}.\n" +
            "Sorted data: ${numList.sorted().joinToString(separator = " ")}")
}

fun formLine(): MutableList<String>{
    val lineList = mutableListOf<String>()
    val scanner = Scanner(System.`in`)
    while (scanner.hasNext()) {
        lineList.add(scanner.nextLine())
    }
    return lineList
}

fun formWorldList(): MutableList<String>{
    val wordList = mutableListOf<String>()
    val scanner = Scanner(System.`in`)
    while (scanner.hasNext()) {
        wordList.add(scanner.next())
    }
    return  wordList
}
