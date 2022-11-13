/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dto;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Binath Perera
 */
public class EmployeeDTO implements SuperDTO{

    private String nic,birthday,nationality,firstName,middleName,lastName,bloodGroup
        ,gender,permanantAddress,addressOfCorrespondance,
        contactNumber,email,emergencyContactNumber,emergencyContactPerson,
        relationshipWithEmergencyContactPerson, department, designation,
        employeeID,managerID ,dateOfJoining, bankAccountNumber,bankName,
        bankBranch,dateOfLeaving;

    
    private boolean maritalStatus,employmentStatus,clockedIn;
    private double salary,otrate,hpd;

    private File qualifications,employmentAgreement;
    ArrayList<String> workWeek;
    public EmployeeDTO(){}
    public EmployeeDTO(String employeeID,String contactNumber,String email,
            String addressOfCorrespondance,String emergencyContactNumber,
            String emergencyContactPerson, String relationshipWithEmergencyContactPerson){
        this.employeeID=employeeID;
        this.contactNumber=contactNumber;
        this.email=email;
        this.addressOfCorrespondance=addressOfCorrespondance;
        this.emergencyContactNumber=emergencyContactNumber;
        this.emergencyContactPerson=emergencyContactPerson;
        this.relationshipWithEmergencyContactPerson=relationshipWithEmergencyContactPerson;
    }
    public EmployeeDTO(String nic, String birthday, String nationality,
            String firstName, String middleName, String lastName,
            String bloodGroup, String Gender, String permanantAddress,
            String addressOfCorrespondance, String contactNumber, String email,
            String emergencyContactNumber, String emergencyContactPerson,
            String relationshipWithEmergencyContactPerson, String department, 
            String designation, String employeeID, String managerID,
            String dateOfJoining, String bankAccountNumber, String bankName, 
            String bankBranch, boolean maritalStatus, boolean employmentStatus,
            double salary, double otrate , double hpd, 
            ArrayList<String> workWeek, File qualifications, File employmentAgreement) {
        this.workWeek=workWeek;
        this.otrate=otrate;
        this.hpd=hpd;
        this.nic = nic;
        this.birthday = birthday;
        this.nationality = nationality;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.bloodGroup = bloodGroup;
        this.gender = Gender;
        this.permanantAddress = permanantAddress;
        this.addressOfCorrespondance = addressOfCorrespondance;
        this.contactNumber = contactNumber;
        this.email = email;
        this.emergencyContactNumber = emergencyContactNumber;
        this.emergencyContactPerson = emergencyContactPerson;
        this.relationshipWithEmergencyContactPerson = relationshipWithEmergencyContactPerson;
        this.department = department;
        this.designation = designation;
        this.employeeID = employeeID;
        this.managerID = managerID;
        this.dateOfJoining = dateOfJoining;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.maritalStatus = maritalStatus;
        this.employmentStatus = employmentStatus;
        this.salary = salary;
        this.qualifications = qualifications;
        this.employmentAgreement = employmentAgreement;
    }
    public EmployeeDTO(String employeeID,String firstName, String middleName, String lastName){
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
    public EmployeeDTO(String nic, String birthday, String nationality,
            String firstName, String middleName, String lastName,
            String bloodGroup, String Gender, String permanantAddress,
            String addressOfCorrespondance, String contactNumber, String email,
            String emergencyContactNumber, String emergencyContactPerson,
            String relationshipWithEmergencyContactPerson, String department, 
            String designation, String employeeID, String managerID,
            String dateOfJoining, String bankAccountNumber, String bankName, 
            String bankBranch, boolean maritalStatus, boolean employmentStatus,
            double salary, double otrate , double hpd, 
            ArrayList<String> workWeek, File qualifications, File employmentAgreement,
            boolean clockedIn,String dateOfLeaving) {
        this.dateOfLeaving=dateOfLeaving;
        this.clockedIn=clockedIn;
        this.workWeek=workWeek;
        this.otrate=otrate;
        this.hpd=hpd;
        this.nic = nic;
        this.birthday = birthday;
        this.nationality = nationality;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.bloodGroup = bloodGroup;
        this.gender = Gender;
        this.permanantAddress = permanantAddress;
        this.addressOfCorrespondance = addressOfCorrespondance;
        this.contactNumber = contactNumber;
        this.email = email;
        this.emergencyContactNumber = emergencyContactNumber;
        this.emergencyContactPerson = emergencyContactPerson;
        this.relationshipWithEmergencyContactPerson = relationshipWithEmergencyContactPerson;
        this.department = department;
        this.designation = designation;
        this.employeeID = employeeID;
        this.managerID = managerID;
        this.dateOfJoining = dateOfJoining;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.maritalStatus = maritalStatus;
        this.employmentStatus = employmentStatus;
        this.salary = salary;
        this.qualifications = qualifications;
        this.employmentAgreement = employmentAgreement;
    }
        public EmployeeDTO(String nic, String birthday, String nationality,
            String firstName, String middleName, String lastName,
            String bloodGroup, String Gender, String permanantAddress,
            String department, 
            String designation, String employeeID, String managerID,
            String dateOfJoining, String bankAccountNumber, String bankName, 
            String bankBranch, boolean maritalStatus, boolean employmentStatus,
            double salary, double otrate , double hpd, 
            ArrayList<String> workWeek, File qualifications, File employmentAgreement,
            boolean clockedIn,String dateOfLeaving) {
        this.dateOfLeaving=dateOfLeaving;
        this.clockedIn=clockedIn;
        this.workWeek=workWeek;
        this.otrate=otrate;
        this.hpd=hpd;
        this.nic = nic;
        this.birthday = birthday;
        this.nationality = nationality;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.bloodGroup = bloodGroup;
        this.gender = Gender;
        this.permanantAddress = permanantAddress;
        this.department = department;
        this.designation = designation;
        this.employeeID = employeeID;
        this.managerID = managerID;
        this.dateOfJoining = dateOfJoining;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.maritalStatus = maritalStatus;
        this.employmentStatus = employmentStatus;
        this.salary = salary;
        this.qualifications = qualifications;
        this.employmentAgreement = employmentAgreement;
    }
    
    public String getDateOfLeaving() {
        return dateOfLeaving;
    }

    public void setDateOfLeaving(String dateOfLeaving) {
        this.dateOfLeaving = dateOfLeaving;
    }

    public boolean isClockedIn() {
        return clockedIn;
    }

    public void setClockedIn(boolean clockedIn) {
        this.clockedIn = clockedIn;
    }
    
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getOtrate() {
        return otrate;
    }

    public void setOtrate(double otrate) {
        this.otrate = otrate;
    }

    public double getHpd() {
        return hpd;
    }

    public void setHpd(double hpd) {
        this.hpd = hpd;
    }

    public ArrayList<String> getWorkWeek() {
        return workWeek;
    }

    public void setWorkWeek(ArrayList<String> workWeek) {
        this.workWeek = workWeek;
    }
    
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String Gender) {
        this.gender = Gender;
    }

