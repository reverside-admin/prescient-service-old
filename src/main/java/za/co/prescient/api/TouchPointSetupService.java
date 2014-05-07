package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.Setup;
import za.co.prescient.model.TouchPoint;
import za.co.prescient.repository.local.SetupRepository;
import za.co.prescient.repository.local.TouchPointRepository;

import java.util.List;

@RestController
@Slf4j
public class TouchPointSetupService {

    @Autowired
    SetupRepository setupRepository;

    @Autowired
    TouchPointRepository touchPointRepository;

    @RequestMapping(value = "api/tpsetup/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Setup setup) {

        Setup tpSetup = setupRepository.findOne(id);

        //tpSetup.setName(setup.getName());

        tpSetup.setSetupName(setup.getSetupName());
        tpSetup.setDescription(setup.getDescription());
        tpSetup.setImageName(setup.getImageName());
        tpSetup.setFileName(setup.getFileName());
        tpSetup.setLengthX(setup.getLengthX());
        tpSetup.setLengthY(setup.getLengthY());
        tpSetup.setFilePath(setup.getFilePath());

        setupRepository.save(tpSetup);
    }

    @RequestMapping(value = "api/tp/setup", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Setup setup) {
        setupRepository.save(setup);
    }

    // TODO : Fix the url in angular ui app
    @RequestMapping(value = "api/touchpoint/{touchPointId}/setups")
    public List<Setup> get(@PathVariable("touchPointId") Long touchPointId) {
        TouchPoint touchPoint = touchPointRepository.findOne(touchPointId);
        return setupRepository.findByTouchPoint(touchPoint);
    }


    //testing
    @RequestMapping(value = "api/tp/setups")
    public List<Setup> getAllll() {
        return setupRepository.findAll();
    }

    @RequestMapping(value = "api/tpsetup/{setupId}")
    public Setup getDetail(@PathVariable("setupId") Long id) {
        return setupRepository.findOne(id);
    }

    @RequestMapping(value = "api/tpsetup/{id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public void delete(@PathVariable("id") Long id) {
        setupRepository.delete(id);
    }


    @RequestMapping(value = "api/touchpoint/{touchPointId}/setup/{setupId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateCurrentSetupOfTouchPoint(@PathVariable("touchPointId") Long touchPointId, @PathVariable("setupId") Long setupId) {
        TouchPoint touchPoint = touchPointRepository.findOne(touchPointId);

        List<Setup> setups = setupRepository.findByTouchPoint(touchPoint);
        for (Setup aSetup : setups) {
            aSetup.setIndicator(null);
        }
        setupRepository.save(setups);

        Setup setup = setupRepository.findOne(setupId);
        setup.setIndicator(true);
        setupRepository.save(setup);
    }


    //TODO : Fix the URL in the angular ui code
    @RequestMapping(value = "api/touchpoint/{touchPointId}/setups/current", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Setup getCurrentSetup(@PathVariable("touchPointId") Long touchPointId) {
        TouchPoint touchPoint = touchPointRepository.findOne(touchPointId);
        return setupRepository.findByTouchPointAndIndicator(touchPoint, true);
    }

}
