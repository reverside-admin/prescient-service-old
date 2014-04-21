package za.co.prescient.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.co.prescient.model.TouchPointSetup;
import za.co.prescient.repository.TouchPointSetupRepository;

import java.util.List;

@RestController
@Slf4j
public class TouchPointSetupService {

    @Autowired
    TouchPointSetupRepository touchPointSetupRepository;

    @RequestMapping(value = "tpsetup/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody TouchPointSetup touchPointSetup) {

        TouchPointSetup tpSetup = touchPointSetupRepository.findOne(id);

        tpSetup.setSetupName(touchPointSetup.getSetupName());
        tpSetup.setSetupDescription(touchPointSetup.getSetupDescription());
        tpSetup.setImageName(touchPointSetup.getImageName());
        tpSetup.setFileName(touchPointSetup.getFileName());
        tpSetup.setLengthX(touchPointSetup.getLengthX());
        tpSetup.setLengthY(touchPointSetup.getLengthY());
        tpSetup.setFilePath(touchPointSetup.getFilePath());

        touchPointSetupRepository.save(tpSetup);
    }

    @RequestMapping(value = "api/tp/setup", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody TouchPointSetup touchPointSetup) {
        touchPointSetupRepository.save(touchPointSetup);
    }

    @RequestMapping(value = "api/tp/{tpid}/setups")
    public List<TouchPointSetup> getAll(@PathVariable("tpid") Long tpid) {
        return touchPointSetupRepository.findTpSetupsByTpId(tpid);
    }

    @RequestMapping(value = "api/tpsetup/{setupId}")
    public TouchPointSetup getDetail(@PathVariable("setupId") Long id) {
        return touchPointSetupRepository.findOne(id);
    }

    @RequestMapping(value = "api/tpsetup/{id}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public void delete(@PathVariable("id") Long id) {
        touchPointSetupRepository.delete(id);
    }


}
