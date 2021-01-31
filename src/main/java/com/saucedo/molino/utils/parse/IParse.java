package com.saucedo.molino.utils.parse;

public interface IParse<ENTITY,JSON>{
	ENTITY parseJsonToEntity(JSON json);
	JSON parseEntityToJson(ENTITY entity);
}
