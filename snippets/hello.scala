val greeting = "Hello, World!"

def repeat(n: Int): Unit = for i <- 1 to n do println(s"$greeting ($i)")
