package com.company.app.core.aop.logging.performance.component.action;

import com.company.app.core.aop.logging.performance.component.api.Reflector;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractAction implements Action {

	@Autowired
	Reflector reflector;
}
