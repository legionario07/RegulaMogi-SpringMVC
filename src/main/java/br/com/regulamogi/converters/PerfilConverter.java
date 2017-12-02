package br.com.regulamogi.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.regulamogi.domain.Perfil;

@Component
public class PerfilConverter implements Converter<String, Perfil>{

	@Override
	public Perfil convert(String id) {
		 Long perfilId = Long.valueOf(id);
         return new Perfil(perfilId);
	}

}
