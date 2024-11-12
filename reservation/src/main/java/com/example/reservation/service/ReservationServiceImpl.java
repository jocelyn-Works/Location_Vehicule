package com.example.reservation.service;

import com.example.reservation.DTO.Maintenance;
import com.example.reservation.DTO.User;
import com.example.reservation.DTO.Vehicle;
import com.example.reservation.execption.UserNotAvalaible;
import com.example.reservation.execption.VehicleNotAvailable;
import com.example.reservation.model.Reservation;
import com.example.reservation.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    RestTemplate restTemplate;

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ResponseEntity<Reservation> create(Reservation reservation) {
        List<Reservation> r = reservationRepository.findAllReservationBetweenDatesWithVehicleId(reservation.getStartDate(), reservation.getEndDate(), reservation.getVehicleId());
        List<Reservation> j = reservationRepository.findAllReservationBetweenDatesWithUserId(reservation.getStartDate(), reservation.getEndDate(), reservation.getUserId());

        ResponseEntity<User> response = findUserById(reservation.getUserId());
        ResponseEntity<Vehicle> response2 = findVehicleById(reservation.getVehicleId());

        LocalDate dateAujourdhui = LocalDate.now();
        LocalDate userBirthDate = response.getBody().getBirthDate().toLocalDate();
        LocalDate dtaOfObtainingPermit = response.getBody().getDateOfObtaining().toLocalDate();
        String vehiculeType = response2.getBody().getVehicleType();
        float taxHorse = response2.getBody().getTaxHorse();
        float basePrice = response2.getBody().getReservationPrice();
        float kmPrice = response2.getBody().getKmPrice();

        long userAge = calculateAge(userBirthDate, dateAujourdhui);
        long permitAvailable = calculateAge(dtaOfObtainingPermit, dateAujourdhui);

        if (!r.isEmpty()) {
            throw new VehicleNotAvailable("Vehicle non disponible a ces dates");
        }
        if (!j.isEmpty()) {
            throw new VehicleNotAvailable("L'utilisateur possède déja une reservation sur ces dates");
        }
        if (userAge < 18 || permitAvailable < 0) {
            throw new UserNotAvalaible("tu a moin de 18 ou ton permis n'est plus valide");
        }
        if (userAge <= 21 && taxHorse >= 8) {
            throw new UserNotAvalaible("tu a moin de 21ans et tu ne peu pas choisir un vehicule a moin de 8 chevaux fiscaux");
        }
        if (userAge >= 21 && userAge <= 25 && taxHorse >= 13) {
            throw new UserNotAvalaible("tu a entre 21 et 25ans selectionne donc un vehicule avec moin de 13 chevaux fiscaux");
        }

        if (Objects.equals(vehiculeType, "voiture")){
            reservation.setTotalPrice(basePrice + kmPrice * reservation.getKmToWish());
        }
        if (Objects.equals(vehiculeType, "2_roue")){
            reservation.setTotalPrice((float) (basePrice + response2.getBody().getCylinder() * 0.001 *  kmPrice * reservation.getKmToWish()));
        }
        if (Objects.equals(vehiculeType, "utilitaire")){
            reservation.setTotalPrice((float) (basePrice + response2.getBody().getVolume() * 0.05 * kmPrice * reservation.getKmToWish()));
        }
        return ResponseEntity.ok(reservationRepository.save(reservation));
    }

    @Override
    public ResponseEntity<String> modify(Reservation reservation, int id) {
        Optional<Reservation> reservationOptional = Optional.ofNullable(reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        if (reservationOptional.isEmpty()) {
            return new ResponseEntity<>("Reservation not found", HttpStatus.BAD_REQUEST);
        }

        List<Reservation> r = reservationRepository.findAllReservationBetweenDatesWithVehicleId(reservation.getStartDate(), reservation.getEndDate(), reservation.getVehicleId());
        List<Reservation> j = reservationRepository.findAllReservationBetweenDatesWithUserId(reservation.getStartDate(), reservation.getEndDate(), reservation.getUserId());

        ResponseEntity<User> response = findUserById(reservation.getUserId());
        ResponseEntity<Vehicle> response2 = findVehicleById(reservation.getVehicleId());

        LocalDate dateAujourdhui = LocalDate.now();
        LocalDate userBirthDate = response.getBody().getBirthDate().toLocalDate();
        LocalDate dtaOfObtainingPermit = response.getBody().getDateOfObtaining().toLocalDate();
        String vehiculeType = response2.getBody().getVehicleType();
        float taxHorse = response2.getBody().getTaxHorse();
        float basePrice = response2.getBody().getReservationPrice();
        float kmPrice = response2.getBody().getKmPrice();

        long userAge = calculateAge(userBirthDate, dateAujourdhui);
        long permitAvailable = calculateAge(dtaOfObtainingPermit, dateAujourdhui);

        if (!r.isEmpty()) {
            throw new VehicleNotAvailable("Vehicle non disponible a ces dates");
        }
        if (!j.isEmpty()) {
            throw new VehicleNotAvailable("L'utilisateur possède déja une reservation sur ces dates");
        }
        if (userAge < 18 || permitAvailable < 0) {
            throw new UserNotAvalaible("tu a moin de 18 ou ton permis n'est plus valide");
        }
        if (userAge <= 21 && taxHorse >= 8) {
            throw new UserNotAvalaible("tu a moin de 21ans et tu ne peu pas choisir un vehicule a moin de 8 chevaux fiscaux");
        }
        if (userAge >= 21 && userAge <= 25 && taxHorse >= 13) {
            throw new UserNotAvalaible("tu a entre 21 et 25ans selectionne donc un vehicule avec moin de 13 chevaux fiscaux");
        }
        if (Objects.equals(vehiculeType, "voiture")){
            reservation.setTotalPrice(basePrice + kmPrice * reservation.getKmToWish());
        }
        if (Objects.equals(vehiculeType, "2_roue")){
            reservation.setTotalPrice((float) (basePrice + response2.getBody().getCylinder() * 0.001 *  kmPrice * reservation.getKmToWish()));
        }
        if (Objects.equals(vehiculeType, "utilitaire")){
            reservation.setTotalPrice((float) (basePrice + response2.getBody().getVolume() * 0.05 * kmPrice * reservation.getKmToWish()));
        }

        reservation.setId(id);
        reservation.setUserId(reservation.getUserId());
        reservation.setVehicleId(reservation.getVehicleId());
        reservation.setStartDate(reservation.getStartDate());
        reservation.setEndDate(reservation.getEndDate());
        reservation.setKmToWish(reservation.getKmToWish());
        reservation.setTotalPrice(reservation.getTotalPrice());
        reservationRepository.save(reservation);
        return new ResponseEntity<>("Reservation updated", HttpStatus.OK);
    }

    public int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }

    public ResponseEntity<User> findUserById(int userId) {
        String url = "http://USER/user/{userId}";
        return restTemplate.getForEntity(url, User.class, userId);
    }

    public ResponseEntity<Vehicle> findVehicleById(int vehicleId) {
        String url2 = "http://VEHICLE/api/vehicle/{reservation.getVehicleId()}";
        return restTemplate.getForEntity(url2, Vehicle.class, vehicleId);
    }

    public Reservation ReservationMaintenanceUpdate( int id , Reservation reservation) {
        Optional<Reservation> reservationOptional = Optional.ofNullable(reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://VEHICLE/api/maintenance/vehicle/" + reservation.getVehicleId();
        ResponseEntity<Maintenance> maintenanceResponse = restTemplate.getForEntity(url, Maintenance.class);

        String url2 = "http://VEHICLE/api/vehicle/" + reservation.getVehicleId();
        Vehicle vehiculeResponse = restTemplate.getForObject(url2, Vehicle.class);

        String url3 = "http://VEHICLE/api/vehicle/" + reservation.getVehicleId();

        vehiculeResponse.setId(reservation.getVehicleId());
        vehiculeResponse.setTraveledKm(vehiculeResponse.getTraveledKm() + reservationOptional.get().getKmToWish() + reservation.getKmRealized());

        HttpEntity<Vehicle> request = new HttpEntity<Vehicle>(vehiculeResponse);
        restTemplate.exchange(url3, HttpMethod.PUT, request, Vehicle.class);

        if (reservationOptional.isPresent()) {
            reservation.setId(id);
            reservation.setKmRealized(reservationOptional.get().getKmToWish() + reservation.getKmRealized());

            String url4 = "http://VEHICLE/api/maintenance/" + reservation.getVehicleId();
            Maintenance maintenance = new Maintenance();
            if (vehiculeResponse.getVehicleType().equals("moto")) {
                if ((vehiculeResponse.getTraveledKm() - maintenanceResponse.getBody().getKmVehicle()) >= 1000 || calculateAge(LocalDate.from(maintenanceResponse.getBody().getUpdatedAt()), LocalDate.from(reservationOptional.get().getEndDate())) >= 1) {
                    maintenance.setNotification("chaîne motos +1000 km");
                    maintenance.setRealize(false);
                    HttpEntity<Maintenance> request2 = new HttpEntity<Maintenance>(maintenance);
                    restTemplate.postForEntity(url4, request2, Maintenance.class);

                }
                if (calculateAge(LocalDate.from(maintenanceResponse.getBody().getUpdatedAt()), LocalDate.from(reservationOptional.get().getEndDate())) >= 1) {
                    maintenance.setNotification("liquide de frein");
                    maintenance.setRealize(false);
                    HttpEntity<Maintenance> request2 = new HttpEntity<Maintenance>(maintenance);
                    restTemplate.postForEntity(url4, request2, Maintenance.class);
                }
            }
            if (vehiculeResponse.getVehicleType().equals("voiture") || vehiculeResponse.getVehicleType().equals("utilitaire")) {
                if ((vehiculeResponse.getTraveledKm() - maintenanceResponse.getBody().getKmVehicle()) >= 100000) {
                    maintenance.setNotification("courroie de distribution +100 000 Km");
                    maintenance.setRealize(false);
                    HttpEntity<Maintenance> request2 = new HttpEntity<Maintenance>(maintenance);
                    restTemplate.postForEntity(url4, request2, Maintenance.class);
                }
                if (calculateAge(LocalDate.from(maintenanceResponse.getBody().getUpdatedAt()), LocalDate.from(reservationOptional.get().getEndDate())) >= 1) {
                    maintenance.setNotification("les pneus doit etre changer + 1 ans");
                    maintenance.setRealize(false);
                    HttpEntity<Maintenance> request2 = new HttpEntity<Maintenance>(maintenance);
                    restTemplate.postForEntity(url4, request2, Maintenance.class);
                }
            }
            if (vehiculeResponse.getVehicleType().equals("utilitaire")) {
                if (calculateAge(LocalDate.from(maintenanceResponse.getBody().getUpdatedAt()), LocalDate.from(reservationOptional.get().getEndDate())) >= 2) {
                    maintenance.setNotification("les suspensions doivent etre changer + 2 ans");
                    maintenance.setRealize(false);
                    HttpEntity<Maintenance> request2 = new HttpEntity<Maintenance>(maintenance);
                    restTemplate.postForEntity(url4, request2, Maintenance.class);
                }
            }
            return reservationRepository.save(reservation);
        }
        return null;
    }


}
