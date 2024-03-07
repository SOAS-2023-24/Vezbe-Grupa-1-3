package api.services;

import org.springframework.web.bind.annotation.GetMapping;

import api.dtos.CurrencyExchangeDto;

public interface CurrencyExchangeService {

	@GetMapping("/currency-exchange")
	CurrencyExchangeDto getExchange();
}
