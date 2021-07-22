package com.saucedo.molino.routes;

public class RoutePath {
	public static final String PRODUCTOR_CONTROLLER_PATH ="/productor";
	
	public static final String EMPLEADO_CONTROLLER_PATH ="/empleado";
	public static final String ARROZ_PROCEDENCIA_CONTROLLER_PATH ="/arroz/procedencia";
	public static final String ARROZ_TIPO_CONTROLLER_PATH ="/arroz/tipo";
	public static final String ARROZ_LOTE_CONTROLLER_PATH ="/arroz/ingreso_lote";
	public static final String AREA_SECADO_CONTROLLER_PATH ="/area/secado";
	public static final String INGRESO_AREA_SECADO_CONTROLLER_PATH ="/arroz/ingreso_secado";
	public static final String ARROZ_INGRESO_SECADO_TENDIDO="/arroz/detalle_tendido";
	public static final String ARROZ_INGRESO_SECADO_RECOJO="/arroz/detalle_recojo";
	public static final String YEAR_REPOSRTS="/reporte/years";
	public static final String PRODUCTORES_REPOSRTS="/reporte/productores/{year}";
	public static final String ARROZ_REPOSRTS="/reporte/arroz/{year}";
	public static final String ARROZ_INGRESO_REPOSRTS="/reporte/arroz_ingreso/{year}";
	
	public static final String GET_ALL ="/all";
	public static final String GET ="/{id}";
	public static final String INSERT ="/insert";
	public static final String UPDATE ="/update";
	public static final String DELETE ="/{id}";
}
