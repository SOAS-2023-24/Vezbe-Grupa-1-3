package currencyConversion;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import api.dtos.CurrencyConversionDto;
import api.dtos.CurrencyExchangeDto;
import api.services.CurrencyConversionService;
import util.exceptions.NoDataFoundException;

@RestController
public class CurrencyConversionServiceImpl implements CurrencyConversionService {
	
	private RestTemplate template = new RestTemplate();

	@Override
	public ResponseEntity<?> getConversion(String from, String to, BigDecimal quantity) {
		HashMap<String,String> uriVariables = new HashMap<String,String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		CurrencyExchangeDto dto = null;
		
		try {
			ResponseEntity<CurrencyExchangeDto> response = template.getForEntity
					("http://localhost:8000/currency-exchange?from={from}&to={to}",
							CurrencyExchangeDto.class, uriVariables);
			dto = response.getBody();
		} catch (HttpClientErrorException e) {
			throw new NoDataFoundException(e.getMessage());
		}
		
		
		return ResponseEntity.ok(exchangeToConversion(dto,quantity));
	}
	
	public CurrencyConversionDto exchangeToConversion(CurrencyExchangeDto exchange,
			BigDecimal quantity) {
		return new CurrencyConversionDto(exchange, quantity, exchange.getTo(),
				quantity.multiply(exchange.getExchangeValue()));
		
	}

}
