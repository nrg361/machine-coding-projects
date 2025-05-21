package org.anurag.zomatoApp.service;

import lombok.AllArgsConstructor;
import org.anurag.zomatoApp.models.Seat;
import org.anurag.zomatoApp.models.Show;
import org.anurag.zomatoApp.models.Theatre;
import org.anurag.zomatoApp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class ShowService {
    TheatreService theatreService;
    private List<Show> showList;

    public Show getShowDetails(String showId){
        return showList.stream().filter((show) -> show.getShowId().equals(showId)).findFirst().orElse(null);
    }

    public String createShow(String showName, String theatreId){
        Show show = Show.builder()
                .showName(showName)
                .showId(CommonUtils.getUniqueId())
                .theatreId(theatreId)
                .seats(new ArrayList<>())
                .build();
        showList.add(show);
        Theatre theatre = theatreService.getTheatreDetails(theatreId);
        theatre.getShows().add(show);
        return show.getShowId();
    }
}
