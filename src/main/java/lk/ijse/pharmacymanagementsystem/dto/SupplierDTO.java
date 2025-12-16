package lk.ijse.pharmacymanagementsystem.dto;

public class SupplierDTO {
    private String company_name;
    private String address;
    private String email;
    private int company_contact;
    private String supplier_name;
    private int supplier_contact;

    public SupplierDTO() {
    }

    public SupplierDTO(String company_name, String address, String email, int company_contact, String supplier_name, int supplier_contact) {
        this.company_name = company_name;
        this.address = address;
        this.email = email;
        this.company_contact = company_contact;
        this.supplier_name = supplier_name;
        this.supplier_contact = supplier_contact;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCompany_contact() {
        return company_contact;
    }

    public void setCompany_contact(int company_contact) {
        this.company_contact = company_contact;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public int getSupplier_contact() {
        return supplier_contact;
    }

    public void setSupplier_contact(int supplier_contact) {
        this.supplier_contact = supplier_contact;
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
                "company_name='" + company_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", company_contact=" + company_contact +
                ", supplier_name='" + supplier_name + '\'' +
                ", supplier_contact=" + supplier_contact +
                '}';
    }
}
