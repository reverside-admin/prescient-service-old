package za.co.prescient.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.prescient.Application;
import za.co.prescient.model.GuestProfileDetail;

import javax.sql.DataSource;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public class GuestCardAllocationRepositoryTest {

    @Autowired
    GuestCardAllocationRepository guestCardAllocationRepository;

    @Autowired
    DataSource dataSource;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("insert into guest_profile_detail (id, hotel_id, first_name) values (100, 1, 'guest1')");
        jdbcTemplate.execute("insert into guest_profile_detail (id, hotel_id, first_name) values (101, 1, 'guest2')");
        jdbcTemplate.execute("insert into guest_profile_detail (id, hotel_id, first_name) values (102, 1, 'guest3')");

        jdbcTemplate.execute("insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (1, 1000, 100)");
        jdbcTemplate.execute("insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (2, 1001, 101)");
        jdbcTemplate.execute("insert into guest_card_allocation (id, guest_card_id, guest_profile_detail_id) values (3, 1002, 102)");


    }

    @Test
    public void shouldReturnGuestsAllocatedWithTagIds() {
        assertNotNull(guestCardAllocationRepository);
        List<GuestProfileDetail> guestProfileDetails = guestCardAllocationRepository.findGuestsWithTags(asList(1000, 1001));
        assertEquals(2, guestProfileDetails.size());
    }

    @Test
    public void shouldNotReturnAnyGuestIfTagIsNotAllocated() {
        assertNotNull(guestCardAllocationRepository);
        List<GuestProfileDetail> guestProfileDetails = guestCardAllocationRepository.findGuestsWithTags(asList(1000, 1002));
        assertEquals(1, guestProfileDetails.size());
    }

    @Test
    public void shouldReturnAGuestNameAllocatedWithTagId() {
        assertNotNull(guestCardAllocationRepository);
        List<GuestProfileDetail> guestProfileDetails = guestCardAllocationRepository.findGuestsWithTags(asList(1000));
        assertEquals("guest1", guestProfileDetails.get(0).getFirstName());
    }

    @Test
    public void shouldReturnGuestNamesAllocatedWithTagIds() {
        assertNotNull(guestCardAllocationRepository);
        List<GuestProfileDetail> guestProfileDetails = guestCardAllocationRepository.findGuestsWithTags(asList(1000, 1001, 1003));
        assertEquals("guest1", guestProfileDetails.get(0).getFirstName());
        assertEquals("guest2", guestProfileDetails.get(1).getFirstName());
    }


}
