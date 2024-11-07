import java.io.File
import kotlin.random.Random

const val BG_GREEN = "\u001B[42m"
const val BG_YELLOW = "\u001B[43m"
const val RESET = "\u001B[0m"
const val BLACK = "\u001B[30m"
const val GREEN = "\u001B[32m"
fun main() {
    println("${GREEN}1. Jugar")
    println("${GREEN}2. Ver traza de último intento")
    println("${GREEN}3. Salir")
    print("${GREEN}opción: ")
    var opcion = readln().toInt()
    println("${RESET}")
    while (opcion != 3) { // empieza el juego al escribir 3

        if (opcion == 1) { // Se prepara el juego
            var l = (1..6).toList()
            l = l.shuffled()
            var nSecreto = ""
            for (i in 0 until 4) {
                nSecreto += l[i]
            }
            var Secreto = nSecreto.toList()
            print(Secreto) // Borrar esto cuando se acabe de probar
            File("traza.txt").writeText("numero screto:$nSecreto")
            var nintentos = 0

            while (nintentos < 10) {
                print("teclea un numero de cuatro cifras sin números repetido, escribe (Me rindo) para abandonar: ") // Empieza el juego
                var respuesta = readln()
                var respuestaN = respuesta.split("")
                var Aciertos = 0
                var Coincidencias = 0
                for (i in 0 until 4) { // Se comprueba la respuesta (Hay que corregirlo)
                    for (j in 0 until 4) {
                        if (respuestaN[i] == Secreto[i].toString()) {
                            Aciertos++
                        }
                        if (respuestaN[i] == nSecreto[j].toString()) {
                            Coincidencias++
                        }
                    }
                }
                print("$respuesta ${BG_GREEN}${BLACK}$Aciertos ${RESET}")
                println("${BG_YELLOW}${BLACK}$Coincidencias ${RESET}")

                File("traza.txt").appendText("\nIntento $nintentos: $respuesta, Aciertos:$Aciertos Coincidencias:$Coincidencias")
                nintentos++
                if (nintentos == 10 || respuesta == "Me rindo") {
                    println("Lo siento no adivinaste el número secreto $nSecreto en $nintentos intentos")
                    break
                }
            }
        }
        if (opcion == 2) {
            val content = File("traza.txt").readText()
            println(content)
        }

        if (opcion == 3) {
            println("Gracias por jugar")
            break
        }
        println("${GREEN}1. Jugar")
        println("${GREEN}2. Ver traza de último intento")
        println("${GREEN}3. Salir")
        print("${GREEN}opción: ")
        opcion = readln().toInt()
        println("${RESET}")
    }
}