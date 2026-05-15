//write a scala program to to define a fuction to calculate sum of two numbers using val

object sum{
    def main(args: Array[String]):Unit={
        val sum = (x: Int, y: Int) => x + y
        println(sum(10, 20))
    }
}

// write a scala program using anoymous function to multiply two numbers
object MultiplyAnonymous {
  def main(args: Array[String]): Unit = {
    val multiply = (a: Int, b: Int) => a * b
    val num1 = 5
    val num2 = 4
    val result = multiply(num1, num2)
    println("Multiplication result: " + result)
  }
}

//write a scala program demostrating higher order function
object HigherOrderFunction {
  def main(args: Array[String]): Unit = {
    def applyOperation(a: Int, b: Int, operation: (Int, Int) => Int): Int = {
      operation(a, b)
    }
    val add = (x: Int, y: Int) => x + y
    val result = applyOperation(5, 4, add)
    println("Result: " + result)
  }
}

//create a list and use map, filter and reduce operation
object ListOperations {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5)
    val mapped = list.map(_ * 2)
    val filtered = list.filter(_ % 2 == 0)
    val reduced = list.reduce(_ + _)
    println("Mapped: " + mapped)
    println("Filtered: " + filtered)
    println("Reduced: " + reduced)
  }
}

//use pattern matching to classify numbers as zero , positive and negtive
object PatternMatching {
  def main(args: Array[String]): Unit = {
    def classify(x: Int): String = x match {
      case 0 => "Zero"
      case x if x > 0 => "Positive"
      case _ => "Negative"
    }
    println(classify(0))
    println(classify(5))
    println(classify(-5))
  }
}

//define  a case class Student and perform filter and map operation
case class Student(name: String, age: Int, grade: String)
object CaseClass {
  def main(args: Array[String]): Unit = {
    val students = List(
      Student("John", 20, "A"),
      Student("Jane", 21, "B"),
      Student("Bob", 22, "C")
    )
    val filtered = students.filter(_.age > 20)
    val mapped = students.map(_.name)
    println("Filtered: " + filtered)
    println("Mapped: " + mapped)
  }
}