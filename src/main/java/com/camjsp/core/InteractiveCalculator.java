package com.camjsp.core;

/*import com.camjsp.cli.CommandLineInterface;
import picocli.CommandLine;

public class FlexiFileApp {
    public static void main(String[] args) {
        CommandLineInterface cli = new CommandLineInterface();
        CommandLine cmd = new CommandLine(cli);

        // Exécute la commande avec les arguments fournis
        int exitCode = cmd.execute(args);

        // Vous pouvez retourner le code d'état pour une gestion spécifique
        System.exit(exitCode);
    }
}
 */

 import picocli.CommandLine;
 import picocli.CommandLine.Command;
 import picocli.CommandLine.Option;
 import picocli.CommandLine.Parameters;
 
 import java.util.Scanner;
 import java.util.concurrent.Callable;
 
 @Command(name = "Calculator", mixinStandardHelpOptions = true, version = "Calculator 2.0",
         description = "An interactive CLI calculator with various operations.")
 public class InteractiveCalculator implements Callable<Integer> {
 
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         boolean running = true;
 
         while (running) {
             System.out.println("\n--- Interactive Calculator Menu ---");
             System.out.println("1. Basic Operations (Addition, Subtraction, Multiplication, Division)");
             System.out.println("2. Advanced Operations (Power, Square Root)");
             System.out.println("3. Exit");
             System.out.print("Choose an option (1-3): ");
             String choice = scanner.nextLine();
 
             switch (choice) {
                 case "1":
                     new CommandLine(new BasicOperations()).execute(promptArgs(scanner, "basic"));
                     break;
                 case "2":
                     new CommandLine(new AdvancedOperations()).execute(promptArgs(scanner, "advanced"));
                     break;
                 case "3":
                     running = false;
                     System.out.println("Exiting calculator...");
                     break;
                 default:
                     System.out.println("Invalid option. Please choose between 1 and 3.");
             }
         }
         scanner.close();
     }
 
     private static String[] promptArgs(Scanner scanner, String operationType) {
         System.out.print("Enter the operation (-o add/sub/mul/div for basic; -o pow/sqrt for advanced): ");
         String operation = scanner.nextLine();
         System.out.print("Enter the first operand: ");
         String operand1 = scanner.nextLine();
         
         if (operationType.equals("basic") || operation.equals("pow")) {
             System.out.print("Enter the second operand: ");
             String operand2 = scanner.nextLine();
             return new String[]{"-o", operation, operand1, operand2};
         } else {
             return new String[]{"-o", operation, operand1};
         }
     }
 
     @Command(name = "basic", description = "Basic arithmetic operations")
     static class BasicOperations implements Callable<Integer> {
 
         @Option(names = {"-o", "--operation"}, description = "Operation to perform: add, sub, mul, div", required = true)
         private String operation;
 
         @Parameters(index = "0", description = "First operand")
         private double operand1;
 
         @Parameters(index = "1", description = "Second operand")
         private double operand2;
 
         @Override
         public Integer call() {
             double result;
             switch (operation) {
                 case "add":
                     result = operand1 + operand2;
                     System.out.printf("Result: %.2f + %.2f = %.2f%n", operand1, operand2, result);
                     break;
                 case "sub":
                     result = operand1 - operand2;
                     System.out.printf("Result: %.2f - %.2f = %.2f%n", operand1, operand2, result);
                     break;
                 case "mul":
                     result = operand1 * operand2;
                     System.out.printf("Result: %.2f * %.2f = %.2f%n", operand1, operand2, result);
                     break;
                 case "div":
                     if (operand2 == 0) {
                         System.out.println("Error: Division by zero.");
                         return 1;
                     }
                     result = operand1 / operand2;
                     System.out.printf("Result: %.2f / %.2f = %.2f%n", operand1, operand2, result);
                     break;
                 default:
                     System.out.println("Invalid operation. Use add, sub, mul, or div.");
                     return 1;
             }
             return 0;
         }
     }
 
     @Command(name = "advanced", description = "Advanced mathematical operations")
     static class AdvancedOperations implements Callable<Integer> {
 
         @Option(names = {"-o", "--operation"}, description = "Operation to perform: pow, sqrt", required = true)
         private String operation;
 
         @Parameters(index = "0", description = "Operand (or base for power operation)")
         private double operand1;
 
         @Parameters(index = "1", description = "Exponent (required for power operation)", arity = "0..1")
         private Double operand2;
 
         @Override
         public Integer call() {
             double result;
             switch (operation) {
                 case "pow":
                     if (operand2 == null) {
                         System.out.println("Exponent is required for power operation.");
                         return 1;
                     }
                     result = Math.pow(operand1, operand2);
                     System.out.printf("Result: %.2f ^ %.2f = %.2f%n", operand1, operand2, result);
                     break;
                 case "sqrt":
                     if (operand1 < 0) {
                         System.out.println("Error: Cannot calculate the square root of a negative number.");
                         return 1;
                     }
                     result = Math.sqrt(operand1);
                     System.out.printf("Result: √%.2f = %.2f%n", operand1, result);
                     break;
                 default:
                     System.out.println("Invalid operation. Use pow or sqrt.");
                     return 1;
             }
             return 0;
         }
     }

    @Override
    public Integer call() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'call'");
    }
 }
 