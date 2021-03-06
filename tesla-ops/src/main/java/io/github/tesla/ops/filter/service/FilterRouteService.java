/*
 * Copyright 2014-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.github.tesla.ops.filter.service;

import java.util.List;
import java.util.Map;

import io.github.tesla.ops.system.domain.PageDO;
import io.github.tesla.ops.utils.Query;
import io.github.tesla.ops.filter.dto.FilterRouteDto;

/**
 * @author liushiming
 * @version GateWayRouteService.java, v 0.0.1 2018年1月5日 上午10:44:41 liushiming
 */
public interface FilterRouteService {

  PageDO<FilterRouteDto> queryList(Query query);

  FilterRouteDto get(Long routeId);

  List<FilterRouteDto> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(FilterRouteDto zuulDto);

  int update(FilterRouteDto zuulDto);

  int remove(Long routeId);

  int batchRemove(Long[] routeIds);

}
