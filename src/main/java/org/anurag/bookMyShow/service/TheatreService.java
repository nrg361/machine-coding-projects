package org.anurag.bookMyShow.service;

import lombok.AllArgsConstructor;
import org.anurag.bookMyShow.models.Theatre;
import org.anurag.bookMyShow.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TheatreService {
    private List<Theatre> theatreList;

    public List<Theatre> getTheatreList(){
        return theatreList;
    }

    public String createTheatre(String theatreName){
        Theatre theatre = Theatre.builder()
                .shows(new ArrayList<>())
                .theatreId(CommonUtils.getUniqueId())
                .name(theatreName)
                .build();
        theatreList.add(theatre);
        return theatre.getTheatreId();
    }

    public Theatre getTheatreDetails(String theatreId){
        return theatreList.stream().filter((theatre) -> theatre.getTheatreId().equals(theatreId)).findFirst().orElse(null);
    }
}
