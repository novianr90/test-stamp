
fun main() {
    printFooBar()
}

/**
 * A method for printing Foo, Bar and FooBar based on Given Brief.
 *
 * Iterate from 100 to 1 when i [isPrime], the iterate will continue into next value.
 *
 * Print Foo when i % 3 is 0
 *
 * Print Bar when i % 5 is 0
 *
 * Print FooBar when i % 3 and i % 5 is 0
 *
 * @return Unit
 */
fun printFooBar() {
    for (i in 100 downTo 1) {

        if (isPrime(i)) {
            continue
        }

        when {
            i % 3 == 0 && i % 5 == 0 -> print("FooBar ")
            i % 3 == 0 -> print("Foo ")
            i % 5 == 0 -> print("Bar ")
            else -> print("$i ")
        }
    }
}

/**
 * A method to check a number is Prime or Not Prime.
 *
 * Number will be divided from 2 until the number itself.
 *
 * @param Int
 * @return Boolean
 */
fun isPrime(number: Int): Boolean {

    if (number <= 1) {
        return false
    }

    for (i in 2 until number) {
        if (number % i == 0) {
            return false
        }
    }

    return true
}