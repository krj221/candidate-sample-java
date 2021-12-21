package com.bravo.user.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bravo.user.dao.model.Address;
import com.bravo.user.dao.model.mapper.ResourceMapper;
import com.bravo.user.dao.repository.AddressRepository;
import com.bravo.user.model.dto.AddressDto;
import com.bravo.user.utility.PageUtil;

@Service
public class AddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

	private final AddressRepository addressRepository;
	private final ResourceMapper resourceMapper;

	public AddressService(AddressRepository addressRepository, ResourceMapper resourceMapper) {
		this.addressRepository = addressRepository;
	    this.resourceMapper = resourceMapper;
	  }

		public List<AddressDto> retrieveByUserId(final String userId,
				final PageRequest pageRequest, final HttpServletResponse httpResponse) {

			Page<Address> addressPage = addressRepository.findByUserId(userId, pageRequest);
			List<AddressDto> addresses = resourceMapper.convertAddresses(addressPage.getContent());
			LOGGER.info("found {} address(es)", addresses.size());

			PageUtil.updatePageHeaders(httpResponse, addressPage, pageRequest);
			return addresses;
	}
}
