package com.fms.events.controller;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fms.events.entity.UserInfo;
import com.fms.events.repository.UserInfoRepository;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EventsControllerTest.class)
//@Import(EmployeeService.class)
class EventsControllerTest {
	@MockBean
	UserInfoRepository userInfoRepository;
	@Autowired
	private WebTestClient webClient;

	@Test
	void testDashboardSummaryForAdminPMO() {
	}

	@Test
	void testGetDashboardDsetails() {
	}

	@Test
	void testDashboardSummaryForAdminPOC() {
	}

	@Test
	void testGetAllEventsAdminPMO() {
	}

	@Test
	void testGetEventsById() {
	}

	//@Test
	void testAssignUserRoles() {
		UserInfo user = new UserInfo();
		user.setId(Long.valueOf(1));
		user.setFirstname("Test");
		user.setLastname("Test2");
		user.setActiveuser(true);
		user.setEmailid("tet@test.com");
		user.setRolename("test");
		user.setUserpassword("test");
		Mockito.when(userInfoRepository.save(user)).thenReturn(Mono.just(user));

		webClient.post().uri("/assignRoles").contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(user)).exchange().expectStatus().isCreated();
		Mockito.verify(userInfoRepository, times(1)).save(user);
	}

	@Test
	void testRemoveAssignedRoles() {
	}

}
