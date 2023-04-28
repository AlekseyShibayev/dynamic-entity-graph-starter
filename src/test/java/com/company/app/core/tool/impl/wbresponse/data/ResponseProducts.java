package com.company.app.core.tool.impl.wbresponse.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseProducts {

	Integer id;
	String name;
	Integer salePriceU;
}
