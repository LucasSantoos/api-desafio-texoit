package com.santos.texoit.representations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TopTailWinnersInterval {

    private List<WinnerInterval> min;
    private List<WinnerInterval> max;

    public static TopTailWinnersInterval fromWinnersInterval(Set<WinnerInterval> winners){
        Integer minInterval = winners.stream().min(Comparator.naturalOrder())
                .map(WinnerInterval::getInterval)
                .orElse(0);

        Integer maxInterval = winners.stream().max(Comparator.naturalOrder())
                .map(WinnerInterval::getInterval)
                .orElse(0);

        List<WinnerInterval> min = winners.stream()
                .filter(winnerInterval -> winnerInterval.getInterval().compareTo(minInterval) == 0).collect(Collectors.toList());

        List<WinnerInterval> max = winners.stream()
                .filter(winnerInterval -> winnerInterval.getInterval().compareTo(maxInterval) == 0).collect(Collectors.toList());

        return new TopTailWinnersInterval(min, max);
    }

}
