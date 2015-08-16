package com.dandy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.Enumeration;
import com.dandy.core.Gender;
import com.dandy.core.Person;
import com.dandy.core.Contact;
import com.dandy.core.Role;
import com.dandy.core.RoleService;
import com.dandy.core.PersonService;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.FloatValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import javax.servlet.http.HttpServletRequest;

public class InputValidator {

    FloatValidator floatValidator;

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
        RoleService roleService = new RoleService();
        List<Role> roles = roleService.getRoles();
        if(person.getContacts() != null) {
            PersonService personService = new PersonService();
            personService.removeContacts(person);

        }
            Set<Contact> contacts = new HashSet<Contact>();
            person.setContacts(contacts);

        String[] ctype = request.getParameterValues("ctype");
        String[] numbers = request.getParameterValues("number");
        if(ctype!=null){
            for (int i = 0; i < ctype.length; i++) {
                    Contact contact = new Contact();
                    contact.setDescription(ctype[i]);
                    contact.setNumber(longFormat(numbers[i]));
                    if(contact.getNumber() != (long) 0){
                        person.getContacts().add(contact);
                    }
            }
        }
        String[] results = request.getParameterValues("role");
        Set<Role> newRoles = new HashSet<Role>();
        if(results!=null) {
            for (int i = 0; i < results.length; i++) {
                newRoles.add(roles.get(Integer.valueOf(results[i])));
            }
        }
        person.setRoles(newRoles);

        return person;

    }

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

   public long longFormat(String contactNumber) {
        if(contactNumber == "") {
            return (long) 0;
        } else {
            return Long.valueOf(contactNumber);
        }
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
                    int compare = validator.compareYears(parsed, new Date(), null);
                    if (compare > -1) {
                        return javaDate;
                    }else{
                        javaDate = sqlFormat.parse(sqlString);
                    }
                } catch (ParseException e) {
                    return javaDate;
                }
            } 
	    return javaDate;
    }

    public Gender genderProcess(Integer choice) {
        Gender gender = Gender.Male;
            switch (choice) {
                case 0:
           	    gender = Gender.Male;
                    break;
                case 1:
           	    gender = Gender.Female;
                    break;
                case 2:
           	    gender = Gender.Undecided;
                    break;
            }
        return gender;
    }
}
