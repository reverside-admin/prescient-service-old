package za.co.prescient.repository;

import org.junit.Test;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.prescient.Application;
import za.co.prescient.model.Setup;
import za.co.prescient.model.TouchPoint;
import za.co.prescient.model.User;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public class SetupRepositoryTest {

    @Autowired
    SetupRepository setupRepository;

    @Autowired
    DataSource dataSource;


    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("insert into hotel(id,name) values(1,'Orion')");
        jdbcTemplate.execute("insert into hotel(id,name) values(2,'Taj')");

        jdbcTemplate.execute("insert into department(id,name,hotel_id) values(1,'Travel',1)");
        jdbcTemplate.execute("insert into department(id,name,hotel_id) values(2,'Hospitality',2)");


        jdbcTemplate.execute("insert into touch_point(id,name,department_id) values(1,'dinind',1)");
        jdbcTemplate.execute("insert into touch_point(id,name,department_id) values(2,'parking',1)");
        jdbcTemplate.execute("insert into touch_point(id,name,department_id) values(3,'reception',1)");

        jdbcTemplate.execute("insert into setup(id,name,description,indicator,image_name,file_name,lengthx,lengthy,file_path,touch_point_id) values(1,'dining setup','dining setup desc',true,'dining_image','dining.jpeg',1500,2000,'c:\\images',1)");
        jdbcTemplate.execute("insert into setup(id,name,description,indicator,image_name,file_name,lengthx,lengthy,file_path,touch_point_id) values(2,'dining evening setup','dining evening setup desc',false,'dining_image','dining.jpeg',1500,2000,'c:\\images',1)");


    }

    @Test
    public void shouldFindSetupsIfTouchPointIdIsValid() {
        List<Setup> setups = setupRepository.findByTouchPointId(1L);
        assertNotNull(setups);
    }

    @Test
    public void shouldReturnEmptyListIfTouchPointIdIsInValid() {
        List<Setup> setups = setupRepository.findByTouchPointId(2L);
        assertEquals(0, setups.size());
    }

    @Test
    public void shouldResetTheSetupIndicatorToNullOfTheGivenTouchPoint() {
        setupRepository.resetIndicatorForTouchPointId(1L);
        Setup setup = setupRepository.findOne(1L);
        assertNull(setup.getIndicator());
    }

    @Test
    public void shouldReturnTheCurrentSetupOfATouchPoint() {
        Setup setup = setupRepository.findCurrentSetupOfATouchPoint(1L);
        assertEquals(1L, setup.getId().longValue());
    }

    @Test
    public void shouldUpdateSetupIndicator() {
        setupRepository.setIndicator(2L);
        Setup setup = setupRepository.findOne(2L);
        assertTrue(setup.getIndicator());

    }

    @After
    public void clear() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("delete from setup");
        jdbcTemplate.execute("delete from touch_point");
        jdbcTemplate.execute("delete from department");
        jdbcTemplate.execute("delete from hotel");

    }


}
