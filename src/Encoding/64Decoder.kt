package Encoding;

import Utilities.util
import java.util.Base64

fun main(args: Array<String>) {
    util("Please Input: ").display();

    val originalString = readLine().toString();
    val encodedString: String = Base64.getEncoder().withoutPadding().encodeToString(originalString.toByteArray());
    val decodedBytes = Base64.getDecoder().decode(encodedString);
    val decodeString = String(decodedBytes);

    util("Decoded {String} ==> $decodeString").display();
}

