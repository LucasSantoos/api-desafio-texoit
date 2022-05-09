package com.santos.texoit.entities;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "producers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "movie_producer",
			joinColumns = @JoinColumn(name = "producer_id"),
			inverseJoinColumns = @JoinColumn(name = "movie_id")
	)
	private Set<Movie> movies = new HashSet<>();
}