package com.lambdaschool.countrysearch;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/population")
public class CountryControllerPopulation {

    @GetMapping(value = "/size/{people}", produces = {"application/json"})
    public ResponseEntity<?> countryPopGreaterEqual(@PathVariable Long people) {

        List<Country> countries = CountrysearchApplication.ourCountryList.findCountries(c -> c.getPopulation() >= people);
        countries.sort((c1, c2) -> c1.getCountryName().compareToIgnoreCase(c2.getCountryName()));
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }


    @RequestMapping(value = "/min", produces = {"application/json"})
    public ResponseEntity<?> getCountriesPopMin() {
        CountrysearchApplication.ourCountryList.countryList.sort(Comparator.comparingLong(Country::getPopulation));
        Country countries = CountrysearchApplication.ourCountryList.countryList.get(0);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @RequestMapping(value = "/max", produces = {"application/json"})
    public ResponseEntity<?> getCountriesPopMax() {
        CountrysearchApplication.ourCountryList.countryList.sort(Comparator.comparingLong(Country::getPopulation));
        Country countries = CountrysearchApplication.ourCountryList.countryList.get(CountrysearchApplication.ourCountryList.countryList.size() - 1);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
}
