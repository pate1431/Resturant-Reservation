package ca.sheridancollege.pate1431.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Resturant {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String location;
	private Long phoneNumber;
	
	@OneToMany
	@JoinTable(name="Resturant_Reservation", joinColumns= @JoinColumn(name="Resturant_ID"),
	inverseJoinColumns=@JoinColumn(name="Reservation_ID"))
	private List<Reservation> reservation= new ArrayList<Reservation>();
}
