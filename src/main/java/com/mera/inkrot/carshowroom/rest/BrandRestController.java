package com.mera.inkrot.carshowroom.rest;

import com.mera.inkrot.carshowroom.model.Brand;
import com.mera.inkrot.carshowroom.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/brand")
public class BrandRestController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Brand> getBrand(@PathVariable("id") Long brandId) {
        if (brandId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Brand customer = brandService.getById(brandId);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Brand> deleteBrand(@PathVariable("id") Long brandId) {
        if (brandId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        brandService.delete(brandId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
