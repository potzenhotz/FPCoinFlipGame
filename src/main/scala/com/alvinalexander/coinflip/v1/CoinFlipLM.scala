package com.alvinalexander.coinflip.v1

import CoinFlipUtils._
import scala.annotation.tailrec
import scala.util.Random

//case class GameStateLM(numFlips: Int, numCorrect: Int)

object CoinFlipLM extends App {

    val r = Random
    val s = GameState(0, 0)
    mainLoop(s, r)

    @tailrec
    def mainLoop(gameState: GameState, random: Random) {
        showPrompt()
        val userInput = getUserInput()
        userInput match {
            case "H" | "T" => {
                val result = tossCoin(r)
                val newNumFlips = gameState.numFlips + 1
                println(newNumFlips)
                if (userInput == result) {
                    val newNumCorrect = gameState.numCorrect +1
                    val newGameState = gameState.copy(numFlips= newNumFlips,numCorrect= newNumCorrect)
                    println(newGameState)
                    printGameState(result, newGameState)
                    mainLoop(newGameState, r)
                }
                else {
                    val newGameState = gameState.copy(numFlips=newNumFlips)
                    println(newGameState)
                    printGameState(result, newGameState)
                    mainLoop(newGameState, r)
                }
            }
            case "Q" => {
                printGameState(gameState)
            }
            case _ => {
                printGameOver()
                printGameState(gameState)
            }
        }

    }

}
