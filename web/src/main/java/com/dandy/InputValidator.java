package com.dandy;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.dandy.core.Gender;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.FloatValidator;

public class InputValidator {

    Scanner scanner = new Scanner(System.in);

    public int integerChecker() {
        int number = -1;
        do {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Must be a number");
            }finally{
                scanner.nextLine();
            }
        } while (number < 0);
        return number;
    }

    public String simpleString() {
        String text = scanner.nextLine();
        return text;
    }
    
    
    public Date dateFormatter() {
        DateValidator validator = DateValidator.getInstance();
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean valid = false;
        Date javaDate = null;
        do {
            String date = scanner.next();
            valid = validator.isValid(date, "MM-dd-yyyy");
            if (valid) {
                try {
	                Date parsed = inputFormat.parse(date);
                    String sqlString = new SimpleDateFormat("yyyy-MM-dd").format(parsed);
                    javaDate = sqlFormat.parse(sqlString);
                    int compare = validator.compareYears(parsed, new Date(), null);
                    if (compare > -1) {
                        System.out.println("Must be lower than this year");
                        valid = false;
                    }
                } catch (ParseException e) {
                    valid = false;          
                }
            } else {
                System.out.println("Wrong format");
            }
        }while(!valid);
	return javaDate;
    }

    public float gwaChecker() {
        FloatValidator floatValidator = new FloatValidator();
        boolean valid = false;
        float input = (float)0.0;
        do {
            try {
                input = scanner.nextFloat();
                valid = floatValidator.isInRange(input, 1.0, 5.0);
            } catch (InputMismatchException e) {
                System.out.println("1.0 to 5.0 only");
                scanner.nextLine();
            }
        } while (!valid);
        return input;
    }

    public Gender genderProcess() {
        Gender gender = Gender.Male;
        int choice;
        boolean run = true;
        do {
            System.out.print("1. Male \t2. Female \t3. Undecided");
            choice = integerChecker();
            switch (choice) {
                case 1:
           	    gender = Gender.Male;
                    run = false;
                    break;
                case 2:
           	    gender = Gender.Female;
                    run = false;
                    break;
                case 3:
           	    gender = Gender.Undecided;
                    run = false;
                    break;
                default:
		    System.out.println("Please choose 1, 2 or 3 :");
		    break;
            }
        }while(run);
        return gender;
    }


    public boolean employmentProcess() {
        boolean employed = true;
        int choice;
        boolean run = true;
        do {
            System.out.print("1. Yes");
            System.out.println("\t2. No");
            choice = integerChecker();
            switch (choice) {
                case 1:
           	    employed = true;
                    run = false;
                    break;
                case 2:
           	    employed = false;
                    run = false;
                    break;
                default:
		    System.out.println("Please choose 1 or 2 :");
		    break;
            }
        }while(run);
        return employed;
    }

    public String contactDescriptor() {
        String contactDescription="";
        int choice;
        boolean run = true;
        do {
            System.out.print("1. Telephone");
            System.out.println("\t2. Cellphone");
            choice = integerChecker();
            switch (choice) {
                case 1:
           	    contactDescription = "Telephone";
                    run = false;
                    break;
                case 2:
           	    contactDescription = "Cellphone";
                    run = false;
                    break;
                default:
		    System.out.println("Please choose 1 or 2 :");
		    break;
            }
        }while(run);
        return contactDescription;
    }

    public long longChecker() {
        long number = -1;
        do {
            try {
                number = scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                scanner.nextLine();
            }
        } while (number < 0);
        return number;
    }
    
    public String dateDisplay(Date date) {
        String sqlString = new SimpleDateFormat("MM-dd-yyyy").format(date);
        return sqlString;
    }
}
