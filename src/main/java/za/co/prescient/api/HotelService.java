package za.co.prescient.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.prescient.model.Hotel;
import za.co.prescient.repository.HotelRepository;

import java.util.List;

@RestController
@RequestMapping(value = "api/hotels")
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @RequestMapping
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @RequestMapping(value = "{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId") Long hotelId) {
        return hotelRepository.findOne(hotelId);
    }
}
