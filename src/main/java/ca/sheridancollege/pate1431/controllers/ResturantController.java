package ca.sheridancollege.pate1431.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.pate1431.beans.Reservation;
import ca.sheridancollege.pate1431.beans.ReservationStatus;
import ca.sheridancollege.pate1431.beans.Resturant;
import ca.sheridancollege.pate1431.repositories.ReservationRepository;
import ca.sheridancollege.pate1431.repositories.ResturantRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ResturantController {


	private ResturantRepository resturantRepository;
	private ReservationRepository reservationRepository;
	@GetMapping("/")
	public String index(Model model)
	{

		model.addAttribute("streamingService", ReservationStatus.values());
		model.addAttribute("resturant", new Resturant());
		model.addAttribute("reservation", new Reservation());
		List<Resturant> resturantList= resturantRepository.findAll();
		List<Reservation> reservationList= reservationRepository.findAll(); 

		model.addAttribute("reservationList", reservationList);	
		model.addAttribute("resturantList", resturantList);
		return "index";
	}
	@PostMapping("/addReservation")
	public String index(Model model, @RequestParam String userName, @RequestParam Long userPhone, 
			@RequestParam Long pid,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime appointmentTime)
	{
		model.addAttribute("streamingService", ReservationStatus.values());
		Resturant data=resturantRepository.findById(pid).get();
		
		Reservation reservation=new Reservation();
		reservation.setUserName(userName);
		reservation.setUserPhone(userPhone);
		reservation.setAppointmentDate(appointmentDate);
		reservation.setAppointmentTime(appointmentTime);
		reservation.setStatus(ReservationStatus.APPROVED);
		
		reservationRepository.save(reservation);
		
		data.getReservation().add(reservation);
		List<Reservation> reservationList= reservationRepository.findAll(); 
		
		resturantRepository.save(data);
		
		model.addAttribute("reservationList", reservationList);
		model.addAttribute("resturant", new Resturant());
		List<Resturant> resturantList= resturantRepository.findAll();
		model.addAttribute("resturantList", resturantList);
		return "index";
	}
}
