import java.util.Scanner;
import java.util.InputMismatchException;

public class Canteen {
    public static double[] calculatePrice(double price, int quantity, boolean isStudent){
            double subtotal = price * quantity;
            double discountAmount;
            
            if(isStudent){
                discountAmount = (subtotal >= 500) ? subtotal * 0.15 : subtotal * 0.10;
            }else{
                discountAmount = (subtotal >= 500) ? subtotal * 0.05 : 0.00;
            }

            double orderTotal = subtotal - discountAmount;
            System.out.println();
            System.out.printf("Subtotal: $%.2f%n", subtotal);
            System.out.printf("Discount: $%.2f%n", discountAmount);
            System.out.printf("Order total: $%.2f%n", orderTotal);
            System.out.println();

            return new double[] {subtotal, discountAmount, orderTotal};
        }
        
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String[] menu = {"Pizza", "Buldak", "Brownies", "Banana Bread", "Donuts"};
        double[] prices = {50.00, 15.00, 60.00, 150.00, 200.00};

        int itemQuantity = 0;
        double totalDiscount = 0;
        double totalBeforeDiscount = 0;

        System.out.println("===== M E N U =====");
        for (int i=0; i < menu.length; i++){
            System.out.println((i + 1) + ". " + menu[i] + "\t - $" + prices[i] + "0");
        }

        boolean continueOrder = true;

        while(continueOrder){
            try {
                System.out.print("Enter item number: ");
                int itemNum = scanner.nextInt();

                if (itemNum < 1 || itemNum > menu.length) {
                    System.out.println("Invalid order");
                } else {
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();

                    if (quantity <= 0) {
                        System.out.println("Invalid order");
                    } else {
                        System.out.print("Are you a student (Y/N): ");
                        String studentInput = scanner.next();
                        boolean isStudent = studentInput.equalsIgnoreCase("Y");

                        double[] details = calculatePrice(prices[itemNum-1], quantity, isStudent);

                        itemQuantity += quantity;
                        totalBeforeDiscount += details[0];
                        totalDiscount += details[1];
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid order");
                scanner.nextLine();
            }

            System.out.print("Do you want to order again? (Y/N): ");
            String orderAgain = scanner.next();
            continueOrder = orderAgain.equalsIgnoreCase("Y");
            System.out.println();
        }

        double finalAmount = totalBeforeDiscount - totalDiscount;
        if(itemQuantity == 0){
            System.out.println("No order placed.");
        }else{
        System.out.println("===== ORDER SUMMARY =====");
        System.out.println("Total items: " + itemQuantity);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalAmount);
        System.out.println("Thankyou for ordering!");
        }
        
        
        scanner.close();
    }
}