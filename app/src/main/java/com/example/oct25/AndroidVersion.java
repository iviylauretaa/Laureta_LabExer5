package com.example.oct25;

public class AndroidVersion {
    private String name, country, industry, ceo;
    private int logo;

    public AndroidVersion(String name, String country, String industry, String ceo, int logo) {
        this.name = name;
        this.country = country;
        this.industry = industry;
        this.ceo = ceo;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getIndustry() {
        return industry;
    }

    public String getCeo() {
        return ceo;
    }

    public int getLogo() {
        return logo;
    }
}
