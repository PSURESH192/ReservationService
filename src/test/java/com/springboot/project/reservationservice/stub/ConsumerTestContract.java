package com.springboot.project.reservationservice.stub;

import com.springboot.project.reservationservice.ReservationServiceApplication;
import com.springboot.project.reservationservice.service.feign.GuestServiceFeignClient;
import com.springboot.project.reservationservice.service.feign.HotelServiceFeignClient;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ReservationServiceApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = {"com.springboot.project:HotelService:+:stubs:9001","com.springboot.project:GuestService:+:stubs:9003"})
public class ConsumerTestContract {

    @Autowired
    GuestServiceFeignClient guestServiceFeignClient;

    @Autowired
    HotelServiceFeignClient hotelServiceFeignClient;

    @Test
    public void clientShouldReturnHotelForGivenID_checkHoteltName() throws Exception {
        BDDAssertions.then(this.hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getHotelName()).isEqualTo("Orbis");
    }

    @Test
    public void clientShouldReturnGuestForGivenID_checkGuestName() throws Exception {
        BDDAssertions.then(this.guestServiceFeignClient.getGuest("bff7c415bdab4563a7ec2c4e09e0b28e").getGuestName()).isEqualTo("Virat");
    }
}