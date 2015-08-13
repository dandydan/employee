package com.dandy;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.dandy.core.Gender;
import com.dandy.core.Person;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import javax.servlet.http.HttpServletRequest;

public class InputValidator {

    Scanner scanner = new Scanner(System.in);
    FloatValidator floatValidator;


    public float gwaChecker(String floatString) {
        floatValidator = new FloatValidator();
        boolean valid = false;
        float input = floatCheck(floatString);
        if (floatValidator.isInRange(input, 1.0, 5.0)) {
            return input;
        } else {
            return (float)0.0;
        }
    }

    private float floatCheck(String floatString) {
        floatValidator = new FloatValidator();
        Float result = null;
        if((result=floatValidator.validate(floatString)) != null) {
            return (float)result;
        } else {
            return (float) 0.0;
        }
    }

    public int integerChecker(String intString) {
        IntegerValidator integerValidator = new IntegerValidator();
        Integer integer = null;
        if ((integer=integerValidator.validate(intString)) != null) {
            return (int) integer;
        } else {
            return 0;
        }
    }


    public String dateDisplay(Date date) {
        String sqlString = new SimpleDateFormat("MM-dd-yyyy").format(date);
        return sqlString;
    }

    public Person personCheck(HttpServletRequest request, Person person) {
        String title = (String) request.getParameter("title");
        String firstname = (String) request.getParameter("firstname");
        String middlename = (String) request.getParameter("middlename");
        String lastname = (String) request.getParameter("lastname");
        String suffix = (String) request.getParameter("suffix");
        String birthday = (String) request.getParameter("birthday");
        Boolean employed = Boolean.valueOf(request.getParameter("employed"));
        String gwa = (String)request.getParameter("gwa");
        String gender = (String) request.getParameter("gender");
        String stNo = (String) request.getParameter("stNo");
        String brgy = (String) request.getParameter("brgy");
        String subdivision = (String) request.getParameter("subdivision");
        String city = (String) request.getParameter("city");
        String zipcode = (String) request.getParameter("zipcode");

        person.setTitle(title);
        person.setFirstName(firstname);
        person.setMiddleName(middlename);
        person.setLastName(lastname);
        person.setSuffix(suffix);
        person.setBirthday(dateFormatter(birthday));
	    person.setEmployed(employed);
	    person.setGwa(gwaChecker(gwa));
        person.setGender(genderProcess(integerChecker(gender)));

        person.getAddress().setStNo(integerChecker(stNo));
        person.getAddress().setBrgy(brgy);
        person.getAddress().setSubdivision(subdivision);
        person.getAddress().setCity(city); 
        person.getAddress().setZipcode(integerChecker(zipcode));

        return person;

    }





























    public String simpleString() {
        String text = scanner.nextLine();
        return text;
    }
    
    
    public Date dateFormatter(String date) {
        DateValidator validator = DateValidator.getInstance();
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean valid = false;
        Date javaDate = null;
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
	return javaDate;
    }


    public Gender genderProcess(Integer choice) {
        Gender gender = Gender.Male;
            switch (choice) {
                case 1:
           	    gender = Gender.Male;
                    break;
                case 2:
           	    gender = Gender.Female;
                    break;
                case 3:
           	    gender = Gender.Undecided;
                    break;
            }
        return gender;
    }


  /*  public boolean employmentProcess() {
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
    }*/

    public String contactDescriptor() {
        String contactDescription="";
        int choice =    1;
        boolean run = true;
        do {
            System.out.print("1. Telephone");
            System.out.println("\t2. Cellphone");
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
    

}
