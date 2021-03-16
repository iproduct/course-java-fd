package invoicing.util;

import invoicing.dao.exception.InvalidEntityDataException;
import invoicing.model.Product;
import invoicing.model.Unit;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static invoicing.model.Unit.PCS;

public class InputUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static boolean inputProduct(Product p) {
        do {
            System.out.print("Product code:");
            String ans = sc.nextLine();
            if(Pattern.matches("([A-Z]{2})(\\d{3})", ans)) {
                p.setCode(ans);
            } else {
                System.out.println("Invalid product code. Example code: BK001");
            }
        } while(p.getCode() == null);
        do {
            System.out.print("Product name:");
            String ans = sc.nextLine().trim();
            if(ans.length() > 1) {
                p.setName(ans);
            } else {
                System.out.println("Invalid product name. Should be at least two characters long.");
            }
        } while(p.getName() == null);
        do {
            System.out.print("Product description:");
            String ans = sc.nextLine().trim();
            if(ans.length() > 9) {
                p.setDescription(ans);
            } else {
                System.out.println("Invalid product description. Should be at least ten characters long.");
            }
        } while(p.getDescription() == null);
        do {
            System.out.print("Product price:");
            String ans = sc.nextLine();
            p.setPrice(-1);
            try {
                p.setPrice(Double.parseDouble(ans));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price number format. Example: 32.50");
            }
            if(p.getPrice() < 0) {
                System.out.println("Invalid price - should be positive number.");
            }
        } while(p.getPrice() < 0);
        do{
            System.out.print(Arrays.stream(Unit.values())
                .map(unit -> (unit.ordinal() + 1) + ": " + unit)
                .collect(Collectors.joining(", ")) + " [Enter for PCS]: ");
            String ans = sc.nextLine().trim();
            if(ans.length() == 0) {
                p.setUnit(PCS);
                break;
            }
            int unitOrd = -1;
            try {
                unitOrd = Integer.parseInt(ans);
            } catch (NumberFormatException e) {}
            if(unitOrd < 1 || unitOrd > Unit.values().length) {
                System.out.println("Invalid unit choice. Try again.");
            } else {
                p.setUnit(Unit.values()[unitOrd  - 1]);
            }
        } while(p.getUnit() == null);
        return true;
    }
}