    public String getPermanantAddress() {
        return permanantAddress;
    }

    public void setPermanantAddress(String permanantAddress) {
        this.permanantAddress = permanantAddress;
    }

    public String getAddressOfCorrespondance() {
        return addressOfCorrespondance;
    }

    public void setAddressOfCorrespondance(String addressOfCorrespondance) {
        this.addressOfCorrespondance = addressOfCorrespondance;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getEmergencyContactPerson() {
        return emergencyContactPerson;
    }

    public void setEmergencyContactPerson(String emergencyContactPerson) {
        this.emergencyContactPerson = emergencyContactPerson;
    }

    public String getRelationshipWithEmergencyContactPerson() {
        return relationshipWithEmergencyContactPerson;
    }

    public void setRelationshipWithEmergencyContactPerson(String relationshipWithEmergencyContactPerson) {
        this.relationshipWithEmergencyContactPerson = relationshipWithEmergencyContactPerson;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public boolean isMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public boolean isEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(boolean employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public File getQualifications() {
        return qualifications;
    }

    public void setQualifications(File qualifications) {
        this.qualifications = qualifications;
    }

    public File getEmploymentAgreement() {
        return employmentAgreement;
    }

    public void setEmploymentAgreement(File employmentAgreement) {
        this.employmentAgreement = employmentAgreement;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
