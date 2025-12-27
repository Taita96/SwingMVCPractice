package model.entity.enums;

public enum City {


    MADRID(Country.SPAIN),
    BARCELONA(Country.SPAIN),
    ZARAGOZA(Country.SPAIN),


    PARIS(Country.FRANCE),
    LYON(Country.FRANCE),
    MARSEILLE(Country.FRANCE),


    BERLIN(Country.GERMANY),
    MUNICH(Country.GERMANY),
    HAMBURG(Country.GERMANY),


    ROME(Country.ITALY),
    MILAN(Country.ITALY),
    NAPLES(Country.ITALY);

    private final Country country;

    City(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }
}
