package edu.campus.numerique.module.java.license.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.campus.numerique.module.java.license.model.License;


public interface LicenseRepository extends JpaRepository<License, String> {

}
