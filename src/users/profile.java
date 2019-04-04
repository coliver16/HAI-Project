package users;

import items.item;

public class profile {
    //
    public user user;
    private String firstName;
    private String lastName;
    public String email;
    private String pw;
    private String phoneNumber;
    private String insuranceCompanyName;
    private String insuranceCompanyFax;
    private String insuranceCompanyEmail;

    //Constructer to create a new profile
    public profile(user user, String fname, String lname, String email, String pw, String phoneNumber, String insuranceCompanyName, String insuranceCompanyFax, String insuranceCompanyEmail) {
        this.user = user;
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.pw = pw;
        this.phoneNumber = phoneNumber;
        this.insuranceCompanyName = insuranceCompanyName;
        this.insuranceCompanyEmail = insuranceCompanyEmail;
        this.insuranceCompanyFax = insuranceCompanyFax;
    }

    public void profileUpdate(profile oldProfile, user user, String fname, String lname, String email, String pw, String phoneNumber, String insuranceCompanyName, String insuranceCompanyFax, String insuranceCompanyEmail) {
        if (user == null) {
            this.user = oldProfile.getUser();
        }
        else {
        this.user = user;
        }

        if (fname.equals("")) {
            this.firstName = oldProfile.getFirstName();
        }
        else {
            this.firstName = fname;
        }

        if (lname.equals("")) {
            this.lastName = oldProfile.getLastName();
        }
        else {
            this.lastName = lname;
        }

        if (email.equals("")) {
            this.email = oldProfile.getEmail();
        }
        else {
            this.email = email;
        }

        if (pw.equals("")) {
            this.pw = oldProfile.getPw();
        }
        else {
            this.pw = pw;
        }

        if (phoneNumber.equals("")) {
            this.phoneNumber = oldProfile.getPhoneNumber();
        }
        else {
            this.phoneNumber = phoneNumber;
        }

        if (insuranceCompanyName.equals("")) {
            this.insuranceCompanyName = oldProfile.getInsuranceCompanyName();
        }
        else {
            this.insuranceCompanyName = insuranceCompanyName;
        }

        if (insuranceCompanyEmail.equals("")) {
            this.insuranceCompanyEmail = oldProfile.getInsuranceCompanyEmail();
        }
        else {
            this.insuranceCompanyEmail = insuranceCompanyEmail;
        }

        if (insuranceCompanyFax.equals("")) {
            this.insuranceCompanyFax = oldProfile.getInsuranceCompanyFax();
        }
        else {
            this.insuranceCompanyFax = insuranceCompanyFax;
        }
    }

    //Delete profile
    public void profileDelete(profile profile) {
        profile = null;
    }


    //Getters
    public user getUser() {
        return user;
    }

    private String getFirstName() {
        return firstName;
    }

    private String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    private String getPw() {
        return pw;
    }

    private String getPhoneNumber() {
        return phoneNumber;
    }

    private String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    private String getInsuranceCompanyFax() {
        return insuranceCompanyFax;
    }

    private String getInsuranceCompanyEmail() {
        return insuranceCompanyEmail;
    }

}