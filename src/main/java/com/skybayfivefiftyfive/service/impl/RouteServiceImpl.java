package com.skybayfivefiftyfive.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skybayfivefiftyfive.dao.GenericDAO;
import com.skybayfivefiftyfive.service.GenericService;
import com.skybayfivefiftyfive.service.impl.GenericServiceImpl;
import com.skybayfivefiftyfive.dao.RouteDAO;
import com.skybayfivefiftyfive.domain.Route;
import com.skybayfivefiftyfive.dto.RouteDTO;
import com.skybayfivefiftyfive.dto.RouteSearchDTO;
import com.skybayfivefiftyfive.dto.RoutePageDTO;
import com.skybayfivefiftyfive.dto.RouteConvertCriteriaDTO;
import com.skybayfivefiftyfive.dto.common.RequestDTO;
import com.skybayfivefiftyfive.dto.common.ResultDTO;
import com.skybayfivefiftyfive.service.RouteService;
import com.skybayfivefiftyfive.util.ControllerUtils;





@Service
public class RouteServiceImpl extends GenericServiceImpl<Route, Integer> implements RouteService {

    private final static Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

	@Autowired
	RouteDAO routeDao;

	


	@Override
	public GenericDAO<Route, Integer> getDAO() {
		return (GenericDAO<Route, Integer>) routeDao;
	}
	
	public List<Route> findAll () {
		List<Route> routes = routeDao.findAll();
		
		return routes;	
		
	}

	public ResultDTO addRoute(RouteDTO routeDTO, RequestDTO requestDTO) {

		Route route = new Route();

		route.setRouteId(routeDTO.getRouteId());


		route.setDuration(routeDTO.getDuration());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		route = routeDao.save(route);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Route> getAllRoutes(Pageable pageable) {
		return routeDao.findAll(pageable);
	}

	public Page<Route> getAllRoutes(Specification<Route> spec, Pageable pageable) {
		return routeDao.findAll(spec, pageable);
	}

	public ResponseEntity<RoutePageDTO> getRoutes(RouteSearchDTO routeSearchDTO) {
	
			Integer routeId = routeSearchDTO.getRouteId(); 
  			String sortBy = routeSearchDTO.getSortBy();
			String sortOrder = routeSearchDTO.getSortOrder();
			String searchQuery = routeSearchDTO.getSearchQuery();
			Integer page = routeSearchDTO.getPage();
			Integer size = routeSearchDTO.getSize();

	        Specification<Route> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, routeId, "routeId"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Route> routes = this.getAllRoutes(spec, pageable);
		
		//System.out.println(String.valueOf(routes.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(routes.getTotalPages()));
		
		List<Route> routesList = routes.getContent();
		
		RouteConvertCriteriaDTO convertCriteria = new RouteConvertCriteriaDTO();
		List<RouteDTO> routeDTOs = this.convertRoutesToRouteDTOs(routesList,convertCriteria);
		
		RoutePageDTO routePageDTO = new RoutePageDTO();
		routePageDTO.setRoutes(routeDTOs);
		routePageDTO.setTotalElements(routes.getTotalElements());
		return ResponseEntity.ok(routePageDTO);
	}

	public List<RouteDTO> convertRoutesToRouteDTOs(List<Route> routes, RouteConvertCriteriaDTO convertCriteria) {
		
		List<RouteDTO> routeDTOs = new ArrayList<RouteDTO>();
		
		for (Route route : routes) {
			routeDTOs.add(convertRouteToRouteDTO(route,convertCriteria));
		}
		
		return routeDTOs;

	}
	
	public RouteDTO convertRouteToRouteDTO(Route route, RouteConvertCriteriaDTO convertCriteria) {
		
		RouteDTO routeDTO = new RouteDTO();
		
		routeDTO.setRouteId(route.getRouteId());

	
		routeDTO.setDuration(route.getDuration());

	

		
		return routeDTO;
	}

	public ResultDTO updateRoute(RouteDTO routeDTO, RequestDTO requestDTO) {
		
		Route route = routeDao.getById(routeDTO.getRouteId());

		route.setRouteId(ControllerUtils.setValue(route.getRouteId(), routeDTO.getRouteId()));

		route.setDuration(ControllerUtils.setValue(route.getDuration(), routeDTO.getDuration()));



        route = routeDao.save(route);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RouteDTO getRouteDTOById(Integer routeId) {
	
		Route route = routeDao.getById(routeId);
			
		
		RouteConvertCriteriaDTO convertCriteria = new RouteConvertCriteriaDTO();
		return(this.convertRouteToRouteDTO(route,convertCriteria));
	}







}
