package org.anurag.zomatoApp.service;

import lombok.AllArgsConstructor;
import org.anurag.zomatoApp.models.Show;
import org.anurag.zomatoApp.models.Theatre;
import org.anurag.zomatoApp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
