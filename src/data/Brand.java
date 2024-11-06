package data;

public class Brand {
    private String brandId;
    private String name;
    private String country;

    public Brand(String brandId, String name, String country) {
        this.brandId = brandId;
        this.name = name;
        this.country = country;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
