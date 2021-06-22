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
    public void clientShouldReturnHotelForGivenID_checkHotelDetails() throws Exception {
        BDDAssertions.then(hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getHotelName()).isEqualTo("Orbis");
        BDDAssertions.then(hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getHotelId()).isEqualTo("f387022e93774015aede2d4f5453a2aa");
        BDDAssertions.then(hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getContact()).isEqualTo(9090989897L);
        BDDAssertions.then(hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getAddress()).isEqualTo("KPHB");
        BDDAssertions.then(hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getCity()).isEqualTo("Hyderbad");
        BDDAssertions.then(hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getZipcode()).isEqualTo(560080L);
        BDDAssertions.then(hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getRoomDetails().get(0).getRoomId()).isEqualTo(46);
        BDDAssertions.then(hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getRoomDetails().get(0).getRoomType()).isEqualTo("PREMIUM");
        BDDAssertions.then(hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getRoomDetails().get(0).getTotalRooms()).isEqualTo(2);
        BDDAssertions.then(hotelServiceFeignClient.getHotel("f387022e93774015aede2d4f5453a2aa").getRoomDetails().get(0).getPrice()).isEqualTo(2500);
    }

    @Test
    public void clientShouldReturnGuestForGivenID_checkGuestDetails() throws Exception {
        BDDAssertions.then(guestServiceFeignClient.getGuest("bff7c415bdab4563a7ec2c4e09e0b28e").getGuestName()).isEqualTo("Virat");
        BDDAssertions.then(guestServiceFeignClient.getGuest("bff7c415bdab4563a7ec2c4e09e0b28e").getGuestId()).isEqualTo("bff7c415bdab4563a7ec2c4e09e0b28e");
        BDDAssertions.then(guestServiceFeignClient.getGuest("bff7c415bdab4563a7ec2c4e09e0b28e").getContact()).isEqualTo(9090989897L);
        BDDAssertions.then(guestServiceFeignClient.getGuest("bff7c415bdab4563a7ec2c4e09e0b28e").getAddressDetails().get(0).getId()).isEqualTo(26);
        BDDAssertions.then(guestServiceFeignClient.getGuest("bff7c415bdab4563a7ec2c4e09e0b28e").getAddressDetails().get(0).getAddressType()).isEqualTo("HOME");
        BDDAssertions.then(guestServiceFeignClient.getGuest("bff7c415bdab4563a7ec2c4e09e0b28e").getAddressDetails().get(0).getCity()).isEqualTo("Hyderabad");
        BDDAssertions.then(guestServiceFeignClient.getGuest("bff7c415bdab4563a7ec2c4e09e0b28e").getAddressDetails().get(0).getStreet()).isEqualTo("KPHB");
        BDDAssertions.then(guestServiceFeignClient.getGuest("bff7c415bdab4563a7ec2c4e09e0b28e").getAddressDetails().get(0).getState()).isEqualTo("Telangana");
        BDDAssertions.then(guestServiceFeignClient.getGuest("bff7c415bdab4563a7ec2c4e09e0b28e").getAddressDetails().get(0).getZipcode()).isEqualTo(500082L);
    }
}