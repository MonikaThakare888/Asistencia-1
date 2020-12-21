package com.asistencia.request;


public class AnimalSignUpRequest 
{

	private String fullName;
	private String dateOfBirth;
	private String gender;
	private int phonenumber;
	private String city;
	private String pincode;
	private String password;
	private String addres;
	
	public AnimalSignUpRequest(String fullName, String dateOfBirth, String gender, int phonenumber, String city,
			String pincode, String password, String addres) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phonenumber = phonenumber;
		this.city = city;
		this.pincode = pincode;
		this.password = password;
		this.addres = addres;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	@Override
	public String toString() {
		return "AnimalSignUpRequest [fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
				+ ", phonenumber=" + phonenumber + ", city=" + city + ", pincode=" + pincode + ", password=" + password
				+ ", addres=" + addres + "]";
	}
	
	
	
}
