package za.co.prescient.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.Hotel;
import za.co.prescient.repository.HotelRepository;

@RestController
@RequestMapping(value = "api/hotels")
public class HotelService {
    private static final Logger LOGGER = Logger.getLogger(HotelService.class);

    @Autowired
    HotelRepository hotelRepository;

    @RequestMapping
    public Iterable<Hotel> getHotels() {
        LOGGER.info("Get All Hotel service");
        return hotelRepository.findAll();
    }

    @RequestMapping(value = "{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId") Long hotelId) {
        LOGGER.info("Get a single Hotel by HotelId service");
        return hotelRepository.findOne(hotelId);
    }
}
