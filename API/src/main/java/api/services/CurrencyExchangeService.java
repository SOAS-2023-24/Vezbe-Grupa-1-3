package api.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import api.dtos.CurrencyExchangeDto;

public interface CurrencyExchangeService {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	CurrencyExchangeDto getExchange(@PathVariable String from, @PathVariable String to);
}
