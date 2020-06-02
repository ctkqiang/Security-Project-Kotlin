package Encoding;

import Utilities.util
import java.util.Base64

fun main(args: Array<String>) {
    util("Please Input: ").display();

    val originalString = readLine().toString();
    val encodedString: String = Base64.getEncoder().withoutPadding().encodeToString(originalString.toByteArray());

    util("Encoded {String} ==> $encodedString").display();
}