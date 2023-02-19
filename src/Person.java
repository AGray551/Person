public class Person
{
    private String ID;
    private String firstName;
    private String lastName;
    private String titleName;
    private int birth;
    static private int IDSeed =  1;

    public static void setIDSeed(int IDSeed) {
        Person.IDSeed = IDSeed;
    }

    public static int getIDSeed() {
        return IDSeed;
    }

    public Person(String ID, String firstName, String lastName, String titleName, int birth)
    {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.titleName = titleName;
        this.birth = birth;
    }

    public Person(String firstName, String lastName, String titleName, int birth)
    {
        this.ID = this.genID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.titleName = titleName;
        this.birth = birth;
    }


    public String getID() {
        return ID;
    }

    private String genID() {
        String newID = "" + IDSeed;
        while(newID.length() < 6)
        {
            newID = "0" + newID;
        }

        IDSeed++;

        return newID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID='" + ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", titleName='" + titleName + '\'' +
                ", birth=" + birth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return birth == person.birth && ID.equals(person.ID) && firstName.equals(person.firstName) && lastName.equals(person.lastName) && titleName.equals(person.titleName);
    }

    public String toJSONRecord()
    {
        String retString = "";
        char DQ = '\u0022';  // Assign the double quote char to a variable
        retString =  "{" + DQ + "ID" + DQ + ":" + DQ + this.ID + DQ + ",";
        retString += DQ + "firstName" + DQ + ":" + DQ + this.firstName + DQ + ",";
        retString += " " + DQ + "lastName"  + DQ + ":" + DQ + this.lastName + DQ + ",";
        retString += " " + DQ + "titleName" + DQ + ":" + DQ + this.titleName + DQ + ",";
        retString += " " + DQ + "birth"  + DQ + ":" + this.birth + "}";

        return retString;
    }

    public String toXMLRecord()
    {
        String retString = "";

        retString = "<Person>" + "<ID>" + this.ID + "</ID>";
        retString += "<firstName>" + this.firstName + "</firstName>";
        retString += "<lastName>" + this.lastName + "</lastName>";
        retString += "<titleName" + this.titleName + "</titleName>";
        retString += "<birth>" + this.birth + "</birth></Person>";

        return retString;
    }
    public String toCSVRecord() {
        return  this.ID + ", " + this.firstName + "," + this.lastName + "," + this.titleName + "," + birth;
    }
}
