package users;

public class Profile {
    //public User user;
    private String firstName;
    private String lastName;
    public String email;
    private String pw;
    private String phoneNumber;
    private String insuranceCompanyName;
    private String insuranceCompanyFax;
    private String insuranceCompanyEmail;

    //Constructer to create a new Profile
    public Profile(/*User user,*/ String fname, String lname, String email, String pw, String phoneNumber, String insuranceCompanyName, String insuranceCompanyFax, String insuranceCompanyEmail) {
        //this.user = user;
        this.firstName = fname;
        this.lastName = lname;
        this.email = email;
        this.pw = pw;
        this.phoneNumber = phoneNumber;
        this.insuranceCompanyName = insuranceCompanyName;
        this.insuranceCompanyEmail = insuranceCompanyEmail;
        this.insuranceCompanyFax = insuranceCompanyFax;
    }

    public void profileUpdate(Profile oldProfile, /*User user,*/ String fname, String lname, String email, String pw, String phoneNumber, String insuranceCompanyName, String insuranceCompanyFax, String insuranceCompanyEmail) {
        /*if (user == null) {
            this.user = oldProfile.getUser();
        }
        else {
        this.user = user;
        }*/

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

    //Delete Profile
    public void profileDelete(Profile profile) {
        profile = null;
    }

    /*
    //Add Profile
    public void profileAdd(Profile newProfile, User User, String fname, String lname, String email, String pw, String phoneNumber, String insuranceCompanyName, String insuranceCompanyFax, String insuranceCompanyEmail)
    {
        INSERT INTO dbo.Profile_454 (profile_user, profile_firstname, profile_lastname, profile_email, profile_password, profile_phone_number, policy_company, policy_fax, policy_claims_email)
            VALUES (User,
                    fname,
                    lname,
                    email,
                    pw,
                    phoneNumber,
                    insuranceCompanyName,
                    insuranceCompanyFax,
                    insuranceCompanyEmail
            )
    }
     */

    //Getters
    /*public User getUser() {
        return user;
    }*/

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