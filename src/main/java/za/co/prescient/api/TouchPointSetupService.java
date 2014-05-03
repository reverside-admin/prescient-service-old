package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.Setup;
import za.co.prescient.repository.SetupRepository;

import java.util.List;

@RestController
@Slf4j
public class TouchPointSetupService {

    @Autowired
    SetupRepository setupRepository;

    @RequestMapping(value = "api/tpsetup/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Setup setup) {

        Setup tpSetup = setupRepository.findOne(id);

        tpSetup.setName(setup.getName());
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

    @RequestMapping(value = "api/tp/{tpid}/setups")
    public List<Setup> getAll(@PathVariable("tpid") Long tpid) {
        return setupRepository.findByTouchPointId(tpid);
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


    @RequestMapping(value = "api/touchpoint/{tpid}/setup/{setupid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void setCurrentSetup(@PathVariable("tpid") Long tpId,@PathVariable("setupid") Long setupId) {
        setupRepository.resetIndicatorForTouchPointId(tpId);
        setupRepository.setIndicator(setupId);
    }


    @RequestMapping(value = "api/touchpoint/{tpid}/currentsetup", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Setup getCurrentSetup(@PathVariable("tpid") Long tpId) {
        Setup tps= setupRepository.findCurrentSetupOfATouchPoint(tpId);
        return tps;

    }

}
