package edu.campus.numerique.module.java.license.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.campus.numerique.module.java.license.service.ILicenseServices;

@Api("API allowing the verification of driving licenses")
@RestController
public class LicenseController {

	@Autowired
	private ILicenseServices service;

	@ApiOperation(value = "Return a Boolean representing the validity of the driving license number")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success")})
	@GetMapping("/licenses/{licenseId}")
	  Boolean IsLicenseValid(@ApiParam(name = "licenseId", value = "The id of the driving license to check.", required = true) @PathVariable String licenseId) {
	    return service.isLicenseValid(licenseId);
	  }
	
}
