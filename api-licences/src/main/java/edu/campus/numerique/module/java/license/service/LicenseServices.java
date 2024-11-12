package edu.campus.numerique.module.java.license.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.campus.numerique.module.java.license.dao.LicenseRepository;
import edu.campus.numerique.module.java.license.model.License;

@Service
public class LicenseServices implements ILicenseServices {

	@Autowired
	private LicenseRepository repository;

	@Override
	public Boolean isLicenseValid(String licenseId) {

		Optional<License> l = repository.findById(licenseId);

		if (l.isPresent()) {
			return l.get().getIsValid();
		}

		Random r = new Random();
		Boolean result = r.nextBoolean();
		repository.save(new License(licenseId,result));
		
		return result;
	}

}
