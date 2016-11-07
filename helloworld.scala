import scala._;
// def evaluates when called
// val evaluates when defined

// scala is expression oriented as opposed to statement oriented like most other functional programming languages

// object Helloworld is a singleton
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello world Cmdline");
    addMethod(2,4);
    val kk = 4; // val is const value, cannot be changed later;

    var j = 3; // var can be changed though
    println(j);
    j = 11;
    println(j);
    println(addMultiple(1)(1)(1));

    // partial application on a simple function
    //Note: _ means different things in different contexts, here it means, if
    // u are using it to ignore specifying all arguments then return a fn
    val addTwoer = addMethod(2, _: Int);
    println(addTwoer(99));

    // another partial application example on a simple function
    val mult10 = mult(10, _: Int);
    println(mult10(88));

    println(mult(10,10));

    //anonymous functions are arrow functions, and can be stored in val
    val addOne = (x: Int) => x + 1;

    println(addOne(999));

    // same as javascript anonymous arrow functions, use {} to have multi statements
    val timesTwo = (x: Int) => {
      println("inside timesTwo");
      x * 2; // this is return type of value of fn
    }

    println(timesTwo(32));

    // partial application on curried method :  addMultiple
    // param and return types are inferred from curried fn
    val add66 = addMultiple(33)(33) _ // curried methods need eta conversion, curried functions do not need eta conversion
    println(add66(4));


    // convert any normal function to curried version
    val multCurried = (mult _).curried
    println(multCurried(5)(5));

    val mult7 = multCurried(7); // this does not complain for missing args or _
    println(mult7(5));



    // make instances of classes
    val myBmw = new BMW
    println("instance of "+myBmw.brand);

    val mycalc = new Calculator("HP");
    println(mycalc.color);

    // converting a method to a function by eta conversion
    val addFn = addMethod _ 
    println("8 + 2 = " + addFn(8,2));

    val add5 = addMultiple(5) _; // add5 is now function -> curried def to val
    val add5and6 = add5(6); //no eta conversion need because add5 is already fn
    println("11 + 11 = " + add5and6(11));

    // classes and companion objects
    val hw = MyString("hello","world");
    val hh = MyString("hello");
    println(hw)
    println(hh)

  }

  // see how arguments are annoted with types using : (like in many fn prog langs) also the function definition is given by using = before starting block
  def addMethod(a: Int, b: Int): Int = {
    return a + b;
  }

  // you can function signature in scala by opening scala repl and
  // :type HelloWorld.addMethod _
  // this methods just prints sum but returns unit () like void
  def addMethodUnitReturn(a: Int, b: Int): Unit = {
    println(a+b);
  }

  // return type is optional bcoz type inference figures it out
  // but it is necessary to specify types for fn parameters
  def stringCutter(str: String, length: Int) = {
    val result = str.substring(0, length);
    result
  }

  // fn type is Int => (Int => (Int => Int))
  def addMultiple(a: Int)(b:Int)(c:Int): Int = {
    return a +  b + c;
  }
  // should be called like addMultiple(1)(1)(1)


  def mult(x: Int, y: Int) = x * y

  // class name with arguments itself is the constructor
  // brand/argument becomes an member variable of Calculator class automatically
  class Calculator(brand: String){
    // this val is a constructor, no special thing as constructor
    val color: String = if (brand == "TI"){
      "blue"
    } else if (brand == "HP") {
      "black"
    } else {
      "white"
    }

    def ad(m: Int, n:Int): Int = m + n
  }


  // MyString class with companion object
  class MyString(val jString: String) {
    private var extraData = ""
    override def toString = jString + extraData
  }

  // companion object to MyString class
  object MyString {
    // static factory with two arguments
    def apply(base: String, extras: String) = {
      val s = new MyString(base)
      s.extraData = extras
      s
    }

    // static factory with single argument
    def apply(base: String) = new MyString(base)
  }

  /*-------------- Traits ----------------------*/
  // traits are collection of fields and behaviours like java interfaces + typeclasses
  trait Car {
    val brand: String
  }

  trait Shiny {
    val shineRefraction: Int
  }

  class BMW extends Car {
    val brand = "BMW";
  }

  // extend several traits using with
  class Audi extends Car with Shiny {
    val brand = "Audi";
    val shineRefraction = 12;
  }

}
