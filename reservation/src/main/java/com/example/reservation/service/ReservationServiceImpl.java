package com.example.reservation.service;

import com.example.reservation.DTO.User;
import com.example.reservation.DTO.Vehicle;
import com.example.reservation.execption.UserNotAvalaible;
import com.example.reservation.execption.VehicleNotAvailable;
import com.example.reservation.model.Reservation;
import com.example.reservation.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
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
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9191/user/{userId}";
        return restTemplate.getForEntity(url, User.class, userId);
    }

    public ResponseEntity<Vehicle> findVehicleById(int vehicleId) {
        RestTemplate restTemplate = new RestTemplate();
        String url2 = "http://localhost:8080/api/vehicle/{reservation.getVehicleId()}";
        return restTemplate.getForEntity(url2, Vehicle.class, vehicleId);
    }
}
