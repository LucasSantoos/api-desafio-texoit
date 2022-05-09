package com.santos.texoit.representations;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WinnerInterval implements Comparable<WinnerInterval> {
	private static final long serialVersionUID = 1L;

	private String producer;
	private Integer previousWin;
	private Integer followingWin;
	private Integer interval;

	@Override
	public int compareTo(WinnerInterval o) {
		return this.getInterval().compareTo(o.getInterval());
	}

}
