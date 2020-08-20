import com.github.karbonpowered.api.Karbon
import com.github.karbonpowered.karbon.initialize
import com.github.karbonpowered.text.serializer.KarbonFormattingCodeTextSerializer

fun main() {
    Karbon.initialize()
    val input = "§aHello §c§lWorld! §rok?"
    val serializer = KarbonFormattingCodeTextSerializer('§')
    val text = serializer.deserialize(input)
    println(text)
}