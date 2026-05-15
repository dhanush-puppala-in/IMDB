// object loop extends app{
//  /*   val square = (x : INT) => x*x
//     println(square(8))*/
//     val nums = list(1,2,3,4,5)
//     val double = nums.map(x => x*2)
//     println(double)
// }

// object pattern{
//     def main(args: Array[String]){
//         println(pattern_match(1))
//     }
//     def pattern_match(x: Int) = x match{
//         case 1 => "One"
//         case 2 => "Two"
//         case 3 => "Three"
//         case _ => "Other"
//     }
// }

// object transform
// {
//     def main(args: Array[String]):Unit ={
//         val data = list(for(i <- 1 to 10) yield i)
//         val result = data.filter(_%2 == 0).map(_*10).flatmap(x =>List(x, x+1))
//         println(result)
//     }
// }

// object transform{
//     def main(args: Array[String]):Unit ={
//         val transction = list(
//             ("Mobile", 10000,"success"),
//             ("Laptop", 50000,"success"),
//             ("Phone", 20000,"failed")
//         )
//         val result = transction.filter(_._3 == "success").map(_._2)
//         println(result)
//     }
// }

object Transform {
  def main(args: Array[String]): Unit = {

    val transaction = List(
      ("Mobile", 10000, "success"),
      ("Laptop", 50000, "success"),
      ("Phone", 20000, "failed"),
      ("Mobile", 15000, "success")
    )
    val mapped = transaction
      .filter { case (_, _, status) => status == "success" }
      .map { case (category, revenue, _) => (category, revenue) }
    val grouped = mapped.groupBy(_._1)
    val result = grouped.map {
      case (category, values) =>
        val total = values.map(_._2).sum
        (category, total)
    }

    println(result)
  }
}