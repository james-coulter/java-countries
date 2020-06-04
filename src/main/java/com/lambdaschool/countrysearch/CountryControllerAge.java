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
@RequestMapping("/age")
public class CountryControllerAge {

    @GetMapping(value = "/age/{age}", produces = {"application/json"})
    public ResponseEntity<?> countriesByAge(@PathVariable int age) {

        List<Country> countries = CountrysearchApplication.ourCountryList.findCountries(c -> c.getMedianAge() >= age);
        countries.sort((c1, c2) -> c1.getCountryName().compareToIgnoreCase(c2.getCountryName()));
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping(value = "/min", produces = {"application/json"})
    public ResponseEntity<?> countrySmallestMedianAge() {
        CountrysearchApplication.ourCountryList.countryList.sort(Comparator.comparingLong(Country::getMedianAge));
        Country countries = CountrysearchApplication.ourCountryList.countryList.get(0);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping(value = "/max", produces = {"application/json"})
    public ResponseEntity<?> countryLargestMedianAge() {
        CountrysearchApplication.ourCountryList.countryList.sort(Comparator.comparingLong(Country::getMedianAge));
        Country countries = CountrysearchApplication.ourCountryList.countryList.get(CountrysearchApplication.ourCountryList.countryList.size() - 1);
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

}
