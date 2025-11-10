
class HelloWorld{
    /*
     * 
     * main method — the program entry point
    // Typical signature
    // public static void main(String[] args)


    // Each part matters:

    // public — JVM must be able to access the method. The standard JVM looks for a public method named main with the correct signature. If it’s not public the JVM will not find it and you’ll get an error like NoSuchMethodError: main.

    // static — JVM calls main without creating an instance of the class. static means the method belongs to the class, not an object.

    // void — main does not return a value to the JVM. (The process exit code can still be set with System.exit(int).)

    // main — the method name JVM expects.

    // String[] args — array of command-line arguments passed by the JVM. If you run java MyApp foo bar, args contains {"foo", "bar"}.

    // Valid variations

    // public static void main(String[] args) — canonical.

    // public static void main(String... args) — varargs form; functionally equivalent.

    // You can use different parameter names: String[] argv, etc.

    // You can overload main (i.e., add public static void main(int x)), but JVM calls the exact signature above.

    // You can declare throws (e.g. public static void main(String[] args) throws Exception) — allowed.

    // Private or non-static main is allowed as a method but JVM won’t call it as entry point.
     * 
     */
    public static void main(String[] args){
        String name="Mourya";
        int age=25;

        /*
         * 
         * System.out.println — printing to the console
        // What is System.out?

        // System is a class in java.lang.

        // out is a public static field: public static final PrintStream out.

        // So System.out is a PrintStream instance connected by default to the JVM’s standard output stream (the console).

        // println explained

        // println is an overloaded method on PrintStream
         * 
         * 
         */



        System.out.println("Hello World"+name);
        System.out.println("Mourya"+age);
    }
}