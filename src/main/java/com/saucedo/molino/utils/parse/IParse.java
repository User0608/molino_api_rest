package com.saucedo.molino.utils.parse;

import org.springframework.stereotype.Service;

@Service
public interface IParse<ENTITY,JSON>{
	ENTITY parseJsonToEntity(JSON json);
	JSON parseEntityToJson(ENTITY entity);
}
