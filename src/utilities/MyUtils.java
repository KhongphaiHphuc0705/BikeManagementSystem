package utilities;

import java.time.LocalDate;
import java.util.Scanner;

public class MyUtils {
    public static Scanner sc = new Scanner(System.in);
    
    public static int getInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.err.println(errorMsg);
            }
        }
    }
    
    public static int getInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.err.println(errorMsg);
            }
        }
    }

    public static int getInteger(String inputMsg, String errorMsg, int lowerBound) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.err.println(errorMsg);
            }
        }
    }

    public static double getDouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.err.println(errorMsg);
            }
        }
    }
    
    public static double getDouble(String inputMsg, String errorMsg, double lowerBound) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < lowerBound)
                    throw new Exception();                
                return n;
            } catch (Exception e) {
                System.err.println(errorMsg);
            }
        }
    }
    
    public static String getString1(String inputMsg, String errorMsg, String format) {
        String str;
        boolean matched;
        while (true) {
            System.out.print(inputMsg);
            str = sc.nextLine().trim();
            if(str.isEmpty())
                return str;
            matched = str.matches(format);
            if (matched == false)
                System.err.println(errorMsg);
            else
                return str;            
        }
    }
    
    public static String getString(String inputMsg, String errorMsg, String format) {
        String str;
        boolean matched;
        while (true) {
            System.out.print(inputMsg);
            str = sc.nextLine().trim();
            matched = str.matches(format);
            if (str.length() == 0 || str.isEmpty() || matched == false)
                System.err.println(errorMsg);
            else
                return str;            
        }
    }
    
    public static String getString(String inputMsg, String errorMsg) {
        String str;        
        while (true) {
            System.out.print(inputMsg);
            str = sc.nextLine().trim();            
            if (str.length() == 0 || str.isEmpty())
                System.err.println(errorMsg);
            else 
                return str;
        }
    }
}
