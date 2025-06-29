package org.anurag.bookMyShow.service;

import lombok.AllArgsConstructor;
import org.anurag.bookMyShow.models.Show;
import org.anurag.bookMyShow.models.Theatre;
import org.anurag.bookMyShow.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

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
