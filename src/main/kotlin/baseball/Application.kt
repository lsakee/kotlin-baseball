package baseball

import camp.nextstep.edu.missionutils.Randoms
import camp.nextstep.edu.missionutils.Console
import java.util.regex.Matcher
import java.util.regex.Pattern

fun main() {
    var computerRandomNumber = createComputerRandomNumber()
    println("숫자 야구 게임을 시작합니다.")
    while (true) {


    }
}

fun createComputerRandomNumber(): String {

    val computerNumStringList = mutableListOf<String>()

    while (computerNumStringList.size < 3) {
        val randomNumber = Randoms.pickNumberInRange(1, 9)
        if (!computerNumStringList.contains(randomNumber.toString())) {
            computerNumStringList.add(randomNumber.toString())
        }
    }
    return computerNumStringList
            .joinToString("")
}

fun inputUserNumber(): String {
    print("숫자를 입력해주세요 : ")
    val userInputNumber = Console.readLine()
    checkInputUserNumber(userInputNumber)
    return userInputNumber.toString()
}

fun checkInputUserNumber(userInputNumber: String?) {
    val checkOverlapInputNumber = mutableListOf<String>()
    val inputNumberStandard = "^[1-9]*$"

    //중복체크
    userInputNumber ?: throw IllegalArgumentException()

    for (userInputNumberWord in userInputNumber) checkOverlapInputNumber.add(userInputNumberWord.toString())

    checkInputMessagePatten(inputNumberStandard, checkOverlapInputNumber)
    if (checkOverlapInputNumber.size !=3) throw IllegalArgumentException()
    if (checkOverlapInputNumber.distinct().size != 3) throw IllegalArgumentException()

}

fun checkStrike(userInputNumber: String,
                computerRandomNumber: String): Int {
    var strike = 0
    for (index in userInputNumber.indices)
        if (userInputNumber[index] == computerRandomNumber[index]) strike++

    return strike
}

//ball 수
fun checkBall(
        userInputNumber: String,
        computerRandomNumber: String,
        strike: Int): Int {
    var ball = 0
    for (userInputNumberWord in userInputNumber)
        if (computerRandomNumber.contains(userInputNumberWord)) ball++

    ball -= strike
    return ball
}

fun printResult(strike: Int, ball: Int) {

    return when {
        1 <= strike && 1 <= ball -> println("$ball" + "볼" + " " + "$strike" + "스트라이크")
        1 <= strike && ball == 0 -> println(("$strike" + "스트라이크"))
        strike == 0 && 1 <= ball -> println("$ball" + "볼")
        else -> println("낫싱")
    }
}



fun checkInputMessagePatten(standard: String, checkOverlap: MutableList<String>) {
    val pattern: Pattern = Pattern.compile(standard)

    for (inputNum in checkOverlap) {
        val matcher: Matcher = pattern.matcher(inputNum)
        if (matcher.find().toString() == "false") throw IllegalArgumentException()
    }
}