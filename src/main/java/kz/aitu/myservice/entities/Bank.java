package kz.aitu.myservice.entities;

public class Bank {
    private Integer bankId;
    private String name;
    private String country;

    public Bank() {
    }

    public Bank(Integer bankId, String name, String country) {
        this.bankId = bankId;
        this.name = name;
        this.country = country;
    }

    public Integer getBankId() { return bankId; }
    public void setBankId(Integer bankId) { this.bankId = bankId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}