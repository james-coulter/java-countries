package com.lambdaschool.countrysearch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/names")
public class CountryControllerNames {

    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> getCountries() {
        CountrysearchApplication.ourCountryList.countryList
                .sort((c1, c2) -> c1.getCountryName().compareToIgnoreCase(c2.getCountryName()));
        return new ResponseEntity<>(CountrysearchApplication.ourCountryList.countryList, HttpStatus.OK);
    }

    @GetMapping(value = "/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getAlphabeticCountry(@PathVariable char letter) {
        CountrysearchApplication.ourCountryList.countryList
                .sort((c1, c2) -> c1.getCountryName().compareToIgnoreCase(c2.getCountryName()));
        List<Country> countries = CountrysearchApplication.ourCountryList
                .findCountries(c -> c.getCountryName().toUpperCase().charAt(0) == Character.toUpperCase(letter));

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

}
