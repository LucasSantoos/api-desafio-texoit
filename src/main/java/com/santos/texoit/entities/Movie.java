package com.santos.texoit.entities;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie implements Serializable, Comparable<Movie>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer year;
	private String title;
	private String studios;
	private boolean winner;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "movie_producer",
			joinColumns = @JoinColumn(name = "movie_id"),
			inverseJoinColumns = @JoinColumn(name = "producer_id")
	)
	private Set<Producer> producers = new HashSet<>();

	@Override
	public int compareTo(Movie o) {
		return this.getYear().compareTo(o.getYear());
	}

}
