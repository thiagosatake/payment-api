package io.payment.api.mappers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.payment.api.entities.GatewayEntity;
import io.payment.api.services.GatewaysService.GatewayDetails;

@SpringBootTest
public class GatewayMapperTest {
	
	@Autowired
	private GatewayMapper gatewayMapper;
	
	@Test
	public void gatewayMapperTest() {
		GatewayEntity entity = new GatewayEntity(UUID.randomUUID(), "Test", "Test Description", null, null, null);
		GatewayDetails details = gatewayMapper.toGatewayDetails(entity);
		assertNotNull(details);
	}
	
	@Test
	public void gatewayMapperListTest() {
		GatewayEntity entity = new GatewayEntity(UUID.randomUUID(), "Test", "Test Description", null, null, null);
		List<GatewayEntity> myEntityList = new ArrayList<GatewayEntity>();
		myEntityList.add(entity);
		
		List<GatewayDetails> detailsList = gatewayMapper.toGatewayDetailsList(myEntityList);
		assertNotNull(detailsList);
	}
	
}
